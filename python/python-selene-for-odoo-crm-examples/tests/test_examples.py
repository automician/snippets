# MIT License
#
# Copyright (c) 2016-2022 Iakiv Kramarenko
#
# Permission is hereby granted, free of charge, to any person obtaining a copy
# of this software and associated documentation files (the "Software"), to deal
# in the Software without restriction, including without limitation the rights
# to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
# copies of the Software, and to permit persons to whom the Software is
# furnished to do so, subject to the following conditions:
#
# The above copyright notice and this permission notice shall be included in all
# copies or substantial portions of the Software.
#
# THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
# IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
# FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
# AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
# LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
# OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
# SOFTWARE.
import dataclasses
from typing import Union, Callable, Tuple, List

from selene import have, Browser, be
from selene.core.condition import Condition
from selene.core.conditions import ElementCondition
from selene.core.entity import Element as SeleneElement, Element
from selene.core.wait import Command
from selenium.common.exceptions import ElementNotInteractableException

from framework import shared
from framework.assist.python.none_object import NoneObject
from framework.assist.python.string import is_quoted_with_double_guillemets
from framework.assist.selene.queries import the
from framework.shared import browser


def test_login_in(web):
    web.open('/')

    web.element('email').type('admin')
    web.element('password').type('admin')
    web.element('«Log in»').click()


def test_login_with_browser():
    browser.open('/')

    browser.element('email').type('admin')
    browser.element('password').type('admin')
    browser.element('«Log in»').click()


class Form:

    def __init__(self, locator: Union[SeleneElement, str, None] = None):
        self.context: Union[SeleneElement, Browser] = (
            browser.element(locator)
            if isinstance(locator, str)
            else locator
        ) if locator is not None else browser

    def fill(self, data=None, /, **kwargs):
        # from framework.helpers import dataclass_to_dict
        # using below:
        # **dataclass_to_dict(data, skip_None_keys=True, empty_on_None=True),
        # """
        # this will not work because we need to convert to dict
        # only on the first level
        # we don't need "nested dumping to dict"...
        # """
        data_dict = {
            key: value
            for key, value in {
                **(data or NoneObject()).__dict__,
                **kwargs,
            }.items()
            if value is not None
        }
        for key, value in data_dict.items():
            if isinstance(value, str):
                def fn(element: Element):
                    webelement = ...
                    try:
                        if element.config.wait_for_no_overlap_found_by_js:
                            webelement = element._actual_not_overlapped_webelement
                        else:
                            webelement = element()
                        webelement.send_keys(str(value))
                    except ElementNotInteractableException:
                        # if the element we guessed above
                        # can't receive send_keys
                        # then let's try to find the inner input
                        # and send_keys to it...
                        inner = webelement.find_element_by_css_selector('input')
                        inner.send_keys(str(value))

                input = Command(f'type: «{value}»', fn)

                # self.context.element(key).type(value)
                self.context.element(key).perform(input)
            elif isinstance(value, InputDropdown.Value):
                dropdown = InputDropdown(key, context=self.context)
                if value.index is not None:
                    dropdown.select_by_index(value.index)
                else:
                    dropdown.autocomplete(value.text)
            elif isinstance(value, Dropdown.Value):
                dropdown = Dropdown(key, context=self.context)
                if value.index is not None:
                    dropdown.select_by_index(value.index)
                elif value.xmlid is not None:
                    dropdown.choose_by_text(value.xmlid)
                else:
                    dropdown.choose_by_text(value.text)
            else:
                raise ValueError(
                    f'not supported type of the value: {value} for key {key}'
                )

            # TODO: implement automatic field type recognition
            #  for: date-pickers, radio, checkboxes
        return self

    def should_have_validation_for(self, data=None, /, **kwargs):
        """
        check that validation is present on fields with ellipsis ... as value
        """
        data_dict = {
            key: value
            for key, value in {
                **(data or NoneObject()).__dict__,
                **kwargs,
            }.items()
            if value is ...
        }

        for key in data_dict:
            self.context.element(key).should(have.css_class('o_field_invalid'))

        return self

    def should_have_filled(self, data, /, **kwargs):
        # TODO: implement
        return self

    def submit(
            self,
            data=None,
            /,
            *,
            by_button: Union[
                SeleneElement,
                str,
                List[Union[SeleneElement, str]],
            ] = '[type=submit]',
            **kwargs
    ):
        """
        on no args passed - will just submit the form by clicking the submit button
        """
        self.fill(data, **kwargs)

        buttons = by_button if isinstance(by_button, list) else [by_button]
        for button in buttons:
            if isinstance(button, str):
                self.context.element(button).click()
            elif isinstance(button, SeleneElement):
                button.click()

        return self


@dataclasses.dataclass
class UserData:
    name: str = None
    email: str = None
    password: str = None


def test_login_with_form_fill_and_submit():
    browser.open('/')

    Form('o_login_form').fill(UserData(
        email='admin',
        password='admin',
    )).submit()


def test_login_with_form_submit():
    browser.open('/')

    Form('o_login_form').submit(UserData(
        email='admin',
        password='admin',
    ))


def test_login_with_form_submit_by_data_as_kwargs():
    browser.open('/')

    Form('o_login_form').submit(
        email='admin',
        password='admin',
    )


class Dropdown:
    @dataclasses.dataclass
    class Value:
        index: int = None
        xmlid: str = None
        text: str = None

    class Selectors:
        toggle = '[data-toggle=dropdown]'

    @staticmethod
    def for_(locator, *, title: str = None):
        if title is None:
            return Dropdown(browser.element(locator).element(
                Dropdown.Selectors.toggle
            ).element('..'))
        else:
            return Dropdown.by_title(
                title,
                context=lambda: browser.element(locator),
            )

    @staticmethod
    def by_title(
            xmlid_or_quoted_text: str,
            *,
            context: Callable[[], Union[Browser, Element]]
            = lambda: shared.browser,
    ):
        return (
            Dropdown.by_title_text(xmlid_or_quoted_text[1:-1], context=context)
            if is_quoted_with_double_guillemets(xmlid_or_quoted_text)
            else Dropdown.by_title_xmlid(xmlid_or_quoted_text, context=context)
        )

    @staticmethod
    def by_title_text(
            value: str,
            *,
            context: Callable[[], Union[Browser, Element]]
            = lambda: shared.browser,
    ):
        return Dropdown(context().all(
                Dropdown.Selectors.toggle
            ).element_by(
                 have.exact_text(value)
            ).element('..'))

    @staticmethod
    def by_title_xmlid(
            value: str,
            *,
            context: Callable[[], Union[Browser, Element]]
            = lambda: shared.browser,
    ):
        return Dropdown(context().all(
                Dropdown.Selectors.toggle
            ).element_by(
                have.attribute(
                    'data-menu-xmlid'
                ).value_containing(value.lower())
            ).element('..'))

    def __init__(
            self,
            locator: Union[SeleneElement, str],
            context: Union[
                Browser,
                SeleneElement,
                Callable[[], Union[Browser, SeleneElement]]
            ] = lambda: shared.browser,
    ):
        context = (
            context
            if isinstance(context, Browser) or isinstance(context, SeleneElement)
            else context()
        )
        self.element = (
            context.element(locator)
            if isinstance(locator, str)
            else locator
        )
        self.items = self.element.all('.dropdown-item')

    def open(self):
        self.element.click()
        return self

    def choose(self, index_or_item, /):
        if isinstance(index_or_item, int):
            self.choose_by_index(index_or_item)
        elif is_quoted_with_double_guillemets(index_or_item):
            self.choose_by_text(index_or_item[1:-1])
        else:
            self.choose_by_xmlid(index_or_item)
        return self

    def choose_by_index(self, value: int, /):
        self.items[value].click()
        return self

    def choose_by_xmlid(self, partial_value, /):
        self.items.element_by(
            have.attribute('data-menu-xmlid').value_containing(partial_value)
        ).click()
        return self

    def choose_by_text(self, item):
        self.items.element_by(have.exact_text(item)).click()
        return self

    def select(self, item):
        self.open().choose(item)
        return self

    def select_by_index(self, value: int, /):
        self.open().choose_by_index(value)
        return self

    def select_by_xmlid(self, partial_value, /):
        self.open().choose_by_xmlid(partial_value)
        return self

    def select_by_text(self, item):
        self.open().choose_by_text(item)
        return self


class InputDropdown:
    @dataclasses.dataclass
    class Value:
        index: int = None
        text: str = None

    def __init__(
            self,
            locator: Union[SeleneElement, str],
            *,
            context: Union[
                Browser,
                SeleneElement,
                Callable[[], Union[Browser, SeleneElement]]
            ] = lambda: shared.browser
    ):
        context = (
            context
            if isinstance(context, Browser) or isinstance(context, SeleneElement)
            else context()
        )
        self.element = (
            context.element(locator)
            if isinstance(locator, str)
            else locator
        )
        self.input = self.element.element('.ui-autocomplete-input')
        self.button = self.element.element('.o_dropdown_button')
        self.items = self.element.element(
            '.ui-autocomplete.ui-menu:not([style*="display: none;"])'
        ).all('.ui-menu-item')
        # self.items = self.element.all('.dropdown-item')

    def open(self):
        self.element.click()
        return self

    def autocomplete(self, partial_value: str, /):
        self.input.type(partial_value).press_enter()
        return self

    def choose(self, index_or_item: Union[int, str], /):
        if isinstance(index_or_item, int):
            self.choose_by_index(index_or_item)
        elif is_quoted_with_double_guillemets(index_or_item):
            self.choose_by_text(index_or_item[1:-1])
        else:
            self.choose_by_text(index_or_item)
        return self

    def choose_by_index(self, value: int, /):
        self.items[value].click()
        return self

    def choose_by_text(self, item):
        self.items.element_by(have.exact_text(item)).click()
        return self

    def select(self, item):
        self.open().choose(item)
        return self

    def select_by_index(self, value: int, /):
        self.open().choose_by_index(value)
        return self

    def select_by_text(self, item):
        self.open().choose_by_text(item)
        return self


class Table:

    def __init__(
            self,
            locator: Union[SeleneElement, str] = '.o_list_table',
    ):
        self.element = (
            browser.element(locator)
            if isinstance(locator, str)
            else locator
        )
        self.headers = self.element.all('th')
        self.rows = self.element.all('tbody tr')

    def index_of_header(self, name_or_text_or_title: str, /):
        import re
        if is_quoted_with_double_guillemets(name_or_text_or_title):
            return self.index_of_header_by_text(
                name_or_text_or_title[1:-1]
            )
        if name_or_text_or_title.islower():
            return self.index_of_header_by_name(
                re.sub(r'\s+', '_', name_or_text_or_title)
            )
        else:
            return self.index_of_header_by_title(
                name_or_text_or_title
            )

    def index_of_header_by(self, condition, /):
        return self.headers.get(the.index_of_element_by(
            condition
        ))

    def index_of_header_by_text(self, value, /):
        return self.index_of_header_by(
            have.text(value)
        )

    def index_of_header_by_title(self, value, /):
        return self.index_of_header_by(
            have.attribute('title').value(value)
        )

    def index_of_header_by_name(self, value, /):
        return self.index_of_header_by(
            have.attribute('data-name').value(value)
        )

    def cells_in_row(self, index_or_condition: Union[int, ElementCondition], /):
        row = (
            self.rows[index_or_condition]
            if isinstance(index_or_condition, int)
            else self.rows.element_by(index_or_condition)
        )
        return row.all('td')

    def cell(
            self,
            *,
            row: Union[int, Condition[Element]],
            column: Union[int, str],
    ):
        column_index = (
            column
            if isinstance(column, int)
            else self.index_of_header(column)
        )
        return self.cells_in_row(row)[column_index]


class Modal:
    @staticmethod
    def by_title(text: str, /):
        text = (
            text[1:-1]
            if is_quoted_with_double_guillemets(text)
            else text
        )

        return Modal(
            browser.all(
                'header'
            ).element_by(
                have.text(text)
            ).element('..')
        )

    def __init__(
            self,
            locator: Union[SeleneElement, str],
            *,
            context: Union[
                Browser,
                SeleneElement,
                Callable[[], Union[Browser, SeleneElement]]
            ] = lambda: shared.browser
    ):
        context = (
            context
            if isinstance(context, Browser) or isinstance(context, SeleneElement)
            else context()
        )
        self.element = (
            context.element(locator)
            if isinstance(locator, str)
            else locator
        )
        self.form = Form(self.element)


@dataclasses.dataclass
class AccessData:
    access_label: str = None
    owner_id: Union[str, InputDropdown.Value] = None
    receiver_id: Union[str, InputDropdown.Value] = None


def test_login_and_try_to_add_device():
    browser.open('/')

    Form('.o_login_form').submit(UserData(
        email='admin',
        password='admin',
    ))

    Dropdown.for_('o_menu_apps').select('«Company»')
    """
    # OR:
    Dropdown.for_('o_menu_apps').select('company_root')
    # OR:
    Dropdown.for_('o_menu_apps').select_by_xmlid('company_root')
    """

    Dropdown.by_title('Registry').select('«Devices»')
    """
    # OR:
    Dropdown.for_(
        'o_menu_sections', title='Registry'
    ).select('«Devices»')
    """

    Table().cell(row=have.text('main-phone'), column='«Device ID»').click()

    browser.element('action_share_access').click()

    Modal.by_title('«Share Access»').form.fill(AccessData(
        access_label='director access',
        owner_id=InputDropdown.Value(text='admin@asdfgcompany.com'),
        receiver_id=InputDropdown.Value(text='jackright@asdfgcompany.com'),
    )).submit(
        by_button='do_share'
    ).should_have_validation_for(AccessData(
        owner_id=...
    ))
    # )).submit(by_button=['do_share', 'do_close'])
    # """
    # here we could try to submit form by clicking two buttons
    # but there will be some validation errors...
    # """
    """
    # OR:
    dialog = Modal.by_title('Share Access').element
    dialog.element('access_label').type('director access')
    dialog.element('owner_id').element('input').type('admin@asdfgcompany.com')
    dialog.element('receiver_id').element('input').type('jackright@asdfgcompany.com')
    dialog.element('do_share').click()
    dialog.element('do_close').click()
    """

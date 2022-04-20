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
from typing import Union, List

from selene import Browser, have
from selene.core.entity import Element as SeleneElement, Element
from selene.core.wait import Command
from selenium.common.exceptions import ElementNotInteractableException

from framework.assist.python.none_object import NoneObject
from framework.shared import browser
from framework.model.admin_pro_web.controls.dropdown_input import InputDropdown
from framework.model.admin_pro_web.controls.dropdown import Dropdown


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
                """
                more precise search inside the Form root element
                """
            elif isinstance(value, InputDropdown.Value):
                dropdown = InputDropdown(key, context=self.context)
                if value.index is not None:
                    dropdown.select_by_index(value.index)
                else:
                    if value.autocomplete is None:
                        dropdown.type(value.text)

                    if value.autocomplete is False:
                        dropdown.find_and_choose(value.text)

                    if value.autocomplete is True:
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
            else:
                button.click()

        return self

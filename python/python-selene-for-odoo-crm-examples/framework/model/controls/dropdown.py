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
from typing import Callable, Union

from selene import Browser, have
from selene.core.entity import Element, Element as SeleneElement

from framework import shared
from framework.assist.python.string import is_quoted_with_double_guillemets
from framework.shared import browser


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

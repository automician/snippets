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
from typing import Union, Callable, Optional

from selene import Browser, have
from selene.core.entity import Element as SeleneElement
from selenium.webdriver import Keys

from framework import shared
from framework.assist.python.string import is_quoted_with_double_guillemets


class InputDropdown:
    @dataclasses.dataclass
    class Value:
        index: int = None
        text: str = None
        autocomplete: Optional[bool] = False

    def __init__(
            self,
            locator: Union[SeleneElement, str],
            *,
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
        self.input = self.element.element('.ui-autocomplete-input')
        self.button = self.element.element('.o_dropdown_button')
        self.items = shared.browser.element(
            '.ui-autocomplete.ui-menu:not([style*="display: none;"])'
        ).all('.ui-menu-item')

    def open(self):
        self.element.click()
        return self

    def type(self, text):
        self.input.type(text)
        return self

    def autocomplete(self, partial_value: str, /, by=Keys.ENTER):
        self.input.type(partial_value)
        self.items.should(have.size_greater_than(0))
        self.input.press(by)
        return self

    def find_and_choose(self, partial_value: str, /):
        self.input.type(partial_value)
        self.choose_by_text(partial_value)
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
        self.items.element_by(have.text(item)).click()
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

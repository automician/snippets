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
from typing import Union, Tuple

from selene.core.conditions import CollectionCondition, ElementCondition
from selene.core.entity import Element, Collection
from selenium.webdriver.common.by import By

from framework.assist.python import monkey
from selene import Browser

from framework.assist.python.etc import is_word_an_html_tag


def is_word_with_dashes_underscores_or_numbers(selector):
    import re
    return re.match(r'^[a-zA-Z_\d\-]+$', selector)


def _by(selector: str):
    # --- Handle XPath ---
    if (
            selector.startswith('/')
            or selector.startswith('./')
            or selector.startswith('..')
            or selector.startswith('(')
    ):
        return By.XPATH, selector

    # --- Handle custom conventions ---
    from selene import by
    if (
        selector.lower().startswith('text="')
        or selector.startswith("text='")
    ):
        return by.text(selector[6:-1])
    if (
        selector.startswith('«')
        and selector.endswith('»')
    ):
        return by.text(selector[1:-1])
    if selector.startswith('text='):
        return by.partial_text(selector[5:-0])

    # --- Handle guessed selectors based on words ---
    lowered = selector.lower()
    if (
        is_word_with_dashes_underscores_or_numbers(selector)
        and not is_word_an_html_tag(selector)
    ):
        return (
            By.CSS_SELECTOR,
            ''
            + f'#{selector}'
            + (f',#{lowered}' if not selector.islower() else '')
            + f',[name={selector}]'
            + (f',[name={selector.lower()}]' if not selector.islower() else '')
            + f',.{selector}'
            + (f',.{lowered}' if not selector.islower() else '')
            + (
                f',[placeholder^={selector.capitalize()}]'
                if '_' not in selector
                else ''
            )
        )

    # --- Handle CSS ---
    return By.CSS_SELECTOR, selector


original_browser_element = Browser.element


@monkey.patch_method_in(Browser)
def element(self, selector: Union[str, tuple]) -> Element:
    return original_browser_element(self, _by(selector))


original_browser_all = Browser.all


@monkey.patch_method_in(Browser)
def all(self, selector: Union[str, tuple]) -> Collection:
    return original_browser_all(self, _by(selector))


original_element_element = Element.element


@monkey.patch_method_in(Element)
def element(self, selector: Union[str, tuple]) -> Element:
    return original_element_element(self, _by(selector))


original_element_all = Element.all


@monkey.patch_method_in(Element)
def all(self, selector: Union[str, tuple]) -> Collection:
    return original_element_all(self, _by(selector))


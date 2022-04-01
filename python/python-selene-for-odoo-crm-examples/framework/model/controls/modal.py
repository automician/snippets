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
from typing import Union, Callable

from selene import have, Browser
from selene.core.entity import Element as SeleneElement

from framework import shared
from framework.assist.python.string import is_quoted_with_double_guillemets
from framework.model.admin_pro_web.controls.form import Form
from framework.shared import browser


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

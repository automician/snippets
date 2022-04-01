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
from typing import Callable

from selene.core.wait import Wait as SeleneWait, E, R


class ReportedWait(SeleneWait[E]):

    def for_(self, fn: Callable[[E], R]) -> R:
        original = super().for_

        from framework import assist

        @assist.allure.report.step(
            display_context=False,
            params_separator=': ',
            derepresent_params=True,
            translations=(
                    ('browser.element', 'element'),
                    ('browser.all', 'all'),
                    ("'css selector', ", ""),
                    (r"'\ue007'", "Enter"),
                    ('((', '('),
                    ('))', ')'),
                    (': has ', ': have '),
                    (': have ', ': should have '),
                    (': is ', ': should be'),
            )
        )
        def _(locator, action) -> R:
            return original(fn)

        return _(str(self._entity), str(fn))

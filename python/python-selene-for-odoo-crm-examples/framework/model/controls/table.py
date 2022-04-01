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
from typing import Union

from selene import have
from selene.core.condition import Condition
from selene.core.conditions import ElementCondition
from selene.core.entity import Element as SeleneElement, Element

from framework.assist.python.string import is_quoted_with_double_guillemets
from framework.assist.selene.queries import the
from framework.shared import browser


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

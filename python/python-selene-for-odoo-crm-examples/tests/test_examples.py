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
from selene import have

from framework.model.controls.dropdown import Dropdown
from framework.model.controls.dropdown_input import InputDropdown
from framework.model.controls.form import Form
from framework.model.controls.modal import Modal
from framework.model.controls.table import Table
from framework.model.data import UserData, AccessData
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

    # First Try
    browser.element('action_share_access').click()

    Modal.by_title('«Share Access»').form.fill(AccessData(
        access_label='director access',
        owner_id=InputDropdown.Value(
            text='not-admin@asdfgcompany.com',
            autocomplete=None,
        ),
        key_id=InputDropdown.Value(
            text='asdf-lkjh-jkli-ieqw',
            autocomplete=None,
        ),
        receiver_id=InputDropdown.Value(
            text='jackright@asdfgcompany.com',
            autocomplete=None,
        ),
    )).submit(
        by_button='do_share'
    ).should_have_validation_for(AccessData(
        owner_id=...,
        key_id=...,
    ))
    """
    # OR:
    dialog = Modal.by_title('Share Access').element
    dialog.element('access_label').type('director access')
    dialog.element('owner_id').element('input').type('admin@asdfgcompany.com')
    dialog.element('receiver_id').element('input').type('jackright@asdfgcompany.com')
    dialog.element('do_share').click()
    # todo: check validation ...
    dialog.element('do_close').click()
    # ...
    """

    # Second Try
    browser.element('action_add_device_to_hub').click()

    Modal.by_title('«Share Access»').form.fill(AccessData(
        device_label='director access',
        owner_id=InputDropdown.Value(text='admin@'),
        receiver_id=InputDropdown.Value(text='jackright@asdfgcompany.com'),
    )).submit(
        by_button=['do_share', 'do_close']
    )

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

import allure
import pytest
from selene import Browser, Config
from selene.support.shared import browser
from selenium.webdriver.remote.webdriver import WebDriver

import project
from framework.assist.allure import report
from framework.assist.selenium.typing import WebDriverOptions
from framework import shared, assist
from _pytest.nodes import Item
from _pytest.runner import CallInfo


@pytest.fixture(scope='session', autouse=True)
def patch_selene():
    import framework.assist.selene.patch


# TODO: make it work...
# @pytest.fixture(scope='session', autouse=True)
# def add_reporting_to_selene_steps():
#     original_open = browser.open
#
#     from selene.support.shared import SharedConfig, SharedBrowser
#     from framework.assist.python import monkey
#
#     @monkey.patch_method_in(SharedBrowser)
#     def open(self, relative_or_absolute_url: str):
#         return report.step(original_open)(relative_or_absolute_url)
#
#     @monkey.patch_method_in(SharedConfig)  # todo: consider patching Wait explicitly
#     def wait(self, entity):
#         hook = self._inject_screenshot_and_page_source_pre_hooks(
#             self.hook_wait_failure
#         )
#
#         from framework.assist.selene.report.wait import ReportedWait
#         return ReportedWait(
#             entity,
#             at_most=self.timeout,
#             or_fail_with=hook
#         )


@pytest.fixture(autouse=True)
def browser_management():

    yield shared.browser

    if (
        not project.settings.hold_browser_open
        and assist.selenium.check.is_alive(shared.browser.driver)
    ):
        shared.browser.quit()


@pytest.fixture()
def web(browser_management):
    """
    just an alias to browser_management fixture
    """

    yield browser_management


@pytest.hookimpl(tryfirst=True, hookwrapper=True)
def pytest_runtest_setup(item):

    yield
    # TODO: implement
    # shared.prev_test_screenshot = browser.config.last_screenshot
    # shared.prev_test_screenshot = browser.config.last_page_source


@pytest.hookimpl(tryfirst=True, hookwrapper=True)
def pytest_runtest_makereport(item: Item, call: CallInfo):
    """
    Attach snapshots on test failure
    """

    # All code prior to yield statement would be ran prior
    # to any other of the same fixtures defined

    outcome = yield  # Run all other pytest_runtest_makereport non wrapped hooks

    result = outcome.get_result()

    if result.when == 'call' and result.failed:
        last_screenshot = browser.config.last_screenshot
        if (
            last_screenshot
            and not last_screenshot == shared.prev_test_screenshot
        ):
            allure.attach.file(source=last_screenshot,
                               name='screenshot',
                               attachment_type=allure.attachment_type.PNG)

        last_page_source = browser.config.last_page_source
        if (
            last_page_source
            and not last_page_source == shared.prev_test_page_source
        ):
            allure.attach.file(source=last_page_source,
                               name='page source',
                               attachment_type=allure.attachment_type.HTML)

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
from selene import Browser, Config
from selenium.common.exceptions import WebDriverException
from selenium.webdriver.remote.webdriver import WebDriver
from selenium.webdriver.support.wait import WebDriverWait
from framework import assist

import project


# --- WEB ---


driver: WebDriver = ...
prev_test_screenshot = None
"""
TODO: finish implementation
"""
prev_test_page_source = None
"""
TODO: finish implementation
"""


def _web_driver_from(settings: project.Settings) -> WebDriver:
    driver_options = project.settings_to_web_driver_options()

    global driver
    from selenium import webdriver
    if (
        driver is ...
        or not assist.selenium.check.is_alive(driver)
    ):
        driver = (
            assist.webdriver_manager.set_up.local(
                settings.browser_name,
                driver_options,
            )
            if not settings.remote_url
            else webdriver.Remote(
                command_executor=settings.remote_url,
                options=driver_options,
            )
        )

        if settings.maximize_window:
            driver.maximize_window()
        else:
            driver.set_window_size(
                width=settings.window_width,
                height=settings.window_height,
            )

    # other driver configuration todos:
    # file upload when remote
    # - http://allselenium.info/file-upload-using-python-selenium-webdriver/
    #   - https://sqa.stackexchange.com/questions/12851/how-can-i-work-with-file-uploads-during-a-webdriver-test

    return driver


browser = Browser(Config(
    driver=lambda: _web_driver_from(project.settings),
    base_url=project.settings.base_url,
    timeout=project.settings.timeout,
))

web = browser
"""
just an alias to shared.browser
"""

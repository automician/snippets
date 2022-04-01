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
from typing import Dict, Callable, Optional

from selenium import webdriver
from selenium.webdriver.remote.webdriver import WebDriver
from webdriver_manager.chrome import ChromeDriverManager
from webdriver_manager.firefox import GeckoDriverManager
from webdriver_manager.microsoft import EdgeChromiumDriverManager, IEDriverManager
from webdriver_manager.opera import OperaDriverManager
from webdriver_manager.utils import ChromeType


from framework.assist.selenium.typing import WebDriverOptions
from . import supported

installers: Dict[
    supported.BrowserName,
    Callable[[Optional[WebDriverOptions]], WebDriver]
] = {
    supported.chrome:
        lambda opts: webdriver.Chrome(
            ChromeDriverManager().install(),
            options=opts,
        ),
    supported.chromium:
        lambda opts: webdriver.Chrome(
            ChromeDriverManager(chrome_type=ChromeType.CHROMIUM).install(),
            options=opts,
        ),
    supported.firefox:
        lambda opts: webdriver.Firefox(
            executable_path=GeckoDriverManager().install(),
            options=opts,
        ),
    supported.ie:
        lambda opts: webdriver.Ie(
            IEDriverManager().install(),
            options=opts,
        ),
    supported.edge:
        lambda ____: webdriver.Edge(
            EdgeChromiumDriverManager().install(),
        ),
    supported.opera:
        lambda opts: webdriver.Opera(
            executable_path=OperaDriverManager().install(),
            options=opts,
        ),
}


def local(
        name: supported.BrowserName = 'chrome',                                 # todo: consider change default to ... and then get it from options if passed
        options: WebDriverOptions = None
) -> WebDriver:
    return installers[name](options)

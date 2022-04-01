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
from selene.core.exceptions import TimeoutException
from selene.support.shared import browser


def attach_snapshots_on_failure(error: TimeoutException) -> Exception:
    """
    An example of selene hook_wait_failure that attaches snapshots to failed test step.
    It is actually might not needed,
    because using pytest_runtest_makereport hook
    you can achieve similar
    by attaching screenshots to the test body itself,
    that is more handy during analysis of test report

    but if you need it, you can use it by adding to your browser setup fixture::

        import web_test
        browser.config.hook_wait_failure = \
            web_test.assist.selene.shared.hook.attach_snapshots_on_failure

    otherwise, you can skip it;)
    """
    last_screenshot = browser.config.last_screenshot
    if last_screenshot:
        allure.attach.file(source=last_screenshot,
                           name='screenshot on failure',
                           attachment_type=allure.attachment_type.PNG)

    last_page_source = browser.config.last_page_source
    if last_page_source:
        allure.attach.file(source=last_page_source,
                           name='page source on failure',
                           attachment_type=allure.attachment_type.HTML)
    return error

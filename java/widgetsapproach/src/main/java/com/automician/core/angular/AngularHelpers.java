package com.automician.core.angular;

import com.automician.core.checks.Assertions;
import com.automician.core.exceptions.ScriptNotFoundException;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.ScriptTimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.io.IOException;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class AngularHelpers {

    public static String rootSelector = "[ng-app]"; //!!! need to setup before usage

    public static void waitForRequestsToFinish() {
        Assertions.have(requestIsFinished());
    }

    private static String waitForRequestToFinishScriptTemplate = loadScript("waitForAngularRequestToFinish");

    private static String getWaitForRequestToFinishScript() {
        return String.format(waitForRequestToFinishScriptTemplate, rootSelector);
    }

    private static String loadScript(String scriptName) {
        try {
            return "var callback = arguments[arguments.length - 1];\n" +
                    "var rootSelector = '%s';\n" +
                    "\n" + IOUtils.toString(AngularHelpers.class.getResourceAsStream("/js/" + scriptName), "UTF-8");
        } catch (IOException e) {
            throw new ScriptNotFoundException(scriptName, e);
        }
    }

    private static void executeAsyncScript(String script, Object... arguments) {
        ((JavascriptExecutor) getWebDriver()).executeAsyncScript(script, arguments);
    }

    private static ExpectedCondition<Boolean> requestIsFinished() {
        return new ExpectedCondition<Boolean>() {

            @Override
            public Boolean apply(WebDriver webDriver) {
                try {
                    executeAsyncScript(getWaitForRequestToFinishScript());
                    return true;
                } catch (ScriptTimeoutException e) {
                    return false;
                }
            }

            @Override
            public String toString() {
                return "wait for angular request to finish";
            }
        };
    }

}
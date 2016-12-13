package com.automician.core.angular;

import com.automician.core.exceptions.ScriptNotFoundException;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.JavascriptExecutor;

import java.io.IOException;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class AngularHelpers {

    private static String waitForRequestToFinishScript = loadScript("waitForAngularRequestToFinish");
    private static String rootSelector = "[ng-app]";

    private static String loadScript(String scriptName) {
        try {
            return IOUtils.toString(AngularHelpers.class.getResourceAsStream("/js/" + scriptName), "UTF-8");
        } catch (IOException e) {
            throw new ScriptNotFoundException(scriptName, e);
        }
    }

    private static void executeAsyncScript(String script, Object... arguments) {
        ((JavascriptExecutor) getWebDriver()).executeAsyncScript(script, arguments);
    }

    public static void waitForRequestsToFinish() {
        final long startTime = System.currentTimeMillis();
        executeAsyncScript("var callback = arguments[arguments.length - 1];\n" +
                "var rootSelector = '" + rootSelector + "';\n" +
                "\n" +
                waitForRequestToFinishScript);
    }

}

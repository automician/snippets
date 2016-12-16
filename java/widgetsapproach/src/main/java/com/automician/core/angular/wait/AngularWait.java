package com.automician.core.angular.wait;

import com.automician.core.checks.Assertions;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.ScriptTimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import ru.yandex.qatools.allure.annotations.Step;
import com.automician.core.angular.wait.exceptions.ScriptNotFoundException;
import java.io.IOException;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;


public class AngularWait {

    public static String rootSelector = ".no-touchevents";

    private static int countOfWaits = 0;
    private static long timeOwWaits = 0;

    @Step
    public static void forRequestsToFinish() {
        Assertions.have(requestIsFinished());
    }

    private static String forRequestToFinishScriptTemplate = loadScript("waitForAngularRequestToFinish");

    private static String getWaitForRequestToFinishScript() {
        return String.format(forRequestToFinishScriptTemplate, rootSelector);
    }

    private static String loadScript(String scriptName) {
        try {
            return "var callback = arguments[arguments.length - 1];\n" +
                    "var rootSelector = '%s';\n" +
                    "\n" + IOUtils.toString(AngularWait.class.getResourceAsStream("/js/" + scriptName), "UTF-8");
        } catch (IOException e) {
            throw new ScriptNotFoundException(scriptName, e);
        }
    }

    private static void executeAsyncScript(String script, Object... arguments) {
        long startTime = System.currentTimeMillis();
        ((JavascriptExecutor) getWebDriver()).executeAsyncScript(script, arguments);
        timeOwWaits += (System.currentTimeMillis() - startTime);
        countOfWaits++;
        System.out.println("Angular waits: count = " + countOfWaits + ", time in ms = " + timeOwWaits + ", average time in ms = " + (timeOwWaits / countOfWaits));
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

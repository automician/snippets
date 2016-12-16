package com.automician.core.checks;

import com.automician.core.Core;
import com.codeborne.selenide.Configuration;
import com.google.common.base.Function;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class Assertions {

    public static <V> V be(ExpectedCondition<V> condition) {
//        return (V) new FluentWait(getWebDriver()).
//                withTimeout(Configuration.timeout, TimeUnit.MILLISECONDS).
//                pollingEvery(Configuration.pollingInterval, TimeUnit.MILLISECONDS).
//                until(condition);
        return new WebDriverWait(getWebDriver(), Configuration.timeout / 1000, Configuration.pollingInterval).until(condition);
    }

    public static <V> V have(ExpectedCondition<V> condition) {
        return be(condition);
    }

    public static void urlToBe(String expectedUrl) {
        have(ExpectedConditions.urlToBe(expectedUrl));
    }
}

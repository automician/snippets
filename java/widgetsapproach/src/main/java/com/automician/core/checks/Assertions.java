package com.automician.core.checks;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class Assertions {

    public static <V> V be(ExpectedCondition<V> condition) {
        return new WebDriverWait(getWebDriver(), Configuration.timeout / 1000).until(condition);
    }

    public static <V> V have(ExpectedCondition<V> condition) {
        return be(condition);
    }

    public static void urlToBe(String expectedUrl) {
        have(ExpectedConditions.urlToBe(expectedUrl));
    }
}

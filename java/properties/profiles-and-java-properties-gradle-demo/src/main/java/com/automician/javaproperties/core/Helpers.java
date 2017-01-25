package com.automician.javaproperties.core;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.automician.javaproperties.core.CustomConditions.ajaxCompleted;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class Helpers {
    public static void waitUntilAjaxComplete() {
        new WebDriverWait(getWebDriver(), Configuration.timeout / 1000).until(ajaxCompleted());
    }
}

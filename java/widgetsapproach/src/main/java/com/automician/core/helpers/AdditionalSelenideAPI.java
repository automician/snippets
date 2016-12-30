package com.automician.core.helpers;

import com.automician.core.angular.entities.AngularElement;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.JavascriptExecutor;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class AdditionalSelenideAPI {

    //scrollTo works not always
    //scrollWithOffset(0, -150); - this variant doesn't help in situations when something is shown under the header
    public static void scrollWithOffsetOn(SelenideElement element, int x, int y) {
        element.shouldBe(visible);

        String code = "window.scroll(" + (element.getLocation().x + x) + "," + (element.getLocation().y + y) + ");";
        ((JavascriptExecutor) getWebDriver()).executeScript(code, element, x, y);
    }

}

package com.automician.core.locators;

import org.openqa.selenium.By;

public class Locators {

    public static By dt(String attrDataTestValue) {
        return By.cssSelector("[data-test='" + attrDataTestValue + "']");
    }

    public static By dtStarts(String attrDataTestValue) {
        return By.cssSelector("[data-test^='" + attrDataTestValue + "']");
    }

}

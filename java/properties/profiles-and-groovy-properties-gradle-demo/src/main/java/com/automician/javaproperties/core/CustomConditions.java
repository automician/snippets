package com.automician.javaproperties.core;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class CustomConditions {

    public static ExpectedCondition<Boolean> ajaxCompleted() {
        return new ExpectedCondition<Boolean>() {
            boolean result;
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    result = (Boolean) ((JavascriptExecutor) driver).executeScript("return typeof($) !== 'undefined' && $.active == 0");
                    //System.out.println("Ajax return $.active == 0  is: '" + result + "'");
                } catch (WebDriverException e) {
                    return false;
                }
                return result;
            }
            public String toString() {
                return "Ajax return $.active == 0  is: '" + result + "'";
            }
        };
    }

}
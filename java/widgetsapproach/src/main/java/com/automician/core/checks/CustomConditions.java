package com.automician.core.checks;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import java.util.Arrays;

import static com.codeborne.selenide.Selenide.actions;

public class CustomConditions {

    public static Condition dt(String attrDataTestValue) {
        return Condition.attribute("data-test", attrDataTestValue);
    }

    public static Condition dtStarts(final String attrDataTestValue) {
        return attrValueStarts("data-test", attrDataTestValue);
    }

    public static Condition oneOfDt(final String... attrDataTestValues) {
        return oneOfAttrValues("data-test", attrDataTestValues);
    }

    public static Condition attrValueStarts(final String attrName, final String attrDataTestValue) {
        return new Condition("attrValueStarts") {
            @Override
            public boolean apply(WebElement element) {
                return getAttribute(attrName, element).startsWith(attrDataTestValue);
            }

            @Override
            public String toString() {
                return "Attribute " + attrName + " starts with " + attrDataTestValue;
            }
        };
    }

    public static Condition oneOfAttrValues(final String attrName, final String... attrDataTestValues) {
        return new Condition("oneOfAttrValues") {
            @Override
            public boolean apply(WebElement element) {
                for (String attrDataTestValue : attrDataTestValues) {
                    if (getAttribute(attrName, element).equals(attrDataTestValue)) {
                        return true;
                    }
                }
                return false;
            }

            @Override
            public String toString() {
                return "Attribute " + attrName + " should be equal one of " + Arrays.toString(attrDataTestValues);
            }
        };
    }

    private static String getAttribute(String attrName, WebElement element) {
        String attr = element.getAttribute("data-test");
        return attr == null ? "" : attr.trim();
    }

}

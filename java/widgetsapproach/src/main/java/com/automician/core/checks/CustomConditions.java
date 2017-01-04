package com.automician.core.checks;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import java.util.Arrays;

import static com.codeborne.selenide.Selenide.actions;

public class CustomConditions {

    public static Condition attrValueStarts(final String attrName, final String attrValue) {
        return new Condition("attrValueStarts") {
            @Override
            public boolean apply(WebElement element) {
                return getAttribute(attrName, element).startsWith(attrValue);
            }

            @Override
            public String toString() {
                return "Attribute " + attrName + " starts with " + attrValue;
            }
        };
    }

    public static Condition oneOfAttrValues(final String attrName, final String... attrValues) {
        return new Condition("oneOfAttrValues") {
            @Override
            public boolean apply(WebElement element) {
                for (String attrValue : attrValues) {
                    if (getAttribute(attrName, element).equals(attrValue)) {
                        return true;
                    }
                }
                return false;
            }

            @Override
            public String toString() {
                return "Attribute " + attrName + " should be equal one of " + Arrays.toString(attrValues);
            }
        };
    }

    private static String getAttribute(String attrName, WebElement element) {
        String attr = element.getAttribute(attrName);
        return attr == null ? "" : attr.trim();
    }

}

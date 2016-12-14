package com.automician.core.checks;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selenide.actions;

public class CustomConditions {

    public static Condition dt(String attrDataTestValue) {
        return Condition.attribute("data-test", attrDataTestValue);
    }

    public static Condition dtStarts(final String attrDataTestValue) {
        return new Condition("dtStarts") {
            @Override
            public boolean apply(WebElement element) {
                String attr = element.getAttribute("data-test");
                String attributeValue = attr == null ? "" : attr.trim();
                return attrDataTestValue.startsWith(attributeValue);
            }

            @Override
            public String toString() {
                return "Attribute data-test starts with " + attrDataTestValue;
            }
        };
    }

}

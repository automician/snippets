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

    public static Condition hoverAndOpenMenu(final ElementsCollection menuItems) {
        return new Condition("hoverAndOpenMenu") {
            @Override
            public boolean apply(WebElement element) {
                actions().moveToElement(element).perform();
                return menuItems.get(0).isDisplayed();
            }

            @Override
            public String toString() {
                return name + " '" + menuItems + '\'';
            }
        };
    }

    public static Condition hoverAndOpenMenuAndSelectItem(final ElementsCollection menuItems, final Condition conditionToSelectMenuItem) {
        return new Condition("hoverAndOpenMenu") {
            @Override
            public boolean apply(WebElement element) {
                actions().moveToElement(element).perform();
                WebElement menuItem = menuItems.find(conditionToSelectMenuItem).toWebElement();
                if (menuItem == null) {
                    return false;
                }
                try {
                    menuItem.click();
                    return true;
                } catch (WebDriverException e) {
                    return false;
                }
            }

            @Override
            public String toString() {
                return name + " '" + menuItems + '\'' + " \ncondition to select menu item " + conditionToSelectMenuItem;
            }
        };
    }

}

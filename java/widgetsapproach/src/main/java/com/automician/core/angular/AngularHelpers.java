package com.automician.core.angular;

import com.automician.core.angular.entities.AngularCollection;
import com.automician.core.angular.entities.AngularElement;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

public class AngularHelpers {

    public static SelenideElement $(By elementLocator) {
        return new AngularElement(Selenide.$(elementLocator));
    }

    public static SelenideElement $(String cssSelector) {
        return $(By.cssSelector(cssSelector));
    }

    public static ElementsCollection $$(By elementsLocator) {
        return new AngularCollection(Selenide.$$(elementsLocator));
    }

    public static ElementsCollection $$(String cssSelector) {
        return $$(By.cssSelector(cssSelector));
    }

}

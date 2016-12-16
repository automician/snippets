package com.automician.core.angular;

import com.automician.core.angular.entities.AngularCollection;
import com.automician.core.angular.entities.AngularElement;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;

public class AngularHelpers {

    public static AngularElement $(By elementLocator) {
        return new AngularElement(Selenide.$(elementLocator));
    }

    public static AngularElement $(String cssSelector) {
        return $(By.cssSelector(cssSelector));
    }

    public static AngularCollection $$(By elementsLocator) {
        return new AngularCollection(Selenide.$$(elementsLocator));
    }

    public static AngularCollection $$(String cssSelector) {
        return $$(By.cssSelector(cssSelector));
    }

}

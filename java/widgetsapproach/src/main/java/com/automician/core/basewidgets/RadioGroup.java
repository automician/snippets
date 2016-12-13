package com.automician.core.basewidgets;

import com.codeborne.selenide.SelenideElement;

import static com.automician.core.locators.Locators.dt;

public class RadioGroup {

    private SelenideElement self;

    public RadioGroup(SelenideElement container) {
        this.self = container;
    }

    public void clickOn(String attrDataTestValue) {
        self.find(dt(attrDataTestValue)).parent().click();
    }

    public void clickOn(Object attrDataTestValue) {
        clickOn(String.valueOf(attrDataTestValue));
    }
}

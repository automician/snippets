package com.automician.widgets.basic;

import com.automician.core.angular.entities.AngularElement;

import static com.automician.core.locators.Locators.dt;

public class RadioGroup {

    private AngularElement container;

    public RadioGroup(AngularElement container) {
        this.container = container;
    }

    public void clickOn(String attrDataTestValue) {
        this.container.find("...").click();//replace ... to appropriate value
    }

    public void clickOn(Object attrDataTestValue) {
        this.clickOn(String.valueOf(attrDataTestValue));
    }
}

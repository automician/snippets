package com.automician.widgets.basic;

import com.automician.core.angular.entities.AngularElement;

import java.util.ArrayList;
import java.util.List;

import static com.automician.core.locators.Locators.dt;

public class CheckBoxGroup {

    private AngularElement container;

    private String[] attrDataTestValues;

    public CheckBoxGroup(AngularElement container, String... attrDataTestValues) {
        this.container = container;
        this.attrDataTestValues = attrDataTestValues;
    }

    private List<AngularElement> getElements() {
        List<AngularElement> elements = new ArrayList<AngularElement>();

        for (String attrDataTestValue : this.attrDataTestValues) {
            elements.add(getElement(attrDataTestValue));
        }
        return elements;
    }

    private AngularElement getElement(String attrDataTestValue) {
        return this.container.find("....");//replace to appropriate case
    }

    public void clickAll() {
        for (AngularElement element:getElements()) {
                element.click();
        }
    }

}
package com.automician.widgets.basic;

import com.automician.core.angular.entities.AngularElement;

import java.util.ArrayList;
import java.util.List;

import static com.automician.core.locators.Locators.dt;

public class TextFieldGroup {

    private AngularElement container;
    private String[] attrDataTestValues;

    public TextFieldGroup(AngularElement container, String... attrDataTestValues) {
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
        return this.container.find("...");//replace ... to appropriate value
    }

    public void fill(String... texts) {
        List<AngularElement> elements = getElements();

        for (int i = 0; i < Math.min(texts.length, elements.size()); i++) {
            elements.get(i).setValue(texts[i]);
        }
    }
}

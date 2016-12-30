package com.automician.widgets.basic;

import com.codeborne.selenide.SelenideElement;

import java.util.ArrayList;
import java.util.List;

import static com.automician.core.locators.Locators.dt;

public class TextFieldGroup {

    private final SelenideElement container;
    private final String[] attrDataTestValues;

    public TextFieldGroup(SelenideElement container, String... attrDataTestValues) {
        this.container = container;
        this.attrDataTestValues = attrDataTestValues;
    }

    private List<SelenideElement> getElements() {
        List<SelenideElement> elements = new ArrayList<SelenideElement>();

        for (String attrDataTestValue : this.attrDataTestValues) {
            elements.add(this.container.find(dt(attrDataTestValue)));
        }
        return elements;
    }

    public void fill(String... texts) {
        List<SelenideElement> elements = getElements();

        for (int i = 0; i < Math.min(texts.length, elements.size()); i++) {
            elements.get(i).setValue(texts[i]);
        }
    }
}

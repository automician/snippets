package com.automician.core.basewidgets;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.ArrayList;
import java.util.List;

import static com.automician.core.locators.Locators.dt;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;

public class TextFieldGroup {

    private SelenideElement self;
    private String[] attrDataTestValues;

    private List<SelenideElement> elements;

    public TextFieldGroup(SelenideElement container) {
        this.self = container;
    }

    public TextFieldGroup(SelenideElement container, String... attrDataTestValues) {
        this.self = container;
        this.attrDataTestValues = attrDataTestValues;
    }

    private List<SelenideElement> getElements(int minimumOfCount) {
        List<SelenideElement> elements = new ArrayList<>();

        if (attrDataTestValues == null) {
            ElementsCollection collection = self.findAll("input").shouldHave(sizeGreaterThanOrEqual(minimumOfCount));
            for (SelenideElement element : collection) {
                elements.add(element);
            }
        } else {
            for (String attrDataTestValue : attrDataTestValues) {
                elements.add(self.find(dt(attrDataTestValue)));
            }
        }
        return elements;
    }

    public void fill(String... texts) {
        List<SelenideElement> elements = getElements(texts.length);

        for (int i = 0; i < Math.min(texts.length, elements.size()); i++) {
            elements.get(i).setValue(texts[i]);
        }
    }
}

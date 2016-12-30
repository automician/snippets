package com.automician.widgets.basic;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;


public class TextFieldGroup {

    private final SelenideElement container;
    private final String[] attrDataTestValues;
    private final ElementsCollection items;

    public TextFieldGroup(SelenideElement container, ElementsCollection items, String... attrDataTestValues) {
        this.container = container;
        this.attrDataTestValues = attrDataTestValues;
        this.items = items;
    }

    public void fill(String... texts) {
        items.shouldHave(CollectionCondition.sizeGreaterThanOrEqual(texts.length));
        for (int i = 0; i < Math.min(texts.length, items.size()); i++) {
            items.get(i).setValue(texts[i]);
        }
    }
}

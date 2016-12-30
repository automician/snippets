package com.automician.widgets.basic;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.automician.core.locators.Locators.childHasOneOfDt;
import static com.automician.core.locators.Locators.oneOfDt;
import static com.codeborne.selenide.Condition.checked;

public class CheckBoxGroup {

    private final SelenideElement container;
    private final ElementsCollection items;

    private CheckBoxGroup(SelenideElement container, ElementsCollection items ) {
        this.container = container;
        this.items = items;
    }

    public void toggleAll() {
        for (SelenideElement element : items) {
            element.click();
        }
    }

    public void checkAll() {
        for (SelenideElement element : items) {
            element.shouldNotBe(checked).click();
        }
    }
}
package com.automician.widgets.basic;

import com.codeborne.selenide.SelenideElement;

import java.util.ArrayList;
import java.util.List;

import static com.automician.core.locators.Locators.dt;
import static com.codeborne.selenide.Condition.checked;

public class CheckBoxGroup {

    private final SelenideElement container;
    private final List<SelenideElement> items;

    private CheckBoxGroup(SelenideElement container, List<SelenideElement> items ) {
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
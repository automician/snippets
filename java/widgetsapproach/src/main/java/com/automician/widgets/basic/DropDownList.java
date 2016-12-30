package com.automician.widgets.basic;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.exactText;

public class DropDownList {

    private final SelenideElement container;
    private final ElementsCollection items;

    public DropDownList(SelenideElement container, ElementsCollection items) {
        this.container = container;
        this.items = items;
    }

    public DropDownList(SelenideElement container, String itemsCssSelector) {
        this(
                container,
                container.findAll(itemsCssSelector)
        );
    }

    public DropDownList(SelenideElement container) {
        this(
                container,
                ".your-items-locator"
        );
    }

    public DropDownList open() {
        this.container.click();
        return this;
    }

    public void select(String itemText) {
        selectBy(exactText(itemText));
    }

    public void select(By itemLocator) {
        this.container.find(itemLocator).click();
    }

    public void selectBy(Condition condition) {
        this.items.find(condition).click();
    }

    public void select(int index) {
        this.items.get(index).click();
    }

}

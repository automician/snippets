package com.automician.widgets.basic;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exactText;

public class Menu {

    private final SelenideElement container;
    private final ElementsCollection items;

    public Menu(SelenideElement container, ElementsCollection items) {
        this.container = container;
        this.items = items;
    }

    public Menu(SelenideElement container) {
        this(
                container,
                container.findAll("ul>li") //to use default items collection
        );
    }

    public Menu open() {
        this.container.hover();
        return this;
    }

    public void select(String itemText) {
        this.items.find(exactText(itemText)).click();
    }

}

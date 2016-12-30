package com.automician.widgets.basic;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

public class Buttons {

    private final SelenideElement container;
    private final ElementsCollection items;

    public Buttons(SelenideElement container, String itemsCssSelector) {
        this.container = container;
        this.items = this.container.findAll(itemsCssSelector);
    }

    public Buttons(SelenideElement container) {
        this(container, "...");//to use default itemsCssSelector
    }

    public void click(Condition condition) {
        this.items.find(condition).click();
    }

    public void click(String buttonText) {
        click(Condition.exactText(buttonText));
    }

}

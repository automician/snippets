package com.automician.widgets.basic;

import com.automician.core.angular.entities.AngularCollection;
import com.automician.core.angular.entities.AngularElement;
import com.codeborne.selenide.Condition;

public class Buttons {

    private AngularElement container;

    private AngularCollection items;

    public Buttons (AngularElement container) {
        this.container = container;
        this.items = this.container.findAll("...");//replace ... to default value
    }

    public Buttons withItems(String cssSelector) {
        this.items = this.container.findAll(cssSelector);
        return this;
    }

    public void click(Condition condition) {
        this.items.find(condition).click();
    }

    public void click(String buttonText) {
        click(Condition.exactText(buttonText));
    }

}

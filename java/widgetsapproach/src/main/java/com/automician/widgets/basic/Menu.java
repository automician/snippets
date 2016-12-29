package com.automician.widgets.basic;

import com.automician.core.angular.entities.AngularCollection;
import com.automician.core.angular.entities.AngularElement;

import static com.codeborne.selenide.Condition.exactText;

public class Menu {

    private AngularElement container;
    private AngularCollection items;

    public Menu(AngularElement container) {
        this.container = container;
        this.items = container.findAll("...");//replace ... to default value
    }

    public Menu withItems(AngularCollection items) {
        this.items = items;
        return this;
    }

    public Menu open() {
        this.container.hover();
        return this;
    }

    public void select(String itemText) {
        this.items.find(exactText(itemText)).click();
    }

}

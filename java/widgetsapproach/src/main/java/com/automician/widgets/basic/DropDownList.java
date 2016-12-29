package com.automician.widgets.basic;

import com.automician.core.angular.entities.AngularCollection;
import com.automician.core.angular.entities.AngularElement;
import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.exactText;

public class DropDownList {

    private AngularElement container;
    private AngularCollection items;

    public DropDownList(AngularElement container) {
        this.container = container;
        this.items = container.findAll("...");//replace ... to default value
    }

    public DropDownList open() {
        this.container.click();
        return this;
    }

    public DropDownList withItems(AngularCollection items) {
        this.items = items;
        return this;
    }

    public DropDownList withItems(String itemsCssSelector) {
        return withItems(this.container.findAll(By.cssSelector(itemsCssSelector)));
    }

    public void select(String itemText) {
        select(exactText(itemText));
    }

    public void select(By itemLocator) {
        this.container.find(itemLocator).click();
    }

    public void select(Condition condition) {
        this.items.find(condition).click();
    }

    public void select(int index) {
        this.items.get(index).click();
    }

}

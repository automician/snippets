package com.automician.core.basewidgets;

import com.automician.core.angular.AngularHelpers;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

public class MenuItems {

    public final ElementsCollection self;
    public final SelenideElement parent;

    public MenuItems(SelenideElement parent, ElementsCollection menuItems) {
        this.parent = parent;
        this.self = menuItems;
    }

    public MenuItems open() {
        AngularHelpers.waitForRequestsToFinish();
        parent.hover();
        return this;
    }

    public void select(Condition conditionToSelectItem) {
        self.findBy(conditionToSelectItem).click();
    }

    public void openAndSelect(Condition conditionToSelectItem) {
        open();
        select(conditionToSelectItem);
    }
}

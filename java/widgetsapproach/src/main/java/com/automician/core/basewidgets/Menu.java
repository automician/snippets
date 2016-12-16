package com.automician.core.basewidgets;

import com.automician.core.angular.entities.AngularCollection;
import com.automician.core.angular.entities.AngularElement;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

public class Menu {

    public AngularCollection menuElements() {
        return menuElements;
    }

    public AngularElement container() {
        return self;
    }

    private final AngularElement parent;
    private final AngularElement self;
    private final AngularCollection menuElements;

    public Menu(AngularElement parent, AngularElement menu, AngularCollection menuElements) {
        this.parent = parent;
        this.self = menu;
        this.menuElements = menuElements;
    }

    public Menu(AngularElement parent, String menuCssSelector, String menuElementsCssSelector) {
        this.parent = parent;
        this.self = parent.$(menuCssSelector);
        this.menuElements = this.self.$$(menuElementsCssSelector);
    }

    public MenuItems menuItems() {
        return new MenuItems(self, menuElements());
    }

    public MenuItems open() {
        return menuItems().open();
    }

    public void openAndSelect(Condition conditionToSelectItem) {
        menuItems().openAndSelect(conditionToSelectItem);
    }
}

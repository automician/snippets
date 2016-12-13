package com.automician.core.basewidgets;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

public class Menu {

    public ElementsCollection menuElements() {
        return menuElements;
    }

    public SelenideElement container() {
        return self;
    }

    private final SelenideElement parent;
    private final SelenideElement self;
    private final ElementsCollection menuElements;

    public Menu(SelenideElement parent, SelenideElement menu, ElementsCollection menuElements) {
        this.parent = parent;
        this.self = menu;
        this.menuElements = menuElements;
    }

    public Menu(SelenideElement parent, String menuCssSelector, String menuElementsCssSelector) {
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

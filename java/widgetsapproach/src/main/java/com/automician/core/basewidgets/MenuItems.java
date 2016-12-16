package com.automician.core.basewidgets;

import com.automician.core.angular.entities.AngularCollection;
import com.automician.core.angular.entities.AngularElement;
import com.automician.core.angular.wait.AngularWait;
import com.codeborne.selenide.*;

public class MenuItems {

    public final AngularCollection self;
    public final AngularElement parent;

    public MenuItems(AngularElement parent, AngularCollection menuItems) {
        this.parent = parent;
        this.self = menuItems;
    }

    public MenuItems open() {
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

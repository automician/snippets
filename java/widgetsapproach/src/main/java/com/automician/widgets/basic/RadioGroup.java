package com.automician.widgets.basic;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.automician.core.locators.Locators.dt;

public class RadioGroup {

    private final SelenideElement container;

    public RadioGroup(SelenideElement container) {
        this.container = container;
    }

    public void clickOn(By itemLocator) {
        this.container.find(itemLocator).click();
    }
}

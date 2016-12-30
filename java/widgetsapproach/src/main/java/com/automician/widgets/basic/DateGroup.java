package com.automician.widgets.basic;

import com.codeborne.selenide.SelenideElement;
import ru.yandex.qatools.allure.annotations.Step;

import static com.automician.core.helpers.AdditionalSelenideAPI.scrollWithOffsetOn;
import static com.automician.core.locators.Locators.dt;

public class DateGroup {

    private final SelenideElement container;
    private final SelenideElement dayElement;
    private final SelenideElement monthElement;
    private final SelenideElement yearElement;

    public DateGroup(SelenideElement container, SelenideElement dayElement, SelenideElement monthElement, SelenideElement yearElement) {
        this.container = container;
        this.dayElement = dayElement;
        this.monthElement = monthElement;
        this.yearElement = yearElement;
    }


    private DateGroup setDay(int day) {
        this.dayElement.setValue(String.valueOf(day));
        return this;
    }

    private DateGroup setMonth(int month) {
        scrollWithOffsetOn(this.monthElement, 0, -150);//without scroll error for firefox in Xvfb run - header shows over month element
        new DropDownList(
                this.monthElement,
                "..." //to use items selector
        )
                .open()
                .select(month - 1);
        return this;
    }

    private DateGroup setYear(int year) {
        this.yearElement.setValue(String.valueOf(year));
        return this;
    }

    @Step
    public void set(int day, int month, int year) {
        setDay(day).setMonth(month).setYear(year);
    }
}

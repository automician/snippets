package com.automician.widgets.basic;

import com.automician.core.angular.entities.AngularElement;
import ru.yandex.qatools.allure.annotations.Step;

import static com.automician.core.locators.Locators.dt;

public class DateGroup {

    private AngularElement container;

    private AngularElement dayElement;
    private AngularElement monthElement;
    private AngularElement yearElement;

    public DateGroup(AngularElement container) {
        this.container = container;
        this.dayElement = this.container.find("...");//replace ... to default value
        this.monthElement = this.container.find("...").parent();//replace ... to default value
        this.yearElement = this.container.find("...");//replace ... to default value
    }

    private DateGroup setDay(int day) {
        this.dayElement.setValue(String.valueOf(day));
        return this;
    }

    private DateGroup setMonth(int month) {
        this.monthElement.scrollWithOffset(0, -150);//without scroll error for firefox in Xvfb run - header shows over month element
        new DropDownList(this.monthElement).open().select(month-1);
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

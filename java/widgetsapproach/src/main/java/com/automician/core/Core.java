package com.automician.core;

import com.automician.core.angular.AngularHelpers;
import com.automician.core.checks.Assertions;
import com.automician.core.checks.CustomConditions;
import com.automician.core.helpers.UniqueData;
import com.automician.core.locators.Locators;
import com.automician.core.properties.PropertiesReader;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.UIAssertionError;

public class Core {

    private static Core core = new Core();

    private UniqueData uniqueData = new UniqueData();
    private AngularHelpers angular = new AngularHelpers();

    public static Core core() {
        return core;
    }

    public Locators by() {
        return new Locators();
    }

    public Assertions asserts() {
        return new Assertions();
    }

    public boolean waitingIs(SelenideElement element, Condition condition) {
        angular().waitForRequestsToFinish();
        try {
            element.should(condition);
            return true;
        } catch (UIAssertionError e) {
            return false;
        }
    }

    public AngularHelpers angular() {
        return angular;
    }

    public void open(String url) {
        Selenide.open(url);
    }

    public PropertiesReader properties() {
        return new PropertiesReader();
    }

    public UniqueData uniqueData() {
        return uniqueData;
    }

    public CustomConditions conditions() {
        return new CustomConditions();
    }

    public String the(String name) {
        return uniqueData().the(name);
    }

    public String the(String name, String suffix) {
        return uniqueData().the(name, suffix);
    }

}
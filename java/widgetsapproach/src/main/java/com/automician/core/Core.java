package com.automician.core;

import com.automician.core.angular.entities.AngularElement;
import com.automician.core.angular.wait.AngularWait;
import com.automician.core.checks.Assertions;
import com.automician.core.checks.CustomConditions;
import com.automician.core.helpers.UniqueData;
import com.automician.core.locators.Locators;
import com.automician.core.properties.PropertiesReader;
import com.codeborne.selenide.*;
import com.codeborne.selenide.ex.UIAssertionError;

public class Core {

    private static Core core = new Core();

    private UniqueData uniqueData = new UniqueData();
    private AngularWait angular = new AngularWait();

    public static Core core() {
        return core;
    }

    public Locators by() {
        return new Locators();
    }

    public Assertions asserts() {
        return new Assertions();
    }

    public boolean waitingIs(AngularElement element, Condition condition) {
        try {
            element.should(condition);
            return true;
        } catch (UIAssertionError e) {
            return false;
        }
    }

    public AngularWait angular() {
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

    public String unique(String name) {
        return uniqueData().the(name);
    }

    public String unique(String name, String suffix) {
        return uniqueData().the(name, suffix);
    }

}
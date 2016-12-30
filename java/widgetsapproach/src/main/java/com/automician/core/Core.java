package com.automician.core;

import com.automician.core.angular.wait.AngularWait;
import com.automician.core.checks.Assertions;
import com.automician.core.checks.CustomConditions;
import com.automician.core.helpers.UniqueData;
import com.automician.core.locators.Locators;
import com.automician.core.properties.PropertiesReader;
import com.codeborne.selenide.*;

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

    public AngularWait angular() {
        return this.angular;
    }

    public void open(String url) {
        Selenide.open(url);
    }

    public PropertiesReader properties() {
        return new PropertiesReader();
    }

    public CustomConditions conditions() {
        return new CustomConditions();
    }

    public UniqueData uniqueData() {
        return this.uniqueData;
    }

    public String unique(String name) {
        return uniqueData().the(name);
    }

    public String unique(String name, String suffix) {
        return uniqueData().the(name, suffix);
    }

}
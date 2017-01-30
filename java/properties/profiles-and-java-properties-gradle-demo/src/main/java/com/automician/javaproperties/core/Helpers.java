package com.automician.javaproperties.core;

import com.automician.javaproperties.core.conditions.Have;
import com.codeborne.selenide.Selenide;

public class Helpers {

    public static void waitUntilJQueryLoaded() {
        String jQueryLoaded = "return typeof($) !== 'undefined' && $.active == 0";
        Selenide.Wait().until(Have.jsReturnedTrue(jQueryLoaded));
    }
}

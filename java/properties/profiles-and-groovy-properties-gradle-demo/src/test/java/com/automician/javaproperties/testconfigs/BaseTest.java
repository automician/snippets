package com.automician.javaproperties.testconfigs;

import com.automician.javaproperties.core.Helpers;
import com.codeborne.selenide.Configuration;
import org.junit.After;
import org.junit.Before;

import java.util.Properties;

import static com.automician.javaproperties.core.Helpers.waitUntilAjaxComplete;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.open;

public class BaseTest {

    public static String appURL;

    static {
        Properties properties = Helpers.getProperties();
        appURL = properties.getProperty("app.url");
        Configuration.browser = properties.getProperty("test.browser");
    }

    @Before
    public void openApp() {
        open(appURL);
        waitUntilAjaxComplete();
    }

    @After
    public void clearData() {
        executeJavaScript("localStorage.clear();");
    }
}

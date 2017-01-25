package com.automician.javaproperties.testconfigs;

import com.automician.javaproperties.core.Helpers;
import com.automician.javaproperties.core.PropertiesHelper;
import com.codeborne.selenide.Configuration;
import org.junit.After;
import org.junit.Before;

import java.util.Properties;

import static com.automician.javaproperties.core.Helpers.waitUntilAjaxComplete;
import static com.automician.javaproperties.core.PropertiesHelper.configureTestOn;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.open;

public class BaseTest {

    public static String appURL;

    static {
        Properties properties = PropertiesHelper.read();
        appURL = properties.getProperty("app.url");
        Configuration.browser = properties.getProperty("browser");

        String testOn = properties.getProperty("test.on", "local");
        configureTestOn(testOn, properties);
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

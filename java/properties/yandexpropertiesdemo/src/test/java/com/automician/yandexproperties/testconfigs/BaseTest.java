package com.automician.yandexproperties.testconfigs;

import com.automician.yandexproperties.core.Helpers;
import com.codeborne.selenide.Configuration;
import org.junit.After;
import org.junit.Before;

import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.open;
import static com.automician.yandexproperties.core.Helpers.EnvProperties;

public class BaseTest {

    public static String appURL;

    static {
        EnvProperties properties = Helpers.getEnvProperties(System.getProperty("env", "test"));

        System.out.println("\n[Properties reading] ---------------------------------------------------------");

        appURL = properties.getAppUrl();
        System.out.println("app.url = " + appURL );

        Configuration.browser = properties.getBrowserName();
        System.out.println("browser = " + Configuration.browser );

        System.out.println("[Properties reading] ---------------------------------------------------------\n");
    }

    @Before
    public void openApp() {
        open(appURL);
    }

    @After
    public void clearData() {
        executeJavaScript("localStorage.clear();");
    }
}

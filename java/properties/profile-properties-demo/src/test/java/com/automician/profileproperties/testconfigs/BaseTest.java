package com.automician.profileproperties.testconfigs;

import com.codeborne.selenide.Configuration;
import org.junit.After;
import org.junit.Before;

import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.open;

public class BaseTest {

    public static String appURL;

    static {
        System.out.println("\n[Properties reading] ---------------------------------------------------------");

        appURL = System.getProperty("app.url");
        System.out.println("app.url = " + appURL );

        Configuration.browser = System.getProperty("browser");
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

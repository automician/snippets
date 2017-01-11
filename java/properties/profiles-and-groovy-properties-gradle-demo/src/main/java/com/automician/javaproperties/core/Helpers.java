package com.automician.javaproperties.core;

import com.codeborne.selenide.Configuration;
import groovy.util.ConfigObject;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;
import java.util.Properties;
import groovy.util.ConfigSlurper;

import static com.automician.javaproperties.core.CustomConditions.ajaxCompleted;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class Helpers {

    public static Properties getProperties() {

        Properties profileProperties = new Properties();
        String profile = System.getProperty("profile","test");

        try {
            profileProperties = new ConfigSlurper(profile).parse(new File("src/main/resources/config.groovy").toURI().toURL()).toProperties();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

            Properties systemProperties = System.getProperties();

            System.out.println("\n[Properties reading] ---------------------------------------------------------");

            for (Map.Entry entry : profileProperties.entrySet()) {
                String key = String.valueOf(entry.getKey());
                System.out.println(key + " = " + entry.getValue());
                if (systemProperties.containsKey(key)) {
                    String value = systemProperties.getProperty(key);

                    if (!value.isEmpty()) {
                        profileProperties.setProperty(key, value);
                        System.out.println(key + " = " + entry.getValue() + " !!! corrected");
                    }
                }
            }
            System.out.println("[Properties reading] ---------------------------------------------------------\n");

        return profileProperties;
    }

    public static void waitUntilAjaxComplete() {
        new WebDriverWait(getWebDriver(), Configuration.timeout / 1000).until(ajaxCompleted());
    }
}

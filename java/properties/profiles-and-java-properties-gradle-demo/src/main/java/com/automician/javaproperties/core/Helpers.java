package com.automician.javaproperties.core;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import static com.automician.javaproperties.core.CustomConditions.ajaxCompleted;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class Helpers {

    public static Properties getProperties() {
        Properties profileProperties = new Properties();

        try {
            profileProperties.load(
                    Helpers.class.getClassLoader().getResourceAsStream(
                                    System.getProperty("profile","test")+
                                    "/" + "config.properties"
                    )
            );

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

        } catch (IOException e) {
            System.out.println("Error : config.properties is not exist");
            e.printStackTrace();
        }
        return profileProperties;
    }

    public static void waitUntilAjaxComplete() {
        new WebDriverWait(getWebDriver(), Configuration.timeout / 1000).until(ajaxCompleted());
    }
}

package com.automician.javaproperties.core;


import com.automician.javaproperties.core.webdriverconfig.AppiumDriverProvider;
import com.codeborne.selenide.Configuration;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

public class PropertiesHelper {

  public static Properties read() {

    Properties profileProperties = new Properties();

    try {
      profileProperties.load(
              PropertiesHelper.class.getClassLoader().getResourceAsStream(
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

  public static void configureTestOn(String testOn, Properties properties) {

    if (testOn.equals("grid")) {
      //!!!to run node - use -Dwebdriver.firefox.marionette=false - for FireFox<=47.0.1 usage
      Configuration.remote = properties.getProperty("test.grid.url");
      Configuration.browser = properties.getProperty("test.grid.browser", "chrome");
      System.setProperty("capabilities.marionette", "false");
    } else if (testOn.equals("browserStack")) {
      Configuration.remote = "https://" + properties.getProperty("test.browserStack.userName") + ":" + properties.getProperty("test.browserStack.automateKey") + "@hub-cloud.browserstack.com/wd/hub";
      System.setProperty("capabilities.browserstack.debug", "true");
      PropertiesHelper.fillCapabilities("test.browserStack", properties);
    } else if (testOn.equals("sauceLabs")) {
      String remoteUrl = "https://" + properties.getProperty("test.sauceLabs.userName") + ":" + properties.getProperty("test.sauceLabs.automateKey") + "@ondemand.saucelabs.com:443/wd/hub";
      if (properties.getProperty("test.sauceLabs.capabilities.appiumVersion") != null) {
        AppiumDriverProvider.setCapabilities(getCapabilities("test.sauceLabs", properties));
        AppiumDriverProvider.setUrl(remoteUrl);
        Configuration.browser = AppiumDriverProvider.class.getName();
      } else {
        Configuration.browser = properties.getProperty("test.sauceLabs.browser");
        Configuration.remote = remoteUrl;
        PropertiesHelper.fillCapabilities("test.sauceLabs", properties);
      }
    } else {
      //by default = local
      Configuration.browser = properties.getProperty("test.local.browser", "chrome");
    }
  }

  public static void fillCapabilities(String capabilityPropertyPrefix, Properties properties) {
    System.out.println("\n[Capabilities filling] ---------------------------------------------------------");

    for (Map.Entry entry : properties.entrySet()) {
      String key = String.valueOf(entry.getKey());
      if (key.startsWith(capabilityPropertyPrefix + ".capabilities")) {
        String systemPropertyName = key.replaceFirst(capabilityPropertyPrefix + ".", "");
        String propertyValue = String.valueOf(entry.getValue());
        System.out.println(systemPropertyName + " = " + propertyValue);
        System.setProperty(systemPropertyName, propertyValue);
      }
    }
    System.out.println("\n[Capabilities filling] ---------------------------------------------------------");
  }

  public static DesiredCapabilities getCapabilities(String capabilityPropertyPrefix, Properties properties) {

    DesiredCapabilities capabilities = new DesiredCapabilities();

    System.out.println("\n[Capabilities getting] ---------------------------------------------------------");

    for (Map.Entry entry : properties.entrySet()) {
      String key = String.valueOf(entry.getKey());
      if (key.startsWith(capabilityPropertyPrefix + ".capabilities")) {
        String capabilityName = key.replaceFirst(capabilityPropertyPrefix + ".capabilities.", "");
        String propertyValue = String.valueOf(entry.getValue());
        System.out.println(capabilityName + " = " + propertyValue);
        capabilities.setCapability(capabilityName, propertyValue);
      }
    }
    System.out.println("\n[Capabilities getting] ---------------------------------------------------------");
    return capabilities;
  }

}

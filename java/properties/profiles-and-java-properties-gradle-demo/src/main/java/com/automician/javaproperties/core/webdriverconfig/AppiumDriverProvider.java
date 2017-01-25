package com.automician.javaproperties.core.webdriverconfig;

import com.automician.javaproperties.core.webdriverconfig.exceptions.WebDriverProviderInitialisationError;
import com.codeborne.selenide.WebDriverProvider;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class AppiumDriverProvider implements WebDriverProvider {

  private static DesiredCapabilities capabilities;
  private static String url;

  public static void setCapabilities(DesiredCapabilities capabilities) {
    AppiumDriverProvider.capabilities = capabilities;
  }

  public static void setUrl(String url) {
    AppiumDriverProvider.url = url;
  }

  @Override
  public WebDriver createDriver(DesiredCapabilities capabilities) {
    try {
      String platform = String.valueOf(AppiumDriverProvider.capabilities.getCapability("platformName"));
      if ("Android".equals(platform)) {
        return new AndroidDriver(new URL(url), AppiumDriverProvider.capabilities);
      } if ("iOS".equals(platform)) {
        return new IOSDriver<WebElement>(new URL(url), AppiumDriverProvider.capabilities);
      } else {
        throw new WebDriverProviderInitialisationError("Unknown platform "+platform);
      }
    } catch (MalformedURLException e) {
      throw new WebDriverProviderInitialisationError(e);
    }
  }
}

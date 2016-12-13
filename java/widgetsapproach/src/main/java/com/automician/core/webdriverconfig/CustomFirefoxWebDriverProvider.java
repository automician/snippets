package com.automician.core.webdriverconfig;

import com.codeborne.selenide.WebDriverProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class CustomFirefoxWebDriverProvider implements WebDriverProvider {
    /*
    * By default Selenium uses geckodriver for firefox
    * in geckodriver there are some problems (such as https://github.com/mozilla/geckodriver/issues/233)
    * it can be possible to use firefox driver (for Firefox v <= 47.0.1)
    * this class ensure this
    */
    @Override
    public WebDriver createDriver(DesiredCapabilities capabilities) {
        capabilities.setCapability("marionette", false);
        return new FirefoxDriver(capabilities);
    }

}

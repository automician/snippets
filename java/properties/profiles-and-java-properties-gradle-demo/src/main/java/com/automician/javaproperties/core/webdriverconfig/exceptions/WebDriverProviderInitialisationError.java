package com.automician.javaproperties.core.webdriverconfig.exceptions;

import org.openqa.selenium.WebDriverException;

public class WebDriverProviderInitialisationError extends WebDriverException {

  public WebDriverProviderInitialisationError(String message) {
    super(message);
  }

  public WebDriverProviderInitialisationError(Throwable cause) {
    super(cause);
  }

}

package com.automician.core.angular.wait.exceptions;

import org.openqa.selenium.WebDriverException;

public class ScriptNotFoundException extends WebDriverException {
    public ScriptNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

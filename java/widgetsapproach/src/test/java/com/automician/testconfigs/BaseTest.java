package com.automician.testconfigs;

import com.automician.widgets.App;
import com.codeborne.selenide.Screenshots;
import com.google.common.io.Files;
import org.junit.After;
import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.annotations.Step;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import static com.automician.core.Core.core;

public class BaseTest {

    private static App app;
    private static Properties properties;

    static {
        properties = core().properties().read();
        app = new App();
    }

    @After
    public void makeScreenshot() throws IOException {
        attachScreenshot();
    }

    @Attachment(type = "image/png")
    public byte[] attachScreenshot() throws IOException {
        File screenshot = Screenshots.takeScreenShotAsFile();
        return Files.toByteArray(screenshot);
    }

    /*
    * methods to access to widgets entry point
    * and good reporting and readability of tests
    */

    @Step
    public App APP() {
        return app;
    }

    @Step
    public App GIVEN() {
        return APP();
    }

    @Step
    public App WHEN() {
        return APP();
    }

    @Step
    public App THEN() {
        return APP();
    }

    @Step
    public App AND() {
        return APP();
    }

    @Step
    public App APP(String message) {
        return APP();
    }

    @Step
    public App GIVEN(String message) {
        return APP();
    }

    @Step
    public App WHEN(String message) {
        return APP();
    }

    @Step
    public App THEN(String message) {
        return APP();
    }

    @Step
    public App AND(String message) {
        return APP();
    }

}
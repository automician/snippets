package com.automician.core.angular.entities;

import com.automician.core.angular.wait.AngularWait;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.UIAssertionError;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.internal.Coordinates;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class AngularElement {

    SelenideElement element;

    public AngularElement(SelenideElement element) {
        this.element = element;
    }

    public void followLink() {
        AngularWait.waitForRequestsToFinish();
        this.element.followLink();
    }

    public AngularElement setValue(final String text) {
        AngularWait.waitForRequestsToFinish();
        this.element.setValue(text);
        return this;
    }

    public AngularElement val(String text) {
        return setValue(text);
    }

    public AngularElement append(final String text) {
        AngularWait.waitForRequestsToFinish();
        this.element.append(text);
        return this;
    }

    public AngularElement pressEnter() {
        sendKeys(Keys.ENTER);
        return this;
    }

    public AngularElement pressTab() {
        sendKeys(Keys.TAB);
        return this;
    }

    public AngularElement pressEscape() {
        sendKeys(Keys.ESCAPE);
        return this;
    }

    public String getText() {
        AngularWait.waitForRequestsToFinish();
        return this.element.getText();
    }

    public List<WebElement> findElements(final By by) {
        AngularWait.waitForRequestsToFinish();
        return this.element.findElements(by);
    }

    public WebElement findElement(final By by) {
        AngularWait.waitForRequestsToFinish();
        return this.element.findElement(by);
    }

    public String text() {
        AngularWait.waitForRequestsToFinish();
        return this.element.text();
    }

    public String innerText() {
        AngularWait.waitForRequestsToFinish();
        return this.element.innerText();
    }

    public String innerHtml() {
        AngularWait.waitForRequestsToFinish();
        return this.element.innerHtml();
    }

    public String attr(final String attributeName) {
        AngularWait.waitForRequestsToFinish();
        return this.element.attr(attributeName);
    }

    public String name() {
        AngularWait.waitForRequestsToFinish();
        return this.element.name();
    }

    public String val() {
        AngularWait.waitForRequestsToFinish();
        return this.element.val();
    }

    public String getValue() {
        return val();
    }

    public AngularElement selectRadio(final String value) {
        AngularWait.waitForRequestsToFinish();
        this.element.selectRadio(value);
        return this;
    }

    public String data(final String dataAttributeName) {
        AngularWait.waitForRequestsToFinish();
        return this.element.data(dataAttributeName);
    }

    public boolean exists() {
        AngularWait.waitForRequestsToFinish();
        return this.element.exists();
    }

    public boolean isDisplayed() {
        AngularWait.waitForRequestsToFinish();
        return this.element.isDisplayed();
    }

    public Point getLocation() {
        AngularWait.waitForRequestsToFinish();
        return this.element.getLocation();
    }

    public Dimension getSize() {
        AngularWait.waitForRequestsToFinish();
        return this.element.getSize();
    }

    public Rectangle getRect() {
        AngularWait.waitForRequestsToFinish();
        return this.element.getRect();
    }

    public String getCssValue(final String s) {
        AngularWait.waitForRequestsToFinish();
        return this.element.getCssValue(s);
    }

    public boolean is(final Condition condition) {
        AngularWait.waitForRequestsToFinish();
        return this.element.is(condition);
    }

    public boolean has(final Condition condition) {
        return is(condition);
    }

    public AngularElement setSelected(final boolean selected) {
        AngularWait.waitForRequestsToFinish();
        this.element.setSelected(selected);
        return this;
    }

    public AngularElement should(final Condition... condition) {
        AngularWait.waitForRequestsToFinish();
        this.element.should(condition);
        return this;
    }

    public AngularElement shouldHave(Condition... condition) {
        return should(condition);
    }

    public AngularElement shouldBe(Condition... condition) {
        return should(condition);
    }

    public boolean waitingIs(Condition condition) {
        try {
            shouldBe(condition);
            return true;
        } catch (UIAssertionError e) {
            return false;
        }
    }

    public AngularElement shouldNot(final Condition... condition) {
        AngularWait.waitForRequestsToFinish();
        this.element.shouldNot(condition);
        return this;
    }

    public AngularElement shouldNotHave(Condition... condition) {
        return shouldNot(condition);
    }

    public AngularElement shouldNotBe(Condition... condition) {
        return shouldNot(condition);
    }

    public AngularElement waitUntil(final Condition condition, final long timeoutMilliseconds) {
        AngularWait.waitForRequestsToFinish();
        this.element.waitUntil(condition, timeoutMilliseconds);
        return this;
    }

    public AngularElement waitUntil(final Condition condition, final long timeoutMilliseconds, final long pollingIntervalMilliseconds) {
        AngularWait.waitForRequestsToFinish();
        this.element.waitUntil(condition, timeoutMilliseconds, pollingIntervalMilliseconds);
        return this;
    }

    public AngularElement waitWhile(final Condition condition, final long timeoutMilliseconds) {
        AngularWait.waitForRequestsToFinish();
        this.element.waitWhile(condition, timeoutMilliseconds);
        return this;
    }

    public AngularElement waitWhile(final Condition condition, final long timeoutMilliseconds, final long pollingIntervalMilliseconds) {
        AngularWait.waitForRequestsToFinish();
        this.element.waitWhile(condition, timeoutMilliseconds, pollingIntervalMilliseconds);
        return this;
    }

    public AngularElement parent() {
        //AngularWait.waitForRequestsToFinish();
        return new AngularElement(this.element.parent());
    }

    public AngularElement closest(final String tagOrClass) {
        //AngularWait.waitForRequestsToFinish();
        return new AngularElement(this.element.closest(tagOrClass));
    }

    public AngularElement find(final String cssSelector) {
        //AngularWait.waitForRequestsToFinish();
        return new AngularElement(this.element.find(cssSelector));
    }

    public AngularElement find(final String cssSelector, final int index) {
        //AngularWait.waitForRequestsToFinish();
        return new AngularElement(this.element.find(cssSelector, index));
    }

    public AngularElement find(final By selector) {
        //AngularWait.waitForRequestsToFinish();
        return new AngularElement(this.element.find(selector));
    }

    public AngularElement find(final By selector, final int index) {
        //AngularWait.waitForRequestsToFinish();
        return new AngularElement(this.element.find(selector, index));
    }

    public AngularElement $(final String cssSelector) {
        //AngularWait.waitForRequestsToFinish();
        return new AngularElement(this.element.$(cssSelector));
    }

    public AngularElement $(final String cssSelector, final int index) {
        //AngularWait.waitForRequestsToFinish();
        return new AngularElement(this.element.$(cssSelector, index));
    }

    public AngularElement $(final By selector) {
        //AngularWait.waitForRequestsToFinish();
        return new AngularElement(this.element.$(selector));
    }

    public AngularElement $(final By selector, final int index) {
        //AngularWait.waitForRequestsToFinish();
        return new AngularElement(this.element.$(selector, index));
    }

    public AngularCollection findAll(final String cssSelector) {
        //AngularWait.waitForRequestsToFinish();
        return new AngularCollection(this.element.findAll(cssSelector));
    }

    public AngularCollection findAll(final By selector) {
        //AngularWait.waitForRequestsToFinish();
        return new AngularCollection(this.element.findAll(selector));
    }

    public AngularCollection $$(final String cssSelector) {
        //AngularWait.waitForRequestsToFinish();
        return new AngularCollection(this.element.$$(cssSelector));
    }

    public AngularCollection $$(final By selector) {
        //AngularWait.waitForRequestsToFinish();
        return new AngularCollection(this.element.$$(selector));
    }

    public File uploadFromClasspath(final String... fileName) {
        AngularWait.waitForRequestsToFinish();
        return this.element.uploadFromClasspath(fileName);
    }

    public File uploadFile(final File... file) {
        AngularWait.waitForRequestsToFinish();
        return this.element.uploadFile(file);
    }

    public void selectOption(final int... index) {
        AngularWait.waitForRequestsToFinish();
        this.element.selectOption(index);
    }

    public void selectOption(final String... text) {
        AngularWait.waitForRequestsToFinish();
        this.element.selectOption(text);
    }

    public void selectOptionByValue(final String... value) {
        AngularWait.waitForRequestsToFinish();
        this.element.selectOption(value);
    }

    public AngularElement getSelectedOption() throws NoSuchElementException {
        AngularWait.waitForRequestsToFinish();
        return new AngularElement(this.element.getSelectedOption());
    }

    public AngularCollection getSelectedOptions() {
        AngularWait.waitForRequestsToFinish();
        return new AngularCollection(this.element.getSelectedOptions());
    }

    public String getSelectedValue() {
        AngularWait.waitForRequestsToFinish();
        return this.element.getSelectedValue();
    }

    public String getSelectedText() {
        AngularWait.waitForRequestsToFinish();
        return this.element.getSelectedText();
    }

    public AngularElement scrollTo() {
        AngularWait.waitForRequestsToFinish();
        this.element.scrollTo();
        return this;
    }

    public File download() throws FileNotFoundException {
        AngularWait.waitForRequestsToFinish();
        return this.element.download();
    }

    public WebElement toWebElement() {
        AngularWait.waitForRequestsToFinish();
        return this.element.toWebElement();
    }

    public WebElement getWrappedElement() {
        AngularWait.waitForRequestsToFinish();
        return this.element.getWrappedElement();
    }

    public void click() {
        AngularWait.waitForRequestsToFinish();
        this.element.click();
    }

    public void submit() {
        AngularWait.waitForRequestsToFinish();
        this.element.submit();
    }

    public void sendKeys(final CharSequence... charSequences) {
        AngularWait.waitForRequestsToFinish();
        this.element.sendKeys(charSequences);
    }

    public void clear() {
        AngularWait.waitForRequestsToFinish();
        this.element.clear();
    }

    public String getTagName() {
        AngularWait.waitForRequestsToFinish();
        return this.element.getTagName();
    }

    public String getAttribute(final String s) {
        AngularWait.waitForRequestsToFinish();
        return this.element.getAttribute(s);
    }

    public boolean isSelected() {
        AngularWait.waitForRequestsToFinish();
        return this.element.isSelected();
    }

    public boolean isEnabled() {
        AngularWait.waitForRequestsToFinish();
        return this.element.isEnabled();
    }

    public AngularElement contextClick() {
        AngularWait.waitForRequestsToFinish();
        this.element.contextClick();
        return this;
    }

    public AngularElement doubleClick() {
        AngularWait.waitForRequestsToFinish();
        this.element.doubleClick();
        return this;
    }

    public AngularElement hover() {
        AngularWait.waitForRequestsToFinish();
        this.element.hover();
        return this;
    }

    public AngularElement dragAndDropTo(final String targetCssSelector) {
        AngularWait.waitForRequestsToFinish();
        this.element.dragAndDropTo(targetCssSelector);
        return this;
    }

    public AngularElement dragAndDropTo(final WebElement target) {
        AngularWait.waitForRequestsToFinish();
        this.element.dragAndDropTo(target);
        return this;
    }

    public boolean isImage() {
        AngularWait.waitForRequestsToFinish();
        return this.element.isImage();
    }

    public File screenshot() {
        AngularWait.waitForRequestsToFinish();
        return this.element.screenshot();
    }

    public BufferedImage screenshotAsImage() {
        AngularWait.waitForRequestsToFinish();
        return this.element.screenshotAsImage();
    }

    public <X> X getScreenshotAs(final OutputType<X> outputType) throws WebDriverException {
        AngularWait.waitForRequestsToFinish();
        return this.element.getScreenshotAs(outputType);
    }

    public WebElement findElementByClassName(final String s) {
        AngularWait.waitForRequestsToFinish();
        return this.element.findElementByClassName(s);
    }

    public List<WebElement> findElementsByClassName(final String s) {
        AngularWait.waitForRequestsToFinish();
        return this.element.findElementsByClassName(s);
    }

    public WebElement findElementByCssSelector(final String s) {
        AngularWait.waitForRequestsToFinish();
        return this.element.findElementByCssSelector(s);
    }

    public List<WebElement> findElementsByCssSelector(final String s) {
        AngularWait.waitForRequestsToFinish();
        return this.element.findElementsByCssSelector(s);
    }

    public WebElement findElementById(final String s) {
        AngularWait.waitForRequestsToFinish();
        return this.element.findElementById(s);
    }

    public List<WebElement> findElementsById(final String s) {
        AngularWait.waitForRequestsToFinish();
        return this.element.findElementsById(s);
    }

    public WebElement findElementByLinkText(final String s) {
        AngularWait.waitForRequestsToFinish();
        return this.element.findElementByLinkText(s);
    }

    public List<WebElement> findElementsByLinkText(final String s) {
        AngularWait.waitForRequestsToFinish();
        return this.element.findElementsByLinkText(s);
    }

    public WebElement findElementByPartialLinkText(final String s) {
        AngularWait.waitForRequestsToFinish();
        return this.element.findElementByPartialLinkText(s);
    }

    public List<WebElement> findElementsByPartialLinkText(final String s) {
        AngularWait.waitForRequestsToFinish();
        return this.element.findElementsByPartialLinkText(s);
    }

    public WebElement findElementByName(final String s) {
        AngularWait.waitForRequestsToFinish();
        return this.element.findElementByName(s);
    }

    public List<WebElement> findElementsByName(final String s) {
        AngularWait.waitForRequestsToFinish();
        return this.element.findElementsByName(s);
    }

    public WebElement findElementByTagName(final String s) {
        AngularWait.waitForRequestsToFinish();
        return this.element.findElementByTagName(s);
    }

    public List<WebElement> findElementsByTagName(final String s) {
        AngularWait.waitForRequestsToFinish();
        return this.element.findElementsByTagName(s);
    }

    public WebElement findElementByXPath(final String s) {
        AngularWait.waitForRequestsToFinish();
        return this.element.findElementByXPath(s);
    }

    public List<WebElement> findElementsByXPath(final String s) {
        AngularWait.waitForRequestsToFinish();
        return this.element.findElementsByXPath(s);
    }

    public Coordinates getCoordinates() {
        AngularWait.waitForRequestsToFinish();
        return this.element.getCoordinates();
    }

    public WebDriver getWrappedDriver() {
        AngularWait.waitForRequestsToFinish();
        return this.element.getWrappedDriver();
    }

    //scrollTo works not always
    //scrollWithOffset(0, -150); - this variant doesn't help in situations when something is shown under the header
    public AngularElement scrollWithOffset(int x, int y) {
        shouldBe(visible);

        String code = "window.scroll(" + (this.element.getLocation().x + x) + "," + (this.element.getLocation().y + y) + ");";
        ((JavascriptExecutor) getWebDriver()).executeScript(code, element, x, y);

        return this;
    }

}

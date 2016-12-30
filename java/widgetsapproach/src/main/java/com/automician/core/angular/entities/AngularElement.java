package com.automician.core.angular.entities;

import com.automician.core.angular.wait.AngularWait;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
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

public class AngularElement implements SelenideElement {

    SelenideElement element;

    public AngularElement(SelenideElement element) {
        this.element = element;
    }

    @Override
    public void followLink() {
        AngularWait.waitForRequestsToFinish();
        this.element.followLink();
    }

    @Override
    public SelenideElement setValue(final String text) {
        AngularWait.waitForRequestsToFinish();
        this.element.setValue(text);
        return this;
    }

    @Override
    public SelenideElement val(String text) {
        return setValue(text);
    }

    @Override
    public SelenideElement append(final String text) {
        AngularWait.waitForRequestsToFinish();
        this.element.append(text);
        return this;
    }

    @Override
    public SelenideElement pressEnter() {
        sendKeys(Keys.ENTER);
        return this;
    }

    @Override
    public SelenideElement pressTab() {
        sendKeys(Keys.TAB);
        return this;
    }

    @Override
    public SelenideElement pressEscape() {
        sendKeys(Keys.ESCAPE);
        return this;
    }

    @Override
    public String getText() {
        AngularWait.waitForRequestsToFinish();
        return this.element.getText();
    }

    @Override
    public List<WebElement> findElements(final By by) {
        AngularWait.waitForRequestsToFinish();
        return this.element.findElements(by);
    }

    @Override
    public WebElement findElement(final By by) {
        AngularWait.waitForRequestsToFinish();
        return this.element.findElement(by);
    }

    @Override
    public String text() {
        AngularWait.waitForRequestsToFinish();
        return this.element.text();
    }

    @Override
    public String innerText() {
        AngularWait.waitForRequestsToFinish();
        return this.element.innerText();
    }

    @Override
    public String innerHtml() {
        AngularWait.waitForRequestsToFinish();
        return this.element.innerHtml();
    }

    @Override
    public String attr(final String attributeName) {
        AngularWait.waitForRequestsToFinish();
        return this.element.attr(attributeName);
    }

    @Override
    public String name() {
        AngularWait.waitForRequestsToFinish();
        return this.element.name();
    }

    @Override
    public String val() {
        AngularWait.waitForRequestsToFinish();
        return this.element.val();
    }

    @Override
    public String getValue() {
        return val();
    }

    @Override
    public SelenideElement selectRadio(final String value) {
        AngularWait.waitForRequestsToFinish();
        this.element.selectRadio(value);
        return this;
    }

    @Override
    public String data(final String dataAttributeName) {
        AngularWait.waitForRequestsToFinish();
        return this.element.data(dataAttributeName);
    }

    @Override
    public boolean exists() {
        AngularWait.waitForRequestsToFinish();
        return this.element.exists();
    }

    @Override
    public boolean isDisplayed() {
        AngularWait.waitForRequestsToFinish();
        return this.element.isDisplayed();
    }

    @Override
    public Point getLocation() {
        AngularWait.waitForRequestsToFinish();
        return this.element.getLocation();
    }

    @Override
    public Dimension getSize() {
        AngularWait.waitForRequestsToFinish();
        return this.element.getSize();
    }

    @Override
    public Rectangle getRect() {
        AngularWait.waitForRequestsToFinish();
        return this.element.getRect();
    }

    @Override
    public String getCssValue(final String s) {
        AngularWait.waitForRequestsToFinish();
        return this.element.getCssValue(s);
    }

    @Override
    public boolean is(final Condition condition) {
        AngularWait.waitForRequestsToFinish();
        return this.element.is(condition);
    }

    @Override
    public boolean has(final Condition condition) {
        return is(condition);
    }

    @Override
    public SelenideElement setSelected(final boolean selected) {
        AngularWait.waitForRequestsToFinish();
        this.element.setSelected(selected);
        return this;
    }

    @Override
    public SelenideElement should(final Condition... condition) {
        AngularWait.waitForRequestsToFinish();
        this.element.should(condition);
        return this;
    }

    @Override
    public SelenideElement shouldHave(Condition... condition) {
        return should(condition);
    }

    @Override
    public SelenideElement shouldBe(Condition... condition) {
        return should(condition);
    }

    @Override
    public SelenideElement shouldNot(final Condition... condition) {
        AngularWait.waitForRequestsToFinish();
        this.element.shouldNot(condition);
        return this;
    }

    @Override
    public SelenideElement shouldNotHave(Condition... condition) {
        return shouldNot(condition);
    }

    @Override
    public SelenideElement shouldNotBe(Condition... condition) {
        return shouldNot(condition);
    }

    @Override
    public SelenideElement waitUntil(final Condition condition, final long timeoutMilliseconds) {
        AngularWait.waitForRequestsToFinish();
        this.element.waitUntil(condition, timeoutMilliseconds);
        return this;
    }

    @Override
    public SelenideElement waitUntil(final Condition condition, final long timeoutMilliseconds, final long pollingIntervalMilliseconds) {
        AngularWait.waitForRequestsToFinish();
        this.element.waitUntil(condition, timeoutMilliseconds, pollingIntervalMilliseconds);
        return this;
    }

    @Override
    public SelenideElement waitWhile(final Condition condition, final long timeoutMilliseconds) {
        AngularWait.waitForRequestsToFinish();
        this.element.waitWhile(condition, timeoutMilliseconds);
        return this;
    }

    @Override
    public SelenideElement waitWhile(final Condition condition, final long timeoutMilliseconds, final long pollingIntervalMilliseconds) {
        AngularWait.waitForRequestsToFinish();
        this.element.waitWhile(condition, timeoutMilliseconds, pollingIntervalMilliseconds);
        return this;
    }

    @Override
    public SelenideElement parent() {
        return new AngularElement(this.element.parent());
    }

    @Override
    public SelenideElement closest(final String tagOrClass) {
        return new AngularElement(this.element.closest(tagOrClass));
    }

    @Override
    public SelenideElement find(final String cssSelector) {
        return new AngularElement(this.element.find(cssSelector));
    }

    @Override
    public SelenideElement find(final String cssSelector, final int index) {
        return new AngularElement(this.element.find(cssSelector, index));
    }

    @Override
    public SelenideElement find(final By selector) {
        return new AngularElement(this.element.find(selector));
    }

    @Override
    public SelenideElement find(final By selector, final int index) {
        return new AngularElement(this.element.find(selector, index));
    }

    @Override
    public SelenideElement $(final String cssSelector) {
        return new AngularElement(this.element.$(cssSelector));
    }

    @Override
    public SelenideElement $(final String cssSelector, final int index) {
        return new AngularElement(this.element.$(cssSelector, index));
    }

    @Override
    public SelenideElement $(final By selector) {
        return new AngularElement(this.element.$(selector));
    }

    @Override
    public SelenideElement $(final By selector, final int index) {
        return new AngularElement(this.element.$(selector, index));
    }

    @Override
    public ElementsCollection findAll(final String cssSelector) {
        return new AngularCollection(this.element.findAll(cssSelector));
    }

    @Override
    public ElementsCollection findAll(final By selector) {
        return new AngularCollection(this.element.findAll(selector));
    }

    @Override
    public ElementsCollection $$(final String cssSelector) {
        return new AngularCollection(this.element.$$(cssSelector));
    }

    @Override
    public ElementsCollection $$(final By selector) {
        return new AngularCollection(this.element.$$(selector));
    }

    @Override
    public File uploadFromClasspath(final String... fileName) {
        AngularWait.waitForRequestsToFinish();
        return this.element.uploadFromClasspath(fileName);
    }

    @Override
    public File uploadFile(final File... file) {
        AngularWait.waitForRequestsToFinish();
        return this.element.uploadFile(file);
    }

    @Override
    public void selectOption(final int... index) {
        AngularWait.waitForRequestsToFinish();
        this.element.selectOption(index);
    }

    @Override
    public void selectOption(final String... text) {
        AngularWait.waitForRequestsToFinish();
        this.element.selectOption(text);
    }

    @Override
    public void selectOptionByValue(final String... value) {
        AngularWait.waitForRequestsToFinish();
        this.element.selectOption(value);
    }

    @Override
    public SelenideElement getSelectedOption() throws NoSuchElementException {
        AngularWait.waitForRequestsToFinish();
        return new AngularElement(this.element.getSelectedOption());
    }

    @Override
    public ElementsCollection getSelectedOptions() {
        AngularWait.waitForRequestsToFinish();
        return new AngularCollection(this.element.getSelectedOptions());
    }

    @Override
    public String getSelectedValue() {
        AngularWait.waitForRequestsToFinish();
        return this.element.getSelectedValue();
    }

    @Override
    public String getSelectedText() {
        AngularWait.waitForRequestsToFinish();
        return this.element.getSelectedText();
    }

    @Override
    public SelenideElement scrollTo() {
        AngularWait.waitForRequestsToFinish();
        this.element.scrollTo();
        return this;
    }

    @Override
    public File download() throws FileNotFoundException {
        AngularWait.waitForRequestsToFinish();
        return this.element.download();
    }

    @Override
    public WebElement toWebElement() {
        AngularWait.waitForRequestsToFinish();
        return this.element.toWebElement();
    }

    @Override
    public WebElement getWrappedElement() {
        AngularWait.waitForRequestsToFinish();
        return this.element.getWrappedElement();
    }

    @Override
    public void click() {
        AngularWait.waitForRequestsToFinish();
        this.element.click();
    }

    @Override
    public void submit() {
        AngularWait.waitForRequestsToFinish();
        this.element.submit();
    }

    @Override
    public void sendKeys(final CharSequence... charSequences) {
        AngularWait.waitForRequestsToFinish();
        this.element.sendKeys(charSequences);
    }

    @Override
    public void clear() {
        AngularWait.waitForRequestsToFinish();
        this.element.clear();
    }

    @Override
    public String getTagName() {
        AngularWait.waitForRequestsToFinish();
        return this.element.getTagName();
    }

    @Override
    public String getAttribute(final String s) {
        AngularWait.waitForRequestsToFinish();
        return this.element.getAttribute(s);
    }

    @Override
    public boolean isSelected() {
        AngularWait.waitForRequestsToFinish();
        return this.element.isSelected();
    }

    @Override
    public boolean isEnabled() {
        AngularWait.waitForRequestsToFinish();
        return this.element.isEnabled();
    }

    @Override
    public SelenideElement contextClick() {
        AngularWait.waitForRequestsToFinish();
        this.element.contextClick();
        return this;
    }

    @Override
    public SelenideElement doubleClick() {
        AngularWait.waitForRequestsToFinish();
        this.element.doubleClick();
        return this;
    }

    @Override
    public SelenideElement hover() {
        AngularWait.waitForRequestsToFinish();
        this.element.hover();
        return this;
    }

    @Override
    public SelenideElement dragAndDropTo(final String targetCssSelector) {
        AngularWait.waitForRequestsToFinish();
        this.element.dragAndDropTo(targetCssSelector);
        return this;
    }

    @Override
    public SelenideElement dragAndDropTo(final WebElement target) {
        AngularWait.waitForRequestsToFinish();
        this.element.dragAndDropTo(target);
        return this;
    }

    @Override
    public boolean isImage() {
        AngularWait.waitForRequestsToFinish();
        return this.element.isImage();
    }

    @Override
    public File screenshot() {
        AngularWait.waitForRequestsToFinish();
        return this.element.screenshot();
    }

    @Override
    public BufferedImage screenshotAsImage() {
        AngularWait.waitForRequestsToFinish();
        return this.element.screenshotAsImage();
    }

    @Override
    public <X> X getScreenshotAs(final OutputType<X> outputType) throws WebDriverException {
        AngularWait.waitForRequestsToFinish();
        return this.element.getScreenshotAs(outputType);
    }

    @Override
    public WebElement findElementByClassName(final String s) {
        AngularWait.waitForRequestsToFinish();
        return this.element.findElementByClassName(s);
    }

    @Override
    public List<WebElement> findElementsByClassName(final String s) {
        AngularWait.waitForRequestsToFinish();
        return this.element.findElementsByClassName(s);
    }

    @Override
    public WebElement findElementByCssSelector(final String s) {
        AngularWait.waitForRequestsToFinish();
        return this.element.findElementByCssSelector(s);
    }

    @Override
    public List<WebElement> findElementsByCssSelector(final String s) {
        AngularWait.waitForRequestsToFinish();
        return this.element.findElementsByCssSelector(s);
    }

    @Override
    public WebElement findElementById(final String s) {
        AngularWait.waitForRequestsToFinish();
        return this.element.findElementById(s);
    }

    @Override
    public List<WebElement> findElementsById(final String s) {
        AngularWait.waitForRequestsToFinish();
        return this.element.findElementsById(s);
    }

    @Override
    public WebElement findElementByLinkText(final String s) {
        AngularWait.waitForRequestsToFinish();
        return this.element.findElementByLinkText(s);
    }

    @Override
    public List<WebElement> findElementsByLinkText(final String s) {
        AngularWait.waitForRequestsToFinish();
        return this.element.findElementsByLinkText(s);
    }

    @Override
    public WebElement findElementByPartialLinkText(final String s) {
        AngularWait.waitForRequestsToFinish();
        return this.element.findElementByPartialLinkText(s);
    }

    @Override
    public List<WebElement> findElementsByPartialLinkText(final String s) {
        AngularWait.waitForRequestsToFinish();
        return this.element.findElementsByPartialLinkText(s);
    }

    @Override
    public WebElement findElementByName(final String s) {
        AngularWait.waitForRequestsToFinish();
        return this.element.findElementByName(s);
    }

    @Override
    public List<WebElement> findElementsByName(final String s) {
        AngularWait.waitForRequestsToFinish();
        return this.element.findElementsByName(s);
    }

    @Override
    public WebElement findElementByTagName(final String s) {
        AngularWait.waitForRequestsToFinish();
        return this.element.findElementByTagName(s);
    }

    @Override
    public List<WebElement> findElementsByTagName(final String s) {
        AngularWait.waitForRequestsToFinish();
        return this.element.findElementsByTagName(s);
    }

    @Override
    public WebElement findElementByXPath(final String s) {
        AngularWait.waitForRequestsToFinish();
        return this.element.findElementByXPath(s);
    }

    @Override
    public List<WebElement> findElementsByXPath(final String s) {
        AngularWait.waitForRequestsToFinish();
        return this.element.findElementsByXPath(s);
    }

    @Override
    public Coordinates getCoordinates() {
        AngularWait.waitForRequestsToFinish();
        return this.element.getCoordinates();
    }

    @Override
    public WebDriver getWrappedDriver() {
        AngularWait.waitForRequestsToFinish();
        return this.element.getWrappedDriver();
    }

}

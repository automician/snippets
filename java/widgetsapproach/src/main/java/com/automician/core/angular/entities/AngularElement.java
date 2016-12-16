package com.automician.core.angular.entities;

import com.automician.core.angular.wait.AngularWait;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.internal.Coordinates;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;


public class AngularElement {

    SelenideElement self;

    public AngularElement(SelenideElement element) {
        this.self = element;
    }


    public void followLink() {
        AngularWait.forRequestsToFinish();
        self.followLink();
    }

    public AngularElement setValue(final String text) {
        AngularWait.forRequestsToFinish();
        self.setValue(text);
        return this;
    }

    public AngularElement val(String text) {
        return setValue(text);
    }


    public AngularElement append(final String text) {
        AngularWait.forRequestsToFinish();
        self.append(text);
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
        AngularWait.forRequestsToFinish();
        return self.getText();
    }


    public List<WebElement> findElements(final By by) {
        AngularWait.forRequestsToFinish();
        return self.findElements(by);
    }


    public WebElement findElement(final By by) {
        AngularWait.forRequestsToFinish();
        return self.findElement(by);
    }


    public String text() {
        AngularWait.forRequestsToFinish();
        return self.text();
    }


    public String innerText() {
        AngularWait.forRequestsToFinish();
        return self.innerText();
    }


    public String innerHtml() {
        AngularWait.forRequestsToFinish();
        return self.innerHtml();
    }


    public String attr(final String attributeName) {
        AngularWait.forRequestsToFinish();
        return self.attr(attributeName);
    }


    public String name() {
        AngularWait.forRequestsToFinish();
        return self.name();
    }


    public String val() {
        AngularWait.forRequestsToFinish();
        return self.val();
    }


    public String getValue() {
        return val();
    }


    public AngularElement selectRadio(final String value) {
        AngularWait.forRequestsToFinish();
        self.selectRadio(value);
        return this;
    }


    public String data(final String dataAttributeName) {
        AngularWait.forRequestsToFinish();
        return self.data(dataAttributeName);
    }


    public boolean exists() {
        AngularWait.forRequestsToFinish();
        return self.exists();
    }


    public boolean isDisplayed() {
        AngularWait.forRequestsToFinish();
        return self.isDisplayed();
    }


    public Point getLocation() {
        AngularWait.forRequestsToFinish();
        return self.getLocation();
    }


    public Dimension getSize() {
        AngularWait.forRequestsToFinish();
        return self.getSize();
    }


    public Rectangle getRect() {
        AngularWait.forRequestsToFinish();
        return self.getRect();
    }


    public String getCssValue(final String s) {
        AngularWait.forRequestsToFinish();
        return self.getCssValue(s);
    }


    public boolean is(final Condition condition) {
        AngularWait.forRequestsToFinish();
        return self.is(condition);
    }


    public boolean has(final Condition condition) {
        return is(condition);
    }


    public AngularElement setSelected(final boolean selected) {
        AngularWait.forRequestsToFinish();
        self.setSelected(selected);
        return this;
    }


    public AngularElement should(final Condition... condition) {
        AngularWait.forRequestsToFinish();
        self.should(condition);
        return this;
    }


    public AngularElement shouldHave(Condition... condition) {
        return should(condition);
    }


    public AngularElement shouldBe(Condition... condition) {
        return should(condition);
    }


    public AngularElement shouldNot(final Condition... condition) {
        AngularWait.forRequestsToFinish();
        self.shouldNot(condition);
        return this;
    }


    public AngularElement shouldNotHave(Condition... condition) {
        return shouldNot(condition);
    }


    public AngularElement shouldNotBe(Condition... condition) {
        return shouldNot(condition);
    }


    public AngularElement waitUntil(final Condition condition, final long timeoutMilliseconds) {
        AngularWait.forRequestsToFinish();
        self.waitUntil(condition, timeoutMilliseconds);
        return this;
    }


    public AngularElement waitUntil(final Condition condition, final long timeoutMilliseconds, final long pollingIntervalMilliseconds) {
        AngularWait.forRequestsToFinish();
        self.waitUntil(condition, timeoutMilliseconds, pollingIntervalMilliseconds);
        return this;
    }


    public AngularElement waitWhile(final Condition condition, final long timeoutMilliseconds) {
        AngularWait.forRequestsToFinish();
        self.waitWhile(condition, timeoutMilliseconds);
        return this;
    }


    public AngularElement waitWhile(final Condition condition, final long timeoutMilliseconds, final long pollingIntervalMilliseconds) {
        AngularWait.forRequestsToFinish();
        self.waitWhile(condition, timeoutMilliseconds, pollingIntervalMilliseconds);
        return this;
    }


    public AngularElement parent() {
        AngularWait.forRequestsToFinish();
        return new AngularElement(self.parent());
    }


    public AngularElement closest(final String tagOrClass) {
        AngularWait.forRequestsToFinish();
        return new AngularElement(self.closest(tagOrClass));
    }


    public AngularElement find(final String cssSelector) {
        AngularWait.forRequestsToFinish();
        return new AngularElement(self.find(cssSelector));
    }


    public AngularElement find(final String cssSelector, final int index) {
        AngularWait.forRequestsToFinish();
        return new AngularElement(self.find(cssSelector, index));
    }


    public AngularElement find(final By selector) {
        AngularWait.forRequestsToFinish();
        return new AngularElement(self.find(selector));
    }


    public AngularElement find(final By selector, final int index) {
        AngularWait.forRequestsToFinish();
        return new AngularElement(self.find(selector, index));
    }


    public AngularElement $(final String cssSelector) {
        AngularWait.forRequestsToFinish();
        return new AngularElement(self.$(cssSelector));
    }


    public AngularElement $(final String cssSelector, final int index) {
        AngularWait.forRequestsToFinish();
        return new AngularElement(self.$(cssSelector, index));
    }


    public AngularElement $(final By selector) {
        AngularWait.forRequestsToFinish();
        return new AngularElement(self.$(selector));
    }


    public AngularElement $(final By selector, final int index) {
        AngularWait.forRequestsToFinish();
        return new AngularElement(self.$(selector, index));
    }


    public AngularCollection findAll(final String cssSelector) {
        AngularWait.forRequestsToFinish();
        return new AngularCollection(self.findAll(cssSelector));
    }


    public AngularCollection findAll(final By selector) {
        AngularWait.forRequestsToFinish();
        return new AngularCollection(self.findAll(selector));
    }


    public AngularCollection $$(final String cssSelector) {
        AngularWait.forRequestsToFinish();
        return new AngularCollection(self.$$(cssSelector));
    }


    public AngularCollection $$(final By selector) {
        AngularWait.forRequestsToFinish();
        return new AngularCollection(self.$$(selector));
    }


    public File uploadFromClasspath(final String... fileName) {
        AngularWait.forRequestsToFinish();
        return self.uploadFromClasspath(fileName);
    }


    public File uploadFile(final File... file) {
        AngularWait.forRequestsToFinish();
        return self.uploadFile(file);
    }


    public void selectOption(final int... index) {
        AngularWait.forRequestsToFinish();
        self.selectOption(index);
    }


    public void selectOption(final String... text) {
        AngularWait.forRequestsToFinish();
        self.selectOption(text);
    }


    public void selectOptionByValue(final String... value) {
        AngularWait.forRequestsToFinish();
        self.selectOption(value);
    }


    public AngularElement getSelectedOption() throws NoSuchElementException {
        AngularWait.forRequestsToFinish();
        return new AngularElement(self.getSelectedOption());
    }


    public AngularCollection getSelectedOptions() {
        AngularWait.forRequestsToFinish();
        return new AngularCollection(self.getSelectedOptions());
    }


    public String getSelectedValue() {
        AngularWait.forRequestsToFinish();
        return self.getSelectedValue();
    }


    public String getSelectedText() {
        AngularWait.forRequestsToFinish();
        return self.getSelectedText();
    }


    public AngularElement scrollTo() {
        AngularWait.forRequestsToFinish();
        self.scrollTo();
        return this;
    }


    public File download() throws FileNotFoundException {
        AngularWait.forRequestsToFinish();
        return self.download();
    }


    public WebElement toWebElement() {
        AngularWait.forRequestsToFinish();
        return self.toWebElement();
    }


    public WebElement getWrappedElement() {
        AngularWait.forRequestsToFinish();
        return self.getWrappedElement();
    }


    public void click() {
        AngularWait.forRequestsToFinish();
        self.click();
    }


    public void submit() {
        AngularWait.forRequestsToFinish();
        self.submit();
    }


    public void sendKeys(final CharSequence... charSequences) {
        AngularWait.forRequestsToFinish();
        self.sendKeys(charSequences);
    }


    public void clear() {
        AngularWait.forRequestsToFinish();
        self.clear();
    }


    public String getTagName() {
        AngularWait.forRequestsToFinish();
        return self.getTagName();
    }


    public String getAttribute(final String s) {
        AngularWait.forRequestsToFinish();
        return self.getAttribute(s);
    }


    public boolean isSelected() {
        AngularWait.forRequestsToFinish();
        return self.isSelected();
    }


    public boolean isEnabled() {
        AngularWait.forRequestsToFinish();
        return self.isEnabled();
    }


    public AngularElement contextClick() {
        AngularWait.forRequestsToFinish();
        self.contextClick();
        return this;
    }


    public AngularElement doubleClick() {
        AngularWait.forRequestsToFinish();
        self.doubleClick();
        return this;
    }


    public AngularElement hover() {
        AngularWait.forRequestsToFinish();
        self.hover();
        return this;
    }


    public AngularElement dragAndDropTo(final String targetCssSelector) {
        AngularWait.forRequestsToFinish();
        self.dragAndDropTo(targetCssSelector);
        return this;
    }


    public AngularElement dragAndDropTo(final WebElement target) {
        AngularWait.forRequestsToFinish();
        self.dragAndDropTo(target);
        return this;
    }


    public boolean isImage() {
        AngularWait.forRequestsToFinish();
        return self.isImage();
    }


    public File screenshot() {
        AngularWait.forRequestsToFinish();
        return self.screenshot();
    }


    public BufferedImage screenshotAsImage() {
        AngularWait.forRequestsToFinish();
        return self.screenshotAsImage();
    }


    public <X> X getScreenshotAs(final OutputType<X> outputType) throws WebDriverException {
        AngularWait.forRequestsToFinish();
        return self.getScreenshotAs(outputType);
    }


    public WebElement findElementByClassName(final String s) {
        AngularWait.forRequestsToFinish();
        return self.findElementByClassName(s);
    }


    public List<WebElement> findElementsByClassName(final String s) {
        AngularWait.forRequestsToFinish();
        return self.findElementsByClassName(s);
    }


    public WebElement findElementByCssSelector(final String s) {
        AngularWait.forRequestsToFinish();
        return self.findElementByCssSelector(s);
    }


    public List<WebElement> findElementsByCssSelector(final String s) {
        AngularWait.forRequestsToFinish();
        return self.findElementsByCssSelector(s);
    }


    public WebElement findElementById(final String s) {
        AngularWait.forRequestsToFinish();
        return self.findElementById(s);
    }


    public List<WebElement> findElementsById(final String s) {
        AngularWait.forRequestsToFinish();
        return self.findElementsById(s);
    }


    public WebElement findElementByLinkText(final String s) {
        AngularWait.forRequestsToFinish();
        return self.findElementByLinkText(s);
    }


    public List<WebElement> findElementsByLinkText(final String s) {
        AngularWait.forRequestsToFinish();
        return self.findElementsByLinkText(s);
    }

    public WebElement findElementByPartialLinkText(final String s) {
        AngularWait.forRequestsToFinish();
        return self.findElementByPartialLinkText(s);
    }

    public List<WebElement> findElementsByPartialLinkText(final String s) {
        AngularWait.forRequestsToFinish();
        return self.findElementsByPartialLinkText(s);
    }

    public WebElement findElementByName(final String s) {
        AngularWait.forRequestsToFinish();
        return self.findElementByName(s);
    }

    public List<WebElement> findElementsByName(final String s) {
        AngularWait.forRequestsToFinish();
        return self.findElementsByName(s);
    }

    public WebElement findElementByTagName(final String s) {
        AngularWait.forRequestsToFinish();
        return self.findElementByTagName(s);
    }

    public List<WebElement> findElementsByTagName(final String s) {
        AngularWait.forRequestsToFinish();
        return self.findElementsByTagName(s);
    }

    public WebElement findElementByXPath(final String s) {
        AngularWait.forRequestsToFinish();
        return self.findElementByXPath(s);
    }

    public List<WebElement> findElementsByXPath(final String s) {
        AngularWait.forRequestsToFinish();
        return self.findElementsByXPath(s);
    }

    public Coordinates getCoordinates() {
        AngularWait.forRequestsToFinish();
        return self.getCoordinates();
    }

    public WebDriver getWrappedDriver() {
        AngularWait.forRequestsToFinish();
        return self.getWrappedDriver();
    }
}

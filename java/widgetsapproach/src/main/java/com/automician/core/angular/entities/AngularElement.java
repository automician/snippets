package com.automician.core.angular.entities;

import com.automician.core.angular.commands.ElementCommand;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.internal.Coordinates;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import static com.automician.core.angular.commands.WithWaitFor.withWaitForElement;

public class AngularElement {
    SelenideElement self;

    public AngularElement(SelenideElement element) {
        this.self = element;
    }


    public void followLink() {
        withWaitForElement(self).run(new ElementCommand<SelenideElement>() {

            public SelenideElement run(SelenideElement element) {
                element.followLink();
                return element;
            }
        });
    }

    public AngularElement setValue(final String text) {
        withWaitForElement(self).run(new ElementCommand<SelenideElement>() {

            public SelenideElement run(SelenideElement element) {
                return element.setValue(text);
            }
        });
        return this;
    }

    public AngularElement val(String text) {
        return setValue(text);
    }


    public AngularElement append(final String text) {
        withWaitForElement(self).run(new ElementCommand<SelenideElement>() {

            public SelenideElement run(SelenideElement element) {
                return element.append(text);
            }
        });
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
        return withWaitForElement(self).run(new ElementCommand<String>() {

            public String run(SelenideElement element) {
                return element.getText();
            }
        });
    }


    public List<WebElement> findElements(final By by) {
        return withWaitForElement(self).run(new ElementCommand<List<WebElement>>() {

            public List<WebElement> run(SelenideElement element) {
                return element.findElements(by);
            }
        });
    }


    public WebElement findElement(final By by) {
        return withWaitForElement(self).run(new ElementCommand<WebElement>() {

            public WebElement run(SelenideElement element) {
                return element.findElement(by);
            }
        });
    }


    public String text() {
        return withWaitForElement(self).run(new ElementCommand<String>() {

            public String run(SelenideElement element) {
                return element.text();
            }
        });
    }


    public String innerText() {
        return withWaitForElement(self).run(new ElementCommand<String>() {

            public String run(SelenideElement element) {
                return element.innerText();
            }
        });
    }


    public String innerHtml() {
        return withWaitForElement(self).run(new ElementCommand<String>() {

            public String run(SelenideElement element) {
                return element.innerHtml();
            }
        });
    }


    public String attr(final String attributeName) {
        return withWaitForElement(self).run(new ElementCommand<String>() {

            public String run(SelenideElement element) {
                return element.attr(attributeName);
            }
        });
    }


    public String name() {
        return withWaitForElement(self).run(new ElementCommand<String>() {

            public String run(SelenideElement element) {
                return element.name();
            }
        });
    }


    public String val() {
        return withWaitForElement(self).run(new ElementCommand<String>() {

            public String run(SelenideElement element) {
                return element.val();
            }
        });
    }


    public String getValue() {
        return val();
    }


    public AngularElement selectRadio(final String value) {
        withWaitForElement(self).run(new ElementCommand<SelenideElement>() {

            public SelenideElement run(SelenideElement element) {
                return element.selectRadio(value);
            }
        });
        return this;
    }


    public String data(final String dataAttributeName) {
        return withWaitForElement(self).run(new ElementCommand<String>() {

            public String run(SelenideElement element) {
                return element.data(dataAttributeName);
            }
        });
    }


    public boolean exists() {
        return withWaitForElement(self).run(new ElementCommand<Boolean>() {

            public Boolean run(SelenideElement element) {
                return element.exists();
            }
        });
    }


    public boolean isDisplayed() {
        return withWaitForElement(self).run(new ElementCommand<Boolean>() {

            public Boolean run(SelenideElement element) {
                return element.isDisplayed();
            }
        });
    }


    public Point getLocation() {
        return withWaitForElement(self).run(new ElementCommand<Point>() {

            public Point run(SelenideElement element) {
                return element.getLocation();
            }
        });
    }


    public Dimension getSize() {
        return withWaitForElement(self).run(new ElementCommand<Dimension>() {

            public Dimension run(SelenideElement element) {
                return element.getSize();
            }
        });
    }


    public Rectangle getRect() {
        return withWaitForElement(self).run(new ElementCommand<Rectangle>() {

            public Rectangle run(SelenideElement element) {
                return element.getRect();
            }
        });
    }


    public String getCssValue(final String s) {
        return withWaitForElement(self).run(new ElementCommand<String>() {

            public String run(SelenideElement element) {
                return element.getCssValue(s);
            }
        });
    }


    public boolean is(final Condition condition) {
        return withWaitForElement(self).run(new ElementCommand<Boolean>() {

            public Boolean run(SelenideElement element) {
                return element.is(condition);
            }
        });
    }


    public boolean has(final Condition condition) {
        return is(condition);
    }


    public AngularElement setSelected(final boolean selected) {
        withWaitForElement(self).run(new ElementCommand<SelenideElement>() {

            public SelenideElement run(SelenideElement element) {
                return element.setSelected(selected);
            }
        });
        return this;
    }


    public AngularElement should(final Condition... condition) {
        withWaitForElement(self).run(new ElementCommand<SelenideElement>() {

            public SelenideElement run(SelenideElement element) {
                return element.should(condition);
            }
        });
        return this;
    }


    public AngularElement shouldHave(Condition... condition) {
        return should(condition);
    }


    public AngularElement shouldBe(Condition... condition) {
        return should(condition);
    }


    public AngularElement shouldNot(final Condition... condition) {
        withWaitForElement(self).run(new ElementCommand<SelenideElement>() {

            public SelenideElement run(SelenideElement element) {
                return element.shouldNot(condition);
            }
        });
        return this;
    }


    public AngularElement shouldNotHave(Condition... condition) {
        return shouldNot(condition);
    }


    public AngularElement shouldNotBe(Condition... condition) {
        return shouldNot(condition);
    }


    public AngularElement waitUntil(final Condition condition, final long timeoutMilliseconds) {
        withWaitForElement(self).run(new ElementCommand<SelenideElement>() {

            public SelenideElement run(SelenideElement element) {
                return element.waitUntil(condition, timeoutMilliseconds);
            }
        });
        return this;
    }


    public AngularElement waitUntil(final Condition condition, final long timeoutMilliseconds, final long pollingIntervalMilliseconds) {
        withWaitForElement(self).run(new ElementCommand<SelenideElement>() {

            public SelenideElement run(SelenideElement element) {
                return element.waitUntil(condition, timeoutMilliseconds, pollingIntervalMilliseconds);
            }
        });
        return this;
    }


    public AngularElement waitWhile(final Condition condition, final long timeoutMilliseconds) {
        withWaitForElement(self).run(new ElementCommand<SelenideElement>() {

            public SelenideElement run(SelenideElement element) {
                return element.waitWhile(condition, timeoutMilliseconds);
            }
        });
        return this;
    }


    public AngularElement waitWhile(final Condition condition, final long timeoutMilliseconds, final long pollingIntervalMilliseconds) {
        withWaitForElement(self).run(new ElementCommand<SelenideElement>() {

            public SelenideElement run(SelenideElement element) {
                return element.waitWhile(condition, timeoutMilliseconds, pollingIntervalMilliseconds);
            }
        });
        return this;
    }


    public AngularElement parent() {
        SelenideElement element = withWaitForElement(self).run(new ElementCommand<SelenideElement>() {

            public SelenideElement run(SelenideElement element) {
                return element.parent();
            }
        });
        return new AngularElement(element);
    }


    public AngularElement closest(final String tagOrClass) {
        SelenideElement element = withWaitForElement(self).run(new ElementCommand<SelenideElement>() {

            public SelenideElement run(SelenideElement element) {
                return element.closest(tagOrClass);
            }
        });
        return new AngularElement(element);
    }


    public AngularElement find(final String cssSelector) {
        SelenideElement element = withWaitForElement(self).run(new ElementCommand<SelenideElement>() {

            public SelenideElement run(SelenideElement element) {
                return element.find(cssSelector);
            }
        });
        return new AngularElement(element);
    }


    public AngularElement find(final String cssSelector, final int index) {
        SelenideElement element = withWaitForElement(self).run(new ElementCommand<SelenideElement>() {

            public SelenideElement run(SelenideElement element) {
                return element.find(cssSelector, index);
            }
        });
        return new AngularElement(element);
    }


    public AngularElement find(final By selector) {
        SelenideElement element = withWaitForElement(self).run(new ElementCommand<SelenideElement>() {

            public SelenideElement run(SelenideElement element) {
                return element.find(selector);
            }
        });
        return new AngularElement(element);
    }


    public AngularElement find(final By selector, final int index) {
        SelenideElement element = withWaitForElement(self).run(new ElementCommand<SelenideElement>() {

            public SelenideElement run(SelenideElement element) {
                return element.find(selector, index);
            }
        });
        return new AngularElement(element);
    }


    public AngularElement $(final String cssSelector) {
        SelenideElement element = withWaitForElement(self).run(new ElementCommand<SelenideElement>() {

            public SelenideElement run(SelenideElement element) {
                return element.$(cssSelector);
            }
        });
        return new AngularElement(element);
    }


    public AngularElement $(final String cssSelector, final int index) {
        SelenideElement element = withWaitForElement(self).run(new ElementCommand<SelenideElement>() {

            public SelenideElement run(SelenideElement element) {
                return element.$(cssSelector, index);
            }
        });
        return new AngularElement(element);
    }


    public AngularElement $(final By selector) {
        SelenideElement element = withWaitForElement(self).run(new ElementCommand<SelenideElement>() {

            public SelenideElement run(SelenideElement element) {
                return element.$(selector);
            }
        });
        return new AngularElement(element);
    }


    public AngularElement $(final By selector, final int index) {
        SelenideElement element = withWaitForElement(self).run(new ElementCommand<SelenideElement>() {

            public SelenideElement run(SelenideElement element) {
                return element.$(selector, index);
            }
        });
        return new AngularElement(element);
    }


    public AngularCollection findAll(final String cssSelector) {
        ElementsCollection collection = withWaitForElement(self).run(new ElementCommand<ElementsCollection>() {

            public ElementsCollection run(SelenideElement element) {
                return element.findAll(cssSelector);
            }
        });
        return new AngularCollection(collection);
    }


    public AngularCollection findAll(final By selector) {
        ElementsCollection collection = withWaitForElement(self).run(new ElementCommand<ElementsCollection>() {

            public ElementsCollection run(SelenideElement element) {
                return element.findAll(selector);
            }
        });
        return new AngularCollection(collection);
    }


    public AngularCollection $$(final String cssSelector) {
        ElementsCollection collection = withWaitForElement(self).run(new ElementCommand<ElementsCollection>() {

            public ElementsCollection run(SelenideElement element) {
                return element.$$(cssSelector);
            }
        });
        return new AngularCollection(collection);
    }


    public AngularCollection $$(final By selector) {
        ElementsCollection collection = withWaitForElement(self).run(new ElementCommand<ElementsCollection>() {

            public ElementsCollection run(SelenideElement element) {
                return element.$$(selector);
            }
        });
        return new AngularCollection(collection);
    }


    public File uploadFromClasspath(final String... fileName) {
        return withWaitForElement(self).run(new ElementCommand<File>() {

            public File run(SelenideElement element) {
                return element.uploadFromClasspath(fileName);
            }
        });
    }


    public File uploadFile(final File... file) {
        return withWaitForElement(self).run(new ElementCommand<File>() {

            public File run(SelenideElement element) {
                return element.uploadFile(file);
            }
        });
    }


    public void selectOption(final int... index) {
        withWaitForElement(self).run(new ElementCommand<SelenideElement>() {

            public SelenideElement run(SelenideElement element) {
                element.selectOption(index);
                return element;
            }
        });
    }


    public void selectOption(final String... text) {
        withWaitForElement(self).run(new ElementCommand<SelenideElement>() {

            public SelenideElement run(SelenideElement element) {
                element.selectOption(text);
                return element;
            }
        });
    }


    public void selectOptionByValue(final String... value) {
        withWaitForElement(self).run(new ElementCommand<SelenideElement>() {

            public SelenideElement run(SelenideElement element) {
                element.selectOption(value);
                return element;
            }
        });
    }


    public AngularElement getSelectedOption() throws NoSuchElementException {
        SelenideElement element = withWaitForElement(self).run(new ElementCommand<SelenideElement>() {

            public SelenideElement run(SelenideElement element) {
                return element.getSelectedOption();
            }
        });
        return new AngularElement(element);
    }


    public AngularCollection getSelectedOptions() {
        ElementsCollection collection = withWaitForElement(self).run(new ElementCommand<ElementsCollection>() {

            public ElementsCollection run(SelenideElement element) {
                return element.getSelectedOptions();
            }
        });
        return new AngularCollection(collection);
    }


    public String getSelectedValue() {
        return withWaitForElement(self).run(new ElementCommand<String>() {

            public String run(SelenideElement element) {
                return element.getSelectedValue();
            }
        });
    }


    public String getSelectedText() {
        return withWaitForElement(self).run(new ElementCommand<String>() {

            public String run(SelenideElement element) {
                return element.getSelectedText();
            }
        });
    }


    public AngularElement scrollTo() {
        withWaitForElement(self).run(new ElementCommand<SelenideElement>() {

            public SelenideElement run(SelenideElement element) {
                return element.scrollTo();
            }
        });
        return this;
    }


    public File download() throws FileNotFoundException {
        final FileNotFoundException[] lastError = new FileNotFoundException[1];
        File file = withWaitForElement(self).run(new ElementCommand<File>() {

            public File run(SelenideElement element) {
                try {
                    return element.download();
                } catch (FileNotFoundException e) {
                    lastError[0] = e;
                    return null;
                }
            }
        });
        if (file == null) {
            throw new FileNotFoundException(lastError[0].getMessage());
        }
        return file;
    }


    public WebElement toWebElement() {
        return withWaitForElement(self).run(new ElementCommand<WebElement>() {

            public WebElement run(SelenideElement element) {
                return element.toWebElement();
            }
        });
    }


    public WebElement getWrappedElement() {
        return withWaitForElement(self).run(new ElementCommand<WebElement>() {

            public WebElement run(SelenideElement element) {
                return element.getWrappedElement();
            }
        });
    }


    public void click() {
        withWaitForElement(self).run(new ElementCommand<SelenideElement>() {

            public SelenideElement run(SelenideElement element) {
                element.click();
                return element;
            }
        });
    }


    public void submit() {
        withWaitForElement(self).run(new ElementCommand<SelenideElement>() {

            public SelenideElement run(SelenideElement element) {
                element.submit();
                return element;
            }
        });
    }


    public void sendKeys(final CharSequence... charSequences) {
        withWaitForElement(self).run(new ElementCommand<SelenideElement>() {

            public SelenideElement run(SelenideElement element) {
                element.sendKeys(charSequences);
                return element;
            }
        });
    }


    public void clear() {
        withWaitForElement(self).run(new ElementCommand<SelenideElement>() {

            public SelenideElement run(SelenideElement element) {
                element.clear();
                return element;
            }
        });
    }


    public String getTagName() {
        return withWaitForElement(self).run(new ElementCommand<String>() {

            public String run(SelenideElement element) {
                return element.getTagName();
            }
        });
    }


    public String getAttribute(final String s) {
        return withWaitForElement(self).run(new ElementCommand<String>() {

            public String run(SelenideElement element) {
                return element.getAttribute(s);
            }
        });
    }


    public boolean isSelected() {
        return withWaitForElement(self).run(new ElementCommand<Boolean>() {

            public Boolean run(SelenideElement element) {
                return element.isSelected();
            }
        });
    }


    public boolean isEnabled() {
        return withWaitForElement(self).run(new ElementCommand<Boolean>() {

            public Boolean run(SelenideElement element) {
                return element.isEnabled();
            }
        });
    }


    public AngularElement contextClick() {
        withWaitForElement(self).run(new ElementCommand<SelenideElement>() {

            public SelenideElement run(SelenideElement element) {
                return element.contextClick();
            }
        });
        return this;
    }


    public AngularElement doubleClick() {
        withWaitForElement(self).run(new ElementCommand<SelenideElement>() {

            public SelenideElement run(SelenideElement element) {
                return element.doubleClick();
            }
        });
        return this;
    }


    public AngularElement hover() {
        withWaitForElement(self).run(new ElementCommand<SelenideElement>() {

            public SelenideElement run(SelenideElement element) {
                return element.hover();
            }
        });
        return this;
    }


    public AngularElement dragAndDropTo(final String targetCssSelector) {
        withWaitForElement(self).run(new ElementCommand<SelenideElement>() {

            public SelenideElement run(SelenideElement element) {
                return element.dragAndDropTo(targetCssSelector);
            }
        });
        return this;
    }


    public AngularElement dragAndDropTo(final WebElement target) {
        withWaitForElement(self).run(new ElementCommand<SelenideElement>() {

            public SelenideElement run(SelenideElement element) {
                return element.dragAndDropTo(target);
            }
        });
        return this;
    }


    public boolean isImage() {
        return withWaitForElement(self).run(new ElementCommand<Boolean>() {

            public Boolean run(SelenideElement element) {
                return element.isImage();
            }
        });
    }


    public File screenshot() {
        return withWaitForElement(self).run(new ElementCommand<File>() {

            public File run(SelenideElement element) {
                return element.screenshot();
            }
        });
    }


    public BufferedImage screenshotAsImage() {
        return withWaitForElement(self).run(new ElementCommand<BufferedImage>() {

            public BufferedImage run(SelenideElement element) {
                return element.screenshotAsImage();
            }
        });
    }


    public <X> X getScreenshotAs(final OutputType<X> outputType) throws WebDriverException {
        return withWaitForElement(self).run(new ElementCommand<X>() {

            public X run(SelenideElement element) {
                return element.getScreenshotAs(outputType);
            }
        });
    }


    public WebElement findElementByClassName(final String s) {
        return withWaitForElement(self).run(new ElementCommand<WebElement>() {

            public WebElement run(SelenideElement element) {
                return element.findElementByClassName(s);
            }
        });
    }


    public List<WebElement> findElementsByClassName(final String s) {
        return withWaitForElement(self).run(new ElementCommand<List<WebElement>>() {

            public List<WebElement> run(SelenideElement element) {
                return element.findElementsByClassName(s);
            }
        });
    }


    public WebElement findElementByCssSelector(final String s) {
        return withWaitForElement(self).run(new ElementCommand<WebElement>() {

            public WebElement run(SelenideElement element) {
                return element.findElementByCssSelector(s);
            }
        });
    }


    public List<WebElement> findElementsByCssSelector(final String s) {
        return withWaitForElement(self).run(new ElementCommand<List<WebElement>>() {

            public List<WebElement> run(SelenideElement element) {
                return element.findElementsByCssSelector(s);
            }
        });
    }


    public WebElement findElementById(final String s) {
        return withWaitForElement(self).run(new ElementCommand<WebElement>() {

            public WebElement run(SelenideElement element) {
                return element.findElementById(s);
            }
        });
    }


    public List<WebElement> findElementsById(final String s) {
        return withWaitForElement(self).run(new ElementCommand<List<WebElement>>() {

            public List<WebElement> run(SelenideElement element) {
                return element.findElementsById(s);
            }
        });
    }


    public WebElement findElementByLinkText(final String s) {
        return withWaitForElement(self).run(new ElementCommand<WebElement>() {

            public WebElement run(SelenideElement element) {
                return element.findElementByLinkText(s);
            }
        });
    }


    public List<WebElement> findElementsByLinkText(final String s) {
        return withWaitForElement(self).run(new ElementCommand<List<WebElement>>() {

            public List<WebElement> run(SelenideElement element) {
                return element.findElementsByLinkText(s);
            }
        });
    }


    public WebElement findElementByPartialLinkText(final String s) {
        return withWaitForElement(self).run(new ElementCommand<WebElement>() {

            public WebElement run(SelenideElement element) {
                return element.findElementByPartialLinkText(s);
            }
        });
    }


    public List<WebElement> findElementsByPartialLinkText(final String s) {
        return withWaitForElement(self).run(new ElementCommand<List<WebElement>>() {

            public List<WebElement> run(SelenideElement element) {
                return element.findElementsByPartialLinkText(s);
            }
        });
    }


    public WebElement findElementByName(final String s) {
        return withWaitForElement(self).run(new ElementCommand<WebElement>() {

            public WebElement run(SelenideElement element) {
                return element.findElementByName(s);
            }
        });
    }


    public List<WebElement> findElementsByName(final String s) {
        return withWaitForElement(self).run(new ElementCommand<List<WebElement>>() {

            public List<WebElement> run(SelenideElement element) {
                return element.findElementsByName(s);
            }
        });
    }


    public WebElement findElementByTagName(final String s) {
        return withWaitForElement(self).run(new ElementCommand<WebElement>() {

            public WebElement run(SelenideElement element) {
                return element.findElementByTagName(s);
            }
        });
    }


    public List<WebElement> findElementsByTagName(final String s) {
        return withWaitForElement(self).run(new ElementCommand<List<WebElement>>() {

            public List<WebElement> run(SelenideElement element) {
                return element.findElementsByTagName(s);
            }
        });
    }


    public WebElement findElementByXPath(final String s) {
        return withWaitForElement(self).run(new ElementCommand<WebElement>() {

            public WebElement run(SelenideElement element) {
                return element.findElementByXPath(s);
            }
        });
    }


    public List<WebElement> findElementsByXPath(final String s) {
        return withWaitForElement(self).run(new ElementCommand<List<WebElement>>() {

            public List<WebElement> run(SelenideElement element) {
                return element.findElementsByXPath(s);
            }
        });
    }


    public Coordinates getCoordinates() {
        return withWaitForElement(self).run(new ElementCommand<Coordinates>() {

            public Coordinates run(SelenideElement element) {
                return element.getCoordinates();
            }
        });
    }


    public WebDriver getWrappedDriver() {
        return withWaitForElement(self).run(new ElementCommand<WebDriver>() {

            public WebDriver run(SelenideElement element) {
                return element.getWrappedDriver();
            }
        });
    }
}

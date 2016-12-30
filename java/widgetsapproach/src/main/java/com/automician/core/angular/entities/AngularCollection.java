package com.automician.core.angular.entities;

import com.automician.core.angular.wait.AngularWait;
import com.codeborne.selenide.*;
import com.codeborne.selenide.ex.UIAssertionError;
import com.codeborne.selenide.impl.*;
import com.codeborne.selenide.logevents.SelenideLog;
import com.codeborne.selenide.logevents.SelenideLogger;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import java.util.*;

import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Configuration.assertionMode;
import static com.codeborne.selenide.Configuration.collectionsPollingInterval;
import static com.codeborne.selenide.Configuration.collectionsTimeout;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.logevents.ErrorsCollector.validateAssertionMode;
import static com.codeborne.selenide.logevents.LogEvent.EventStatus.PASS;
import static java.util.stream.Collectors.toList;


public class AngularCollection extends ElementsCollection {

    private ElementsCollection collection;

    public AngularCollection(ElementsCollection collection) {
        super(new WebElementsCollection() {
            @Override
            public List<WebElement> getActualElements() {
                return null;
            }

            @Override
            public String description() {
                return this.toString();
            }
        });
        this.collection = collection;
    }

    @Override
    public ElementsCollection shouldHave(final CollectionCondition... conditions) {
        AngularWait.waitForRequestsToFinish();
        this.collection.shouldHave(conditions);
        return this;
    }

    @Override
    protected ElementsCollection should(String prefix, CollectionCondition... conditions) {
        return this.shouldHave(conditions);
    }

    @Override
    public ElementsCollection shouldBe(final CollectionCondition... conditions) {
        return this.shouldHave(conditions);
    }

    @Override
    public ElementsCollection shouldHaveSize(int expectedSize) {
        return this.shouldHave(CollectionCondition.size(expectedSize));
    }

    @Override
    protected void waitUntil(CollectionCondition condition, long timeoutMs) {
        long oldTimeOut = Configuration.collectionsTimeout;
        try {
            Configuration.collectionsTimeout = timeoutMs;
            this.shouldHave(condition);
        } finally {
            Configuration.collectionsTimeout = oldTimeOut;
        }
    }

    @Override
    public ElementsCollection filter(final Condition condition) {
        return new AngularCollection(this.collection.filter(condition));
    }

    @Override
    public ElementsCollection filterBy(Condition condition) {
        return this.filter(condition);
    }

    @Override
    public ElementsCollection exclude(Condition condition) {
        return new AngularCollection(collection.exclude(condition));
    }

    @Override
    public ElementsCollection excludeWith(Condition condition) {
        return this.exclude(condition);
    }

    @Override
    public SelenideElement find(final Condition condition) {
        return new AngularElement(this.collection.find(condition));
    }

    @Override
    public SelenideElement findBy(final Condition condition) {
        return find(condition);
    }

    @Override
    public List<String> texts() {
        AngularWait.waitForRequestsToFinish();
        return this.collection.texts();
    }


    public String[] getTexts() {
        AngularWait.waitForRequestsToFinish();
        return this.collection.getTexts();
    }

    @Override
    public SelenideElement get(int index) {
        return new AngularElement(this.collection.get(index));
    }

    @Override
    public SelenideElement first() {
        return new AngularElement(this.collection.first());
    }

    @Override
    public SelenideElement last() {
        return new AngularElement(this.collection.last());
    }

    @Override
    public int size() {
        AngularWait.waitForRequestsToFinish();
        return this.collection.size();
    }

    @Override
    public Iterator<SelenideElement> iterator() {
        return this.collection.iterator();
    }

    @Override
    public ListIterator<SelenideElement> listIterator(int index) {
        return this.collection.listIterator(index);
    }

    @Override
    public String toString() {
        return "Angular collection for " + this.collection.toString();
    }

}
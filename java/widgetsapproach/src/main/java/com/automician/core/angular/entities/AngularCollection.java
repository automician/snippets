package com.automician.core.angular.entities;

import com.automician.core.angular.wait.AngularWait;
import com.codeborne.selenide.*;
import com.codeborne.selenide.impl.*;
import org.openqa.selenium.WebElement;

import java.util.*;

public class AngularCollection extends ElementsCollection {

    private ElementsCollection collection;

    public AngularCollection(final ElementsCollection collection) {
        super(new WebElementsCollection() {
            @Override
            public List<WebElement> getActualElements() {
                List<WebElement> elements = new ArrayList<WebElement>();
                for (SelenideElement element : collection) {
                    elements.add(element.getWrappedElement());
                }
                return elements;
            }

            @Override
            public String description() {
                return "AngularCollection#WebElementsCollection " + this.toString();
            }
        });
        this.collection = collection;
    }

    @Override
    protected void waitUntil(CollectionCondition condition, long timeoutMs) {
        AngularWait.waitForRequestsToFinish();
        super.waitUntil(condition, timeoutMs);
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
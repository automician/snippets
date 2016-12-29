package com.automician.core.angular.entities;

import com.automician.core.angular.wait.AngularWait;
import com.codeborne.selenide.*;
import com.codeborne.selenide.ex.UIAssertionError;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class AngularCollection implements Iterable<AngularElement> {

    private ElementsCollection collection;

    public AngularCollection(ElementsCollection collection) {
        this.collection = collection;
    }

    public AngularCollection shouldHave(final CollectionCondition... conditions) {
        AngularWait.waitForRequestsToFinish();
        this.collection.shouldHave(conditions);
        return this;
    }

    public AngularCollection shouldBe(final CollectionCondition... conditions) {
        return shouldHave(conditions);
    }

    public boolean waitingIs(CollectionCondition condition) {
        try {
            shouldBe(condition);
            return true;
        } catch (UIAssertionError e) {
            return false;
        }
    }

    public AngularElement get(final int index) {
        //AngularWait.waitForRequestsToFinish();
        return new AngularElement(this.collection.get(index));
    }

    public AngularCollection filter(final Condition condition) {
        //AngularWait.waitForRequestsToFinish();
        return new AngularCollection(this.collection.filter(condition));
    }

    public List<String> texts() {
        AngularWait.waitForRequestsToFinish();
        return this.collection.texts();
    }

    public AngularElement find(final Condition condition) {
        //AngularWait.waitForRequestsToFinish();
        return new AngularElement(this.collection.find(condition));
    }

    public AngularElement findBy(final Condition condition) {
        return find(condition);
    }

    @Override
    public Iterator<AngularElement> iterator() {
        List<AngularElement> list = new ArrayList<AngularElement>();
        for (SelenideElement element : collection) {
            list.add(new AngularElement(element));
        }
        return list.iterator();
    }
}
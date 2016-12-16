package com.automician.core.angular.entities;

import com.automician.core.angular.wait.AngularWait;
import com.codeborne.selenide.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class AngularCollection implements Iterable<AngularElement> {
    private ElementsCollection self;

    public AngularCollection(ElementsCollection collection) {
        self = collection;
    }

    public AngularCollection shouldHave(final CollectionCondition... conditions) {
        AngularWait.forRequestsToFinish();
        self.shouldHave(conditions);
        return this;
    }

    public AngularCollection shouldBe(final CollectionCondition... conditions) {
        return shouldHave(conditions);
    }

    public AngularElement get(final int index) {
        AngularWait.forRequestsToFinish();
        return new AngularElement(self.get(index));
    }

    public AngularCollection filter(final Condition condition) {
        AngularWait.forRequestsToFinish();
        return new AngularCollection(self.filter(condition));
    }

    public AngularElement find(final Condition condition) {
        AngularWait.forRequestsToFinish();
        return new AngularElement(self.find(condition));
    }

    public AngularElement findBy(final Condition condition) {
        return find(condition);
    }

    @Override
    public Iterator<AngularElement> iterator() {
        List<AngularElement> list = new ArrayList<AngularElement>();
        for (SelenideElement element : self) {
            list.add(new AngularElement(element));
        }
        return list.iterator();
    }
}
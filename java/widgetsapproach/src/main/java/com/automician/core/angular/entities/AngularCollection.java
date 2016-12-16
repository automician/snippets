package com.automician.core.angular.entities;

import com.automician.core.angular.commands.CollectionCommand;
import com.codeborne.selenide.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.automician.core.angular.commands.WithWaitFor.withWaitForCollection;

public class AngularCollection implements Iterable<AngularElement> {
    private ElementsCollection self;

    public AngularCollection(ElementsCollection collection) {
        self = collection;
    }

    public AngularCollection shouldHave(final CollectionCondition... conditions) {
        withWaitForCollection(self).run(new CollectionCommand<ElementsCollection>() {
            @Override
            public ElementsCollection run(ElementsCollection collection) {
                return collection.shouldHave(conditions);
            }
        });
        return this;
    }

    public AngularCollection shouldBe(final CollectionCondition... conditions) {
        return shouldHave(conditions);
    }

    public AngularElement get(final int index) {
        SelenideElement element = withWaitForCollection(self).run(new CollectionCommand<SelenideElement>() {
            @Override
            public SelenideElement run(ElementsCollection collection) {
                return collection.get(index);
            }
        });
        return new AngularElement(element);
    }

    public AngularCollection filter(final Condition condition) {
        ElementsCollection collection = withWaitForCollection(self).run(new CollectionCommand<ElementsCollection>() {
            @Override
            public ElementsCollection run(ElementsCollection collection) {
                return collection.filter(condition);
            }
        });
        return new AngularCollection(collection);
    }

    public AngularElement find(final Condition condition) {
        SelenideElement element = withWaitForCollection(self).run(new CollectionCommand<SelenideElement>() {
            @Override
            public SelenideElement run(ElementsCollection collection) {
                return collection.find(condition);
            }
        });
        return new AngularElement(element);
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
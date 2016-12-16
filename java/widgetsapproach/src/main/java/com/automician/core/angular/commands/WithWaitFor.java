package com.automician.core.angular.commands;

import com.automician.core.angular.wait.AngularWait;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;
import com.codeborne.selenide.ex.UIAssertionError;
import org.openqa.selenium.WebDriverException;

public class WithWaitFor<TypeOfEntity> {

    private TypeOfEntity entity;

    public WithWaitFor(TypeOfEntity entity) {
        this.entity = entity;
    }

    public <T> T run(Command<T, TypeOfEntity> command) {
        AngularWait.forRequestsToFinish();
        return command.run(entity);
    }

    public static WithWaitFor<ElementsCollection> withWaitForCollection(ElementsCollection collection) {
        return new WithWaitFor<ElementsCollection>(collection);
    }

    public static WithWaitFor<SelenideElement> withWaitForElement(SelenideElement element) {
        return new WithWaitFor<SelenideElement>(element);
    }

}

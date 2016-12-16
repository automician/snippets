package com.automician.core.angular.commands;

import org.openqa.selenium.WebElement;

public interface Command<TypeOfResult, TypeOfEntity> {
    TypeOfResult run(TypeOfEntity entity);
}

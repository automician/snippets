package com.automician.javaproperties.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.CollectionCondition.empty;
import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ToDoMVC {
    public static ElementsCollection tasks = $$("#todo-list>li");
    public static SelenideElement newTaskField = $("#new-todo");

    public static void add(String... taskTexts) {
        for (String taskText : taskTexts) {
            newTaskField.shouldBe(enabled).setValue(taskText).pressEnter();
        }
    }

    public static void edit(String oldTaskText, String newTaskText) {
        startEdit(oldTaskText, newTaskText).pressEnter();
    }

    public static void cancelEdit(String oldTaskText, String newTaskText) {
        startEdit(oldTaskText, newTaskText).pressEscape();
    }

    public static SelenideElement startEdit(String oldTaskText, String newTaskText) {
        tasks.find(exactText(oldTaskText)).doubleClick();
        return tasks.find(cssClass("editing")).find(".edit").setValue(newTaskText);
    }

    public static void toggle(String taskText) {
        tasks.find(exactText(taskText)).find(".toggle").click();
    }

    public static void toggleAll() {
        $("#toggle-all").click();
    }

    public static void delete(String taskText) {
        tasks.find(exactText(taskText)).hover().find(".destroy").click();
    }

    public static void clearCompleted() {
        $("#clear-completed").click();
        $("clear-completed").shouldNotBe(visible);
    }

    public static void switchToActive() {
        $(By.linkText("Active")).click();
    }

    public static void switchToCompleted() {
        $(By.linkText("Completed")).click();
    }

    public static void switchToAll() {
        $(By.linkText("All")).click();
    }

    public static void assertTasks(String... taskTexts) {
        tasks.shouldHave(exactTexts(taskTexts));
    }

    public static void assertNoTasks() {
        tasks.shouldBe(empty);
    }

    public static void assertVisibleTasks(String... taskTexts) {
        tasks.filter(visible).shouldHave(exactTexts(taskTexts));
    }

    public static void assertNoVisibleTasks() {
        tasks.filter(visible).shouldBe(empty);
    }

    public static void assertItemsLeft(int count) {
        $("#todo-count>strong").shouldHave(exactText(Integer.toString(count)));
    }

    public static void confirmEditByClickOutside(String oldTaskText, String newTaskText) {
        startEdit(oldTaskText, newTaskText);
        newTaskField.click();
    }
}
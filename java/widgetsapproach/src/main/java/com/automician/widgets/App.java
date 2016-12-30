package com.automician.widgets;

import ru.yandex.qatools.allure.annotations.Step;

public class App {
    @Step
    public Widget widget() {
        return new Widget();
    }
}

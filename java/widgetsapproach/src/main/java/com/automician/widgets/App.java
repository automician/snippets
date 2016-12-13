package com.automician.widgets;

import com.automician.core.checks.Assertions;
import ru.yandex.qatools.allure.annotations.Step;

import static com.automician.core.Core.core;

public class App {
    @Step
    public Widget widget() {
        return new Widget();
    }
}

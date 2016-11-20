package com.automician.yandexproperties;

import com.automician.yandexproperties.testconfigs.BaseTest;
import org.junit.Test;

import static com.automician.yandexproperties.pages.ToDoMVC.*;

public class TodoMVCIntegrationTest extends BaseTest {

    @Test
    public void testTasksCommonFlow() {
        add("a");

        //Complete
        toggle("a");
        assertTasks("a");

        switchToActive();
        assertNoVisibleTasks();

        switchToCompleted();
        assertVisibleTasks("a");

        //Reopen
        toggle("a");
        assertNoVisibleTasks();

        switchToAll();

        delete("a");
        assertNoTasks();
    }

}

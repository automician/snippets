package com.automician.profileproperties;

import com.automician.profileproperties.testconfigs.BaseTest;
import org.junit.Test;

import static com.automician.profileproperties.pages.ToDoMVC.*;

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

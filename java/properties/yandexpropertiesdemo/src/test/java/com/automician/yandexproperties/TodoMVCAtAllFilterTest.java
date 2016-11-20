package com.automician.yandexproperties;

import com.automician.yandexproperties.testconfigs.BaseTest;
import org.junit.Test;

import static com.automician.yandexproperties.pages.ToDoMVC.*;

public class TodoMVCAtAllFilterTest extends BaseTest {

    @Test
    public void testEdit() {
        add("a");

        edit("a", "a edited");
        assertTasks("a edited");
        assertItemsLeft(1);
    }

    @Test
    public void testReopen() {
        add("a", "b");
        toggleAll();

        toggle("b");
        assertTasks("a", "b");
        assertItemsLeft(1);
    }

    @Test
    public void testReopenWithBug() {
        add("a", "b");
        toggleAll();

        toggle("b");
        assertTasks("a", "b");
        assertItemsLeft(1);
    }

}

package todomvc;

import org.junit.Test;
import org.openqa.selenium.*;
import testconfig.BaseTest;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;


public class Feature1Test extends BaseTest {

    public Feature1Test(String browserName, String gridUrl) {
        super(browserName, gridUrl);
    }

    @Test
    public void test1() {
        System.out.println("test1, browser = " + this.browserName + ", grid URL = " + this.gridUrl);
        driver.get("http://todomvc4tasj.herokuapp.com/");
        wait.until(visibilityOfElementLocated(By.cssSelector("#new-todo")));
    }

    @Test
    public void test2() {
        System.out.println("test2, browser = " + this.browserName + ", grid URL = " + this.gridUrl);
        driver.get("http://todomvc4tasj.herokuapp.com/");
        wait.until(visibilityOfElementLocated(By.cssSelector("#new-todo")));
    }

}

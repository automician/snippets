package testconfig;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@RunWith(core.Parallelized.class) //for concurrent running parameterized tests
//@RunWith(value = Parameterized.class) //for running parameterized tests in one thread
public class BaseTest {

    public String browserName;
    public String gridUrl;

    @Parameterized.Parameters
    public static List<String[]> getEnvironments() throws Exception {
        List<String[]> environments = new ArrayList<String[]>();
        environments.add(new String[]{"chrome", "http://..../wd/hub"});//!!!correct URL od node
        environments.add(new String[]{"firefox", "http://..../wd/hub"});//!!!correct URL od node
        return environments;
    }

    public BaseTest(String browserName, String gridUrl) {
        this.browserName = browserName;
        this.gridUrl = gridUrl;
    }

    public WebDriver driver;
    public static WebDriverWait wait;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", browserName);
        driver = new RemoteWebDriver(
                new URL(gridUrl),
                capabilities
        );
        wait = new WebDriverWait(driver, 6);
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
}

package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

/**
 * Created by HP-G62 on 15.01.2015.
 */
public class TestBase{
    protected WebDriver driver;
    private static final String BASE_URL = "http://www.yandex.by/";

        @BeforeMethod
        public void before() throws InterruptedException {
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
            driver.manage().timeouts().setScriptTimeout(4, TimeUnit.SECONDS);
            driver.get(BASE_URL);
        }

    @AfterMethod
        public void after() {
            driver.quit();
        }
    }

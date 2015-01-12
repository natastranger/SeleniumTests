package interactions;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

/**
 * Created by Admin on 09.01.2015.
 */
public class TabsTest {
    /**
     * Created by Admin on 05.01.2015.
     */

    public static class InteractionsTest {
        private static final String BASE_URL = "http://www.w3schools.com/html/html5_draganddrop.asp";
        private FirefoxDriver driver;

        @BeforeMethod
        public void before() throws InterruptedException {
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
            driver.manage().timeouts().setScriptTimeout(4, TimeUnit.SECONDS);
            driver.get(BASE_URL);
            sleep(3000);
        }

        @AfterMethod
        public void after() {
            driver.quit();
        }

        @Test
        public void tabsTest() throws InterruptedException {
            WebElement tryItDtn = driver.findElement(By.cssSelector(".tryitbtn"));
            Assert.assertEquals(driver.getWindowHandles().size(), 1);
            tryItDtn.click();
            Assert.assertEquals(driver.getWindowHandles().size(), 2);
            ArrayList<String> titles = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(titles.get(0));
            Assert.assertEquals(driver.getTitle(), "HTML5 Drag and Drop");
            driver.switchTo().window(titles.get(1));
            Assert.assertEquals(driver.getTitle(), "Tryit Editor v2.3");
//            for (String handle: driver.getWindowHandles()){
//                driver.switchTo().window(handle);
//                System.out.println(driver.getTitle());
//                Thread.sleep(3000);
//            }
            driver.close();
            Assert.assertEquals(driver.getWindowHandles().size(), 1);
//цикл может быть реализован через итератор, но в цикле не удобно делать проверки
//            for (String handle : driver.getWindowHandles().iterator().hasNext()) {}
        }

    }
}

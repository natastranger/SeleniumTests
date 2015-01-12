package interactions;

import org.apache.xpath.operations.Bool;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

/**
 * Created by Admin on 05.01.2015.
 */

/*
Alerts and dragAndDrops actions work better in FF browser
*/

public class InteractionsTest {
    private static final String BASE_URL = "http://the-internet.herokuapp.com/";
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
    public void keysTest() throws InterruptedException {
        driver.findElement(By.linkText("Key Presses")).click();
        String letters = "ABCDEFGHIKLMOPQRSTVZXYZ";
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.SHIFT).perform();
        WebElement result = driver.findElement(By.id("result"));
        Assert.assertTrue(result.getText().contains("SHIFT"));
        for (int i = 0; i < letters.length(); i++) {
            String letter = String.valueOf(letters.charAt(i));
            actions.sendKeys(letter).perform();
            sleep(500);
            Assert.assertTrue(result.getText().contains(letter));
        }
    }

    @Test
    public void hoversTest() throws InterruptedException {
        driver.findElement(By.linkText("Hovers")).click();
        Assert.assertFalse(driver.findElement(By.className("figcaption")).isDisplayed());
        List<WebElement> figures = driver.findElements(By.className("figure"));
        Actions actions = new Actions(driver);
        for (WebElement figure : figures) {
            actions.moveToElement(figure).perform();
            sleep(2000);
            Assert.assertTrue(figure.findElement(By.tagName("h5")).isDisplayed());
        }
    }

    @Test
    public void contextMenuTest() throws InterruptedException {
//   5 раз нажать вниз кнопку и потом энтер
        driver.findElement(By.linkText("Context Menu")).click();
        WebElement fieldAction = driver.findElement(By.id("hot-spot"));
        Actions actions = new Actions(driver);
        actions.contextClick(fieldAction).perform();
        Thread.sleep(500);
        actions
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ENTER)
                .perform();
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals(alert.getText(), "You selected a context menu");
        alert.accept();
        Assert.assertFalse(isAlertPresent(driver));
    }

    @Test
    public void alertTest() throws InterruptedException {
        driver.findElement(By.linkText("JavaScript Alerts")).click();
        WebElement alertButton = driver.findElement(By.cssSelector("button[onclick*=jsAlert]"));
        alertButton.click();
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals(alert.getText(), "I am a JS Alert");
        alert.accept();
        Thread.sleep(1000);
        Assert.assertFalse(isAlertPresent(driver));
        WebElement result = driver.findElement(By.id("result"));
        Assert.assertEquals(result.getText(), "You successfuly clicked an alert");
    }

    @Test
    public void confirmTest() throws InterruptedException {
        driver.findElement(By.linkText("JavaScript Alerts")).click();
        WebElement confirmButton = driver.findElement(By.cssSelector("button[onclick*=jsConfirm]"));
        confirmButton.click();
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals(alert.getText(), "I am a JS Confirm");
        alert.dismiss();
        Thread.sleep(1000);
        Assert.assertFalse(isAlertPresent(driver));
        WebElement result = driver.findElement(By.id("result"));
        Assert.assertEquals(result.getText(), "You clicked: Cancel");
        confirmButton.click();
        alert.accept();
        Thread.sleep(1000);
        Assert.assertFalse(isAlertPresent(driver));
        Assert.assertEquals(result.getText(), "You clicked: Ok");
    }

    @Test
    public void promptTest() throws InterruptedException {
        driver.findElement(By.linkText("JavaScript Alerts")).click();
        WebElement promptButton = driver.findElement(By.cssSelector("button[onclick*=jsPrompt]"));
        promptButton.click();
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals(alert.getText(), "I am a JS prompt");
        String message = "I have accepted this alert";
        alert.sendKeys(message);
        alert.accept();
        Thread.sleep(1000);
        Assert.assertFalse(isAlertPresent(driver));
        WebElement result = driver.findElement(By.id("result"));
        Assert.assertTrue(result.getText().contains(message));
    }

    @Test
    public void nestedFramesTest() {
        driver.findElement(By.linkText("Frames")).click();
        driver.findElement(By.linkText("Nested Frames")).click();
        driver.switchTo().frame("frame-top");
        String frames[] = {"LEFT", "MIDDLE", "RIGHT"};
        for (int i = 0; i < frames.length; i++) {
            driver.switchTo().frame(i);
            Assert.assertEquals(driver.findElement(By.tagName("body")).getText(), frames[i]);
            driver.switchTo().parentFrame();
        }
        driver.switchTo().parentFrame();
        driver.switchTo().frame("frame-bottom");
        Assert.assertEquals(driver.findElement(By.tagName("body")).getText(), "BOTTOM");
    }

    @Test
    public void iFramesTest() {
        driver.findElement(By.linkText("Frames")).click();
        driver.findElement(By.linkText("iFrame")).click();
        driver.switchTo().frame(driver.findElement(By.tagName("iframe")));
        driver.findElement(By.id("tinymce")).click();
        Assert.assertEquals(driver.findElement(By.tagName("p")).getText(), "Your content goes here.");
        Actions actions = new Actions(driver);
//        actions.sendKeys(Keys.LEFT_CONTROL, "A").perform();
        driver.findElement(By.id("tinymce")).sendKeys("This is new text");
//        actions.sendKeys(Keys.LEFT_CONTROL, "A").perform();
//        actions.sendKeys(Keys.LEFT_CONTROL).sendKeys("B").perform();
        Assert.assertEquals(driver.findElement(By.tagName("p")).getText(), "This is new text");
    }

    @Test
    public void dragAndDrop() {
        driver.findElement(By.linkText("Drag and Drop")).click();
        WebElement columnA = driver.findElement(By.id("column-a"));
        WebElement columnB = driver.findElement(By.id("column-b"));
        Assert.assertEquals(columnA.getText(), "A");
        Assert.assertEquals(columnB.getText(), "B");
        Actions actions = new Actions(driver);
//        1 способ
//        actions.dragAndDrop(columnA, columnB).perform();
//        Assert.assertEquals(columnA.getText(), "B");
//        Assert.assertEquals(columnA.getText(), "A");

//        2a способ
//        actions.
//                clickAndHold(columnA).perform();
//        actions
//                .moveToElement(columnB)
//                .release()
//                .perform();

//        2б способ
//        actions
//                .moveToElement(columnA)
//                .click()
//                .moveToElement(columnB)
//                .release()
//                .perform();
//        Assert.assertEquals(columnA.getText(), "B");
//        Assert.assertEquals(columnA.getText(), "A");

//        3 способ
//        actions
//                .dragAndDropBy(columnA, columnB.getLocation().getX() - columnA.getLocation().getX() , 0);

//        вызов ожидалки
//        WebElement elem = waitForElement(driver, By.id(""))
    }


    private static boolean isAlertPresent(WebDriver driver) {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException ex) {
            return false;
        }
    }

}



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

/**
 * Created by Admin on 22.12.2014.
 */
public class ElementsTest {
    private static final String BASE_URL = "http://the-internet.herokuapp.com/";
    private WebDriver driver;

    @BeforeMethod
    public void before() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(4, TimeUnit.SECONDS);
        driver.get(BASE_URL);
        sleep(3000);
    }

    @AfterMethod
    public void after() {
        driver.close();
    }

    @Test
    public void checkboxTest() {
        WebElement ref = driver.findElement(By.linkText("Checkboxes"));
        ref.click();
        List<WebElement> checkboxes = driver.findElements(By.cssSelector
                ("input[type=checkbox]"));
        System.out.println("First checkbox, value = " + checkboxes.get(0)
                .isSelected() + "; " + "text = " + checkboxes.get(0).getText());
        System.out.println("Second checkbox, value = " + checkboxes.get(1)
                .isSelected() + "; " + "text = " + checkboxes.get(1).getText());
        checkboxes.get(0).click();
        checkboxes.get(1).click();
        Assert.assertTrue(checkboxes.get(0).isSelected());
        Assert.assertFalse(checkboxes.get(1).isSelected());
    }

    @Test
    public void dynamicControlsTest() throws InterruptedException {
        WebElement ref = driver.findElement(By.linkText("Dynamic Controls"));
        ref.click();
        WebElement removeButton = driver.findElement(By.id("btn"));
        WebElement div = driver.findElement(By.cssSelector("div#checkbox"));
        WebElement checkbox = driver.findElement(By.cssSelector
                ("input[type=checkbox]"));
        Assert.assertFalse(checkbox.isSelected());
        checkbox.click();
        removeButton.click();
        sleep(5000);
        Assert.assertEquals(removeButton.getText(), "Add");
        sleep(5000);
//        Assert.assertTrue(driver.getPageSource().contains("It's gone!"));
        Assert.assertEquals(driver.findElement(By.id("message")).getText(), "It's gone!");
        WebDriverWait wait = new WebDriverWait(driver, 10);
//        wait.until(ExpectedConditions.not(ExpectedConditions
//                .presenceOfAllElementsLocatedBy(By.cssSelector("input[type=checkbox]"))));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("input[type=checkbox]")));
// написать метод на проверку того, что чекбокса нет (или обработать эксепшн через TestNG)
    }

    @Test
    public void dropdownListTest(){
        WebElement ref = driver.findElement(By.linkText("Dropdown"));
        ref.click();

        Select dropdown = new Select(driver.findElement(By.linkText("Dropdown")));
//        new Select(driver.findElement(B))

//                dropdown = driver.findElement(By.id("dropdown"));

//        WebElement dropdown = driver.findElement(By.id("dropdown"));
//         Assert.assertEquals(dropdown.getText(), "      Please select an option\n" +
//                "      Option 1\n" +
//                "      Option 2\n" +
//                "  ");
//        dropdown.click();
//        dropdown.findElement(By.linkText("Option 2")).click();
    }
        @Test
        public void formAuthenticationPositiveTest() throws InterruptedException {
            WebElement ref = driver.findElement(By.linkText("Form Authentication"));
            ref.click();
            WebElement userNameInput = driver.findElement(By.id("username"));
            WebElement passwordInput = driver.findElement(By.id("password"));
            WebElement buttonLogin = driver.findElement(By.className("radius"));

            Assert.assertEquals(userNameInput.getText(), "");
            Assert.assertEquals(passwordInput.getText(), "");

            userNameInput.sendKeys("tomsmith");
            passwordInput.sendKeys("SuperSecretPassword!");
            buttonLogin.click();

            Assert.assertTrue(driver.findElement(By.id("flash")).getText().contains("You logged into a secure area!"));
        }
}

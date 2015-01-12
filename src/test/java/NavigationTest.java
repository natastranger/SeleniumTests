import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by Admin on 22.12.2014.
 */
public class NavigationTest {
    private static final String FIRST_PAGE = "http://www.tut.by";
    private static final String SECOND_PAGE = "http://www.onliner.by/";
    private WebDriver driver;

    @BeforeMethod
    public void before() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(FIRST_PAGE);
    }

    @AfterMethod
    public void after() {
        driver.quit();
    }

    @Test
    public void exampleOpenPageTest() {
        String titleMainPage = driver.getTitle();
        Assert.assertTrue(driver.getTitle().contains("Белорусский портал TUT.BY"));
    }

    @Test
    public void exampleBackPageTest() {
        driver.get(SECOND_PAGE);
        driver.navigate().back();
        Assert.assertTrue(driver.getTitle().contains("Белорусский портал TUT.BY"));
    }

    @Test
    public void exampleForwardPageTest(){
        driver.get(SECOND_PAGE);
        Waiters.waitForTilte(driver, "Onliner.by");
        driver.navigate().back();
        Waiters.waitForTilte(driver, "Белорусский портал TUT.BY");
        driver.navigate().forward();
        Waiters.waitForTilte(driver, "Onliner.by");
        Assert.assertTrue(driver.getTitle().contains("Onliner.by"));
    }

    @Test
    public void exampleRefreshTest() {
        driver.navigate().refresh();
        Assert.assertTrue(driver.getCurrentUrl().contains(FIRST_PAGE));
    }

}

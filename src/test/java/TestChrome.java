import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by Admin on 22.12.2014.
 */
public class TestChrome {
    private static final String FIRST_PAGE = "http://www.tut.by";
    private static final String SECOND_PAGE = "http://www.habrahabr.ru";
    private WebDriver driverChrome;

    @BeforeMethod
    public void before(){
        driverChrome = new ChromeDriver();
        driverChrome.manage().window().maximize();
        driverChrome.get(FIRST_PAGE);
    }

    @AfterMethod
    public void after(){
        driverChrome.close();
    }

    @Test
    public void exampleOpenPageTest() {
        String titleMainPage = driverChrome.getTitle();
        Assert.assertTrue(driverChrome.getTitle().contains("Белорусский портал TUT.BY"));
    }

    @Test
    public void exampleBackPageTest() {
        driverChrome.get(SECOND_PAGE);
        driverChrome.navigate().back();
        Assert.assertTrue(driverChrome.getCurrentUrl().contains("tut.by"));
    }

    @Test
    public void exampleForwardPageTest() {
        driverChrome.get(SECOND_PAGE);
        driverChrome.navigate().back();
        driverChrome.navigate().forward();
        Assert.assertTrue(driverChrome.getCurrentUrl().contains("habrahabr.ru"));
    }

    @Test
    public void exampleRefreshTest() {
        Assert.assertTrue(driverChrome.getCurrentUrl().contains(FIRST_PAGE));
    }

}

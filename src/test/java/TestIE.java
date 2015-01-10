import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Admin on 22.12.2014.
 */
public class TestIE {
    @Test
    public void exampleIEOpenPageTest() {
        WebDriver driverIE = new InternetExplorerDriver();
        driverIE.get("http://tut.by");

        driverIE.manage().window().maximize();
        String titleMainPage = driverIE.getTitle();
        Assert.assertTrue(driverIE.getTitle().contains("Белорусский портал TUT.BY"));
        driverIE.close();
//        String URLMainPage = driverIE.getCurrentUrl();
//        driverIE.navigate().to("http://afisha.tut.by/");
//        String URLAfishaPage = driverIE.getTitle();
//        driverIE.navigate().back();
//
        driverIE.close();

    }

    public void exampleIEBackPageTest() {
        WebDriver driverIE = new InternetExplorerDriver();
        driverIE.get("http://tut.by");
        driverIE.manage().window().maximize();
        String titleMainPage = driverIE.getTitle();
        String URLMainPage = driverIE.getCurrentUrl();
        driverIE.navigate().to("http://afisha.tut.by/");
        String URLAfishaPage = driverIE.getTitle();
        driverIE.navigate().back();

        driverIE.close();


    }




    @Test
    public void exampleFireFoxTest() {
        WebDriver driverFireFox = new FirefoxDriver();
        driverFireFox.get("http://tut.by");
        System.out.println(driverFireFox.getTitle());
        driverFireFox.quit();
    }
}


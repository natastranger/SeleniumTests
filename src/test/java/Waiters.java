import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by HP-G62 on 11.01.2015.
 */
public class Waiters {
    
    //    ожидание загрузки всех JS на странице
    public static void waitForJquery(WebDriver driver) throws InterruptedException {
        new WebDriverWait(driver, 60).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                JavascriptExecutor js = (JavascriptExecutor) webDriver;
                return (Boolean) js.executeScript("return jQuery.active == 0");
            }
        });
    }

    //    ожидание определенного тайтла на странице
    public static void waitForTilte(WebDriver driver, String title) {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return driver.getTitle().contains(title);
            }
        });
    }


    //    ожидание алерта с определенным текстом и любого;
    public void waitForAlert(WebDriver driver, String alertTitle) {

    }

    //    ожидание алерта с определенным текстом и любого;
    void waitForAlert(WebDriver driver) {

    }

    //    ожидание определенного количества открытых окон (вкладок);
    void waitForHandlesMoreThan(WebDriver driver, int handleCount) {

    }

    //    ожидание пока элемент будет кликабельным (enabled);
    void waitForElementClickable(WebDriver driver, By locator) {

    }

    //    ожидание, пока количество элементов, найденных данным локатором не будет больше указанного количества;
    void waitForElementsPresentMoreThan(WebDriver driver, By locator, int count) {

    }

    //    ожидание определенного текста внутри элемента
    void waitForTextInElementPresent(WebDriver driver, By locator, String text) {

    }


    //    кастомерская ожидалки - ожидает, когда кол-во табов больше, чем одна

/*   AJAX работает асинхронно, и страница может обновиться в любой момент
и, если известно, что на сайте работает JQuery (AJAX), то лучше поставить ожидалки,
что в загрузке DOM-модели состояние страницы complete


        public static void waitForJquery() {
            getWaiter().until(new WebDriver webDriver){
                JavascriptExecutor js= (JavascriptExecutor) webDriver;
                return Boolean js.executeScript("return JQuery.active == 0");
            }
        }
 */


//        WebDriverWait wait = new WebDriverWait(driver, 60);
//        wait.until(ExpectedConditions < Boolean > () {
//            @Override
//            public Boolean apply (WebDriver webDriver){
//                return driver.getWindowHandles().size() > 1;
//            }


//    public static void waitforJquery(WebDriver driver) {
//        WebDriverWait wait = new WebDriverWait(driver, 60);
//        wait.until(ExpectedConditions < Boolean > () {
//            @Override
//            public Boolean apply (WebDriver webDriver){
//                return driver.getWindowHandles().size() > 1;
//            }
//
//    public static WebElement waitForElement(final WebDriver driver, final By locator)
//            throws InterruptedException {
//        WebDriverWait wait = new WebDriverWait(driver, 60);
//        ExpectedConditions<WebElement> elementPresence = new ExpectedConditions<WebElement>
//        return wait.until(ExpectedConditions < WebElement > () {
//            public WebElement apply (WebDriver WebElement){
//                @Override
//
//
//            }
//            return driver.getWindowHandles().size() > 1;
////            }
//        }
//    }
//
//    );

}

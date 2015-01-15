package base;

import org.openqa.selenium.WebDriver;

/**
 * Created by HP-G62 on 15.01.2015.
 */
public class PageBase {
    protected final WebDriver driver;

    public PageBase(WebDriver driver) {
        this.driver = driver;
    }
}

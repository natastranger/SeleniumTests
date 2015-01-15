package pageElements;

import base.ElementBase;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HP-G62 on 15.01.2015.
 */

public class Table extends ElementBase {
    public Table(SearchContext host, By locator) {
        super(host, locator);
    }

    public List<WebElement> getHeader() {
        return element.findElements(By.tagName("th"));
    }

    public List<String> getHeaderAsString() {
        return new ArrayList<String>();
    }

    public List<List<WebElement>> getRows() {
        return new ArrayList<List<WebElement>>();
    }

//    public List<WebElement> getRow(int row) {
//
//    }

    public List<List<String>> getRowsAsString() {
        return new ArrayList<List<String>>();
    }

    public List<List<WebElement>> getColumns() {
        return new ArrayList<List<WebElement>>();
    }

//    public List<WebElement> getColumn(int col) {
//
//    }

    public List<List<String>> getColumnsAsString() {
        return new ArrayList<List<String>>();
    }

//    public WebElement getCell(int row, int col) {
//        return;
//    }
}

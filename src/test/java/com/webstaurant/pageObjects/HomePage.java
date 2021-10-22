package com.webstaurant.pageObjects;

import com.webstaurant.common.LazyWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Page Object encapsulates the Main Page
 */
public class HomePage extends BasePage {

    private final By searchVal = By.id("searchval");
    private final By searchButton = By.cssSelector("#searchForm > div > button");

    public HomePage(LazyWebDriver driver) {
        super(driver);
    }

    /**
     * used to get url
     * @param url
     */
    public void getUrl(String url) {
        super.getUrl(url);
    }

    /**
     * Used to search.
     * @param searchTerm item to search for
     */
    public void search(String searchTerm) {
        waitForElementToAppear(searchVal);
        driver.findElement(searchVal).sendKeys(searchTerm);
        waitForElementToAppear(searchButton);
        driver.findElement(searchButton).click();
    }
}
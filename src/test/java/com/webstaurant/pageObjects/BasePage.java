package com.webstaurant.pageObjects;

import com.webstaurant.common.LazyWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    private static final int TIMEOUT = 10;

    protected LazyWebDriver driver;
    private WebDriverWait wait;

    public BasePage(LazyWebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, TIMEOUT), this);
    }

    /**
     * Used for getting url
     * @param url
     */
    protected void getUrl(String url) {
        driver.get(url);
    }

    /**
     * fluent wait for element to appear
     * @param locator
     */
    protected void waitForElementToAppear(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * fluent wait for element to disappear
     * @param locator
     */
    protected void waitForElementToDisappear(By locator) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

}
package com.webstaurant.pageObjects;

import com.webstaurant.common.LazyWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class NavPage extends BasePage{

    private final By itemDesc = By.cssSelector("a[data-testid='itemDescription']");
    private final By prevPage = By.cssSelector("#paging > nav > ul > li.rc-pagination-prev > a");
    private final By nextPage = By.cssSelector("#paging > nav > ul > li.rc-pagination-next > a");
    private final By next = By.cssSelector("#paging > nav > ul > li.rc-pagination-next");

    private final List<String> itemDescriptionsWithoutWord = new ArrayList<>();

    public NavPage(LazyWebDriver driver) {
        super(driver);
    }

    /**
     * Used for getting item descriptions with the "word".
     * @param word
     */
    private void getItemDescriptionsWithoutWord(String word) {
        List<WebElement> itemElements = driver.findElements(itemDesc);
        for (WebElement itemElement : itemElements) {
            if (!itemElement.getText().contains(word)) {
                itemDescriptionsWithoutWord.add(itemElement.getText());
            }
        }
    }

    /**
     * iterates pages using nav next buttons. Uses getItemDescriptionsWithoutWord to scrape page.
     * @param word
     * @return
     */
    public List<String> checkPageForWordsNotInItemDescription(String word) {
        waitForElementToAppear(next);
        String classAttr = driver.findElement(next).getAttribute("class");
        while (!classAttr.contains("rc-pagination-disabled")) {
            getItemDescriptionsWithoutWord(word);
            clickNextPage();
            waitForElementToAppear(next);
            classAttr = driver.findElement(next).getAttribute("class");
        }
        return itemDescriptionsWithoutWord;
    }

    /**
     * used for clicking next page of item results
     */
    public void clickNextPage() {
        waitForElementToAppear(nextPage);
        driver.findElement(nextPage).click();
    }

    /**
     * used for clicking prev page of item results
     */
    public void clickPrevPage() {
        waitForElementToAppear(nextPage);
        driver.findElement(prevPage).click();
    }
}

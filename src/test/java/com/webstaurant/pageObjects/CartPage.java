package com.webstaurant.pageObjects;

import com.webstaurant.common.LazyWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage extends BasePage {

    private final By watNotIfWrapper = By.cssSelector("#watnotif-wrapper > div");
    private final By modalWindowButtons = By.cssSelector("div.ReactModalPortal > div > div > footer > button");
    private final By emptycart = By.cssSelector("a.emptyCartButton");
    private final By primaryButton = By.cssSelector("div.modal-content > div.modal-footer > button.btn-primary");
    private final By checkout = By.cssSelector("a[data-testid='cart-nav-link']");
    private final By addCartInputs = By.cssSelector("input[data-testid='itemAddCart']");
    private final By emptyCartText = By.cssSelector("div.empty-cart__text > p.header-1");

    public CartPage(LazyWebDriver driver) {
        super(driver);
    }

    /**
     * used for adding the last item on the page of result to the cart
     */
    public void addLastItemToCart() {
        List<WebElement> items = driver.findElements(addCartInputs);
        int itemCountOnPage = items.size() - 1;
        items.get(itemCountOnPage).click();
        waitForElementToAppear(modalWindowButtons);
        List<WebElement> buttons = driver.findElements(modalWindowButtons);
        for (WebElement button : buttons) {
            if (button.getText().equals("Add To Cart")) {
                button.click();
            }
        }
    }

    /**
     * used to click the checkout button
     */
    public void  checkOutCart (){
        waitForElementToDisappear(watNotIfWrapper);
        driver.findElement(checkout).click();
    }

    /**
     * Used for clicking the empty cart button
     * @return Strng the empty cart message
     */
    public String emptyCart () {
        waitForElementToAppear(emptycart);
        driver.findElement(emptycart).click();
        waitForElementToAppear(primaryButton);
        driver.findElement(primaryButton).click();
        waitForElementToAppear(emptyCartText);
        return driver.findElement(emptyCartText).getText();
    }
}

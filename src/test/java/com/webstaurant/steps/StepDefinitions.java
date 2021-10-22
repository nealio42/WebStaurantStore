package com.webstaurant.steps;

import com.webstaurant.pageObjects.CartPage;
import com.webstaurant.pageObjects.HomePage;
import com.webstaurant.pageObjects.NavPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StepDefinitions {

    HomePage homePage;
    CartPage cartPage;
    NavPage navPage;
    private static final String HOME_PAGE = "https://www.webstaurantstore.com/";

    public StepDefinitions(HomePage homePage, CartPage cartPage, NavPage navPage) {
        this.homePage = homePage;
        this.cartPage = cartPage;
        this.navPage = navPage;
    }

    @Given("I am on the Home Page")
    public void iAmOnTheHomePage() {
        homePage.getUrl(HOME_PAGE);
    }

    @And("I search for {string}")
    public void iSearchFor(String searchTerm) {
        homePage.search(searchTerm);
    }

    @Then("every product item in the search result has the word {string} in its title.")
    public void everyProductItemInTheSearchResultHasTheWordInItsTitle(String word) {
        List<String> wordNotInItemDescriptionList = navPage.checkPageForWordsNotInItemDescription(word);
        StringBuilder sb = new StringBuilder();
        sb.append("failure: the items \n");
        for (String itemDescWithoutWord : wordNotInItemDescriptionList) {
            sb.append("[");
            sb.append(itemDescWithoutWord);
            sb.append("], \n");
        }
        sb.append("\n do not have the word ");
        sb.append(word);
        sb.append(" in the item description.");
        assertTrue( wordNotInItemDescriptionList.size() < 1, sb.toString());
    }

    @When("I add the last of the found items to the Cart")
    public void iAddTheLastOfTheFoundItemsToTheCart() {
        cartPage.addLastItemToCart();
        cartPage.checkOutCart();

    }

    @Then("I can empty Cart and message indicates {string}")
    public void iCanEmptyCartAndMessageIndicates(String expectedText) {
        String actualText = cartPage.emptyCart();
        assertEquals(expectedText, actualText);
    }
}

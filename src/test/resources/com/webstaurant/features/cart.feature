Feature: Cart

  Background:
    Given I am on the Home Page

  Scenario: Search for item. Verify product has word. Add last of found items to Cart. Empty Cart.
    And I search for "stainless work table"
    Then every product item in the search result has the word "Table" in its title.
    When I add the last of the found items to the Cart
    Then I can empty Cart and message indicates "Your cart is empty."

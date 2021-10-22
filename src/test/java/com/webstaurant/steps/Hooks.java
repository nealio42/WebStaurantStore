package com.webstaurant.steps;

import com.webstaurant.common.LazyWebDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;

public class Hooks {

    LazyWebDriver driver;

    public Hooks(LazyWebDriver driver) {
        this.driver = driver;
    }


    @Before
    public void beforeScenario() {

    }

    @After
    public void afterScenario() {
        driver.quit();
    }
}

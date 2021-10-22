package com.webstaurant.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.picocontainer.Disposable;

import java.util.List;
import java.util.Set;

public class LazyWebDriver implements WebDriver, Disposable {

    private WebDriver delegate = null;

    private WebDriver getDelegate() {
        if (delegate == null) {
            System.setProperty("webdriver.chrome.driver", "lib/chromedriver");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--no-sandbox"); // Bypass OS security model
            options.addArguments("--headless"); // headless
            options.addArguments("start-maximized"); // open Browser in maximized mode
            options.addArguments("disable-infobars"); // disabling infobars
            options.addArguments("--disable-extensions"); // disabling extensions
            options.addArguments("--disable-gpu"); // applicable to windows os only
            options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems

            delegate = new ChromeDriver(options);
        }
        return delegate;
    }

    @Override
    public void get(String url) {
        getDelegate().get(url);
    }

    @Override
    public String getCurrentUrl() {
        return getDelegate().getCurrentUrl();
    }

    @Override
    public String getTitle() {
        return getDelegate().getTitle();
    }

    @Override
    public List<WebElement> findElements(By by) {
        return getDelegate().findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        return getDelegate().findElement(by);
    }

    @Override
    public String getPageSource() {
        return getDelegate().getPageSource();
    }

    @Override
    public void close() {
        getDelegate().close();
    }

    @Override
    public void quit() {
        getDelegate().quit();
    }

    @Override
    public Set<String> getWindowHandles() {
        return getDelegate().getWindowHandles();
    }

    @Override
    public String getWindowHandle() {
        return getDelegate().getWindowHandle();
    }

    @Override
    public TargetLocator switchTo() {
        return getDelegate().switchTo();
    }

    @Override
    public Navigation navigate() {
        return getDelegate().navigate();
    }

    @Override
    public Options manage() {
        return getDelegate().manage();
    }

    @Override
    public void dispose() {
        System.out.println("Killing WebDriver");
        if(delegate != null){
            delegate.quit();
        }
    }
}

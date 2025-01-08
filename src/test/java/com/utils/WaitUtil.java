package com.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class WaitUtil {

    private static WebDriver driver;

    public WaitUtil(WebDriver driver) {
        this.driver = driver;
    }

    // Wait for an element to be visible
    public static WebElement waitForElementVisible(By locator, int timeout) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeout))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Wait for an element to be clickable
    public static WebElement waitForElementClickable(By locator, int timeout) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeout))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    // Wait for an element to be present
    public static WebElement waitForElementPresence(By locator, int timeout) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeout))
                .until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    // Wait for a page to load completely
    public static void waitForPageLoad(int timeout) {
        new WebDriverWait(driver, Duration.ofSeconds(timeout))
                .until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }
}

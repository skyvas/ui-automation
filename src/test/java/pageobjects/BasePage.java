package pageobjects;

import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.JsonUtil;
import utils.LocatorUtil;
import utils.WaitUtil;

public class BasePage {
    protected WebDriver driver;
    protected JSONObject locators;
    protected LocatorUtil locatorUtil;
    protected WaitUtil waitUtil;

    /**
     * Constructor to initialize WebDriver, LocatorUtil, and the JSON file with locators.
     *
     * @param driver WebDriver instance
     */
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.locators = JsonUtil.loadJson(getPageJsonFileName());
        this.locatorUtil = new LocatorUtil(locators);
        this.waitUtil = new WaitUtil(driver);
    }

    /**
     * Method to be overridden by child classes to specify the JSON locator file.
     *
     * @return JSON file name for the page locators.
     */
    protected String getPageJsonFileName() {
        return "defaultPage.json";
    }

    /**
     * Retrieve the locator based on key and type.
     *
     * @param key          The key identifying the locator in JSON.
     * @param locatorType  The type of locator (id, xpath, css, etc.).
     * @return A By object representing the locator.
     */
    public By getLocator(String key, String locatorType) {
        return locatorUtil.getLocator(key, locatorType);
    }

    /**
     * Click an element after ensuring it is clickable.
     *
     * @param key          The key identifying the locator in JSON.
     * @param locatorType  The type of locator.
     */
    public void clickElement(String key, String locatorType) {
        By locator = getLocator(key, locatorType);
        waitUtil.waitForElementToBeClickable(locator).click();
    }

    /**
     * Wait for an element to be visible and return it.
     *
     * @param key          The key identifying the locator in JSON.
     * @param locatorType  The type of locator.
     * @return WebElement once it is visible.
     */
    public WebElement waitForElementToBeVisible(String key, String locatorType) {
        By locator = getLocator(key, locatorType);
        return waitUtil.waitForElementToBeVisible(locator);
    }

    /**
     * Enter text into an input field.
     *
     * @param key          The key identifying the locator in JSON.
     * @param locatorType  The type of locator.
     * @param text         The text to enter.
     */
    public void sendKeysToElement(String key, String locatorType, String text) {
        By locator = getLocator(key, locatorType);
        WebElement element = waitUtil.waitForElementToBeVisible(locator);
        element.sendKeys(text);
    }

    /**
     * Retrieve text from an element.
     *
     * @param key          The key identifying the locator in JSON.
     * @param locatorType  The type of locator.
     * @return The extracted text.
     */
    public String getElementText(String key, String locatorType) {
        By locator = getLocator(key, locatorType);
        WebElement element = waitUtil.waitForElementToBeVisible(locator);
        return element.getText();
    }

    /**
     * Clear an input field and enter new text.
     *
     * @param key          The key identifying the locator in JSON.
     * @param locatorType  The type of locator.
     * @param text         The text to enter after clearing.
     */
    public void clearAndSendKeys(String key, String locatorType, String text) {
        WebElement element = waitForElementToBeVisible(key, locatorType);
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Check if an element is present and visible on the page.
     *
     * @param key          The key identifying the locator in JSON.
     * @param locatorType  The type of locator.
     * @return True if the element is visible, false otherwise.
     */
    public boolean isElementPresent(String key, String locatorType) {
        try {
            WebElement element = waitForElementToBeVisible(key, locatorType);
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Capture a screenshot.
     *
     * @param screenshotName The name for the screenshot file.
     */
    public void takeScreenshot(String screenshotName) {
        // Implement screenshot capture using a utility class (if available)
        System.out.println("Taking screenshot: " + screenshotName);
    }

    /**
     * Navigate back in the browser history.
     */
    public void navigateBack() {
        driver.navigate().back();
    }

    /**
     * Navigate forward in the browser history.
     */
    public void navigateForward() {
        driver.navigate().forward();
    }

    /**
     * Refresh the current page.
     */
    public void refreshPage() {
        driver.navigate().refresh();
    }
}
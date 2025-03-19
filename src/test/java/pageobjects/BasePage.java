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
     * The locators are loaded from a JSON file specific to each page.
     *
     * @param driver WebDriver instance
     */
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.locators = JsonUtil.loadJson(getPageJsonFileName());  // Load locators from JSON
        this.locatorUtil = new LocatorUtil(locators);  // Initialize locator utility
        this.waitUtil = new WaitUtil(driver);  // Initialize wait utility
    }

    /**
     * Method to be overridden by child classes to specify the JSON locator file.
     *
     * @return JSON file name for the page locators.
     */
    protected String getPageJsonFileName() {
        return "defaultPage.json";  // Default JSON file, overridden in child classes
    }

    /**
     * Retrieve the locator based on the key from the JSON file.
     * The locator type (CSS, XPath, ID, etc.) is determined dynamically.
     *
     * @param key The key identifying the locator in JSON.
     * @return A By object representing the locator.
     */
    public By getLocator(String key) {
        return locatorUtil.getLocator(key);
    }

    /**
     * Click an element after ensuring it is clickable.
     * The locator type is automatically determined from JSON.
     *
     * @param key The key identifying the locator in JSON.
     */
    public void clickElement(String key) {
        By locator = getLocator(key);
        waitUtil.waitForElementToBeClickable(locator).click();
    }

    /**
     * Wait for an element to be visible and return it.
     * The locator type is automatically determined from JSON.
     *
     * @param key The key identifying the locator in JSON.
     * @return WebElement once it is visible.
     */
    public WebElement waitForElementToBeVisible(String key) {
        By locator = getLocator(key);
        return waitUtil.waitForElementToBeVisible(locator);
    }

    /**
     * Enter text into an input field.
     * The locator type is automatically determined from JSON.
     *
     * @param key  The key identifying the locator in JSON.
     * @param text The text to enter.
     */
    public void sendKeysToElement(String key, String text) {
        WebElement element = waitForElementToBeVisible(key);
        element.sendKeys(text);
    }

    /**
     * Retrieve text from an element.
     * The locator type is automatically determined from JSON.
     *
     * @param key The key identifying the locator in JSON.
     * @return The extracted text.
     */
    public String getElementText(String key) {
        WebElement element = waitForElementToBeVisible(key);
        return element.getText();
    }

    /**
     * Clear an input field and enter new text.
     * The locator type is automatically determined from JSON.
     *
     * @param key  The key identifying the locator in JSON.
     * @param text The text to enter after clearing.
     */
    public void clearAndSendKeys(String key, String text) {
        WebElement element = waitForElementToBeVisible(key);
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Check if an element is present and visible on the page.
     * The locator type is automatically determined from JSON.
     *
     * @param key The key identifying the locator in JSON.
     * @return True if the element is visible, false otherwise.
     */
    public boolean isElementPresent(String key) {
        try {
            WebElement element = waitForElementToBeVisible(key);
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Capture a screenshot.
     * (Implementation should be provided by a utility class if needed)
     *
     * @param screenshotName The name for the screenshot file.
     */
    public void takeScreenshot(String screenshotName) {
        System.out.println("Taking screenshot: " + screenshotName);
    }
}

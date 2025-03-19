package pageobjects;

import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.JsonUtil;
import utils.LocatorUtil;
import utils.WaitUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BasePage {
    protected WebDriver driver;
    protected JSONObject locators;
    protected LocatorUtil locatorUtil;
    protected WaitUtil waitUtil;
    protected static final Logger logger = LogManager.getLogger(BasePage.class);

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
        logger.info("Attempting to click on element: {}", key);  // Log the element being clicked
        By locator = getLocator(key);
        waitUtil.waitForElementToBeClickable(locator).click();
        logger.info("Clicked on element: {}", key);  // Log successful click
    }

    /**
     * Wait for an element to be visible and return it.
     * The locator type is automatically determined from JSON.
     *
     * @param key The key identifying the locator in JSON.
     * @return WebElement once it is visible.
     */
    public WebElement waitForElementToBeVisible(String key) {
        logger.info("Waiting for element to be visible: {}", key);  // Log the element being waited for
        By locator = getLocator(key);
        WebElement element = waitUtil.waitForElementToBeVisible(locator);
        logger.info("Element visible: {}", key);  // Log when element becomes visible
        return element;
    }

    /**
     * Enter text into an input field.
     * The locator type is automatically determined from JSON.
     *
     * @param key  The key identifying the locator in JSON.
     * @param text The text to enter.
     */
    public void sendKeysToElement(String key, String text) {
        logger.info("Entering text '{}' into element: {}", text, key);  // Log text entry
        WebElement element = waitForElementToBeVisible(key);
        element.sendKeys(text);
        logger.info("Text '{}' entered into element: {}", text, key);  // Log successful text entry
    }

    /**
     * Retrieve text from an element.
     * The locator type is automatically determined from JSON.
     *
     * @param key The key identifying the locator in JSON.
     * @return The extracted text.
     */
    public String getElementText(String key) {
        logger.info("Retrieving text from element: {}", key);  // Log text retrieval
        WebElement element = waitForElementToBeVisible(key);
        String text = element.getText();
        logger.info("Retrieved text from element {}: {}", key, text);  // Log the text retrieved
        return text;
    }

    /**
     * Clear an input field and enter new text.
     * The locator type is automatically determined from JSON.
     *
     * @param key  The key identifying the locator in JSON.
     * @param text The text to enter after clearing.
     */
    public void clearAndSendKeys(String key, String text) {
        logger.info("Clearing and entering text '{}' into element: {}", text, key);  // Log clearing and text entry
        WebElement element = waitForElementToBeVisible(key);
        element.clear();
        element.sendKeys(text);
        logger.info("Text '{}' entered into element after clearing: {}", text, key);  // Log successful text entry
    }

    /**
     * Check if an element is present and visible on the page.
     * The locator type is automatically determined from JSON.
     *
     * @param key The key identifying the locator in JSON.
     * @return True if the element is visible, false otherwise.
     */
    public boolean isElementPresent(String key) {
        logger.info("Checking if element is present: {}", key);  // Log element presence check
        try {
            WebElement element = waitForElementToBeVisible(key);
            boolean isDisplayed = element.isDisplayed();
            logger.info("Element {} is displayed: {}", key, isDisplayed);  // Log the result of the check
            return isDisplayed;
        } catch (Exception e) {
            logger.error("Element {} is not present: {}", key, e.getMessage());  // Log error if element is not found
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
        logger.info("Taking screenshot: {}", screenshotName);  // Log screenshot action
    }
}

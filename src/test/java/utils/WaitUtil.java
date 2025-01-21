package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

/**
 * Utility class to handle common WebDriver wait operations.
 */
public class WaitUtil {

    private WebDriver driver;
    private int defaultTimeout;
    protected ConfigReader config;

    /**
     * Constructor to initialize WaitUtil with WebDriver and default timeout from config.properties.
     *
     * @param driver WebDriver instance
     */
    public WaitUtil(WebDriver driver) {
        this.driver = driver;
        this.defaultTimeout = loadDefaultTimeoutFromConfig();
    }

    /**
     * Load the default timeout value from the config.properties file.
     *
     * @return Default timeout in seconds
     */
    private int loadDefaultTimeoutFromConfig() {
        Properties properties = new Properties();
        try {
            // Load the properties file from the resources folder
            InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties");

            if (input == null) {
                System.err.println("Sorry, unable to find config.properties");
                return 10; // Return default value if properties file is not found
            }

            properties.load(input);
            input.close();

            return Integer.parseInt(properties.getProperty("defaultTimeout", "10")); // Default to 10 seconds if not specified
        } catch (IOException | NumberFormatException e) {
            System.err.println("Failed to load default timeout from config.properties. Using default value of 30 seconds.");
            return 10;
        }
    }

    /**
     * Wait for an element to be visible on the page.
     *
     * @param locator By locator of the element
     * @return WebElement once it is visible
     */
    public WebElement waitForElementVisible(By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(defaultTimeout))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Wait for an element to be visible on the page with a custom timeout.
     *
     * @param locator By locator of the element
     * @param timeout Timeout in seconds
     * @return WebElement once it is visible
     */
    public WebElement waitForElementVisible(By locator, int timeout) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeout))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Wait for an element to be clickable.
     *
     * @param locator By locator of the element
     * @return WebElement once it is clickable
     */
    public WebElement waitForElementClickable(By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(defaultTimeout))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Wait for an element to be clickable with a custom timeout.
     *
     * @param locator By locator of the element
     * @param timeout Timeout in seconds
     * @return WebElement once it is clickable
     */
    public WebElement waitForElementClickable(By locator, int timeout) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeout))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Wait for an element to be present in the DOM.
     *
     * @param locator By locator of the element
     * @return WebElement once it is present
     */
    public WebElement waitForElementPresence(By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(defaultTimeout))
                .until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    /**
     * Wait for an element to be present in the DOM with a custom timeout.
     *
     * @param locator By locator of the element
     * @param timeout Timeout in seconds
     * @return WebElement once it is present
     */
    public WebElement waitForElementPresence(By locator, int timeout) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeout))
                .until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    /**
     * Wait for the page to load completely.
     */
    public void waitForPageLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(defaultTimeout))
                .until(webDriver -> ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState").equals("complete"));
    }

    /**
     * Wait for the page to load completely with a custom timeout.
     *
     * @param timeout Timeout in seconds
     */
    public void waitForPageLoad(int timeout) {
        new WebDriverWait(driver, Duration.ofSeconds(timeout))
                .until(webDriver -> ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState").equals("complete"));
    }

    /**
     * Wait for an alert to be present.
     *
     * @return Alert once it is present
     */
    public Alert waitForAlert() {
        return new WebDriverWait(driver, Duration.ofSeconds(defaultTimeout))
                .until(ExpectedConditions.alertIsPresent());
    }

    /**
     * Wait for an alert to be present with a custom timeout.
     *
     * @param timeout Timeout in seconds
     * @return Alert once it is present
     */
    public Alert waitForAlert(int timeout) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeout))
                .until(ExpectedConditions.alertIsPresent());
    }

    /**
     * Wait for an element's attribute to contain a specific value.
     *
     * @param locator   By locator of the element
     * @param attribute The attribute to check
     * @param value     The value to be contained
     * @return true if the attribute contains the specified value
     */
    public boolean waitForAttributeContains(By locator, String attribute, String value) {
        return new WebDriverWait(driver, Duration.ofSeconds(defaultTimeout))
                .until(ExpectedConditions.attributeContains(locator, attribute, value));
    }

    /**
     * Wait for an element's attribute to contain a specific value with a custom timeout.
     *
     * @param locator   By locator of the element
     * @param attribute The attribute to check
     * @param value     The value to be contained
     * @param timeout   Timeout in seconds
     * @return true if the attribute contains the specified value
     */
    public boolean waitForAttributeContains(By locator, String attribute, String value, int timeout) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeout))
                .until(ExpectedConditions.attributeContains(locator, attribute, value));
    }
}

package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

public class WaitUtil {
    private WebDriver driver;
    private int defaultTimeout;

    public WaitUtil(WebDriver driver) {
        this.driver = driver;
        this.defaultTimeout = loadDefaultTimeoutFromConfig();
    }

    private int loadDefaultTimeoutFromConfig() {
        Properties properties = new Properties();
        try {
            InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties");
            if (input == null) {
                System.err.println("Unable to find config.properties");
                return 10;
            }
            properties.load(input);
            input.close();
            return Integer.parseInt(properties.getProperty("defaultTimeout", "10"));
        } catch (Exception e) {
            System.err.println("Failed to load default timeout. Using default value of 10 seconds.");
            return 10;
        }
    }

    public WebElement waitForElementToBeVisible(By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(defaultTimeout))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForElementToBeVisible(By locator, int timeout) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeout))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForElementToBeClickable(By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(defaultTimeout))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    public WebElement waitForElementToBeClickable(By locator, int timeout) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeout))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    public WebElement waitForElementToBePresent(By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(defaultTimeout))
                .until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public WebElement waitForElementToBePresent(By locator, int timeout) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeout))
                .until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void waitForPageLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(defaultTimeout))
                .until(webDriver -> ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState").equals("complete"));
    }

    public void waitForPageLoad(int timeout) {
        new WebDriverWait(driver, Duration.ofSeconds(timeout))
                .until(webDriver -> ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState").equals("complete"));
    }

    public Alert waitForAlert() {
        return new WebDriverWait(driver, Duration.ofSeconds(defaultTimeout))
                .until(ExpectedConditions.alertIsPresent());
    }

    public Alert waitForAlert(int timeout) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeout))
                .until(ExpectedConditions.alertIsPresent());
    }

    public boolean waitForAttributeToContain(By locator, String attribute, String value) {
        return new WebDriverWait(driver, Duration.ofSeconds(defaultTimeout))
                .until(ExpectedConditions.attributeContains(locator, attribute, value));
    }

    public boolean waitForAttributeToContain(By locator, String attribute, String value, int timeout) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeout))
                .until(ExpectedConditions.attributeContains(locator, attribute, value));
    }
}
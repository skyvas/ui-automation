package com.tests;
import com.utils.BrowserUtil;
import com.utils.ConfigReader;
import com.utils.ScreenshotUtil;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    protected WebDriver driver;
    protected ConfigReader config;
    protected BrowserUtil browserUtil;

    @BeforeMethod
    public void setUp() {
        // Load config
        config = new ConfigReader();

        // Get the headless mode property (defaults to false)
        String isHeadless = System.getProperty("headless", config.getProperty("headless_mode"));

        // Set the ChromeDriver path
        System.setProperty("webdriver.chrome.driver", config.getProperty("chromedriver_path"));

        // Set ChromeOptions for headless mode
        ChromeOptions options = new ChromeOptions();
        if (Boolean.parseBoolean(isHeadless)) {
            options.addArguments("--headless");
            options.addArguments("--window-size=1920x1080");  // Ensures proper viewport size
            options.addArguments("--disable-gpu");            // Disable GPU hardware acceleration
            options.addArguments("--no-sandbox");            // Improve stability in headless mode
        } else {
            options.addArguments("--start-maximized");      // Maximize window in non-headless mode
        }

        // Initialize the ChromeDriver with options
        driver = new ChromeDriver(options);
        browserUtil = new BrowserUtil(driver);  // Initialize BrowserUtil

        // Log to indicate headless or non-headless mode
        System.out.println("Running in " + (Boolean.parseBoolean(isHeadless) ? "Headless" : "Normal") + " mode.");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    /**
     * Handles exceptions in test methods, logs the error, and captures a screenshot.
     *
     * @param methodName the name of the failing test method
     * @param e          the exception that occurred
     */
    protected void handleTestException(String methodName, Exception e) {
        // Log the exception
        System.err.println("Test failed in method: " + methodName);
        System.err.println("Error: " + e.getMessage());

        // Define the screenshot path
        String screenshotPath = "/target/screenshots/" + methodName + "_FAILED.png";

        // Capture a screenshot
        try {
            ScreenshotUtil.takeScreenshot(driver, screenshotPath);
            System.out.println("Screenshot saved at: " + screenshotPath);
        } catch (Exception screenshotException) {
            System.err.println("Failed to capture screenshot: " + screenshotException.getMessage());
        }

        // Rethrow the exception to fail the test
        throw new RuntimeException(e);
    }
}
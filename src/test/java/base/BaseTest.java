package base;

import utils.BrowserUtil;
import utils.ConfigReader;
import utils.ScreenshotUtil;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseTest {
    protected WebDriver driver;
    protected ConfigReader config;
    protected BrowserUtil browserUtil;

    // Logger instance with protected visibility
    protected static final Logger logger = LogManager.getLogger(BaseTest.class);

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
        logger.info("Running in " + (Boolean.parseBoolean(isHeadless) ? "Headless" : "Normal") + " mode.");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            logger.info("Browser closed.");
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
        logger.error("Test failed in method: " + methodName);
        logger.error("Error: " + e.getMessage());

        // Define the screenshot path
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String screenshotPath = System.getProperty("user.dir") + "/target/screenshots/" + "screenshot_"+ methodName + "_" + timestamp + ".png";

        // Capture a screenshot
        try {
            ScreenshotUtil.takeScreenshot(driver, screenshotPath);
            logger.info("Screenshot saved at: " + screenshotPath);
        } catch (Exception screenshotException) {
            logger.error("Failed to capture screenshot: " + screenshotException.getMessage());
        }

        // Rethrow the exception to fail the test
        throw new RuntimeException(e);
    }
}

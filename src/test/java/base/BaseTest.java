package base;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.WebDriver;
import org.slf4j.MDC;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import utils.BrowserUtil;
import utils.ConfigReader;
import utils.ScreenshotUtil;

import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * BaseTest class serves as a foundation for all test classes, providing common setup and teardown functionality.
 * It includes the initialization of the browser, configuration loading, logging setup, and exception handling.
 */
@Listeners(io.qameta.allure.testng.AllureTestNg.class)
public class BaseTest {

    // WebDriver instance to interact with the browser
    protected WebDriver driver;

    // Configuration reader to fetch properties from the configuration files
    protected ConfigReader config;

    // Utility class for browser-related actions
    protected BrowserUtil browserUtil;

    // Logger instance for logging test events and actions
    protected static final Logger logger = LogManager.getLogger(BaseTest.class);

    /**
     * Setup method executed before each test method.
     * It initializes the WebDriver, configuration, and browser options based on the test environment.
     */
    @BeforeMethod
    public void setUp() {
        // Load configuration properties
        config = new ConfigReader();

        // Determine browser type from configuration
        String browser = config.getProperty("browser").toLowerCase();

        // Determine if the browser should run in headless mode
        String isHeadless = System.getProperty("headless", config.getProperty("headless_mode"));

        // Set up WebDriver based on the browser type
        switch (browser) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", config.getProperty("chromedriver_path"));
                ChromeOptions chromeOptions = new ChromeOptions();
                if (Boolean.parseBoolean(isHeadless)) {
                    chromeOptions.addArguments("--headless", "--window-size=1920x1080", "--disable-gpu", "--no-sandbox");
                } else {
                    chromeOptions.addArguments("--start-maximized");
                }
                driver = new ChromeDriver(chromeOptions);
                break;

            case "safari":
                driver = new SafariDriver();
                break;

            case "edge":
                System.setProperty("webdriver.edge.driver", config.getProperty("edgedriver_path"));
                EdgeOptions edgeOptions = new EdgeOptions();
                if (Boolean.parseBoolean(isHeadless)) {
                    edgeOptions.addArguments("--headless", "--window-size=1920x1080");
                }
                driver = new EdgeDriver(edgeOptions);
                break;

            default:
                throw new IllegalArgumentException("Unsupported browser set in config.properties file: " + browser);
        }

        // Existing setup logic
        String testName = this.getClass().getSimpleName();
        MDC.put("testName", testName);
        System.out.println("\n" + " Test execution started for: " + testName);
        browserUtil = new BrowserUtil(driver);
        logger.info("Running tests on browser: {} in {} mode.", browser, Boolean.parseBoolean(isHeadless) ? "Headless" : "Normal");
    }

    /**
     * Teardown method executed after each test method.
     * It performs browser cleanup, quits the WebDriver, and logs the completion.
     */
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            // Quit the WebDriver, closing the browser
            driver.quit();
            logStep("Browser teardown complete.");

            // Clear the MDC (Mapped Diagnostic Context) data to avoid memory leaks
            MDC.clear();
        }
    }

    /**
     * LogStep method logs specific steps during test execution.
     *
     * @param stepDescription A description of the step to be logged
     */
    @Step("{stepDescription}")
    public void logStep(String stepDescription) {
        logger.info(stepDescription);
    }

    /**
     * handleTestException method captures a screenshot and logs the details of a test failure.
     *
     * @param methodName The name of the test method where the failure occurred
     * @param e          The exception that caused the test failure
     * @return The screenshot as a byte array, or null if the screenshot cannot be captured
     */
    @Attachment(value = "Page Screenshot", type = "image/png")
    protected byte[] handleTestException(String methodName, Exception e) {
        // Log the error along with the method name and exception details
        logger.error("Test failed in method: {}", methodName, e);

        // Generate a timestamp for the screenshot file
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        // Define the path for the screenshot
        String screenshotPath = System.getProperty("user.dir") + "/target/screenshots/" + timestamp + "_screenshot_" + methodName + "-" + "FAILED" + ".png";

        try {
            // Capture the screenshot using ScreenshotUtil
            ScreenshotUtil.takeScreenshot(driver, screenshotPath);
            logger.info("Screenshot saved at: {}", screenshotPath);
            return Files.readAllBytes(Paths.get(screenshotPath));  // Return the screenshot as byte array
        } catch (Exception screenshotException) {
            // Log if the screenshot capture fails
            logger.error("Failed to capture screenshot: {}", screenshotException.getMessage());
        }

        return null;  // Return null if no screenshot is captured
    }
}

package base;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.slf4j.MDC;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import utils.BrowserUtil;
import utils.ConfigReader;
import utils.ScreenshotUtil;

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

    protected WebDriver driver;  // WebDriver instance to interact with the browser
    protected ConfigReader config;  // Configuration reader to fetch properties from configuration files
    protected BrowserUtil browserUtil;  // Utility class for browser-related actions
    protected static final Logger logger = LogManager.getLogger(BaseTest.class);

    /**
     * Setup method executed before each test method.
     * Initializes the WebDriver, configuration, and browser options based on the test environment.
     */
    @BeforeMethod
    public void setUp() {
        // Load configuration properties
        config = new ConfigReader();

        // Fetch browser type from configuration or system property
        String browser = System.getProperty("browser", config.getProperty("browser")).toLowerCase();
        String isHeadless = System.getProperty("headless", config.getProperty("headless_mode"));

        // Initialize WebDriver based on the browser type
        switch (browser) {
            case "chrome":
                driver = initChromeDriver(Boolean.parseBoolean(isHeadless));
                break;
            case "edge":
                driver = initEdgeDriver();
                break;
            case "safari":
                driver = initSafariDriver();
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        // Capture the test class name for logging
        String testName = this.getClass().getSimpleName();
        MDC.put("testName", testName);  // Store the test name in MDC for structured logging
        System.out.println("\n" + " Test execution started for: " + testName);

        browserUtil = new BrowserUtil(driver);  // Initialize BrowserUtil with WebDriver
        logger.info("Running tests on {} browser in {} mode.", browser, Boolean.parseBoolean(isHeadless) ? "Headless" : "Normal");
    }

    /**
     * Initialize ChromeDriver with optional headless mode.
     */
    private WebDriver initChromeDriver(boolean isHeadless) {
        System.setProperty("webdriver.chrome.driver", config.getProperty("chromedriver_path"));
        ChromeOptions options = new ChromeOptions();
        if (isHeadless) {
            options.addArguments("--headless", "--window-size=1920x1080", "--disable-gpu", "--no-sandbox");
        } else {
            options.addArguments("--start-maximized");
        }
        return new ChromeDriver(options);
    }

    /**
     * Initialize EdgeDriver.
     */
    private WebDriver initEdgeDriver() {
        System.setProperty("webdriver.edge.driver", config.getProperty("edgedriver_path"));
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--start-maximized");
        return new EdgeDriver(options);
    }

    /**
     * Initialize SafariDriver.
     */
    private WebDriver initSafariDriver() {
        // Safari WebDriver requires no additional configuration on macOS
        return new SafariDriver();
    }

    /**
     * Teardown method executed after each test method.
     * Cleans up the browser session and logs completion.
     */
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();  // Quit WebDriver, closing the browser
            logStep("Browser teardown complete.");
            MDC.clear();  // Clear MDC data to avoid memory leaks
        }
    }

    /**
     * Logs specific steps during test execution.
     *
     * @param stepDescription A description of the step to be logged
     */
    @Step("{stepDescription}")
    public void logStep(String stepDescription) {
        logger.info(stepDescription);
    }

    /**
     * Handles test exceptions by capturing screenshots and logging the details.
     *
     * @param methodName The name of the test method where the failure occurred
     * @param e          The exception that caused the test failure
     * @return The screenshot as a byte array, or null if the screenshot cannot be captured
     */
    @Attachment(value = "Page Screenshot", type = "image/png")
    protected byte[] handleTestException(String methodName, Exception e) {
        logger.error("Test failed in method: {}", methodName, e);
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String screenshotPath = System.getProperty("user.dir") + "/target/screenshots/" + timestamp + "_screenshot_" + methodName + "-FAILED" + ".png";

        try {
            ScreenshotUtil.takeScreenshot(driver, screenshotPath);
            logger.info("Screenshot saved at: {}", screenshotPath);
            return Files.readAllBytes(Paths.get(screenshotPath));
        } catch (Exception screenshotException) {
            logger.error("Failed to capture screenshot: {}", screenshotException.getMessage());
        }

        return null;  // Return null if no screenshot is captured
    }
}
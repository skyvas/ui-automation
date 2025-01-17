package base;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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
 * Base class for all tests, providing common setup and teardown functionality.
 * Includes browser initialization, configuration loading, logging, and exception handling.
 */
@Listeners(io.qameta.allure.testng.AllureTestNg.class)
public class BaseTest {
    protected WebDriver driver;
    protected ConfigReader config;
    protected BrowserUtil browserUtil;

    protected static final Logger logger = LogManager.getLogger(BaseTest.class);

    @BeforeMethod
    public void setUp() {
        config = new ConfigReader();
        String isHeadless = System.getProperty("headless", config.getProperty("headless_mode"));
        System.setProperty("webdriver.chrome.driver", config.getProperty("chromedriver_path"));

        ChromeOptions options = new ChromeOptions();
        if (Boolean.parseBoolean(isHeadless)) {
            options.addArguments("--headless", "--window-size=1920x1080", "--disable-gpu", "--no-sandbox");
        } else {
            options.addArguments("--start-maximized");
        }

        driver = new ChromeDriver(options);
        browserUtil = new BrowserUtil(driver);
        logger.info("Running in {} mode.", Boolean.parseBoolean(isHeadless) ? "Headless" : "Normal");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            logger.info("Browser closed.");
            logStep("Browser teardown complete.");
        }
    }

    @Step("{stepDescription}")
    public void logStep(String stepDescription) {
        logger.info(stepDescription);
    }

    @Attachment(value = "Page Screenshot", type = "image/png")
    protected byte[] handleTestException(String methodName, Exception e) {
        logger.error("Test failed in method: {}", methodName, e);

        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String screenshotPath = System.getProperty("user.dir") + "/target/screenshots/screenshot_" + methodName + "_" + timestamp + ".png";

        try {
            ScreenshotUtil.takeScreenshot(driver, screenshotPath);
            logger.info("Screenshot saved at: {}", screenshotPath);
            return Files.readAllBytes(Paths.get(screenshotPath));
        } catch (Exception screenshotException) {
            logger.error("Failed to capture screenshot: {}", screenshotException.getMessage());
        }

        return null;
    }
}

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

@Listeners(io.qameta.allure.testng.AllureTestNg.class) // Integrate Allure TestNG Listener
public class BaseTest {
    protected WebDriver driver;
    protected ConfigReader config;
    protected BrowserUtil browserUtil;

    // Logger instance
    protected static final Logger logger = LogManager.getLogger(BaseTest.class);

    @BeforeMethod
    public void setUp() {
        config = new ConfigReader();
        String isHeadless = System.getProperty("headless", config.getProperty("headless_mode"));
        System.setProperty("webdriver.chrome.driver", config.getProperty("chromedriver_path"));

        ChromeOptions options = new ChromeOptions();
        if (Boolean.parseBoolean(isHeadless)) {
            options.addArguments("--headless");
            options.addArguments("--window-size=1920x1080");
            options.addArguments("--disable-gpu");
            options.addArguments("--no-sandbox");
        } else {
            options.addArguments("--start-maximized");
        }

        driver = new ChromeDriver(options);
        browserUtil = new BrowserUtil(driver);
        logger.info("Running in " + (Boolean.parseBoolean(isHeadless) ? "Headless" : "Normal") + " mode.");
        logStep("Setup Browser");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            logger.info("Browser closed.");
            logStep("Teardown Browser");
        }
    }

    @Attachment(value = "Page Screenshot", type = "image/png")
    public byte[] attachScreenshot() {
        return ScreenshotUtil.takeScreenshotAsBytes(driver);
    }

    @Step("{stepDescription}")
    public void logStep(String stepDescription) {
        logger.info(stepDescription);
    }

    public void handleTestException(String methodName, Exception e) {
        logger.error("Test failed in method: " + methodName);
        logger.error("Error: " + e.getMessage());
        attachScreenshot();
        throw new RuntimeException(e);
    }
}
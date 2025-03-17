package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.slf4j.MDC;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.safari.SafariDriver;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

@Listeners(io.qameta.allure.testng.AllureTestNg.class)
public class InitialiseTest {

    protected WebDriver driver;
    protected ConfigReader config;
    protected BrowserUtil browserUtil;
    protected static final Logger logger = LogManager.getLogger(InitialiseTest.class);

    @BeforeMethod
    public void setUp() {
        config = new ConfigReader();
        String browser = config.getProperty("browser").toLowerCase();
        String isHeadless = System.getProperty("headless", config.getProperty("headless_mode"));

        // Check if running in CI/CD
        boolean isCiCd = Boolean.parseBoolean(System.getenv("CI") != null ? System.getenv("CI") : "false");

        switch (browser) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();

                if (isCiCd) {
                    // Use ChromeDriver path from environment/config in CI/CD
                    String chromeDriverPath = System.getenv("CHROMEDRIVER_PATH");
                    if (chromeDriverPath == null || chromeDriverPath.isEmpty()) {
                        chromeDriverPath = config.getProperty("chromedriver_path");
                    }
                    System.setProperty("webdriver.chrome.driver", chromeDriverPath);
                    logger.info("Running in CI/CD mode with ChromeDriver path: {}", chromeDriverPath);
                } else {
                    // Use WebDriverManager for local execution
                    WebDriverManager.chromedriver().setup();
                    logger.info("Running in local mode.");
                }

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
                if (isCiCd) {
                    String edgeDriverPath = System.getenv("EDGEDRIVER_PATH");
                    if (edgeDriverPath == null || edgeDriverPath.isEmpty()) {
                        edgeDriverPath = config.getProperty("edgedriver_path");
                    }
                    System.setProperty("webdriver.edge.driver", edgeDriverPath);
                    logger.info("Running in CI/CD mode with EdgeDriver path: {}", edgeDriverPath);
                } else {
                    WebDriverManager.edgedriver().setup();
                    logger.info("Running in local mode.");
                }

                EdgeOptions edgeOptions = new EdgeOptions();
                if (Boolean.parseBoolean(isHeadless)) {
                    edgeOptions.addArguments("--headless", "--window-size=1920x1080");
                }
                driver = new EdgeDriver(edgeOptions);
                break;

            default:
                throw new IllegalArgumentException("Unsupported browser set in config.properties file: " + browser);
        }

        String testName = this.getClass().getSimpleName();
        MDC.put("testName", testName);
        System.out.println("\n Test execution started for: " + testName);
        browserUtil = new BrowserUtil(driver);
        logger.info("Running tests on browser: {} in {} mode.", browser, Boolean.parseBoolean(isHeadless) ? "Headless" : "Normal");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            logStep("Browser teardown complete.");
            MDC.clear();
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
        String screenshotPath = System.getProperty("user.dir") + "/target/screenshots/" + timestamp + "_screenshot_" + methodName + "-" + "FAILED" + ".png";

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

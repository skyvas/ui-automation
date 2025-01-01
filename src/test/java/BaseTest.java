import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    protected WebDriver driver;
    protected ConfigReader config;

    @BeforeMethod
    public void setUp() {
        //Load config
        config = new ConfigReader();

        // Get the headless mode property (defaults to false)
        String isHeadless = System.getProperty("headless", config.getProperty("headless_mode"));

        // Manually specify the path to the ChromeDriver
        // Make sure to update the path to your local chromedriver.exe

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

        // Log to indicate headless or non-headless mode
        System.out.println("Running in " + (Boolean.parseBoolean(isHeadless) ? "Headless" : "Normal") + " mode.");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

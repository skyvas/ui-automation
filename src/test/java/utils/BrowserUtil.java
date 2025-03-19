package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BrowserUtil {

    private WebDriver driver;

    public BrowserUtil(WebDriver driver) {
        this.driver = driver;
    }

    // Maximize the browser window
    public void maximizeWindow() {
        driver.manage().window().maximize();
    }

    // Navigate to a specific URL
    public void navigateTo(String url) {
        driver.get(url);
    }

    /**
     * Navigate back in the browser history.
     */
    public void navigateBack() {
        driver.navigate().back();
    }

    /**
     * Navigate forward in the browser history.
     */
    public void navigateForward() {
        driver.navigate().forward();
    }

    /**
     * Refresh the current page.
     */
    public void refreshPage() {
        driver.navigate().refresh();
    }
}

package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.WaitUtil;

public class SummaryPage {
    private WebDriver driver;
    private WaitUtil waitUtil;  // Add an instance of the WaitUtil class

    // Constructor to initialize the WebDriver and WaitUtil
    public SummaryPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtil = new WaitUtil(driver);  // Initialize WaitUtil
    }

    // Locator for the total label div
    private By totalLabel = By.cssSelector("div.summary_total_label[data-test='total-label']");

    // Method to retrieve the total value as a string (without the "Total: $" part)
    public String getTotalValue() {
        waitUtil.waitForElementVisible(totalLabel);  // Add wait before interacting
        String totalText = driver.findElement(totalLabel).getText();
        return totalText.replace("Total: $", "").trim();
    }
}
package pageobjects;

import org.openqa.selenium.WebDriver;

public class SummaryPage extends BasePage {

    /**
     * Constructor to initialize WebDriver and load locators for SummaryPage.
     *
     * @param driver WebDriver instance
     */
    public SummaryPage(WebDriver driver) {
        super(driver);  // Inherit WebDriver and all functionality from BasePage
    }

    /**
     * Override the method to specify the JSON file for this page
     *
     * @return The name of the JSON file containing locators for this page.
     */
    @Override
    protected String getPageJsonFileName() {
        return "SummaryPage.json";  // SummaryPage-specific JSON file for locators
    }

    /**
     * Get the total value from the summary page.
     *
     * @return The total value as a string, without the "Total: $" part
     */
    public String getTotalValue() {
        waitForElementToBeVisible("totalLabel");  // Wait for the total label element
        String totalText = driver.findElement(getLocator("totalLabel")).getText();
        return totalText.replace("Total: $", "").trim();
    }
}
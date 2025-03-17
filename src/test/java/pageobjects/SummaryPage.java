package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.WaitUtil;
import utils.JsonUtil;
import org.json.JSONObject;

public class SummaryPage {
    private WebDriver driver;
    private WaitUtil waitUtil;
    private JSONObject locators;

    public SummaryPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtil = new WaitUtil(driver);
        this.locators = JsonUtil.loadJson("SummaryPage.json");
    }

    private By getLocator(String key) {
        return By.cssSelector(locators.getString(key));
    }

    /**
     * Get the total value from the summary page.
     *
     * @return The total value as a string, without the "Total: $" part
     */
    public String getTotalValue() {
        waitUtil.waitForElementVisible(getLocator("totalLabel"));  // Wait for the total label element
        String totalText = driver.findElement(getLocator("totalLabel")).getText();
        return totalText.replace("Total: $", "").trim();
    }
}

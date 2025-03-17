package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.WaitUtil;
import utils.JsonUtil;
import org.json.JSONObject;

public class CartPage {
    private WebDriver driver;
    private WaitUtil waitUtil;
    private JSONObject locators;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtil = new WaitUtil(driver);
        this.locators = JsonUtil.loadJson("CartPage.json");
    }

    private By getLocator(String key) {
        return By.cssSelector(locators.getString(key));
    }

    /**
     * Wait for the checkout button to be visible and then click it.
     */
    public void clickCheckout() {
        waitUtil.waitForElementClickable(getLocator("checkoutButton")).click();
    }
}

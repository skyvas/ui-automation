package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.WaitUtil;
import utils.JsonUtil;
import org.json.JSONObject;

public class InventoryPage {
    private WebDriver driver;
    private WaitUtil waitUtil;
    private JSONObject locators;

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtil = new WaitUtil(driver);
        this.locators = JsonUtil.loadJson("InventoryPage.json");
    }

    private By getLocator(String key) {
        return By.cssSelector(locators.getString(key));
    }

    /**
     * Wait for the add-to-cart button to be clickable and click it.
     */
    public void addAnItemToCart() {
        waitUtil.waitForElementClickable(getLocator("addToCartButton")).click();
    }

    /**
     * Wait for the cart link to be clickable and navigate to the cart.
     */
    public void goToCart() {
        waitUtil.waitForElementClickable(getLocator("cartLink")).click();
    }
}

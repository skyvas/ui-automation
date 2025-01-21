package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.WaitUtil;

public class InventoryPage {
    private WebDriver driver;
    private WaitUtil waitUtil;

    private By addToCartButton = By.cssSelector("*[data-test='add-to-cart-sauce-labs-bike-light']");
    private By cartLink = By.cssSelector("*[data-test='shopping-cart-link']");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtil = new WaitUtil(driver);
    }

    /**
     * Wait for the add-to-cart button to be clickable and click it.
     */
    public void addAnItemToCart() {
        waitUtil.waitForElementClickable(addToCartButton).click();
    }

    /**
     * Wait for the cart link to be clickable and navigate to the cart.
     */
    public void goToCart() {
        waitUtil.waitForElementClickable(cartLink).click();
    }
}

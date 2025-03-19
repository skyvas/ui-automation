package pageobjects;

import org.openqa.selenium.WebDriver;

public class InventoryPage extends BasePage {

    /**
     * Constructor to initialize WebDriver and load locators for InventoryPage.
     *
     * @param driver WebDriver instance
     */
    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected String getPageJsonFileName() {
        return "InventoryPage.json"; // Specifies the JSON locator file for this page
    }

    /**
     * Wait for the add-to-cart button to be clickable and click it.
     */
    public void addAnItemToCart() {
        clickElement("addToCartButton");
    }

    /**
     * Wait for the cart link to be clickable and navigate to the cart.
     */
    public void goToCart() {
        clickElement("cartLink");
    }
}

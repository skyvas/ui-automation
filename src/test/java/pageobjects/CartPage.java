package pageobjects;

import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {

    /**
     * Constructor to initialize WebDriver and load locators for CartPage.
     *
     * @param driver WebDriver instance
     */
    public CartPage(WebDriver driver) {
        super(driver);  // Inherit WebDriver and all functionality from BasePage
    }

    /**
     * Override the method to specify the JSON file for this page
     *
     * @return The name of the JSON file containing locators for this page.
     */
    @Override
    protected String getPageJsonFileName() {
        return "CartPage.json";  // CartPage-specific JSON file for locators
    }

    /**
     * Wait for the checkout button to be visible and then click it.
     */
    public void clickCheckout() {
        clickElement("checkoutButton");  // Uses the inherited clickElement method from BasePage
    }
}

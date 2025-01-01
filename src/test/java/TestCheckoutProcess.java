import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCheckoutProcess extends BaseTest {

    @Test
    public void testCheckoutProcess() {
        driver.get(config.getProperty("test_url"));

        LoginPage loginPage = new LoginPage(driver);
        InventoryPage inventoryPage = new InventoryPage(driver);
        CartPage cartPage = new CartPage(driver);
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        SummaryPage summaryPage = new SummaryPage(driver);

        // Login
        loginPage.enterUsername(config.getProperty("username"));
        loginPage.enterPassword(config.getProperty("password"));
        loginPage.clickLogin();

        // Add item to cart
        inventoryPage.addAnItemToCart();
        inventoryPage.goToCart();

        // Checkout
        cartPage.clickCheckout();

        checkoutPage.enterFirstName("a");
        checkoutPage.enterLastName("b");
        checkoutPage.enterPostalCode("c");
        checkoutPage.clickContinue();

        // Verify summary page
        // Get the total value from the SummaryPage
        String totalValue = summaryPage.getTotalValue();

        // Assert that the total value is "10.79"
        Assert.assertEquals(totalValue, "10.79", "Total value is not as expected!");
    }
}
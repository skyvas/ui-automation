package tests.smoke;

import base.BaseTest;
import pageobjects.LoginPage;
import pageobjects.InventoryPage;
import pageobjects.CartPage;
import pageobjects.CheckoutPage;
import pageobjects.SummaryPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCheckoutProcess extends BaseTest {

    @Test
    public void testCheckoutProcess() {
        try {
            browserUtil.navigateTo(config.getProperty("test_url"));

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

            checkoutPage.enterFirstName("John");
            checkoutPage.enterLastName("Doe");
            checkoutPage.enterPostalCode("12345");
            checkoutPage.clickContinue();

            // Verify summary page; it should be 10.79
            String totalValue = summaryPage.getTotalValue();
            Assert.assertEquals(totalValue, "10.79", "Total value is not as expected!");

        } catch (Exception e) {
            // Use the standardized exception handler in BaseTest
            handleTestException(new Object() {}.getClass().getEnclosingMethod().getName(), e);
            throw e;
        }
    }
}

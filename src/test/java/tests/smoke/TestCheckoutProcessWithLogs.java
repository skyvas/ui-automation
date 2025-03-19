package tests.smoke;

import utils.InitialiseTest;
import pageobjects.LoginPage;
import pageobjects.InventoryPage;
import pageobjects.CartPage;
import pageobjects.CheckoutPage;
import pageobjects.SummaryPage;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCheckoutProcessWithLogs extends InitialiseTest {

    @Test
    public void testCheckoutProcessWithLogs() {
        try {
            logger.info("Navigating to URL: " + config.getProperty("test_url"));
            browserUtil.navigateTo(config.getProperty("test_url"));

            LoginPage loginPage = new LoginPage(driver);
            logger.info("Login Page object created.");

            InventoryPage inventoryPage = new InventoryPage(driver);
            logger.info("Inventory Page object created.");

            CartPage cartPage = new CartPage(driver);
            logger.info("Cart Page object created.");

            CheckoutPage checkoutPage = new CheckoutPage(driver);
            logger.info("Checkout Page object created.");

            SummaryPage summaryPage = new SummaryPage(driver);
            logger.info("Summary Page object created.");

            // Login
            logger.info("Entering username and password.");
            loginPage.login();
            logger.info("Login button clicked.");

            // Add item to cart
            logger.info("Adding item to cart.");
            inventoryPage.addAnItemToCart();
            inventoryPage.goToCart();
            logger.info("Navigated to cart.");

            // Checkout
            logger.info("Proceeding to checkout.");
            cartPage.clickCheckout();
            logger.info("Checkout button clicked.");

            checkoutPage.enterFirstName("John");
            checkoutPage.enterLastName("Doe");
            checkoutPage.enterPostalCode("12345");
            checkoutPage.clickContinue();
            logger.info("Checkout details entered.");

            // Verify summary page
            String totalValue = summaryPage.getTotalValue();
            logger.info("Summary total value retrieved: " + totalValue);
            Assert.assertEquals(totalValue, "10.79", "Total value is not as expected!");
            logger.info("Checkout process completed successfully.");

        } catch (Exception e) {
            logger.error("Error during checkout process: ", e);
            handleTestException(new Object() {}.getClass().getEnclosingMethod().getName(), e);
            throw e;
        }
    }
}

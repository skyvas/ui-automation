package tests.reports;

import utils.InitialiseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.*;

/**
 * Test class for verifying the end-to-end checkout process on an e-commerce platform.
 * Includes logging, exception handling, and Allure reporting for enhanced traceability.
 */
@Epic("E-Commerce Platform") // Defines a high-level feature group
@Feature("Checkout Flow")    // Specifies the feature under testing
@Story("End-to-End Checkout Process") // Describes the user story for the test
@Severity(SeverityLevel.CRITICAL)    // Sets the severity level for the test
@TmsLink("TMS-123")                  // Links to the corresponding test case in a Test Management System
public class TestCheckoutProcessWithAllureReportsNew extends InitialiseTest {

    /**
     * Executes the complete checkout process and verifies the total value on the summary page.
     */
    @Test(description = "End-to-end checkout process with logs and Allure reporting")
    @Description("This test performs the end-to-end checkout process and verifies the total.")
    public void testCheckoutProcessWithAllureReportsNew() {
        try {
            logStep("Navigating to URL: " + config.getProperty("test_url"));
            browserUtil.navigateTo(config.getProperty("test_url"));

            // Create page objects
            LoginPage loginPage = new LoginPage(driver);
            InventoryPage inventoryPage = new InventoryPage(driver);
            CartPage cartPage = new CartPage(driver);
            CheckoutPage checkoutPage = new CheckoutPage(driver);
            SummaryPage summaryPage = new SummaryPage(driver);

            // Perform the end-to-end flow
            performLogin(loginPage);
            addItemToCart(inventoryPage);
            performCheckout(cartPage, checkoutPage);
            verifySummaryPage(summaryPage);

        } catch (Exception e) {
            // Handle and log exceptions, then rethrow to fail the test
            handleTestException(new Object() {}.getClass().getEnclosingMethod().getName(), e);
            throw e;
        }
    }

    /**
     * Logs into the application using valid credentials.
     *
     * @param loginPage The login page object.
     */
    @Step("Login with username and password")
    private void performLogin(LoginPage loginPage) {
        logStep("Entering username and password.");
        loginPage.enterUsername(config.getProperty("username"));
        loginPage.enterPassword(config.getProperty("password"));
        loginPage.clickLogin();
        logStep("User successfully logged in.");
    }

    /**
     * Adds an item to the cart and navigates to the cart page.
     *
     * @param inventoryPage The inventory page object.
     */
    @Step("Add item to cart and navigate to cart")
    private void addItemToCart(InventoryPage inventoryPage) {
        logStep("Adding item to cart.");
        inventoryPage.addAnItemToCart();
        inventoryPage.goToCart();
        logStep("Navigated to cart.");
    }

    /**
     * Completes the checkout process by entering required details and proceeding.
     *
     * @param cartPage      The cart page object.
     * @param checkoutPage  The checkout page object.
     */
    @Step("Proceed with checkout and fill in details")
    private void performCheckout(CartPage cartPage, CheckoutPage checkoutPage) {
        logStep("Proceeding to checkout.");
        cartPage.clickCheckout();

        logStep("Entering checkout details.");
        checkoutPage.enterFirstName("John");
        checkoutPage.enterLastName("Doe");
        checkoutPage.enterPostalCode("12345");
        checkoutPage.clickContinue();
        logStep("Checkout details entered successfully.");
    }

    /**
     * Verifies the total value displayed on the summary page.
     *
     * @param summaryPage The summary page object.
     */
    @Step("Verify summary page total value")
    private void verifySummaryPage(SummaryPage summaryPage) {
        String totalValue = summaryPage.getTotalValue();
        logStep("Summary total value retrieved: " + totalValue);
        Assert.assertEquals(totalValue, "10.79", "Total value is not as expected!");
        logStep("Checkout process completed successfully.");
    }
}
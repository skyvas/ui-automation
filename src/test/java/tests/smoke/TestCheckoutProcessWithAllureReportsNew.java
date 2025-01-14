package tests.smoke;

import base.BaseTest;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.TmsLink;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.LoginPage;
import pageobjects.InventoryPage;
import pageobjects.CartPage;
import pageobjects.CheckoutPage;
import pageobjects.SummaryPage;

@Epic("E-Commerce Platform")
@Feature("Checkout Flow")
@Story("End-to-End Checkout Process")
@Severity(SeverityLevel.CRITICAL)
@TmsLink("TMS-123")  // Replace with your actual TMS link
public class TestCheckoutProcessWithAllureReportsNew extends BaseTest {

    @Test(description = "End-to-end checkout process with logs and Allure reporting")
    @Description("This test performs the end-to-end checkout process and verifies the total.")
    public void testCheckoutProcessWithAllureReports() {
        try {
            logStep("Navigating to URL: " + config.getProperty("test_url"));
            browserUtil.navigateTo(config.getProperty("test_url"));

            LoginPage loginPage = new LoginPage(driver);
            logStep("Login Page object created.");

            InventoryPage inventoryPage = new InventoryPage(driver);
            logStep("Inventory Page object created.");

            CartPage cartPage = new CartPage(driver);
            logStep("Cart Page object created.");

            CheckoutPage checkoutPage = new CheckoutPage(driver);
            logStep("Checkout Page object created.");

            SummaryPage summaryPage = new SummaryPage(driver);
            logStep("Summary Page object created.");

            performLogin(loginPage);
            addItemToCart(inventoryPage);
            performCheckout(cartPage, checkoutPage);
            verifySummaryPage(summaryPage);

        } catch (Exception e) {
            logger.error("Error during checkout process: ", e);
            handleTestException(new Object() {}.getClass().getEnclosingMethod().getName(), e);
        }
    }

    @Step("Login with username and password")
    private void performLogin(LoginPage loginPage) {
        logStep("Entering username and password.");
        loginPage.enterUsername(config.getProperty("username"));
        loginPage.enterPassword(config.getProperty("password"));
        loginPage.clickLogin();
        logStep("Login button clicked.");
    }

    @Step("Add item to cart and navigate to cart")
    private void addItemToCart(InventoryPage inventoryPage) {
        logStep("Adding item to cart.");
        inventoryPage.addAnItemToCart();
        inventoryPage.goToCart();
        logStep("Navigated to cart.");
    }

    @Step("Proceed with checkout and fill in details")
    private void performCheckout(CartPage cartPage, CheckoutPage checkoutPage) {
        logStep("Proceeding to checkout.");
        cartPage.clickCheckout();
        logStep("Checkout button clicked.");

        checkoutPage.enterFirstName("John");
        checkoutPage.enterLastName("Doe");
        checkoutPage.enterPostalCode("12345");
        checkoutPage.clickContinue();
        logStep("Checkout details entered.");
    }

    @Step("Verify summary page total value")
    private void verifySummaryPage(SummaryPage summaryPage) {
        String totalValue = summaryPage.getTotalValue();
        logStep("Summary total value retrieved: " + totalValue);
        Assert.assertEquals(totalValue, "10.79", "Total value is not as expected!");
        logStep("Checkout process completed successfully.");
    }
}
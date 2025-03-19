package tests.smoke;

import utils.InitialiseTest;
import pageobjects.LoginPage;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends InitialiseTest {

    @Test
    public void loginTest() {
        try {
            logger.info("Navigating to URL: " + config.getProperty("test_url"));
            browserUtil.navigateTo(config.getProperty("test_url"));

            LoginPage loginPage = new LoginPage(driver);
            logger.info("Login Page object created.");

            // Login
            logger.info("Entering username and password.");
            loginPage.login();
            logger.info("Login button clicked.");

            // Get the current URL
            String currentUrl = driver.getCurrentUrl();

            // The variable part of the URL
            String expectedPage = "inventory.html";

            // Assertion to check if the current URL contains the expected page
            assert currentUrl != null;
            Assert.assertTrue(currentUrl.contains(expectedPage), "The current page is not " + expectedPage);
            logger.info("LoginTest passed.");

        } catch (Exception e) {
            logger.error("Error during checkout process: ", e);
            handleTestException(new Object() {}.getClass().getEnclosingMethod().getName(), e);
            throw e;
        }
    }
}

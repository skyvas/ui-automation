package pageobjects;

import org.openqa.selenium.WebDriver;
import utils.ConfigReader;

public class LoginPage extends BasePage {


    protected ConfigReader config;

    /**
     * Constructor to initialize WebDriver and load locators for LoginPage.
     *
     * @param driver WebDriver instance
     */
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected String getPageJsonFileName() {
        return "LoginPage.json"; // Specifies the JSON locator file for this page
    }

    /**
     * Enter the username into the username field.
     *
     * @param username The username to enter
     */
    public void enterUsername(String username) {
        sendKeysToElement("usernameField", username);
    }

    /**
     * Enter the password into the password field.
     *
     * @param password The password to enter
     */
    public void enterPassword(String password) {
        sendKeysToElement("passwordField", password);
    }

    /**
     * Click the login button.
     */
    public void clickLogin() {
        clickElement("loginButton");
    }


    /**
     * Method to login. Reads username and password from the config file
     */
    public void login() {
        config = new ConfigReader();
        String username = config.getProperty("username");
        String password = config.getProperty("password");
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }
}

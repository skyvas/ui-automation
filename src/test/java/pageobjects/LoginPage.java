package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.WaitUtil;
import utils.JsonUtil;
import org.json.JSONObject;

public class LoginPage {
    private WebDriver driver;
    private WaitUtil waitUtil;
    private JSONObject locators;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtil = new WaitUtil(driver);
        this.locators = JsonUtil.loadJson("LoginPage.json");
    }

    private By getLocator(String key) {
        return By.cssSelector(locators.getString(key));
    }

    /**
     * Enter the username into the username field.
     *
     * @param username The username to enter
     */
    public void enterUsername(String username) {
        waitUtil.waitForElementClickable(getLocator("usernameField")).sendKeys(username);
    }

    /**
     * Enter the password into the password field.
     *
     * @param password The password to enter
     */
    public void enterPassword(String password) {
        waitUtil.waitForElementClickable(getLocator("passwordField")).sendKeys(password);
    }

    /**
     * Click the login button.
     */
    public void clickLogin() {
        waitUtil.waitForElementClickable(getLocator("loginButton")).click();
    }
}

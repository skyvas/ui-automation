package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.WaitUtil;

public class LoginPage {
    WebDriver driver;
    WaitUtil waitUtil;  // Add an instance of the WaitUtil class

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtil = new WaitUtil(driver);  // Initialize WaitUtil
    }

    public void enterUsername(String username) {
        waitUtil.waitForElementVisible(By.cssSelector("*[data-test='username']")).sendKeys(username);
    }

    public void enterPassword(String password) {
        waitUtil.waitForElementVisible(By.cssSelector("*[data-test='password']")).sendKeys(password);
    }

    public void clickLogin() {
        waitUtil.waitForElementClickable(By.cssSelector("*[data-test='login-button']")).click();
    }

    // You can add more methods below and utilize the waitUtil as needed
}
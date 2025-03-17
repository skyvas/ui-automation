package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.WaitUtil;
import utils.JsonUtil;
import org.json.JSONObject;

public class CheckoutPage {
    private WebDriver driver;
    private WaitUtil waitUtil;
    private JSONObject locators;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtil = new WaitUtil(driver);
        this.locators = JsonUtil.loadJson("CheckoutPage.json");
    }

    private By getLocator(String key) {
        return By.cssSelector(locators.getString(key));
    }

    /**
     * Wait for the first name field to be visible and enter text.
     *
     * @param firstName The first name to enter
     */
    public void enterFirstName(String firstName) {
        waitUtil.waitForElementVisible(getLocator("firstNameField")).sendKeys(firstName);
    }

    /**
     * Wait for the last name field to be visible and enter text.
     *
     * @param lastName The last name to enter
     */
    public void enterLastName(String lastName) {
        waitUtil.waitForElementVisible(getLocator("lastNameField")).sendKeys(lastName);
    }

    /**
     * Wait for the postal code field to be visible and enter text.
     *
     * @param postalCode The postal code to enter
     */
    public void enterPostalCode(String postalCode) {
        waitUtil.waitForElementVisible(getLocator("postalCodeField")).sendKeys(postalCode);
    }

    /**
     * Wait for the continue button to be clickable and click it.
     */
    public void clickContinue() {
        waitUtil.waitForElementClickable(getLocator("continueButton")).click();
    }
}

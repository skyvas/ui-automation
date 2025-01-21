package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.WaitUtil;

public class CheckoutPage {
    private WebDriver driver;
    private WaitUtil waitUtil;

    private By firstNameField = By.cssSelector("*[data-test='firstName']");
    private By lastNameField = By.cssSelector("*[data-test='lastName']");
    private By postalCodeField = By.cssSelector("*[data-test='postalCode']");
    private By continueButton = By.cssSelector("*[data-test='continue']");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtil = new WaitUtil(driver);
    }

    /**
     * Wait for the first name field to be visible and enter text.
     *
     * @param firstName The first name to enter
     */
    public void enterFirstName(String firstName) {
        waitUtil.waitForElementVisible(firstNameField).sendKeys(firstName);
    }

    /**
     * Wait for the last name field to be visible and enter text.
     *
     * @param lastName The last name to enter
     */
    public void enterLastName(String lastName) {
        waitUtil.waitForElementVisible(lastNameField).sendKeys(lastName);
    }

    /**
     * Wait for the postal code field to be visible and enter text.
     *
     * @param postalCode The postal code to enter
     */
    public void enterPostalCode(String postalCode) {
        waitUtil.waitForElementVisible(postalCodeField).sendKeys(postalCode);
    }

    /**
     * Wait for the continue button to be clickable and click it.
     */
    public void clickContinue() {
        waitUtil.waitForElementClickable(continueButton).click();
    }
}

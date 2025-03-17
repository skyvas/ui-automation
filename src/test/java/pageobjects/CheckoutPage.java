package pageobjects;

import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage {

    /**
     * Constructor to initialize WebDriver and load locators for CheckoutPage.
     *
     * @param driver WebDriver instance
     */
    public CheckoutPage(WebDriver driver) {
        super(driver);  // Inherit WebDriver and all functionality from BasePage
    }

    /**
     * Override the method to specify the JSON file for this page.
     *
     * @return The name of the JSON file containing locators for this page.
     */
    @Override
    protected String getPageJsonFileName() {
        return "CheckoutPage.json";  // CheckoutPage-specific JSON file for locators
    }

    /**
     * Wait for the first name field to be visible and enter text.
     *
     * @param firstName The first name to enter
     */
    public void enterFirstName(String firstName) {
        waitForElementToBeVisible("firstNameField", "css").sendKeys(firstName);  // Uses inherited method from BasePage
    }

    /**
     * Wait for the last name field to be visible and enter text.
     *
     * @param lastName The last name to enter
     */
    public void enterLastName(String lastName) {
        waitForElementToBeVisible("lastNameField", "css").sendKeys(lastName);  // Uses inherited method from BasePage
    }

    /**
     * Wait for the postal code field to be visible and enter text.
     *
     * @param postalCode The postal code to enter
     */
    public void enterPostalCode(String postalCode) {
        waitForElementToBeVisible("postalCodeField", "css").sendKeys(postalCode);  // Uses inherited method from BasePage
    }

    /**
     * Wait for the continue button to be clickable and click it.
     */
    public void clickContinue() {
        clickElement("continueButton", "css");  // Uses inherited method from BasePage
    }
}
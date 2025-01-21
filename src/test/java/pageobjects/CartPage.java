package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.WaitUtil;

public class CartPage {
    private WebDriver driver;
    private WaitUtil waitUtil;

    private By checkoutButton = By.cssSelector("*[data-test='checkout']");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtil = new WaitUtil(driver);
    }

    /**
     * Wait for the checkout button to be visible and then click it.
     */
    public void clickCheckout() {
        waitUtil.waitForElementClickable(checkoutButton).click();
    }
}
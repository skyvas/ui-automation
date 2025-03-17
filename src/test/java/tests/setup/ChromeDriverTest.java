package tests.setup;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.InitialiseTest;

public class ChromeDriverTest extends InitialiseTest {

    @Test
    public void testGoogleHomePage() {
        logStep("Navigating to Google.com");
        driver.get("https://www.google.com");

        logStep("Verifying if Google search bar is present");
        WebElement searchBox = driver.findElement(By.name("q"));
        Assert.assertTrue(searchBox.isDisplayed(), "Google search box is not displayed");
    }
}

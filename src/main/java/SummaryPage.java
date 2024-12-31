import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SummaryPage {
    WebDriver driver;

    public SummaryPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement verifySummaryPage() {
        return driver.findElement(By.cssSelector("*[data-test='checkout-summary-container']"));
    }
}

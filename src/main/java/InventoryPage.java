import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage {
    WebDriver driver;

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public void addToCart(String itemSelector) {
        driver.findElement(By.cssSelector(itemSelector)).click();
    }

    public void goToCart() {
        driver.findElement(By.cssSelector("*[data-test='shopping-cart-link']")).click();
    }
}

package com.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage {
    WebDriver driver;

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public void addAnItemToCart() {
        driver.findElement(By.cssSelector("*[data-test='add-to-cart-sauce-labs-bike-light']")).click();
    }

    public void goToCart() {
        driver.findElement(By.cssSelector("*[data-test='shopping-cart-link']")).click();
    }
}

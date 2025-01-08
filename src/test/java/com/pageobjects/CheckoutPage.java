package com.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {
    WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterFirstName(String firstName) {
        driver.findElement(By.cssSelector("*[data-test='firstName']")).sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        driver.findElement(By.cssSelector("*[data-test='lastName']")).sendKeys(lastName);
    }

    public void enterPostalCode(String postalCode) {
        driver.findElement(By.cssSelector("*[data-test='postalCode']")).sendKeys(postalCode);
    }

    public void clickContinue() {
        driver.findElement(By.cssSelector("*[data-test='continue']")).click();
    }
}
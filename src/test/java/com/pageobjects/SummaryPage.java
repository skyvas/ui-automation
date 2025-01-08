package com.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SummaryPage {
    private WebDriver driver;

    // Constructor to initialize the WebDriver
    public SummaryPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locator for the total label div
    private By totalLabel = By.cssSelector("div.summary_total_label[data-test='total-label']");

    // Method to retrieve the total value as a string (without the "Total: $" part)
    public String getTotalValue() {
        String totalText = driver.findElement(totalLabel).getText();
        return totalText.replace("Total: $", "").trim();
    }
}

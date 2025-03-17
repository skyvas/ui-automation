package utils;

import org.openqa.selenium.By;
import org.json.JSONObject;

public class LocatorUtil {

    private JSONObject locators;

    public LocatorUtil(JSONObject locators) {
        this.locators = locators;
    }

    /**
     * Retrieves a Selenium locator based on key and type.
     *
     * @param key The key used to fetch the locator value from JSON.
     * @param locatorType The type of locator (e.g., "css", "xpath", "id", etc.).
     * @return The corresponding Selenium By locator.
     */
    public By getLocator(String key, String locatorType) {
        String locatorValue = locators.optString(key, "");

        if (locatorValue.isEmpty()) {
            throw new IllegalArgumentException("Locator key not found: " + key);
        }

        switch (locatorType.toLowerCase()) {
            case "css":
                return By.cssSelector(locatorValue);
            case "xpath":
                return By.xpath(locatorValue);
            case "id":
                return By.id(locatorValue);
            case "name":
                return By.name(locatorValue);
            case "classname":
                return By.className(locatorValue);
            case "tagname":
                return By.tagName(locatorValue);
            case "linktext":
                return By.linkText(locatorValue);
            case "partiallinktext":
                return By.partialLinkText(locatorValue);
            default:
                throw new IllegalArgumentException("Invalid locator type: " + locatorType);
        }
    }
}
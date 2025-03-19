package utils;

import org.json.JSONObject;
import org.openqa.selenium.By;

public class LocatorUtil {
    private JSONObject locators;

    public LocatorUtil(JSONObject locators) {
        this.locators = locators;
    }

    /**
     * Retrieves the locator by extracting its type and value from JSON.
     *
     * @param key The key identifying the locator in JSON.
     * @return A By object representing the locator.
     */
    public By getLocator(String key) {
        if (!locators.has(key)) {
            throw new IllegalArgumentException("Locator key not found in JSON: " + key);
        }

        JSONObject locatorData = locators.getJSONObject(key);
        String locatorType = locatorData.getString("type");
        String locatorValue = locatorData.getString("value");

        return getBy(locatorType, locatorValue);
    }

    private By getBy(String type, String value) {
        switch (type.toLowerCase()) {
            case "id": return By.id(value);
            case "name": return By.name(value);
            case "css": return By.cssSelector(value);
            case "xpath": return By.xpath(value);
            case "class": return By.className(value);
            case "tag": return By.tagName(value);
            case "linktext": return By.linkText(value);
            case "partiallinktext": return By.partialLinkText(value);
            default: throw new IllegalArgumentException("Unsupported locator type: " + type);
        }
    }
}
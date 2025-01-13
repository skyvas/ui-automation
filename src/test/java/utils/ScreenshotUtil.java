package utils;

import org.openqa.selenium.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ScreenshotUtil {

    /**
     * Captures a screenshot and saves it to the specified path.
     * Ensures that the directory exists before saving.
     *
     * @param driver    WebDriver instance
     * @param filePath  Path to save the screenshot
     */
    public static void takeScreenshot(WebDriver driver, String filePath) {
        try {
            if (driver instanceof TakesScreenshot) {
                // Capture the screenshot
                TakesScreenshot ts = (TakesScreenshot) driver;
                File sourceFile = ts.getScreenshotAs(OutputType.FILE);

                // Ensure the target directory exists
                File destFile = new File(filePath);
                File destDir = destFile.getParentFile();
                if (!destDir.exists()) {
                    destDir.mkdirs(); // Create directories if they don't exist
                }

                // Save the screenshot
                Files.copy(sourceFile.toPath(), destFile.toPath());
                System.out.println("Screenshot saved at: " + filePath);
            } else {
                System.err.println("WebDriver does not support screenshots.");
            }
        } catch (WebDriverException e) {
            System.err.println("WebDriver is in an invalid state: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Failed to save screenshot: " + e.getMessage());
        }
    }

    /**
     * Captures a screenshot and returns it as a byte array.
     * This is useful for integrations like Allure reporting.
     *
     * @param driver WebDriver instance
     * @return Screenshot as a byte array
     */
    public static byte[] takeScreenshotAsBytes(WebDriver driver) {
        if (driver instanceof TakesScreenshot) {
            try {
                return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            } catch (WebDriverException e) {
                System.err.println("Failed to capture screenshot as bytes: " + e.getMessage());
            }
        } else {
            System.err.println("WebDriver does not support screenshots.");
        }
        return new byte[0]; // Return an empty byte array if capturing fails
    }
}
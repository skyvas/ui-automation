package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ScreenshotUtil {

    /**
     * Captures a screenshot and saves it to the specified path.
     * Ensures that the directory exists before saving.
     *
     * @param driver    WebDriver instance
     * @param filePath  Path to save the screenshot
     */
    public static void takeScreenshot(WebDriver driver, String filePath) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File sourceFile = ts.getScreenshotAs(OutputType.FILE);

        // Ensure the target directory exists
        File destFile = new File(filePath);
        File destDir = destFile.getParentFile();
        if (!destDir.exists()) {
            destDir.mkdirs();  // Create directories if they don't exist
        }

        try {
            Files.copy(sourceFile.toPath(), Paths.get(filePath));
            System.out.println("Screenshot saved at: " + filePath);
        } catch (IOException e) {
            System.err.println("Failed to capture screenshot: " + e.getMessage());
        }
    }
}
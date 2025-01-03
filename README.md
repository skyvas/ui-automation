# Selenium Project with Maven

This repository contains a Selenium project structured to follow best practices for test automation. The project uses Maven as the build tool and organizes resources and test code for easy management and scalability.

## Folder Structure

```
selenium-project/
├── src/
│   ├── resources/
│   │   └── config.properties
│   └── test/
│       └── java/
│           ├── tests/
│           ├── pageobjects/
│           └── utils/
│               └── ConfigReader.java
```

### Description of Folders and Files

- **src/resources/config.properties**
      - Contains configuration details such as URLs, browser types, and other environment-specific properties.

- **src/test/java/tests/**
      - Contains test classes where all the test cases are written.

- **src/test/java/pageobjects/**
      - Contains Page Object Model (POM) classes for interacting with web pages.

- **src/test/java/utils/ConfigReader.java**
      - A utility class to read and parse the `config.properties` file.

## Prerequisites

Before running the tests, ensure you have the following installed:

- [Java JDK 8 or higher](https://www.oracle.com/java/technologies/javase-downloads.html)
- [Maven](https://maven.apache.org/)
- A compatible web driver (e.g., ChromeDriver, GeckoDriver)

## How to Run the Tests

1. Clone the repository:
   ```bash
   git clone <repository_url>
   cd selenium-project
   ```

2. Update the `config.properties` file with the appropriate values for your environment.

3. Run the tests:
      - Using Maven:
        ```bash
        mvn test
        ```
      - Directly in the IDE:
            1. Open `tests.TestCheckoutProcess.java`.
            2. Right-click and select Run.

## Key Features

- **Maven Integration**: Manage dependencies and run tests seamlessly.
- **Configurable Properties**: Easily switch between environments by editing the `config.properties` file.
- **Utility Classes**: Reusable components like `ConfigReader` simplify common tasks.

## Key Files and Their Purpose

1. **Page Classes (POM):**

2. **ConfigReader.java**: Utility class to read configuration properties from `config.properties`.

3. **tests.TestCheckoutProcess.java**: Main test class to execute the end-to-end test case.

4. **config.properties**: Stores application-specific settings like username and password.

## Notes

- Ensure the browser version and ChromeDriver version are compatible.
- Modify the test script if testing on a different application or platform.

## Troubleshooting

### Common Errors

1. **`config.properties` not found:**
      - Ensure `config.properties` is placed in `src/test/resources/`.
      - Check the `ConfigReader` implementation for the correct file path.

2. **WebDriverException:**
      - Ensure ChromeDriver is installed and available in your PATH.

3. **Test Failures:**
      - Check locators (webElement selectors) in page classes for any changes on the target website.

## Contributions

Contributions are welcome! Please fork the repository and submit a pull request with your changes.
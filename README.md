# SeleniumBase

This repository contains a Selenium-based test automation framework. The project is designed to be modular, maintainable, and easy to use for automated UI testing.

## Project Structure

```plaintext
seleniumbase/
├── src/
│   └── test/
│       ├── java/
│       │   ├── tests/         # Test cases
│       │   ├── utils/         # Utility classes (e.g., ConfigReader)
│       │   └── pageobjects/   # Page Object classes
│       └── resources/         # Resource files (e.g., config.properties)
├── .gitignore                  # Files to ignore in Git
├── pom.xml                     # Maven configuration file
└── README.md                   # Project documentation
```

## Key Components

### `src/test/resources/config.properties`
This file contains the configuration settings for the tests, such as URLs, credentials, and environment-specific details.

### `ConfigReader` Class
Located in `src/test/java/utils/ConfigReader.java`, this utility class reads properties from the `config.properties` file.

#### Example:
```java
String url = ConfigReader.getProperty("url");
System.out.println("URL: " + url);
```

### Page Objects
Page Object classes are located in `src/test/java/pageobjects`. Each class represents a page or component of the application and encapsulates the locators and actions for that page.

#### Example:
```java
public class LoginPage {
    WebDriver driver;

    By usernameField = By.id("username");
    By passwordField = By.id("password");
    By loginButton = By.id("loginBtn");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void login(String username, String password) {
        driver.findElement(usernameField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
    }
}
```

### Test Cases
Test case classes are located in `src/test/java/tests`. Each test class is responsible for verifying a specific functionality of the application under test.

## Setup Instructions

1. **Clone the Repository**
   ```bash
   git clone https://github.com/akash-plato/seleniumbase.git
   cd seleniumbase
   ```

2. **Set Up Dependencies**
   - Using Maven:
     ```bash
     mvn install
     ```

3. **Update `config.properties`**
   Modify the `src/test/resources/config.properties` file to include your environment-specific configurations from github variables.

4. **Run Tests**
   - Using Maven:
     ```bash
     mvn test
     ```

## Troubleshooting

### Error: "Unable to find config.properties"
- Ensure that the `config.properties` file is in `src/test/resources/`.
- Verify that the `resources` folder is marked as a resource root in your IDE.
- Confirm that the `ConfigReader` class is using the correct path:
  ```java
  InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties");
  ```

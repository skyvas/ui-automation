# SeleniumBase

This repository contains a Selenium-based test automation framework. The project is designed to be modular, maintainable, and easy to use for automated UI testing.

## Project Structure

```plaintext
seleniumbase/
├── src/
│   ├── main/                 # For Application code
│   └── test/
│       ├── java/
│       │   ├── tests/
│       │   │   ├── smoke/         # Smoke test cases
│       │   │   ├── regression/    # Regression test cases
│       │   ├── utils/             # Utility classes (e.g., ConfigReader, Helpers)
│       │   ├── pageobjects/       # Page Object classes
│       │   └── exceptions/        # Custom exception classes
│       ├── resources/
│           └── config.properties   # Test environment config
├── .github/
│   └── workflows/
│       └── selenium-ci.yml         # GitHub Actions pipeline
├── .gitignore                      # Files to ignore in Git
├── pom.xml                         # Maven configuration file (with profiles and versioning)
└── README.md                       # Enhanced project documentation

```

## Key Components
Modular Test Structure: Organized directories for better test management.
Page Object Model (POM): Simplifies test script maintenance.
Custom Utilities and Exceptions: Includes reusable utilities and error handling.
Environment Configurations: Centralized configuration management with config.properties.
CI/CD Ready: Integrated GitHub Actions for continuous testing.
Maven Build Support: Dependency management and profile-based execution.

### `src/test/resources/config.properties`
This file contains the configuration settings for the tests, such as URLs, credentials, and environment-specific details (fetched from GitHub Variables)

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

Setup and Installation

Prerequisites:
Java Development Kit (JDK) 8 or higher
Maven 3.6+
Git
IDE (e.g., IntelliJ IDEA, VSCode)


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
## Usage

### Writing Tests

1. Add a Page Object: Define page-specific elements and actions in src/test/java/pageobjects/.
2. Write Test Cases:
3. Smoke test cases: Add to src/test/java/tests/smoke/
4. Regression test cases: Add to src/test/java/tests/regression/
5. Utilize helper methods and custom utilities from the utils/ package for consistency.

### Writing TestsRunning Tests

Run all tests:
   ```bash
mvn test
   ```

Run tests with a specific profile:
   ```bash
mvn test -Psmoke
mvn test -Pregression
   ```

## CI/CD Integration
GitHub Actions is used for continuous testing. The pipeline file selenium-ci.yml is located in .github/workflows/.

### Key Features
1. Automatic test execution on pull requests.
2. Notifications for build failures.
3. To Trigger CI Pipeline
4. Push changes to the repository.
5. The pipeline will automatically execute the defined tests.

### Contributing

Contributions to improve this framework are welcomed! Please follow these steps:
1. Fork the repository.
2. Create a new branch.
3. Make your changes.
4. Submit a pull request.

## Troubleshooting

### Error: "Unable to find config.properties"
- Ensure that the `config.properties` file is in `src/test/resources/`.
- Verify that the `resources` folder is marked as a resource root in your IDE.
- Confirm that the `ConfigReader` class is using the correct path:
  ```java
  InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties");
  ```


## License
This project is licensed under the MIT License.

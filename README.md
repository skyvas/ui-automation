# UI-Automation Test Framework

This repository contains a Selenium-based test automation framework designed for modular, maintainable, and user-friendly automated UI testing.

## Project Structure

```plaintext
seleniumbase/
├── src/                       # Source code directory
│   ├── main/                  # Application code
│   └── test/                  # Test code
│       ├── java/
│       │   ├── tests/         # Test cases
│       │   │   ├── smoke/         # Smoke test cases
│       │   │   ├── regression/    # Regression test cases
│       │   ├── utils/             # Utility classes (e.g., ConfigReader, Helpers)
│       │   ├── pageobjects/       # Page Object classes
│       │   └── exceptions/        # Custom exception classes
│       ├── resources/
│           └── config.properties  # Test environment configuration file
├── .github/
│   └── workflows/
│       └── selenium-ci.yml        # GitHub Actions pipeline
├── .gitignore                     # Files to ignore in Git
├── pom.xml                        # Maven configuration file
└── README.md                      # Project documentation
```

## Key Components

### Allure Reports Integration
This project supports Allure Reports for detailed and visually appealing test reports.

#### Features of Allure Reports
- Detailed, user-friendly, web-based reports.
- Test case pass/fail summaries and timelines.
- Attachments like screenshots and logs for debugging.

#### Generate Allure Reports
1. Run tests:
   ```bash
   mvn test
   ```
2. Generate and view reports:
   ```bash
   mvn allure:serve
   ```

### TestNG Multithreading
TestNG supports parallel execution for efficient performance, especially for large test suites.

#### Configuration Example
Update `testng.xml` for parallel execution:
```xml
<suite name="ParallelSuite" parallel="methods" thread-count="4">
    <test name="Test">
        <classes>
            <class name="tests.smoke.SampleTest"/>
        </classes>
    </test>
</suite>
```
- `parallel="methods"`: Runs test methods in parallel.
- `thread-count="4"`: Specifies the maximum threads.

### Utilities and Configurations
- `src/test/resources/config.properties`: Configuration settings (e.g., URLs, credentials).
- `ConfigReader` Utility: Reads properties from the configuration file.
  ```java
  String url = ConfigReader.getProperty("url");
  ```

### Page Objects
Page Object classes represent application pages or components.

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

## Setup and Installation

### Prerequisites
- JDK 8 or higher
- Maven 3.6+
- Git
- IDE (e.g., IntelliJ IDEA, VSCode)

### Steps
1. Clone the repository:
   ```bash
   git clone https://github.com/skyvas/ui-automation.git
   cd ui-automation
   ```
2. Install dependencies:
   ```bash
   mvn install
   ```
3. Update `config.properties` with environment-specific settings.
4. Run tests:
   ```bash
   mvn test
   ```

## CI/CD Integration
GitHub Actions pipeline (`selenium-ci.yml`) automates testing on pull requests.

### Features
- Automatic test execution.
- Notifications for build failures.

## Troubleshooting

### "Unable to find config.properties"
- Ensure `config.properties` exists in `src/test/resources/`.
- Verify the `ConfigReader` uses the correct path:
  ```java
  InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties");
  ```

## License
This project is distributed under the MIT License.


# SeleniumBase Test Framework

This repository contains a Selenium-based test automation framework designed to be modular, maintainable, and user-friendly for automated UI testing.

## Project Structure

```plaintext
seleniumbase/
├── src/                       # Source code directory
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
│           └── config.properties   # Test environment configuration file
├── .github/
│   └── workflows/
│       └── selenium-ci.yml         # GitHub Actions pipeline
├── .gitignore                      # Files to ignore in Git
├── pom.xml                         # Maven configuration file (with profiles and versioning)
└── README.md                       # Project documentation

```

## Key Components (Updated Section)

### Allure Reports Integration
The project supports Allure Reports for generating detailed and visually appealing test reports, aiding in tracking, analyzing, and debugging failed test cases effectively.

#### Configuration for Allure Reports
1. Add the Allure plugin to your Maven configuration (if not included already):
   ```xml
   <plugin>
       <groupId>io.qameta.allure</groupId>
       <artifactId>allure-maven</artifactId>
       <version>2.10.0</version>
   </plugin>
   ```
2. Ensure your test annotations (TestNG) include steps and reporting-related code as required by Allure.

3. Generate the Allure reports after executing the test cases:
   ```sh
   mvn allure:serve
   ```

#### Features of Allure Reports
- **Enhanced Visuals:** Offers detailed, user-friendly, web-based reports.
- **Test Execution Insights:** Includes test case pass/fail summaries, timelines, duration charts, and more.
- **Attachments:** Easily embed screenshots, logs, and other artifacts for failed test cases.
- **Historical Data:** Track and analyze trends across multiple test runs.

### TestNG Multithreading and Parallelization
To optimize test execution, TestNG offers built-in support for multithreading and parallel test execution. This project handles multithreaded test configurations efficiently to improve performance, especially for large test suites.

#### Multithreading Functionality
1. **TestNG Configuration in XML**  
   The `testng.xml` file specifies configurations for parallel test execution.

2. **Configuration Example:**  
   Modify the `testng.xml` file as follows to execute test methods in parallel:
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
   - `thread-count="4"`: Specifies the maximum number of threads.

3. **Best Practices for Multithreading Execution**  
   - Ensure tests are independent and do not share mutable state.
   - Use `ThreadLocal` for managing WebDriver instances for thread safety.  
     For example:
     ```java
     private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
     driver.set(new ChromeDriver());
     ```
   - Avoid global or shared variables that may lead to race conditions or conflicts.

#### Benefits of Multithreading
- Reduces overall test execution time.
- Optimized performance for large test suites.
- Scalability for CI/CD pipelines and cloud-based environments.
- Modular Test Structure: Organized directories for better test management.
- Page Object Model (POM): Simplifies test script maintenance.
- Custom Utilities and Exceptions: Includes reusable utilities and error handling.
- Environment Configurations: Centralized configuration management with config.properties.
- CI/CD Ready: Integrated GitHub Actions for continuous testing.
- Maven Build Support: Dependency management.
- Support for logging with log4j

### `src/test/resources/config.properties`
This file contains configuration settings for tests, including URLs, credentials, and environment-specific details (fetched from GitHub variables).

### ConfigReader Utility Class
Located in `src/test/java/utils/ConfigReader.java`, this utility class reads properties from the `config.properties` file.

#### Example:
```java
String url = ConfigReader.getProperty("url");
System.out.println("URL: " + url);
```

### Page Objects
Page Object classes, located in `src/test/java/pageobjects`, represent pages or components of the application, encapsulating their locators and actions.

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

### Test Classes
Test case classes are located in `src/test/java/tests`. Each test class is responsible for verifying a specific functionality of the application under test.

## Project Setup Instructions

Setup and Installation

Prerequisites:
Java Development Kit (JDK) version 8 or higher
Maven 3.6+
Git
IDE (e.g., IntelliJ IDEA, VSCode)


1. **Clone the Repository**
   ```bash
   git clone https://github.com/skyvas/ui-automation.git
   cd ui-automation
   ```

2. **Set Up Dependencies**
   - Using Maven:
     ```bash
     mvn install
     ```

3. **Update the `config.properties` File**
   Edit the `src/test/resources/config.properties` file to include your environment-specific configurations from GitHub variables.

4. **Run Tests**
   - Using Maven:
     ```bash
     mvn test
     ```
## Usage Details
#### Generating Allure Reports
1. Run your automated tests using Maven:
   ```bash
   mvn test
   ```
2. Generate and serve the Allure reports:
   ```bash
   mvn allure:serve
   ```
   This opens the Allure Report in your default web browser with detailed statistics.
#### Parallel Test Execution
#### Running Tests in Parallel
To execute tests in parallel, ensure the TestNG configuration (`testng.xml`) is updated for either methods, tests, or classes:
   ```xml
   <suite name="TestSuite" parallel="methods" thread-count="5">
   ```
Run the suite with the following command:
   ```bash
   mvn test -Dsurefire.suiteXmlFiles=testng.xml
   ```

### Writing and Organizing Tests

1. Add a Page Object: Define page-specific elements and actions in src/test/java/pageobjects/.
2. Write Test Cases.
3. Smoke test cases: Add to src/test/java/tests/smoke/
4. Regression test cases: Add them to `src/test/java/tests/regression/`.
5. Utilize helper methods and custom utilities from the utils/ package for consistency.

### Running Tests

Run all tests:
   ```bash
mvn test
   ```

## CI/CD Integration
GitHub Actions is used for continuous testing. The pipeline file selenium-ci.yml is located in .github/workflows/.

### Key Features
- Automatic test execution on pull requests.
- Notifications for build failures.
- To Trigger CI Pipeline
- Push changes to the remote repository.
- The pipeline will automatically execute the defined tests.


## Troubleshooting Common Issues

### Issue: "Unable to find config.properties"
- Ensure that the `config.properties` file is in `src/test/resources/`.
- Verify that the `config.properties` file is present in `src/test/resources/`.
- Confirm that the `ConfigReader` class is using the correct path:
  ```java
  InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties");
  ```


## License
This project is distributed under the MIT License.

Prerequisites
1.	Java Development Kit (JDK): Ensure JDK 8 or higher is installed. You can download
2.	Build Tool:
      o	Maven: Install from Maven Apache.
3.	Browser Driver: Download the ChromeDriver matching your Chrome browser version from ChromeDriver. Add it to your system PATH.
4.	IDE: Use an IDE like IntelliJ IDEA.

________________________________________
Setup Instructions
1.	Clone or Download the Project
2.	git clone <repository-url>
      cd selenium_project
3.	Install Dependencies
      For Maven:
      mvn clean install
4.	Configure Properties
      o	Open src/main/resources/config.properties and update the credentials as required:
      username=your_username
      password=your_password
5.	Verify Driver Path Ensure the ChromeDriver path is correctly set in the test script (or available in your system PATH).

________________________________________
How to Run the Tests
1.	Open your terminal or IDE.
2.	Navigate to the project directory.
3.	Execute the test:
      o	Using Maven:
      mvn test
      o	Directly in the IDE:
      Open TestCheckoutProcess.java.
      Right-click and select Run.
________________________________________
Key Files and Their Purpose
1.	Page Classes (POM):
      o	LoginPage.java: Handles login-related actions.
      o	InventoryPage.java: Interacts with the product listing page.
      o	CartPage.java: Manages the cart page interactions.
      o	CheckoutPage.java: Handles the checkout form.
      o	SummaryPage.java: Verifies the checkout summary page.
2.	ConfigReader.java: Utility class to read configuration properties from config.properties.
3.	TestCheckoutProcess.java: Main test class to execute the end-to-end test case.
4.	config.properties: Stores application-specific settings like username and password.
________________________________________
Notes
•	Ensure the browser version and ChromeDriver version are compatible.
•	Modify the test script if testing on a different application or platform.
________________________________________
Troubleshooting
Common Errors
1.	config.properties not found:
      o	Ensure config.properties is placed in src/main/resources.
      o	Check the ConfigReader implementation for the correct file path.
2.	WebDriverException:
      o	Ensure ChromeDriver is installed and available in your PATH.
3.	Test Failures:
      o	Check locators (CSS selectors) in page classes for any changes on the target website.


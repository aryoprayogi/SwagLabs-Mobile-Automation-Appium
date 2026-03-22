#  Swag Labs Mobile Automation (Appium + TestNG)

A robust, enterprise-level End-to-End Mobile Automation Testing framework built for the **Swag Labs** Android application. This project is engineered using **Java, Appium, and TestNG**, strictly adhering to the **Page Object Model (POM)** design pattern to ensure scalability, maintainability, and clean code architecture.

## 🛠️ Technology Stack
* **Programming Language:** Java (JDK 11+)
* **Mobile Automation:** Appium (UiAutomator2)
* **Testing Framework:** TestNG
* **Build Tool:** Maven
* **Design Pattern:** Page Object Model (POM)
* **Assertions:** TestNG Hard Asserts

## 🏗️ Framework Architecture
This framework is structured to separate test logic from page actions, ensuring a highly maintainable codebase:
* `src/main/java/pages/` - Contains Page Object classes (Locator encapsulation & Actions).
* `src/main/java/utils/` - Contains `BaseTest` for desired capabilities, driver initialization, and automated teardown mechanisms.
* `src/test/java/tests/` - Contains the actual TestNG execution scripts.
* `Screenshots/` - Automatically stores execution proofs (Success/Failure) via dynamic screenshot implementation.

## 📊 Test Scenarios Coverage

| Test ID | Scenario Name | Test Type | Assertion / Expected Result | Status |
| :--- | :--- | :--- | :--- | :--- |
| **TC-01** | Negative Login (Locked Out User) | Negative | Validates "Sorry, this user has been locked out." message. | ✅ PASSED |
| **TC-02** | Negative Login (Invalid Credentials) | Negative | Validates "Username and password do not match..." message. | ✅ PASSED |
| **TC-03** | Positive Cart Addition | Positive | Verifies cart badge dynamically updates to "2" upon adding items. | ✅ PASSED |
| **TC-04** | Dropdown Filter (Low to High) | Positive | Validates the first product is updated to "Sauce Labs Onesie" after filtering. | ✅ PASSED |
| **TC-05** | Negative Checkout (Empty Form) | Negative | Asserts "Error: First Name is required" when trying to bypass validation. | ✅ PASSED |
| **TC-06** | **End-to-End Shopping Masterpiece** | E2E | Calculates mathematical Grand Total validation (`Item Total + Tax`) and asserts the "THANK YOU FOR YOUR ORDER" success screen. | ✅ PASSED |

## 💡 Key Implementations
* **Dynamic DOM Handling:** Successfully implemented `UiScrollable` to dynamically interact with elements outside the visible viewport (e.g., scrolling to the "Finish" button and Price Summary).
* **Advanced XPath Indexing & String Manipulation:** Handled identical `content-desc` attributes and dynamic text strings (parsing `Double` values from UI text for mathematical total assertions).
* **Automated Evidence Collection:** Integrated `TakesScreenshot` interface to automatically capture the final state of the application after every test execution.

## About the Author
**Aryo Prayogi** *Software Quality Assurance* 

📫 **Let's Connect:** www.linkedin.com/in/aryoprayogi01

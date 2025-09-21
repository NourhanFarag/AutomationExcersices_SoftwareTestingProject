# Automation Exercises - Software Testing Project

## 1. Project Overview
This project is a **Selenium WebDriver-based automation testing suite** for the [Automation Exercises](https://automationexercise.com/) web application.  
The goal of the project is to automate the **end-to-end testing of various functionalities**, including user registration, login/logout, product search, cart operations, checkout, subscription, contact forms, and more.  
The automation ensures that the website behaves as expected and helps detect any regression issues efficiently.

---

## 2. Framework Description
The framework is **custom-built** using Java, Selenium WebDriver, TestNG, and Allure reporting. Key features include:

- **Page Object Model (POM)**: Each page has its own class with locators and methods, making the tests maintainable and readable.
- **Reusable Helper Utilities**: A `SeleniumHelper` class handles common actions like click, sendKeys, scroll, wait, and element visibility checks.
- **Data-Driven Testing**: Test data is stored in JSON files and loaded dynamically for login, registration, contact forms, and other scenarios.
- **Allure Reporting**: Integrated Allure reports provide step-by-step execution details, including success/failure messages and screenshots.
- **Soft and Hard Assertions**: Soft asserts are used for multiple verifications in a single test, while critical verifications use hard asserts.
- **Structured Test Cases**: TestNG is used to organize and run the test cases efficiently.

---

## 3. Implemented Test Cases
The project implements **26 automated test cases** covering major functionalities of the website. Examples include:

- **User Authentication**
  - TC1: Register User
  - TC2: Login User with correct email and password
  - TC3: Login User with incorrect email and password
  - TC4: Logout User
  - TC5: Register User with existing email
- **Contact & Feedback**
  - TC6: Contact Us Form
- **Navigation & Products**
  - TC7: Verify Test Cases Page
  - TC8: Verify All Products and product detail page
  - TC9: Search Product
- **Subscription**
  - TC10: Verify Subscription in Home Page
  - TC11: Verify Subscription in Cart Page
- **Cart Operations**
  - TC12: Add Products in Cart
  - TC13: Verify Product quantity in Cart
- **Checkout & Order Placement**
  - TC14: Place Order: Register while Checkout
  - TC15: Place Order: Register before Checkout
  - TC16: Place Order: Login before Checkout
- **Cart Management**
  - TC17: Remove Products from Cart
- **Category & Brand Verification**
  - TC18: View Category Products
  - TC19: View & Cart Brand Products
- **Cart Persistence**
  - TC20: Search Products and Verify Cart After Login
- **Product Interaction**
  - TC21: Add review on product
  - TC22: Add to cart from Recommended items
- **Checkout Verification**
  - TC23: Verify address details in checkout page
  - TC24: Download Invoice after purchase order
- **UI/UX**
  - TC25: Verify Scroll Up using 'Arrow' button and Scroll Down functionality
  - TC26: Verify Scroll Up without 'Arrow' button and Scroll Down functionality

Each test case is implemented as a **separate TestNG class** using page methods and helper utilities for modularity and maintainability. All tests are **data-driven** wherever applicable using JSON files.

---

## 4. Project Structure
AutomationExercises_SoftwareTestingProject/
│
├─ src/
│ ├─ main/
│ │ └─ java/
│ │   ├─ Pages/             # Page classes for POM
│ │   └─ SeleniumFramework/ # Helper utilities
│ │
│ └─ test/
│   └─ java/
│     └─ tests/             # TestNG test classes (TC1-TC20)
│
├─ testData/                 # JSON files for login, registration, contact forms
├─ pom.xml                   # Maven project file
├─ testng.xml                # TestNG suite configuration
└─ README.md                 # Project description and instructions


- **Pages/**: Contains all page objects with locators and methods.  
- **SeleniumFramework/**: Contains helper classes like `SeleniumHelper` for reusable actions.  
- **tests/**: Contains all test classes organized by functionality.  
- **testData/**: JSON files used for data-driven testing.  

---

## 5. Setup Instructions
1. **Clone the repository**
   ```bash
   git clone https://github.com/NourhanFarag/AutomationExcersices_SoftwareTestingProject.git

2. **Open the project in an IDE (IntelliJ IDEA, Eclipse) with Maven support.**

3. **Install dependencies using Maven:**
   ```bash
   mvn clean install

4. **Run tests via TestNG suite:**
   ```bash
   mvn test

5. **Generate Allure reports (optional):**
   ```bash
   mvn allure:serve

This will launch a browser showing the detailed test execution report.

---
**Author:** Nourhan Farag
**Project Type:** Selenium Automation FrameWork with Java, TestNG, and Allure Reports

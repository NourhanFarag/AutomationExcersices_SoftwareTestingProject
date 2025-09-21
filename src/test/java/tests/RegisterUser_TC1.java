package tests;

import Pages.*;
import base.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.Test;
import io.qameta.allure.Step;

/**
 * Test Case 1: Register New User
 * Fully integrated with Allure reporting (steps, attachments, screenshots)
 * 
 * @author Nourhan Farag
 */
public class RegisterUser_TC1 extends BaseTest {

    @DataProvider(name = "registerData")
    public Object[][] getRegisterData() throws Exception {
        LoadingData.RegisterUser[] users = LoadingData.readRegisterUsers("registeredUser.json");
        Object[][] data = new Object[users.length][1];
        for (int i = 0; i < users.length; i++) {
            data[i][0] = users[i];
        }
        return data;
    }

    @Test(dataProvider = "registerData")
    public void testRegisterUser(LoadingData.RegisterUser user) {
        SoftAssert softAssert = new SoftAssert();

        stepVerifyHomePage();
        stepGoToSignup();
        stepFillAccountInfo(user);
        stepCreateAccount(user);
        stepDeleteAccount(user);

        softAssert.assertAll();
    }

    @Step("Step 3: Verify Home Page is visible")
    private void stepVerifyHomePage() {
        HomePage home = new HomePage(driver, helper);
        attachText("Test Input", "Navigated to home page URL");
        attachText("Expected Output", "Home page should be visible");
        if (!home.isHomePageVisible()) {
            attachText("Actual Output", "Home page is NOT visible");
            throw new AssertionError("Home page is not visible!");
        }
    }

    @Step("Step 4-7: Go to Signup Page & Enter Info")
    private void stepGoToSignup() {
        HomePage home = new HomePage(driver, helper);
        LoginPage loginPage = home.clickSignupLogin();
        attachText("Step Info", "Clicked Signup/Login button");
        attachText("Expected Output", "New User Signup! header visible");
        if (!loginPage.getNewUserSignupText().equals("New User Signup!")) {
            attachText("Actual Output", loginPage.getNewUserSignupText());
            throw new AssertionError("New User Signup! header mismatch!");
        }
    }

    @Step("Step 9-12: Fill Account Info")
    private void stepFillAccountInfo(LoadingData.RegisterUser user) {
        HomePage home = new HomePage(driver, helper);
        LoginPage loginPage = home.clickSignupLogin();
        RegisterPage registerPage = loginPage.enterSignupDetails(user.signUpName, user.signUpEmail);

        attachText("Test Input", "Name: " + user.signUpName + ", Email: " + user.signUpEmail);
        attachText("Expected Output", "ENTER ACCOUNT INFORMATION header visible");

        if (!registerPage.getEnterAccountInfoText().equals("ENTER ACCOUNT INFORMATION")) {
            attachText("Actual Output", registerPage.getEnterAccountInfoText());
            throw new AssertionError("ENTER ACCOUNT INFORMATION header mismatch!");
        }

        registerPage.selectTitle(user.title);
        registerPage.enterPass(user.password);
        registerPage.selectDOB(user.dobDay, user.dobMonth, user.dobYear);
        registerPage.enterPersonalDetails(
                user.firstName, user.lastName, user.company,
                user.address1, user.address2, user.country,
                user.state, user.city, user.zipcode, user.mobileNumber
        );
        attachText("Filled Account Info", "All personal details filled successfully");
    }

    @Step("Step 13-14: Create Account")
    private void stepCreateAccount(LoadingData.RegisterUser user) {
        RegisterPage registerPage = new RegisterPage(driver, helper);
        AccountCreatedPage accountCreatedPage = registerPage.clickCreateAccount();

        attachText("Expected Output", "ACCOUNT CREATED! message");
        if (!accountCreatedPage.getAccountCreatedText().equals("ACCOUNT CREATED!")) {
            attachText("Actual Output", accountCreatedPage.getAccountCreatedText());
            throw new AssertionError("Account creation failed!");
        }

        HomePage home = accountCreatedPage.clickContinue();
        attachText("Expected Output", "Logged in as " + user.signUpName);
        if (!home.getLoggedInAsText().equals("Logged in as " + user.signUpName)) {
            attachText("Actual Output", home.getLoggedInAsText());
            throw new AssertionError("User login text mismatch after account creation!");
        }
    }

    @Step("Step 17-18: Delete Account")
    private void stepDeleteAccount(LoadingData.RegisterUser user) {
        HomePage home = new HomePage(driver, helper);
        AccountDeletedPage deletedPage = home.clickDeleteAccount();

        attachText("Expected Output", "ACCOUNT DELETED!");
        if (!deletedPage.getAccountDeletedText().equals("ACCOUNT DELETED!")) {
            attachText("Actual Output", deletedPage.getAccountDeletedText());
            throw new AssertionError("Account deletion failed!");
        }
        deletedPage.clickContinue();
    }
}

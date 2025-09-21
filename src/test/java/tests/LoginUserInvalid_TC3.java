package tests;

import Pages.*;
import base.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import io.qameta.allure.Step;
import tests.LoadingData.LoginUser;

/**
 * Test Case 3: Login with invalid credentials
 * Fully integrated with Allure reporting (steps, attachments, screenshots)
 * 
 * @author Nourhan Farag
 */
public class LoginUserInvalid_TC3 extends BaseTest {

    @DataProvider(name = "invalidLoginData")
    public Object[][] getInvalidLoginData() throws Exception {
        LoginUser[] users = LoadingData.readLoginUsers("invalidLoginUser.json");
        Object[][] data = new Object[users.length][1];
        for (int i = 0; i < users.length; i++) {
            data[i][0] = users[i];
        }
        return data;
    }

    @Test(dataProvider = "invalidLoginData")
    public void testInvalidLogin(LoginUser user) {
        SoftAssert softAssert = new SoftAssert();

        stepVerifyHomePage();
        stepGoToLoginPage();
        stepEnterInvalidCredentials(user);
        stepVerifyErrorMessage();

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

    @Step("Step 4: Navigate to Login Page")
    private void stepGoToLoginPage() {
        HomePage home = new HomePage(driver, helper);
        LoginPage loginPage = home.clickSignupLogin();
        attachText("Step Info", "Clicked Signup/Login button");
        attachText("Expected Output", "Login to your account header visible");
        if (!loginPage.getLoginHeaderText().equals("Login to your account")) {
            attachText("Actual Output", loginPage.getLoginHeaderText());
            throw new AssertionError("Login header mismatch!");
        }
    }

    @Step("Step 5-7: Enter invalid credentials and click login")
    private void stepEnterInvalidCredentials(LoginUser user) {
        HomePage home = new HomePage(driver, helper);
        LoginPage loginPage = home.clickSignupLogin();
        loginPage.enterLoginEmail(user.loginEmail);
        loginPage.enterLoginPassword(user.loginPassword);
        attachText("Test Input", "Email: " + user.loginEmail + " | Password: " + user.loginPassword);
        attachText("Expected Output", "Login should fail with error message");
        loginPage.clickLoginButton();
    }

    @Step("Step 8: Verify login error message")
    private void stepVerifyErrorMessage() {
        LoginPage loginPage = new LoginPage(driver, helper);
        String errorMessage = loginPage.getLoginErrorMessage();
        attachText("Expected Output", "Your email or password is incorrect!");
        if (!errorMessage.equals("Your email or password is incorrect!")) {
            attachText("Actual Output", errorMessage);
            throw new AssertionError("Error message mismatch!");
        }
    }
}

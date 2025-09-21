package tests;

import Pages.*;
import base.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import io.qameta.allure.Step;
import tests.LoadingData.LoginUser;

/**
 * 
 * @author Nourhan Farag
 */
public class LogoutUser_TC4 extends BaseTest {

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() throws Exception {
        LoadingData.LoginUser[] users = LoadingData.readLoginUsers("validLoginUser.json");
        Object[][] data = new Object[users.length][1];
        for (int i = 0; i < users.length; i++) {
            data[i][0] = users[i];
        }
        return data;
    }

    @Test(dataProvider = "loginData")
    public void testLogoutUser(LoginUser user) {
        SoftAssert softAssert = new SoftAssert();

        stepVerifyHomePage();
        stepGoToLoginPage();
        stepLoginWithValidCredentials(user);
        stepLogoutAndVerify();

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

    @Step("Step 5-7: Login with valid credentials")
    private void stepLoginWithValidCredentials(LoginUser user) {
        LoginPage loginPage = new LoginPage(driver, helper);
        loginPage.enterLoginEmail(user.loginEmail);
        loginPage.enterLoginPassword(user.loginPassword);
        attachText("Test Input", "Email: " + user.loginEmail + " | Password: " + user.loginPassword);
        attachText("Expected Output", "User should be logged in successfully");
        loginPage.clickLoginButton();
    }

    @Step("Step 8-10: Logout and verify navigation to login page")
    private void stepLogoutAndVerify() {
        HomePage home = new HomePage(driver, helper);
        LoginPage loginPageState = home.clickLogout();
        attachText("Expected Output", "Login to your account page visible after logout");
        if (!loginPageState.getLoginHeaderText().equals("Login to your account")) {
            attachText("Actual Output", loginPageState.getLoginHeaderText());
            throw new AssertionError("User did not navigate to login page after logout!");
        }
    }
}

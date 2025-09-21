package tests;

import Pages.*;
import base.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import io.qameta.allure.Step;

/**
 * 
 * @author Nourhan Farag
 */
public class LoginUser_TC2 extends BaseTest {

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
    public void testLoginUser(LoadingData.LoginUser user) {
        SoftAssert softAssert = new SoftAssert();

        stepVerifyHomePage();
        stepGoToLoginPage();
        stepEnterCredentials(user);
        stepVerifyLoggedIn();
        //stepDeleteAccount();

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

    @Step("Step 5-6: Enter credentials and login")
    private void stepEnterCredentials(LoadingData.LoginUser user) {
        HomePage home = new HomePage(driver, helper);
        LoginPage loginPage = home.clickSignupLogin();
        loginPage.enterLoginEmail(user.loginEmail);
        loginPage.enterLoginPassword(user.loginPassword);
        attachText("Test Input", "Email: " + user.loginEmail + " | Password: " + user.loginPassword);
        attachText("Expected Output", "User should be logged in successfully");

        loginPage.clickLoginButton();
    }

    @Step("Step 7: Verify user is logged in")
    private void stepVerifyLoggedIn() {
        HomePage home = new HomePage(driver, helper);
        attachText("Expected Output", "Logged in as " + "Nourhan");
        if (!home.isLoggedInAsVisible("Nourhan")) {
            attachText("Actual Output", "User not logged in as expected");
            throw new AssertionError("User not logged in as expected!");
        }
    }

    @Step("Step 8: Delete Account")
    private void stepDeleteAccount() {
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

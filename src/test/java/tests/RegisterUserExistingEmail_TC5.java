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
public class RegisterUserExistingEmail_TC5 extends BaseTest {

    @DataProvider(name = "existingUserData")
    public Object[][] getExistingUserData() throws Exception {
        LoginUser[] users = LoadingData.readLoginUsers("validLoginUser.json");
        Object[][] data = new Object[users.length][1];
        for (int i = 0; i < users.length; i++) {
            data[i][0] = users[i];
        }
        return data;
    }

    @Test(dataProvider = "existingUserData")
    public void testRegisterUserWithExistingEmail(LoginUser user) {
        SoftAssert softAssert = new SoftAssert();

        stepVerifyHomePage();
        stepGoToSignup();
        stepEnterExistingUserData(user);
        stepVerifyErrorMessage();

        softAssert.assertAll();
    }

    @Step("Step 3: Verify Home Page is visible")
    private void stepVerifyHomePage() {
        HomePage home = new HomePage(driver, helper);
        attachText("Expected Output", "Home page visible");
        if (!home.isHomePageVisible()) {
            attachText("Actual Output", "Home page NOT visible");
            throw new AssertionError("Home page is not visible!");
        }
    }

    @Step("Step 4: Navigate to Signup Page")
    private void stepGoToSignup() {
        HomePage home = new HomePage(driver, helper);
        LoginPage loginPage = home.clickSignupLogin();
        attachText("Expected Output", "New User Signup! header visible");
        if (!loginPage.getNewUserSignupHeaderText().equals("New User Signup!")) {
            attachText("Actual Output", loginPage.getNewUserSignupHeaderText());
            throw new AssertionError("Signup header mismatch!");
        }
    }

    @Step("Step 6-7: Enter existing email and click Signup")
    private void stepEnterExistingUserData(LoginUser user) {
        LoginPage loginPage = new LoginPage(driver, helper);
        loginPage.enterSignupName("Nourhan");
        loginPage.enterSignupEmail(user.loginEmail);
        attachText("Test Input", "Name: Nourhan | Email: " + user.loginEmail);
        loginPage.clickSignupButton();
    }

    @Step("Step 8: Verify error 'Email Address already exist!'")
    private void stepVerifyErrorMessage() {
        LoginPage loginPage = new LoginPage(driver, helper);
        String actualError = loginPage.getSignupErrorText();
        attachText("Expected Output", "Email Address already exist!");
        if (!actualError.equals("Email Address already exist!")) {
            attachText("Actual Output", actualError);
            throw new AssertionError("Error message mismatch!");
        }
    }
}

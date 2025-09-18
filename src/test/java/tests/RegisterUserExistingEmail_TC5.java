package tests;

import Pages.*;
import base.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

/**
 *
 * @author Nourhan Farag
 */

public class RegisterUserExistingEmail_TC5 extends BaseTest {

    @DataProvider(name = "existingUserData")
    public Object[][] getExistingUserData() throws Exception {
        LoadingData.LoginUser[] users = LoadingData.readLoginUsers("validLoginUser.json");
        Object[][] data = new Object[users.length][1];
        for (int i = 0; i < users.length; i++) {
            data[i][0] = users[i];
        }
        return data;
    }

    @Test(dataProvider = "existingUserData")
    public void registerUserWithExistingEmail(LoadingData.LoginUser user) {
        SoftAssert softAssert = new SoftAssert();

        // Step 3: Verify home page
        HomePage homePage = new HomePage(driver, helper);
        softAssert.assertTrue(homePage.isHomePageVisible(), "Home page is not visible!");

        // Step 4: Click on 'Signup / Login'
        LoginPage loginPage = homePage.clickSignupLogin();

        // Step 5: Verify 'New User Signup!' is visible
        softAssert.assertEquals(loginPage.getNewUserSignupHeaderText(),
                "New User Signup!", "Signup header mismatch!");

        // Step 6: Enter name + already registered email
        loginPage.enterSignupName("Nourhan");
        loginPage.enterSignupEmail(user.loginEmail);

        // Step 7: Click 'Signup'
        loginPage.clickSignupButton();

        // Step 8: Verify error message
        softAssert.assertEquals(loginPage.getSignupErrorText(),
                "Email Address already exist!", "Error message mismatch!");

        softAssert.assertAll();
    }
}

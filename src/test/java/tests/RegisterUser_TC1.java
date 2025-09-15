package tests;

import Pages.*;
import base.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.Test;

/**
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
        System.out.println("Registering user: " + user.email);

        // Step 3: Verify Home Page
        HomePage home = new HomePage(driver, helper);
        softAssert.assertTrue(home.isHomePageVisible(), "Home page not visible");

        // Step 4-7: Go to Signup Page & Enter Info
        LoginPage loginPage = home.clickSignupLogin();
        softAssert.assertEquals(loginPage.getNewUserSignupText(), "New User Signup!");

        RegisterPage registerPage = loginPage.enterSignupDetails(user.name, user.email);
        softAssert.assertEquals(registerPage.getEnterAccountInfoText(), "ENTER ACCOUNT INFORMATION");

        // Step 9-12: Fill Account Info
        registerPage.selectTitle(user.title);
        registerPage.enterPass(user.password);
        registerPage.selectDOB(user.dobDay, user.dobMonth, user.dobYear);
        registerPage.enterPersonalDetails(
                user.firstName, user.lastName, user.company,
                user.address1, user.address2, user.country,
                user.state, user.city, user.zipcode, user.mobileNumber
        );

        // Step 13-14: Create Account
        AccountCreatedPage accountCreatedPage = registerPage.clickCreateAccount();
        softAssert.assertEquals(accountCreatedPage.getAccountCreatedText(), "ACCOUNT CREATED!");

        home = accountCreatedPage.clickContinue();
        softAssert.assertEquals(home.getLoggedInAsText(), "Logged in as " + user.name);

        // Step 17-18: Delete Account
        AccountDeletedPage deletedPage = home.clickDeleteAccount();
        softAssert.assertEquals(deletedPage.getAccountDeletedText(), "ACCOUNT DELETED!");
        deletedPage.clickContinue();

        softAssert.assertAll();
    }
}

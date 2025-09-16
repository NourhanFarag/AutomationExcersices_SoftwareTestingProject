package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import Pages.*;
import base.BaseTest;
import org.testng.annotations.DataProvider;

public class LoginUser_TC2 extends BaseTest {

    @DataProvider(name = "loginData")
    public Object[][] getRegisterData() throws Exception {
        LoadingData.RegisterUser[] users = LoadingData.readRegisterUsers("registeredUser.json");
        Object[][] data = new Object[users.length][1];
        for (int i = 0; i < users.length; i++) {
            data[i][0] = users[i];
        }
        return data;
    }
    @Test(dataProvider = "loginData")
    public void loginUserWithCorrectCredentials(LoadingData.RegisterUser user) {
        HomePage homePage = new HomePage(driver, helper);

        // Step 3: Verify homepage visible
        Assert.assertTrue(homePage.isHomePageVisible(), "Home page is not visible!");

        // Step 4: Click signup/login
        LoginPage loginPage = homePage.clickSignupLogin();

        // Step 5: Verify login header
        Assert.assertEquals(loginPage.getLoginHeaderText(), "Login to your account", "Login header mismatch!");

        // Step 6: Enter credentials
        loginPage.enterLoginEmail(user.email);
        loginPage.enterLoginPassword(user.password);
        
        // Step 7: Click login
        loginPage.clickLoginButton();

        // Step 8: Verify logged in as username
        Assert.assertEquals(homePage.getLoggedInAsText(), "Logged in as " + user.name);

        // Step 9: Delete account
        //AccountDeletedPage deletedPage = homePage.clickDeleteAccount();

        // Step 10: Verify account deleted
        //Assert.assertEquals(deletedPage.getAccountDeletedText(), "ACCOUNT DELETED!", "Mismatch in Account Deleted message!");
    }
}

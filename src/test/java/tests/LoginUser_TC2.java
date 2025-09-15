package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import Pages.*;
import base.BaseTest;

public class LoginUser_TC2 extends BaseTest {

    @Test
    public void loginUserWithCorrectCredentials() {
        HomePage homePage = new HomePage(driver, helper);

        // Step 3: Verify homepage visible
        Assert.assertTrue(homePage.isHomePageVisible(), "Home page is not visible!");

        // Step 4: Click signup/login
        LoginPage loginPage = homePage.clickSignupLogin();

        // Step 5: Verify login header
        Assert.assertEquals(loginPage.getLoginHeaderText(), "Login to your account", "Login header mismatch!");

        // Step 6: Enter credentials
        String email = "nourhan_test@example.com";
        String password = "Pass123";
        loginPage.enterLoginEmail(email);
        loginPage.enterLoginPassword(password);

        // Step 7: Click login
        loginPage.clickLoginButton();

        // Step 8: Verify logged in as username
        Assert.assertEquals(homePage.getLoggedInUsername(), "Nourhan", "Logged in username mismatch!");

        // Step 9: Delete account
        AccountDeletedPage deletedPage = homePage.clickDeleteAccount();

        // Step 10: Verify account deleted
        Assert.assertEquals(deletedPage.getAccountDeletedText(), "ACCOUNT DELETED!", "Mismatch in Account Deleted message!");
    }
}
package tests;

import Pages.*;
import base.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import tests.LoadingData.LoginUser;

/**
 *
 * @author Nourhan Farag
 */
public class LoginUserInvalid_TC3 extends BaseTest{

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
    public void lologinUserWithIncorrectCredentials(LoginUser user) {
        SoftAssert softAssert = new SoftAssert();
        
        //3. Verify that home page is visible successfully
        HomePage homePage = new HomePage(driver, helper);
        softAssert.assertTrue(homePage.isHomePageVisible(), "Home page is not visible!");
        
        //4. Click on 'Signup / Login' button
        LoginPage loginPage = homePage.clickSignupLogin();
        
        //5. Verify 'Login to your account' is visible
        softAssert.assertEquals(loginPage.getLoginHeaderText(), "Login to your account", "Login header mismatch!");
        
        //6. Enter incorrect email address and password
        loginPage.enterLoginEmail(user.loginEmail);
        loginPage.enterLoginPassword(user.loginPassword);
        
        //7. Click 'login' button
        loginPage.clickLoginButton();
        
        //8. Verify error 'Your email or password is incorrect!' is visible
        softAssert.assertEquals(loginPage.getLoginErrorMessage(), "Your email or password is incorrect!", "Error message mismatch!");
        
        softAssert.assertAll();
    }
}

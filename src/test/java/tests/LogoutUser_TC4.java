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
public class LogoutUser_TC4 extends BaseTest{
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
    public void logoutUserTest(LoadingData.RegisterUser user) {
        SoftAssert softAssert = new SoftAssert();
        
        //3. Verify that home page is visible successfully
        HomePage homePage = new HomePage(driver, helper);
        softAssert.assertTrue(homePage.isHomePageVisible(), "Home Page is not visible!");
        
        //4. Click on 'Signup / Login' button
        LoginPage loginPage = homePage.clickSignupLogin();
        
        //5. Verify 'Login to your account' is visible
        softAssert.assertEquals(loginPage.getLoginHeaderText(), "Login to your account", "Login header mismatch!");

        //6. Enter correct email address and password
        loginPage.enterLoginEmail(user.email);
        loginPage.enterLoginPassword(user.password);
        
        //7. Click 'login' button
        loginPage.clickLoginButton();
        
        //8. Verify that 'Logged in as username'
        softAssert.assertEquals(homePage.getLoggedInAsText(), "Logged in as " + user.name);
        
        //9. Click 'Logout' button
        homePage.clickLogout();
        
        //10. Verify that user is navigated to login page
        softAssert.assertEquals(loginPage.getLoginHeaderText(), "Login to your account", "User did not navigate to login page after logout!");
        
        softAssert.assertAll();
    }
    
}

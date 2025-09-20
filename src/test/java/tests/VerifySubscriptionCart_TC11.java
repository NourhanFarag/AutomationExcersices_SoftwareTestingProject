package tests;

import Pages.*;
import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
/**
 *
 * @author Nourhan Farag
 */
public class VerifySubscriptionCart_TC11 extends BaseTest{
    
    @Test
    public void verifySubscriptionInCartPage(){
        HomePage homePage = new HomePage(driver, helper);
        SoftAssert softAssert = new SoftAssert();
        // Step 3: Verify that home page is visible successfully
        Assert.assertTrue(homePage.isHomePageVisible(), "Home page is not visible!");
        
        // Step 4: Click 'Cart' button
        homePage.clickCart();
        
        // Step 5: Scroll down to footer
        CartPage cartPage = new CartPage(driver, helper);
        cartPage.scrollToFooter();
        
        // Step 6: Verify text 'SUBSCRIPTION'
        softAssert.assertEquals(homePage.getSubscriptionText().toUpperCase(), "SUBSCRIPTION", "'SUBSCRIPTION' text is not visible!");
        
        // Step 7: Enter email address in input and click arrow button
        cartPage.subscribeWithEmail("nourhan@gmeail.com");
        
        // Step 8: Verify success message 'You have been successfully subscribed!' is visible  
        String successMsg = cartPage.getSubscriptionSuccessMessage();
        softAssert.assertEquals(successMsg, "You have been successfully subscribed!", "Success message mismatch!");
        
        System.out.println("Subscription success message: " + successMsg);
        softAssert.assertAll(); 
    }
}

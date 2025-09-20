package tests;

import Pages.*;
import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
/**
 *
 * @author Nourhan Farag
 */
public class VerifySubscriptionHome_TC10 extends BaseTest{
    @Test
    public void verifySubscriptionInHomePage() {
        HomePage homePage = new HomePage(driver, helper);

        // Step 3: Verify Home Page
        Assert.assertTrue(homePage.isHomePageVisible(), "Home page is not visible!");

        // Step 4: Scroll down to footer
        homePage.scrollToFooter();

        // Step 5: Verify text 'SUBSCRIPTION'
        Assert.assertEquals(homePage.getSubscriptionText().toUpperCase(), "SUBSCRIPTION", "'SUBSCRIPTION' text is not visible!");

        // Step 6: Enter email and click arrow button
        String email = "test@gmail.com";
        homePage.subscribeWithEmail(email);

        // Step 7: Verify success message
        Assert.assertEquals(homePage.getSubscriptionSuccessMessage(), "You have been successfully subscribed!",
                "Subscription success message not visible!");
    }
}

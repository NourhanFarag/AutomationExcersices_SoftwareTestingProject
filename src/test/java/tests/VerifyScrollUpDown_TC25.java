package tests;

import Pages.*;
import base.BaseTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
/**
 *
 * @author Nourhan Farag
 */
public class VerifyScrollUpDown_TC25 extends BaseTest{
    @Test
    public void verifyScrollUpDownFunctionality() {
        SoftAssert softAssert = new SoftAssert();

        // Step 3: Verify that home page is visible
        HomePage homePage = new HomePage(driver, helper);
        softAssert.assertTrue(homePage.isHomePageVisible(), "Home page is not visible!");

        // Step 4: Scroll down page to bottom
        homePage.scrollToFooter();

        // Step 5: Verify 'SUBSCRIPTION' section is visible
        softAssert.assertTrue(homePage.isSubscriptionSectionVisible(), "'SUBSCRIPTION' section is not visible!");

        // Step 6: Click on arrow at bottom right side to move upward
        homePage.clickScrollUpArrow();

        // Step 7: Verify that page is scrolled up
        softAssert.assertTrue(homePage.isPageScrolledUp(), "Page did not scroll to top!");
        softAssert.assertTrue(homePage.isTopTextVisible(),
                "'Full-Fledged practice website for Automation Engineers' text not visible!");

        softAssert.assertAll();
    }
}

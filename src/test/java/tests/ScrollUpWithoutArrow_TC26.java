package tests;

import Pages.*;
import base.BaseTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

/**
 *
 * @author Nourhan Farag
 */
public class ScrollUpWithoutArrow_TC26 extends BaseTest {

    @Test
    public void verifyScrollUpWithoutArrow() {
        SoftAssert softAssert = new SoftAssert();

        // Step 3: Open Home Page
        HomePage homePage = new HomePage(driver, helper);
        softAssert.assertTrue(homePage.isHomePageVisible(), "Home page is not visible!");

        // Step 4: Scroll down to bottom
        homePage.scrollToBottom();

        // Step 5: Verify 'SUBSCRIPTION' is visible
        softAssert.assertTrue(homePage.isSubscriptionSectionVisible(), "'SUBSCRIPTION' section not visible after scrolling down!");

        // Step 6: Scroll up to top using JS
        helper.executeJS("window.scrollTo(0, 0);");

        // Step 7: Verify page is scrolled up and top text is visible
        softAssert.assertTrue(homePage.isTopTextVisible(), "'Full-Fledged practice website for Automation Engineers' text is not visible at top!");

        softAssert.assertAll();
    }
}

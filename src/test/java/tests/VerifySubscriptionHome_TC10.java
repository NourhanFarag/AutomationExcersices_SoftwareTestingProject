package tests;

import Pages.*;
import base.BaseTest;
import org.testng.annotations.Test;
import io.qameta.allure.Step;

/**
 * 
 * @author Nourhan Farag
 */
public class VerifySubscriptionHome_TC10 extends BaseTest {

    @Test
    public void verifySubscriptionInHomePage() {
        stepVerifyHomePage();
        stepScrollToFooter();
        stepSubscribeWithEmail("test@gmail.com");
    }

    @Step("Step 3: Verify Home Page is visible")
    private void stepVerifyHomePage() {
        HomePage home = new HomePage(driver, helper);
        attachText("Expected Output", "Home page visible");
        if (!home.isHomePageVisible()) {
            attachText("Actual Output", "Home page NOT visible");
            throw new AssertionError("Home page is not visible!");
        }
    }

    @Step("Step 4: Scroll down to footer and verify 'SUBSCRIPTION'")
    private void stepScrollToFooter() {
        HomePage home = new HomePage(driver, helper);
        home.scrollToFooter();
        if (!home.isSubscriptionSectionVisible()) {
            attachText("Actual Output", "'SUBSCRIPTION' not visible");
            throw new AssertionError("'SUBSCRIPTION' section not visible!");
        }
    }

    @Step("Step 6-7: Subscribe using email '{email}' and verify success message")
    private void stepSubscribeWithEmail(String email) {
        HomePage home = new HomePage(driver, helper);
        home.subscribeWithEmail(email);
        if (!home.getSubscriptionSuccessMessage().equals("You have been successfully subscribed!")) {
            attachText("Actual Output", home.getSubscriptionSuccessMessage());
            throw new AssertionError("Subscription success message not visible!");
        }
    }
}

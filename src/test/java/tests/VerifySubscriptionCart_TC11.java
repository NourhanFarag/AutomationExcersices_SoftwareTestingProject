package tests;

import Pages.*;
import base.BaseTest;
import io.qameta.allure.Step;
import org.testng.annotations.Test;

/**
 *
 * @author Nourhan Farag
 */
public class VerifySubscriptionCart_TC11 extends BaseTest {

    @Test
    public void verifySubscriptionInCartPage() {
        stepVerifyHomePage();
        stepGoToCart();
        stepScrollToFooter();
        stepSubscribeWithEmail("nourhan@gmail.com");
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

    @Step("Step 4: Click 'Cart' button")
    private void stepGoToCart() {
        HomePage home = new HomePage(driver, helper);
        home.clickCart();
        attachText("Step Info", "Navigated to Cart page");
    }

    @Step("Step 5: Scroll to footer and verify 'SUBSCRIPTION'")
    private void stepScrollToFooter() {
        CartPage cart = new CartPage(driver, helper);
        cart.scrollToFooter();
        if (!cart.isSubscriptionSectionVisible()) {
            attachText("Actual Output", "'SUBSCRIPTION' section not visible");
            throw new AssertionError("'SUBSCRIPTION' section not visible!");
        }
    }

    @Step("Step 7: Enter email and subscribe")
    private void stepSubscribeWithEmail(String email) {
        CartPage cart = new CartPage(driver, helper);
        cart.subscribeWithEmail(email);
        String msg = cart.getSubscriptionSuccessMessage();
        if (!msg.equals("You have been successfully subscribed!")) {
            attachText("Actual Output", msg);
            throw new AssertionError("Subscription success message not visible!");
        }
        attachText("Subscription Success Message", msg);
    }
}

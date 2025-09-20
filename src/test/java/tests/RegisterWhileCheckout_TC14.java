package tests;

import Pages.*;
import base.BaseTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

/**
 *
 * @author Nourhan Farag
 */
public class RegisterWhileCheckout_TC14 extends BaseTest {

    @Test
    public void placeOrder_RegisterWhileCheckout() {
        SoftAssert softAssert = new SoftAssert();

        // Step 3: Verify home page
        HomePage homePage = new HomePage(driver, helper);
        softAssert.assertTrue(homePage.isHomePageVisible(), "Home page is not visible!");

        // Step 4: Add product to cart
        ProductsPage productsPage = homePage.clickProducts();
        productsPage.addFirstProductToCart();
        productsPage.clickContinueShopping();

        // Step 5: Click Cart
        homePage.clickCart();
        CartPage cartPage = new CartPage(driver, helper);

        // Step 6: Verify that cart page is displayed
        softAssert.assertTrue(cartPage.isCartPageVisible(), "Cart page is not visible!");

        // Step 7: Proceed To Checkout
        cartPage.clickProceedToCheckout();

        // Step 8: Click 'Register / Login'
        LoginPage loginPage = cartPage.clickRegisterLogin();

        // Step 9: Fill all details in Signup and create account
        String randomEmail = "nourhan" + System.currentTimeMillis() + "@mail.com";
        RegisterPage registerPage = loginPage.enterSignupDetails("Nourhan", randomEmail);

        softAssert.assertEquals(registerPage.getEnterAccountInfoText(), "ENTER ACCOUNT INFORMATION", "Account Info header mismatch!");

        AccountCreatedPage accountCreatedPage = registerPage.registerNewUser(
                "Mrs", "123456", "10", "May", "1999",
                "Nourhan", "Farag", "TechCorp",
                "123 Test St", "Apt 4", "Canada",
                "Ontario", "Toronto", "M5A1A1", "1234567890"
        );

        // Step 10: Verify 'ACCOUNT CREATED!' and click 'Continue' button
        softAssert.assertEquals(accountCreatedPage.getAccountCreatedText(), "ACCOUNT CREATED!", "Account was not created!");
        homePage = accountCreatedPage.clickContinue();

        // Step 11: Verify 'Logged in as username'
        softAssert.assertTrue(homePage.isLoggedInAs("Nourhan"), "Logged in username not displayed!");

        // Step 12: Click Cart button again
        homePage.clickCart();
        cartPage = new CartPage(driver, helper);

        // Step 13: Proceed To Checkout
        cartPage.clickProceedToCheckout();

        // Step 14: Verify Address Details and Review Your Order
        softAssert.assertTrue(cartPage.isAddressDetailsVisible(), "Address details not visible!");
        softAssert.assertTrue(cartPage.isReviewOrderVisible(), "Review order section not visible!");

        // Step 15: Enter description and click 'Place Order'
        cartPage.enterComment("Please deliver between 10 AM and 5 PM.");
        PaymentPage paymentPage = cartPage.clickPlaceOrder();

        // Step 16: Enter payment details
        paymentPage.enterPaymentDetails("Nourhan Farag", "87189581861", "123", "12", "2026");

        // Step 17: Click 'Pay and Confirm Order' button
        OrderConfirmationPage orderConfirmationPage = paymentPage.clickPayAndConfirm();

        // Step 18: Verify success message
        softAssert.assertEquals(
            orderConfirmationPage.getOrderSuccessMessageText(),
            "Congratulations! Your order has been confirmed!",
            "Order success message text did not match!"
        );

        // Step 19: Delete Account
        AccountDeletedPage accountDeletedPage = homePage.clickDeleteAccount();

        // Step 20: Verify 'ACCOUNT DELETED!' and click 'Continue' button
        softAssert.assertEquals(accountDeletedPage.getAccountDeletedText(), "ACCOUNT DELETED!", "Account not deleted!");
        accountDeletedPage.clickContinue();

        // Assert all at once
        softAssert.assertAll();
    }
}

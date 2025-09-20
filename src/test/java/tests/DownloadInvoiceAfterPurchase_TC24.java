package tests;

import Pages.*;
import base.BaseTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class DownloadInvoiceAfterPurchase_TC24 extends BaseTest {

    @Test
    public void downloadInvoiceAfterPurchase() {
        SoftAssert softAssert = new SoftAssert();
        HomePage homePage = new HomePage(driver, helper);

        // Step 3: Verify home page is visible
        softAssert.assertTrue(homePage.isHomePageVisible(), "Home page is not visible!");

        // Step 4: Add products to cart
        ProductsPage productsPage = homePage.clickProducts();
        productsPage.addFirstProductToCart();
        productsPage.clickViewCart();

        // Step 6: Verify cart page
        CartPage cartPage = new CartPage(driver, helper);
        softAssert.assertTrue(cartPage.isCartPageVisible(), "Cart page not displayed!");

        // Step 7: Proceed to Checkout
        cartPage.clickProceedToCheckout();

        // Step 8: Click 'Register / Login'
        LoginPage loginPage = homePage.clickSignupLogin();
        softAssert.assertEquals(loginPage.getNewUserSignupText(), "New User Signup!");

        // Step 9: Fill details and create account
        String email = "nourhan" + System.currentTimeMillis() + "@mail.com";
        loginPage.enterSignupName("Nourhan");
        loginPage.enterSignupEmail(email);
        loginPage.clickSignupButton();

        RegisterPage registerPage = new RegisterPage(driver, helper);
        AccountCreatedPage accountCreatedPage = registerPage.registerNewUser(
                "Mrs", "test123", "15", "May", "2000",
                "Nourhan", "Farag", "TechCorp",
                "123 Street", "Apt 5", "Canada",
                "Ontario", "Toronto", "A1B2C3", "1234567890"
        );

        // Step 10: Verify account created and click continue
        softAssert.assertEquals(accountCreatedPage.getAccountCreatedText(),
                "ACCOUNT CREATED!", "Account created text mismatch!");
        accountCreatedPage.clickContinue();

        // Step 11: Verify logged in
        softAssert.assertTrue(homePage.isLoggedInAs("Nourhan"), "User is not logged in!");

        // Step 12: Click 'Cart' button
        homePage.clickCart();
        softAssert.assertTrue(cartPage.isCartPageVisible(), "Cart page not displayed!");

        // Step 13: Proceed to Checkout
        cartPage.clickProceedToCheckout();

        // Step 14: Verify Address Details and Review Your Order
        softAssert.assertTrue(cartPage.isAddressDetailsVisible(), "Address details not visible!");
        softAssert.assertTrue(cartPage.isReviewOrderVisible(), "Review your order section not visible!");

        // Step 15: Enter comment and place order
        cartPage.enterComment("Please deliver between 9 AM - 5 PM.");
        PaymentPage paymentPage = cartPage.clickPlaceOrder();

        // Step 16: Enter payment details
        paymentPage.enterPaymentDetails(
                "Nourhan Farag", "986689626", "123", "12", "2025"
        );

        // Step 17: Pay and confirm order
        paymentPage.clickPayAndConfirm();

        // Step 18: Verify order success message
        softAssert.assertEquals(paymentPage.getOrderSuccessMessage(),
                "Congratulations! Your order has been confirmed!", "Order success message mismatch!");

        // Step 19: Download invoice and verify download
        paymentPage.downloadInvoice();
        softAssert.assertTrue(paymentPage.isInvoiceDownloaded(), "Invoice was not downloaded!");

        // Step 20: Click 'Continue'
        paymentPage.clickContinue();

        // Step 21: Delete account
        homePage.clickDeleteAccount();

        // Step 22: Verify account deleted
        AccountDeletedPage accountDeletedPage = new AccountDeletedPage(driver, helper);
        softAssert.assertEquals(accountDeletedPage.getAccountDeletedText(),
                "ACCOUNT DELETED!", "Account deleted text mismatch!");
        accountDeletedPage.clickContinue();

        softAssert.assertAll();
    }
}

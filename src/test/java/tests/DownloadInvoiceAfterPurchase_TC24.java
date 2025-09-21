package tests;

import Pages.*;
import base.BaseTest;
import java.io.FileNotFoundException;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class DownloadInvoiceAfterPurchase_TC24 extends BaseTest {

    @Test
    public void downloadInvoiceAfterPurchase() throws FileNotFoundException {
        SoftAssert softAssert = new SoftAssert();
        HomePage homePage = new HomePage(driver, helper);

        // Step 3: Verify home page is visible
        softAssert.assertTrue(homePage.isHomePageVisible(), "Home page is not visible!");

        // Step 4: Add products to cart
        homePage.clickProducts();
        ProductsPage productsPage = new ProductsPage(driver, helper);
        productsPage.addFirstProductToCart();
        productsPage.clickContinueShopping();
        
        // Step 5: Click 'Cart'
        homePage.clickCart();

        // Step 6: Verify cart page
        CartPage cartPage = new CartPage(driver, helper);
        softAssert.assertTrue(cartPage.isCartPageVisible(), "Cart page not displayed!");

        // Step 7: Proceed to Checkout
        CheckoutPage checkoutPage = cartPage.clickProceedToCheckout();

        // Step 8: Click 'Register / Login'
        LoginPage loginPage = cartPage.clickRegisterLogin();

        // Step 9: Fill details and create account
        String randomEmail = "nourhan" + System.currentTimeMillis() + "@mail.com";
        RegisterPage registerPage = loginPage.enterSignupDetails("Nourhan", randomEmail);

        softAssert.assertEquals(registerPage.getEnterAccountInfoText(), "ENTER ACCOUNT INFORMATION", "Account Info header mismatch!");

        AccountCreatedPage accountCreatedPage = registerPage.registerNewUser(
                "Mrs", "123456", "10", "May", "1999",
                "Nourhan", "Farag", "TechCorp",
                "123 Test St", "Apt 4", "Canada",
                "Ontario", "Toronto", "M5A1A1", "1234567890"
        );

        // Step 10: Verify account created and click continue
        softAssert.assertEquals(accountCreatedPage.getAccountCreatedText(), "ACCOUNT CREATED!", "Account created text mismatch!");
        accountCreatedPage.clickContinue();

        // Step 11: Verify logged in
        softAssert.assertTrue(homePage.isLoggedInAs("Nourhan"), "User is not logged in!");

        // Step 12: Click 'Cart' button
        homePage.clickCart();

        // Step 13: Proceed to Checkout
        cartPage.clickProceedToCheckout();
        softAssert.assertTrue(cartPage.isAddressDetailsVisible(), "Address Details not visible");
        softAssert.assertTrue(cartPage.isReviewOrderVisible(), "Review Your Order section not visible");

        // Step 14: Enter comment and place order
        cartPage.enterComment("Please deliver between 9 AM - 5 PM.");
        PaymentPage paymentPage = checkoutPage.clickPlaceOrder();

        // Step 15: Enter payment details
        paymentPage.enterPaymentDetails("Nourhan Farag", "986689626", "123", "12", "2025");
        OrderConfirmationPage orderConfirmationPage = paymentPage.clickPayAndConfirm();

        // Step 16: Verify order success message
        softAssert.assertTrue(orderConfirmationPage.isOrderSuccessMessageVisible(), "Order success message not displayed");
        softAssert.assertTrue(orderConfirmationPage.getOrderSuccessMessageText()
                .contains("Congratulations! Your order has been confirmed!"));
        
        // Step 17: Download invoice and verify download
        paymentPage.downloadInvoice();
        softAssert.assertTrue(paymentPage.isInvoiceDownloaded(), "Invoice was not downloaded!");

        // Step 18: Click 'Continue'
        paymentPage.clickContinue();

        // Step 19: Delete account
        homePage.clickDeleteAccount();

        // Step 20: Verify account deleted
        AccountDeletedPage accountDeletedPage = new AccountDeletedPage(driver, helper);
        softAssert.assertEquals(accountDeletedPage.getAccountDeletedText(),
                "ACCOUNT DELETED!", "Account deleted text mismatch!");
        accountDeletedPage.clickContinue();

        softAssert.assertAll();
    }
}

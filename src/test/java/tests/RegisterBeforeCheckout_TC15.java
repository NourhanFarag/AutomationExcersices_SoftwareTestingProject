package tests;

import Pages.*;
import base.BaseTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
/**
 *
 * @author Nourhan Farag
 */
public class RegisterBeforeCheckout_TC15 extends BaseTest{
    
     @Test
    public void placeOrder_RegisterBeforeCheckout() {
        SoftAssert softAssert = new SoftAssert();
        HomePage homePage = new HomePage(driver, helper);

        // Step 3: Verify Home Page
        softAssert.assertTrue(homePage.isHomePageVisible(), "Home page is not visible!");

        // Step 4: Click Signup/Login
        LoginPage loginPage = homePage.clickSignupLogin();

        // Step 5: Fill details in Signup
        String name = "Nourhan";
        String email = "nourhan" + System.currentTimeMillis() + "@test.com";
        RegisterPage registerPage = loginPage.enterSignupDetails(name, email);

        // Fill account info
        softAssert.assertEquals(registerPage.getEnterAccountInfoText(), "ENTER ACCOUNT INFORMATION", "Account info header mismatch!");
        registerPage.selectTitle("Mrs");
        registerPage.enterPass("Test@123");
        registerPage.selectDOB("10", "May", "1999");
        registerPage.checkNewsletter();
        registerPage.checkSpecialOffers();
        registerPage.enterPersonalDetails("Nourhan", "Farag", "Shoubra", "Cairo", "Nasr City",
                                          "India", "Cairo", "Cairo", "11765", "01012345678");

        AccountCreatedPage accountCreatedPage = registerPage.clickCreateAccount();

        // Step 6: Verify account created
        softAssert.assertEquals(accountCreatedPage.getAccountCreatedText(), "ACCOUNT CREATED!", "Account creation text mismatch!");
        HomePage homePageAfterRegister = accountCreatedPage.clickContinue();

        // Step 7: Verify logged in as username
        softAssert.assertTrue(homePageAfterRegister.isLoggedInAs(name), "Logged in username not displayed!");

        // Step 8: Add products to cart
        ProductsPage productsPage = homePageAfterRegister.clickProducts();
        ProductDetailPage productDetailPage = productsPage.clickFirstViewProduct();
        productDetailPage.clickAddToCart();
        CartPage cartPage = productDetailPage.clickViewCart();

        // Step 9 + 10: Verify cart page is displayed
        softAssert.assertTrue(cartPage.isCartPageVisible(), "Cart page is not visible!");

        // Step 11: Proceed to Checkout
        CheckoutPage checkoutPage = cartPage.clickProceedToCheckout();

        // Step 12: Verify Address & Review Order
        softAssert.assertTrue(checkoutPage.isAddressDetailsVisible(), "Address details not visible!");
        softAssert.assertTrue(checkoutPage.isReviewOrderVisible(), "Review Order section not visible!");

        // Step 13: Enter description and place order
        checkoutPage.enterComment("Please deliver between 9AM-5PM");
        PaymentPage paymentPage = (PaymentPage) checkoutPage.clickPlaceOrder();

        // Step 14: Enter payment details
        paymentPage.enterPaymentDetails("Nourhan Farag", "238263506326852", "123", "12", "2026");

        // Step 15: Pay & Confirm Order
        OrderConfirmationPage orderConfirmationPage = paymentPage.clickPayAndConfirm();

        // Step 16: Verify success message (assertEquals instead of assertTrue)
        softAssert.assertEquals(
            orderConfirmationPage.getOrderSuccessMessageText(),
            "Congratulations! Your order has been confirmed!",
            "Order success message text did not match!"
        );

        // Step 17: Delete account
        AccountDeletedPage accountDeletedPage = homePageAfterRegister.clickDeleteAccount();

        // Step 18: Verify account deleted
        softAssert.assertEquals(accountDeletedPage.getAccountDeletedText(), "ACCOUNT DELETED!", "Account deleted text mismatch!");
        accountDeletedPage.clickContinue();

        // Collect all assertions
        softAssert.assertAll();
    }
}

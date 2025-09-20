package tests;

import Pages.*;
import base.BaseTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyAddressDetailsCheckout_TC23 extends BaseTest {

    @Test
    public void verifyAddressDetailsInCheckout() {
        SoftAssert softAssert = new SoftAssert();
        HomePage homePage = new HomePage(driver, helper);

        // Step 3: Verify home page
        softAssert.assertTrue(homePage.isHomePageVisible(),
                "Home page is not visible!");

        // Step 4: Go to Signup/Login
        LoginPage loginPage = homePage.clickSignupLogin();
        softAssert.assertEquals(loginPage.getNewUserSignupText(), "New User Signup!");

        // Step 5: Enter signup details
        String email = "nourhan" + System.currentTimeMillis() + "@mail.com";
        RegisterPage registerPage = loginPage.enterSignupDetails("Nourhan", email);

        // Step 6: Verify account info page
        softAssert.assertEquals(registerPage.getEnterAccountInfoText(),
                "ENTER ACCOUNT INFORMATION", "Account info header mismatch!");

        // Register new user and save address
        AccountCreatedPage accountCreatedPage = registerPage.registerNewUser(
                "Mrs", "test123", "15", "May", "2000",
                "Nourhan", "Farag", "TechCorp",
                "123 Street", "Apt 5", "Canada",
                "Ontario", "Toronto", "A1B2C3", "1234567890"
        );

        // Verify account created
        softAssert.assertEquals(accountCreatedPage.getAccountCreatedText(),
                "ACCOUNT CREATED!", "Account created text mismatch!");
        homePage = accountCreatedPage.clickContinue();

        // Step 7: Verify logged in
        softAssert.assertTrue(homePage.isLoggedInAs("Nourhan"),
                "User is not logged in!");

        // Step 8: Add product to cart
        ProductsPage productsPage = homePage.clickProducts();
        productsPage.addFirstProductToCart();
        productsPage.clickViewCart();

        CartPage cartPage = new CartPage(driver, helper);
        // Step 9-10: Verify cart page
        softAssert.assertTrue(cartPage.isCartPageVisible(),
                "Cart page not displayed!");

        // Step 11: Proceed to Checkout
        CheckoutPage checkoutPage = cartPage.clickProceedToCheckout();

        // Step 12-13: Verify delivery & billing addresses
        Address expectedAddress = registerPage.getRegisteredAddress();
        Address deliveryAddress = checkoutPage.getDeliveryAddress();
        Address billingAddress = checkoutPage.getBillingAddress();

        softAssert.assertEquals(deliveryAddress, expectedAddress,
                "Delivery address does not match registered address!");
        softAssert.assertEquals(billingAddress, expectedAddress,
                "Billing address does not match registered address!");

        // Step 14: Delete account
        AccountDeletedPage accountDeletedPage = homePage.clickDeleteAccount();

        // Step 15: Verify account deleted
        softAssert.assertEquals(accountDeletedPage.getAccountDeletedText(),
                "ACCOUNT DELETED!", "Account deleted text mismatch!");
        accountDeletedPage.clickContinue();

        softAssert.assertAll();
    }
}

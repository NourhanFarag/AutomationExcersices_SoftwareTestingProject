package tests;

import Pages.*;
import base.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
/**
 *
 * @author Nourhan Farag
 */
public class LoginBeforeCheckout_TC16 extends BaseTest{
    
    @DataProvider(name = "loginData")
    public Object[][] getLoginData() throws Exception {
        LoadingData.LoginUser[] users = LoadingData.readLoginUsers("validLoginUser.json");
        Object[][] data = new Object[users.length][1];
        for (int i = 0; i < users.length; i++) {
            data[i][0] = users[i];
        }
        return data;
    }
    
    @Test(dataProvider = "loginData")
    public void placeOrder_LoginBeforeCheckout(LoadingData.LoginUser user) {
        SoftAssert softAssert = new SoftAssert();

        HomePage homePage = new HomePage(driver, helper);

        // Step 3: Verify home page
        softAssert.assertTrue(homePage.isHomePageVisible(), "Home page is not visible!");

        // Step 4: Click Signup/Login
        LoginPage loginPage = homePage.clickSignupLogin();

        // Step 5: Login with provided JSON data
        loginPage.enterLoginEmail(user.loginEmail);
        loginPage.enterLoginPassword(user.loginPassword);
        loginPage.clickLoginButton();

        // Step 6: Verify logged in as username
        softAssert.assertTrue(homePage.isLoggedInAs(user.loginEmail.split("f")[0]), "Logged in username not displayed!");

        // Step 7: Add products to cart
        ProductsPage productsPage = homePage.clickProducts();
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
        paymentPage.enterPaymentDetails("Nourhan Farag", "57186825581", "123", "12", "2026");

        // Step 15: Pay & Confirm Order
        OrderConfirmationPage orderConfirmationPage = paymentPage.clickPayAndConfirm();

        // Step 16: Verify success message
        softAssert.assertEquals(
            orderConfirmationPage.getOrderSuccessMessageText(),
            "Congratulations! Your order has been confirmed!",
            "Order success message text did not match!"
        );

        // Step 17: Delete account
//        AccountDeletedPage accountDeletedPage = homePage.clickDeleteAccount();
//
//        // Step 18: Verify account deleted
//        softAssert.assertEquals(accountDeletedPage.getAccountDeletedText(), "ACCOUNT DELETED!", "Account deleted text mismatch!");
//        accountDeletedPage.clickContinue();

        softAssert.assertAll();
    }
}

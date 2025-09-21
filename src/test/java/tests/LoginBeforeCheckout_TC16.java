package tests;

import Pages.*;
import base.BaseTest;
import io.qameta.allure.Step;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 *
 * @author Nourhan Farag
 */
public class LoginBeforeCheckout_TC16 extends BaseTest {

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
        stepVerifyHomePage();
        stepLoginBeforeCheckout(user);
        stepAddProductToCart();
        stepGoToCart();
        stepProceedCheckout();
        stepFillCheckoutAndPlaceOrder();
        stepEnterPaymentAndConfirm();
    }

    @Step("Step 3: Verify Home Page")
    private void stepVerifyHomePage() {
        HomePage home = new HomePage(driver, helper);
        attachText("Expected", "Home page visible");
        if (!home.isHomePageVisible()) {
            attachText("Actual", "Home page NOT visible");
            throw new AssertionError("Home page not visible");
        }
    }

    @Step("Step 4-5: Login Before Checkout")
    private void stepLoginBeforeCheckout(LoadingData.LoginUser user) {
        HomePage home = new HomePage(driver, helper);
        LoginPage login = home.clickSignupLogin();
        login.enterLoginEmail(user.loginEmail);
        login.enterLoginPassword(user.loginPassword);
        login.clickLoginButton();

        if (!home.isLoggedInAs(user.loginEmail.split("f")[0])) {
            throw new AssertionError("Logged in username not displayed!");
        }
    }

    @Step("Step 6-8: Add Product to Cart")
    private void stepAddProductToCart() {
        HomePage home = new HomePage(driver, helper);
        ProductsPage products = home.clickProducts();
        ProductDetailPage detail = products.clickFirstViewProduct();
        detail.clickAddToCart();
    }

    @Step("Step 9-10: Go to Cart and Proceed to Checkout")
    private void stepGoToCart() {
        HomePage home = new HomePage(driver, helper);
        home.clickCart();
    }

    @Step("Step 11: Proceed to Checkout")
    private void stepProceedCheckout() {
        CartPage cart = new CartPage(driver, helper);
        cart.clickProceedToCheckout();
    }

    @Step("Step 12-13: Fill Checkout details and place order")
    private void stepFillCheckoutAndPlaceOrder() {
        CheckoutPage checkout = new CheckoutPage(driver, helper);
        checkout.enterComment("Please deliver between 9AM-5PM");
        checkout.clickPlaceOrder();
    }

    @Step("Step 14-15: Enter Payment Details and Confirm")
    private void stepEnterPaymentAndConfirm() {
        PaymentPage payment = new PaymentPage(driver, helper);
        payment.enterPaymentDetails("Nourhan Farag", "57186825581", "123", "12", "2026");
        payment.clickPayAndConfirm();
        OrderConfirmationPage orderConfirmation = new OrderConfirmationPage(driver, helper);
        attachText("Expected", "Congratulations! Your order has been confirmed!");
        if (!orderConfirmation.getOrderSuccessMessageText().equals(
                "Congratulations! Your order has been confirmed!")) {
            attachText("Actual", orderConfirmation.getOrderSuccessMessageText());
            throw new AssertionError("Order confirmation message mismatch!");
        }
    }
}

package tests;

import Pages.*;
import base.BaseTest;
import io.qameta.allure.Step;
import org.testng.annotations.Test;

/**
 *
 * @author Nourhan Farag
 */
public class RegisterBeforeCheckout_TC15 extends BaseTest {

    @Test
    public void placeOrder_RegisterBeforeCheckout() {
        stepVerifyHomePage();
        stepRegisterBeforeCheckout();
        stepAddProductToCart();
        stepGoToCart();
        stepProceedCheckout();
        stepFillCheckoutAndPlaceOrder();
        stepEnterPaymentAndConfirm();
        stepDeleteAccount();
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

    @Step("Step 4-5: Register Before Checkout")
    private void stepRegisterBeforeCheckout() {
        HomePage home = new HomePage(driver, helper);
        LoginPage login = home.clickSignupLogin();

        String name = "Nourhan";
        String email = "nourhan" + System.currentTimeMillis() + "@test.com";
        RegisterPage register = login.enterSignupDetails(name, email);
        attachText("Test Input", "Name: " + name + ", Email: " + email);

        register.selectTitle("Mrs");
        register.enterPass("Test@123");
        register.selectDOB("10", "May", "1999");
        register.checkNewsletter();
        register.checkSpecialOffers();
        register.enterPersonalDetails(
                "Nourhan", "Farag", "Shoubra", "Cairo", "Nasr City",
                "India", "Cairo", "Cairo", "11765", "01012345678"
        );

        AccountCreatedPage accountCreated = register.clickCreateAccount();
        attachText("Expected", "ACCOUNT CREATED!");
        if (!accountCreated.getAccountCreatedText().equals("ACCOUNT CREATED!")) {
            attachText("Actual", accountCreated.getAccountCreatedText());
            throw new AssertionError("Account creation failed!");
        }

        accountCreated.clickContinue();

        HomePage homeAfterRegister = new HomePage(driver, helper);
        if (!homeAfterRegister.isLoggedInAs(name)) {
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
        payment.enterPaymentDetails("Nourhan Farag", "238263506326852", "123", "12", "2026");
        payment.clickPayAndConfirm();
        OrderConfirmationPage orderConfirmation = new OrderConfirmationPage(driver, helper);
        attachText("Expected", "Congratulations! Your order has been confirmed!");
        if (!orderConfirmation.getOrderSuccessMessageText().equals(
                "Congratulations! Your order has been confirmed!")) {
            attachText("Actual", orderConfirmation.getOrderSuccessMessageText());
            throw new AssertionError("Order confirmation message mismatch!");
        }
    }

    @Step("Step 16-17: Delete Account")
    private void stepDeleteAccount() {
        HomePage home = new HomePage(driver, helper);
        AccountDeletedPage deleted = home.clickDeleteAccount();
        if (!deleted.getAccountDeletedText().equals("ACCOUNT DELETED!")) {
            throw new AssertionError("Account deletion failed!");
        }
        deleted.clickContinue();
    }
}

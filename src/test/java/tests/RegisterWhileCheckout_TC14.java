package tests;

import Pages.*;
import base.BaseTest;
import io.qameta.allure.Step;
import org.testng.annotations.Test;

/**
 *
 * @author Nourhan Farag
 */
public class RegisterWhileCheckout_TC14 extends BaseTest {

    @Test
    public void placeOrder_RegisterWhileCheckout() {
        stepVerifyHomePage();
        stepAddProductToCart();
        stepGoToCart();
        stepProceedToCheckout();
        stepRegisterDuringCheckout();
        stepFillAccountInfoAndCreateAccount();
        stepVerifyLoggedInAs();
        stepProceedCheckoutAndPlaceOrder();
        stepEnterPaymentDetailsAndConfirm();
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

    @Step("Step 4: Add first product to cart")
    private void stepAddProductToCart() {
        HomePage home = new HomePage(driver, helper);
        ProductsPage productsPage = home.clickProducts();
        productsPage.addFirstProductToCart();
        productsPage.clickContinueShopping();
    }

    @Step("Step 5: Navigate to Cart")
    private void stepGoToCart() {
        HomePage home = new HomePage(driver, helper);
        home.clickCart();
    }

    @Step("Step 6: Proceed to Checkout")
    private void stepProceedToCheckout() {
        CartPage cart = new CartPage(driver, helper);
        cart.clickProceedToCheckout();
    }

    @Step("Step 7-9: Register during Checkout")
    private void stepRegisterDuringCheckout() {
        CartPage cart = new CartPage(driver, helper);
        LoginPage login = cart.clickRegisterLogin();

        String email = "nourhan" + System.currentTimeMillis() + "@mail.com";
        RegisterPage register = login.enterSignupDetails("Nourhan", email);
        attachText("Test Input", "Email: " + email);
    }

    @Step("Step 10: Fill account info and create account")
    private void stepFillAccountInfoAndCreateAccount() {
        RegisterPage register = new RegisterPage(driver, helper);
        AccountCreatedPage accountCreated = register.registerNewUser(
                "Mrs", "123456", "10", "May", "1999",
                "Nourhan", "Farag", "TechCorp",
                "123 Test St", "Apt 4", "Canada",
                "Ontario", "Toronto", "M5A1A1", "1234567890"
        );

        attachText("Expected", "ACCOUNT CREATED!");
        if (!accountCreated.getAccountCreatedText().equals("ACCOUNT CREATED!")) {
            attachText("Actual", accountCreated.getAccountCreatedText());
            throw new AssertionError("Account creation failed");
        }

        accountCreated.clickContinue();
    }

    @Step("Step 11: Verify logged in as username")
    private void stepVerifyLoggedInAs() {
        HomePage home = new HomePage(driver, helper);
        if (!home.isLoggedInAs("Nourhan")) {
            throw new AssertionError("Logged in username not displayed!");
        }
        home.clickCart();
    }

    @Step("Step 12-15: Proceed Checkout and Place Order")
    private void stepProceedCheckoutAndPlaceOrder() {
        CartPage cart = new CartPage(driver, helper);
        cart.clickProceedToCheckout();
        cart.enterComment("Please deliver between 10 AM and 5 PM.");
        PaymentPage payment = cart.clickPlaceOrder();
    }

    @Step("Step 16-17: Enter Payment Details and Confirm Order")
    private void stepEnterPaymentDetailsAndConfirm() {
        PaymentPage payment = new PaymentPage(driver, helper);
        payment.enterPaymentDetails("Nourhan Farag", "87189581861", "123", "12", "2026");
        payment.clickPayAndConfirm();
        OrderConfirmationPage orderConfirmation = new OrderConfirmationPage(driver, helper);
        attachText("Expected", "Congratulations! Your order has been confirmed!");
        if (!orderConfirmation.getOrderSuccessMessageText().equals("Congratulations! Your order has been confirmed!")) {
            attachText("Actual", orderConfirmation.getOrderSuccessMessageText());
            throw new AssertionError("Order confirmation message mismatch!");
        }
    }

    @Step("Step 18-19: Delete Account")
    private void stepDeleteAccount() {
        HomePage home = new HomePage(driver, helper);
        AccountDeletedPage deleted = home.clickDeleteAccount();
        if (!deleted.getAccountDeletedText().equals("ACCOUNT DELETED!")) {
            throw new AssertionError("Account deletion failed!");
        }
        deleted.clickContinue();
    }
}

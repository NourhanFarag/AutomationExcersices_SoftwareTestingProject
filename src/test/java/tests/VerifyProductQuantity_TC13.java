package tests;

import Pages.*;
import base.BaseTest;
import io.qameta.allure.Step;
import org.testng.annotations.Test;

/**
 *
 * @author Nourhan Farag
 */
public class VerifyProductQuantity_TC13 extends BaseTest {

    @Test
    public void verifyProductQuantityInCart() {
        stepVerifyHomePage();
        stepOpenFirstProductDetail();
        stepSetQuantity("4");
        stepAddToCart();
        stepVerifyQuantityInCart("4");
    }

    @Step("Step 3: Verify Home Page")
    private void stepVerifyHomePage() {
        HomePage home = new HomePage(driver, helper);
        if (!home.isHomePageVisible()) {
            attachText("Actual Output", "Home page not visible");
            throw new AssertionError("Home page not visible!");
        }
    }

    @Step("Step 4-5: Open first product detail page")
    private void stepOpenFirstProductDetail() {
        HomePage home = new HomePage(driver, helper);
        home.clickFirstViewProduct();
    }

    @Step("Step 6: Set quantity to '{quantity}'")
    private void stepSetQuantity(String quantity) {
        ProductDetailPage detail = new ProductDetailPage(driver, helper);
        detail.setQuantity(quantity);
    }

    @Step("Step 7: Add product to cart")
    private void stepAddToCart() {
        ProductDetailPage detail = new ProductDetailPage(driver, helper);
        detail.clickAddToCart();
        detail.clickViewCart();
    }

    @Step("Step 9: Verify product quantity in cart is '{expectedQuantity}'")
    private void stepVerifyQuantityInCart(String expectedQuantity) {
        CartPage cart = new CartPage(driver, helper);
        String actual = cart.getFirstProductQuantity();
        attachText("Expected Quantity", expectedQuantity);
        attachText("Actual Quantity", actual);
        if (!actual.equals(expectedQuantity)) {
            throw new AssertionError("Product quantity in cart mismatch!");
        }
    }
}

package tests;

import Pages.*;
import base.BaseTest;
import io.qameta.allure.Step;
import org.testng.annotations.Test;

/**
 *
 * @author Nourhan Farag
 */
public class AddProducts_TC12 extends BaseTest {

    @Test
    public void addProductsInCart() {
        stepVerifyHomePage();
        stepGoToProductsPage();
        stepAddFirstProduct();
        stepAddSecondProduct();
        stepVerifyCartProducts();
    }

    @Step("Step 3: Verify Home Page")
    private void stepVerifyHomePage() {
        HomePage home = new HomePage(driver, helper);
        if (!home.isHomePageVisible()) {
            attachText("Actual Output", "Home page not visible");
            throw new AssertionError("Home page not visible!");
        }
    }

    @Step("Step 4: Navigate to Products Page")
    private void stepGoToProductsPage() {
        HomePage home = new HomePage(driver, helper);
        home.clickProducts();
    }

    @Step("Step 5-6: Add first product to cart")
    private void stepAddFirstProduct() {
        ProductsPage products = new ProductsPage(driver, helper);
        products.addFirstProductToCart();
        products.clickContinueShopping();
    }

    @Step("Step 7-8: Add second product and view cart")
    private void stepAddSecondProduct() {
        ProductsPage products = new ProductsPage(driver, helper);
        products.addSecondProductToCart();
        products.clickViewCart();
    }

    @Step("Step 9-10: Verify products in cart")
    private void stepVerifyCartProducts() {
        CartPage cart = new CartPage(driver, helper);

        attachText("First Product", cart.getFirstProductName() + " | " + cart.getFirstProductQuantity() + " | " + cart.getFirstProductPrice());
        attachText("Second Product", cart.getSecondProductName() + " | " + cart.getSecondProductQuantity() + " | " + cart.getSecondProductPrice());

        if (!cart.getFirstProductQuantity().equals("1") || !cart.getSecondProductQuantity().equals("1")) {
            throw new AssertionError("Product quantities mismatch in cart!");
        }
    }
}

package tests;

import Pages.*;
import base.BaseTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

/**
 *
 * @author Nourhan Farag
 */
public class RemoveProductsFromCart_TC17 extends BaseTest {

    @Test
    public void removeProductFromCart() {
        SoftAssert softAssert = new SoftAssert();

        HomePage homePage = new HomePage(driver, helper);

        // Step 3: Verify home page
        softAssert.assertTrue(homePage.isHomePageVisible(), "Home page is not visible!");

        // Step 4: Add products to cart
        ProductsPage productsPage = homePage.clickProducts();
        ProductDetailPage productDetailPage = productsPage.clickFirstViewProduct();
        productDetailPage.clickAddToCart();

        // Step 5: Click 'Cart' button
        CartPage cartPage = productDetailPage.clickViewCart();

        // Step 6: Verify cart page is displayed
        softAssert.assertTrue(cartPage.isCartPageVisible(), "Cart page is not visible!");

        // Step 7: Click 'X' button corresponding to product
        cartPage.removeFirstProduct();

        // Step 8: Verify product is removed
        softAssert.assertTrue(cartPage.isCartEmpty(), "Product was not removed from the cart!");

        softAssert.assertAll();
    }
}

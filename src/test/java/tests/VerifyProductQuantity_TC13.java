package tests;

import Pages.*;
import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
/**
 *
 * @author Nourhan Farag
 */
public class VerifyProductQuantity_TC13 extends BaseTest{
    
    @Test
    public void verifyProductQuantityInCart() {
        HomePage homePage = new HomePage(driver, helper);

        // Step 3: Verify Home Page
        Assert.assertTrue(homePage.isHomePageVisible(), "Home page is not visible!");

        // Step 4: Click 'View Product' for first product
        homePage.clickFirstViewProduct();

        ProductDetailPage detailPage = new ProductDetailPage(driver, helper);

        // Step 5: Verify product detail is opened
        Assert.assertTrue(detailPage.isProductInfoVisible(), "Product detail page is not visible!");

        // Step 6: Increase quantity to 4
        detailPage.setQuantity("4");

        // Step 7: Click 'Add to cart'
        detailPage.clickAddToCart();

        // Step 8: Click 'View Cart'
        detailPage.clickViewCart();

        CartPage cartPage = new CartPage(driver, helper);

        // Step 9: Verify product is displayed with exact quantity
        Assert.assertEquals(cartPage.getFirstProductQuantity(), "4", "Product quantity in cart is not correct!");
    }
}

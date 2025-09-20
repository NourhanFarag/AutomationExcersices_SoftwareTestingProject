package tests;

import Pages.*;
import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
/**
 *
 * @author Nourhan Farag
 */
public class AddProducts_TC12 extends BaseTest{
    
    @Test
    public void addProductsInCart() {
        HomePage homePage = new HomePage(driver, helper);

        // Step 3: Verify Home Page (Hard Assert)
        Assert.assertTrue(homePage.isHomePageVisible(), "Home page is not visible!");

        // Step 4: Click on 'Products'
        homePage.clickProducts();

        ProductsPage productsPage = new ProductsPage(driver, helper);
        Assert.assertTrue(productsPage.isProductsListVisible(), "Products list is not visible!");

        // Step 5: Hover over first product and click 'Add to cart'
        productsPage.addFirstProductToCart();

        // Step 6: Click 'Continue Shopping'
        productsPage.clickContinueShopping();

        // Step 7: Hover over second product and click 'Add to cart'
        productsPage.addSecondProductToCart();

        // Step 8: Click 'View Cart'
        productsPage.clickViewCart();

        CartPage cartPage = new CartPage(driver, helper);
        Assert.assertTrue(cartPage.isCartPageVisible(), "Cart table is not visible!");

        // Step 9 + 10: Verify product names, prices, quantities, totals
        SoftAssert softAssert = new SoftAssert();

        // Product names
        softAssert.assertFalse(cartPage.getFirstProductName().isEmpty(), "First product name is missing!");
        softAssert.assertFalse(cartPage.getSecondProductName().isEmpty(), "Second product name is missing!");

        // Prices
        softAssert.assertTrue(cartPage.getFirstProductPrice().startsWith("Rs."), "First product price is invalid!");
        softAssert.assertTrue(cartPage.getSecondProductPrice().startsWith("Rs."), "Second product price is invalid!");

        // Quantities
        softAssert.assertEquals(cartPage.getFirstProductQuantity(), "1", "First product quantity mismatch!");
        softAssert.assertEquals(cartPage.getSecondProductQuantity(), "1", "Second product quantity mismatch!");

        // Totals
        softAssert.assertTrue(cartPage.getFirstProductTotal().startsWith("Rs."), "First product total invalid!");
        softAssert.assertTrue(cartPage.getSecondProductTotal().startsWith("Rs."), "Second product total invalid!");

        System.out.println("Cart Products:");
        System.out.println("1 of " + cartPage.getFirstProductName() + " | " + cartPage.getFirstProductPrice() +
                " | Qty: " + cartPage.getFirstProductQuantity() + " | Total: " + cartPage.getFirstProductTotal());
        System.out.println("2 of " + cartPage.getSecondProductName() + " | " + cartPage.getSecondProductPrice() +
                " | Qty: " + cartPage.getSecondProductQuantity() + " | Total: " + cartPage.getSecondProductTotal());

        // Collect all soft asserts
        softAssert.assertAll();
    }
}

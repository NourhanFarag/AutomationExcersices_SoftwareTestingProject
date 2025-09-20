package tests;

import Pages.*;
import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
/**
 *
 * @author Nourhan Farag
 */
public class AddToCartFromRecommended_TC22 extends BaseTest{
    @Test
    public void addToCartFromRecommendedItems() {
        HomePage homePage = new HomePage(driver, helper);

        // Step 3: Scroll to bottom of the page
        homePage.scrollToBottom();

        // Step 4: Verify 'RECOMMENDED ITEMS' are visible
        Assert.assertTrue(homePage.isRecommendedSectionVisible(),
                "Recommended items section is not visible!");

        // Step 5: Add first recommended product to cart
        homePage.addFirstRecommendedProductToCart();

        // Step 6: Click 'View Cart' button
        CartPage cartPage = homePage.clickViewCartFromRecommended();

        // Step 7: Verify product is displayed in cart page
        Assert.assertTrue(cartPage.isAnyProductInCart(),
                "No product from recommended items is displayed in cart!");
    }
}

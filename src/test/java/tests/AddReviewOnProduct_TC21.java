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
public class AddReviewOnProduct_TC21 extends BaseTest {

    @Test
    public void addReviewOnProduct() {
        SoftAssert softAssert = new SoftAssert();
        HomePage homePage = new HomePage(driver, helper);

        // Step 3: Click 'Products'
        ProductsPage productsPage = homePage.clickProducts();

        // Step 4: Verify user is navigated to ALL PRODUCTS page
        softAssert.assertEquals(productsPage.getAllProductsHeaderText(),
                "ALL PRODUCTS", "Not on All Products page!");

        // Step 5: Click 'View Product'
        ProductDetailPage productDetailPage = productsPage.clickFirstViewProduct();

        // Step 6: Verify 'Write Your Review' is visible
        softAssert.assertTrue(productDetailPage.isReviewSectionVisible(),
                "'Write Your Review' section not visible!");

        // Step 7: Enter name, email, and review
        productDetailPage.enterReviewDetails("Nourhan", "nourhan@example.com",
                "Great quality and amazing fit!");

        // Step 8: Click 'Submit'
        productDetailPage.submitReview();

        // Step 9: Verify success message
        softAssert.assertEquals(productDetailPage.getReviewSuccessMessage(),
                "Thank you for your review.",
                "Review success message mismatch!");
        
        softAssert.assertAll();
    }
}

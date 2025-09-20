package tests;

import Pages.*;
import base.BaseTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

/**
 *
 * @author Nourhan Farag
 */
public class ViewCategoryProducts_TC18 extends BaseTest {

    @Test
    public void viewCategoryProducts() {
        SoftAssert softAssert = new SoftAssert();
        HomePage homePage = new HomePage(driver, helper);

        // Step 3: Verify categories sidebar is visible
        softAssert.assertTrue(homePage.isCategoriesSidebarVisible(), "Categories sidebar is not visible!");

        // Step 4 + 5: Select Women -> Dress
        ProductsPage productsPage = homePage.clickProducts();
        productsPage.clickCategory("Women");
        productsPage.clickSubCategory("Women", "Dress");

        // Step 6: Verify category page header text
        softAssert.assertEquals(
            productsPage.getCategoryPageHeaderText(),
            "WOMEN - DRESS PRODUCTS",
            "Women - Dress category page header mismatch!"
        );

        // Step 7: Select Men -> Tshirts
        productsPage.clickCategory("Men");
        productsPage.clickSubCategory("Men", "Tshirts");

        // Step 8: Verify category page header text
        softAssert.assertEquals(
            productsPage.getCategoryPageHeaderText(),
            "MEN - TSHIRTS PRODUCTS",
            "Men - Tshirts category page header mismatch!"
        );

        softAssert.assertAll();
    }
}

package tests;

import Pages.*;
import base.BaseTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

/**
 *
 * @author Nourhan Farag
 */
public class ViewBrandProducts_TC19 extends BaseTest {

    @Test
    public void viewAndCartBrandProducts() {
        SoftAssert softAssert = new SoftAssert();
        HomePage homePage = new HomePage(driver, helper);

        // Step 3: Click Products
        ProductsPage productsPage = homePage.clickProducts();

        // Step 4: Verify brands are visible
        softAssert.assertTrue(productsPage.areBrandProductsVisible(), "Brands sidebar is not visible!");

        // Step 5: Click on Polo brand
        productsPage.clickBrand("Polo");

        // Step 6: Verify Polo brand page header
        softAssert.assertEquals(
            productsPage.getBrandPageHeaderText(),
            "BRAND - POLO PRODUCTS",
            "Polo brand page header mismatch!"
        );
        softAssert.assertTrue(productsPage.areBrandProductsVisible(), "Polo products not displayed!");

        // Step 7: Click on H&M brand
        productsPage.clickBrand("H&M");

        // Step 8: Verify H&M brand page header
        softAssert.assertEquals(
            productsPage.getBrandPageHeaderText(),
            "BRAND - H&M PRODUCTS",
            "H&M brand page header mismatch!"
        );
        softAssert.assertTrue(productsPage.areBrandProductsVisible(), "H&M products not displayed!");

        softAssert.assertAll();
    }
}

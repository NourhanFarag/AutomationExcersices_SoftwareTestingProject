package tests;

import Pages.*;
import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
/**
 *
 * @author Nourhan Farag
 */
public class VerifyProducts_TC8 extends BaseTest{
    
    @Test
    public void testVerifyAllProductsAndProductDetail() {
        HomePage homePage = new HomePage(driver, helper);

        // Step 3: Verify home page is visible
        Assert.assertTrue(homePage.isHomePageVisible(), "Home page is not visible!");

        // Step 4: Click on 'Products' button
        ProductsPage productsPage = homePage.clickProducts();

        // Step 5: Verify user is navigated to ALL PRODUCTS page
        Assert.assertEquals(productsPage.getAllProductsHeaderText(), "ALL PRODUCTS", "Not on All Products page!");

        // Step 6: Verify products list is visible
        Assert.assertTrue(productsPage.isProductsListVisible(), "Products list is not visible!");

        // Step 7: Click on 'View Product' of first product
        ProductDetailPage detailPage = productsPage.clickFirstViewProduct();

        // Step 8 & 9: Verify product details with assertEquals
        Assert.assertEquals(detailPage.getProductName(), "Blue Top", "Product name mismatch!");
        Assert.assertEquals(detailPage.getCategory(), "Category: Women > Tops", "Category mismatch!");
        Assert.assertEquals(detailPage.getPrice(), "Rs. 500", "Price mismatch!");
        Assert.assertEquals(detailPage.getAvailability(), "Availability: In Stock", "Availability mismatch!");
        Assert.assertEquals(detailPage.getCondition(), "Condition: New", "Condition mismatch!");
        Assert.assertEquals(detailPage.getBrand(), "Brand: Polo", "Brand mismatch!");
    }
}

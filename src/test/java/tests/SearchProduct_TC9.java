package tests;

import Pages.HomePage;
import Pages.ProductsPage;
import base.BaseTest;
import io.qameta.allure.Attachment;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 *
 * @author Nourhan
 */
public class SearchProduct_TC9 extends BaseTest {

    @Test
    public void searchProductTest() {
        HomePage homePage = new HomePage(driver, helper);
        ProductsPage productsPage = new ProductsPage(driver, helper);

        // Step 3: Verify Home Page
        Assert.assertTrue(homePage.isHomePageVisible(), "Home page is not visible!");

        // Step 4: Click on 'Products' button
        homePage.clickProducts();

        // Step 5: Verify user is navigated to ALL PRODUCTS page
        Assert.assertEquals(productsPage.getAllProductsHeaderText().toUpperCase(), "ALL PRODUCTS",
                "User was not navigated to ALL PRODUCTS page!");

        // Step 6: Enter product name in search and click search button
        String searchKeyword = "Tshirt";
        productsPage.searchForProduct(searchKeyword);

        // Step 7: Verify 'SEARCHED PRODUCTS' is visible
        Assert.assertEquals(productsPage.getSearchedProductsHeaderText().toUpperCase(), "SEARCHED PRODUCTS",
                "'SEARCHED PRODUCTS' header is not visible!");

        // Step 8: Verify products related to search are visible
        Assert.assertTrue(productsPage.areSearchedProductsVisible(), "Searched products are not visible!");

        // Step 9: Get all product names
        List<String> productNames = productsPage.getAllSearchedProductNames();
        System.out.println("===== Search Results for: " + searchKeyword + " =====");
        for (String name : productNames) {
            System.out.println(name);
        }
        System.out.println("======================================");
        // Attach to Allure report
        saveSearchResultsToAllure(searchKeyword, productNames);
        Assert.assertFalse(productNames.isEmpty(), "No products found in search results!");
    }

    @Attachment(value = "Search Results", type = "text/plain")
    public String saveSearchResultsToAllure(String keyword, List<String> productNames) {
        StringBuilder sb = new StringBuilder();
        sb.append("Search Keyword: ").append(keyword).append("\n\n");
        sb.append("Search Results:\n");
        for (String name : productNames) {
            sb.append("- ").append(name).append("\n");
        }
        return sb.toString();
    }
}

package tests;

import Pages.*;
import base.BaseTest;
import io.qameta.allure.Step;
import io.qameta.allure.Attachment;
import org.testng.annotations.Test;
import java.util.List;

/**
 * 
 * @author Nourhan Farag
 */
public class SearchProduct_TC9 extends BaseTest {

    @Test
    public void searchProductTest() {
        stepVerifyHomePage();
        stepGoToProductsPage();
        stepSearchProduct("Tshirt");
    }

    @Step("Step 3: Verify Home Page is visible")
    private void stepVerifyHomePage() {
        HomePage home = new HomePage(driver, helper);
        attachText("Expected Output", "Home page visible");
        if (!home.isHomePageVisible()) {
            attachText("Actual Output", "Home page NOT visible");
            throw new AssertionError("Home page is not visible!");
        }
    }

    @Step("Step 4: Navigate to Products page")
    private void stepGoToProductsPage() {
        HomePage home = new HomePage(driver, helper);
        home.clickProducts();
        attachText("Expected Output", "Navigated to Products page");
    }

    @Step("Step 6-9: Search for product '{keyword}' and verify results")
    private void stepSearchProduct(String keyword) {
        ProductsPage products = new ProductsPage(driver, helper);
        products.searchForProduct(keyword);

        if (!products.getSearchedProductsHeaderText().equalsIgnoreCase("SEARCHED PRODUCTS")) {
            attachText("Actual Output", products.getSearchedProductsHeaderText());
            throw new AssertionError("'SEARCHED PRODUCTS' header is not visible!");
        }

        List<String> productNames = products.getAllSearchedProductNames();
        attachSearchResultsToAllure(keyword, productNames);

        if (productNames.isEmpty()) {
            throw new AssertionError("No products found in search results!");
        }
    }

    @Attachment(value = "Search Results", type = "text/plain")
    private String attachSearchResultsToAllure(String keyword, List<String> productNames) {
        StringBuilder sb = new StringBuilder();
        sb.append("Search Keyword: ").append(keyword).append("\n\n");
        sb.append("Search Results:\n");
        for (String name : productNames) {
            sb.append("- ").append(name).append("\n");
        }
        return sb.toString();
    }
}

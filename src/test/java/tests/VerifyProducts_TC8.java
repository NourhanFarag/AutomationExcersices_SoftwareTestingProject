package tests;

import Pages.*;
import base.BaseTest;
import io.qameta.allure.Step;
import org.testng.annotations.Test;

/**
 * 
 * @author Nourhan Farag
 */
public class VerifyProducts_TC8 extends BaseTest {

    @Test
    public void testVerifyAllProductsAndProductDetail() {
        stepVerifyHomePage();
        stepGoToProductsPage();
        stepVerifyAllProductsPage();
        stepVerifyFirstProductDetail();
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

    @Step("Step 5-6: Verify ALL PRODUCTS page and products list")
    private void stepVerifyAllProductsPage() {
        ProductsPage products = new ProductsPage(driver, helper);
        attachText("Expected Output", "ALL PRODUCTS header visible and products list visible");
        if (!products.getAllProductsHeaderText().equals("ALL PRODUCTS")) {
            attachText("Actual Output", products.getAllProductsHeaderText());
            throw new AssertionError("Not on All Products page!");
        }
        if (!products.isProductsListVisible()) {
            throw new AssertionError("Products list is not visible!");
        }
    }

    @Step("Step 7-9: Verify first product details")
    private void stepVerifyFirstProductDetail() {
        ProductsPage products = new ProductsPage(driver, helper);
        ProductDetailPage detail = products.clickFirstViewProduct();

        if (!detail.getProductName().equals("Blue Top")) attachText("Product Name", detail.getProductName());
        if (!detail.getCategory().equals("Category: Women > Tops")) attachText("Category", detail.getCategory());
        if (!detail.getPrice().equals("Rs. 500")) attachText("Price", detail.getPrice());
        if (!detail.getAvailability().equals("Availability: In Stock")) attachText("Availability", detail.getAvailability());
        if (!detail.getCondition().equals("Condition: New")) attachText("Condition", detail.getCondition());
        if (!detail.getBrand().equals("Brand: Polo")) attachText("Brand", detail.getBrand());

        if (!detail.getProductName().equals("Blue Top") ||
            !detail.getCategory().equals("Category: Women > Tops") ||
            !detail.getPrice().equals("Rs. 500") ||
            !detail.getAvailability().equals("Availability: In Stock") ||
            !detail.getCondition().equals("Condition: New") ||
            !detail.getBrand().equals("Brand: Polo")) {
            throw new AssertionError("Product details mismatch!");
        }
    }
}

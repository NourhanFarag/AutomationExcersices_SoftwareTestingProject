package tests;

import Pages.*;
import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import org.testng.annotations.DataProvider;
import org.testng.asserts.SoftAssert;

/**
 *
 * @author Nourhan Farag
 */
public class SearchProductsVerifyCartAfterLogin_TC20 extends BaseTest {

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() throws Exception {
        LoadingData.LoginUser[] users = LoadingData.readLoginUsers("validLoginUser.json");
        Object[][] data = new Object[users.length][1];
        for (int i = 0; i < users.length; i++) {
            data[i][0] = users[i];
        }
        return data;
    }
    
    @Test(dataProvider = "loginData")    
    public void searchProductsAndVerifyCartAfterLogin(LoadingData.LoginUser user) {
        SoftAssert softAssert = new SoftAssert();
        HomePage homePage = new HomePage(driver, helper);

        // Step 3: Click 'Products'
        ProductsPage productsPage = homePage.clickProducts();

        // Step 4: Verify user is navigated to ALL PRODUCTS page
        softAssert.assertEquals(productsPage.getAllProductsHeaderText(), 
                "ALL PRODUCTS", "Not on All Products page!");

        // Step 5: Enter product name in search input and click search
        String searchTerm = "Dress";
        productsPage.searchForProduct(searchTerm);

        // Step 6: Verify 'SEARCHED PRODUCTS' is visible
        softAssert.assertEquals(productsPage.getSearchedProductsHeaderText(),
                "SEARCHED PRODUCTS", "Searched Products header mismatch!");

        // Step 7: Verify all related products are visible
        List<String> searchedProducts = productsPage.getAllSearchedProductNames();
        Assert.assertFalse(searchedProducts.isEmpty(),
                "No products found for search term!");

        // Step 8: Add those products to cart
        productsPage.addAllSearchResultsToCart();

        // Step 9: Go to Cart & verify products are visible
        productsPage.clickViewCart();
        CartPage cartPage = new CartPage(driver, helper);

        List<String> cartProductsBeforeLogin = cartPage.getAllCartProductNames();
        Assert.assertTrue(cartProductsBeforeLogin.containsAll(searchedProducts),
                "Not all searched products are in cart before login!");

        // Step 10: Login
        homePage.clickSignupLogin();
        LoginPage loginPage = new LoginPage(driver, helper);
        loginPage.enterLoginEmail(user.loginEmail);
        loginPage.enterLoginPassword(user.loginPassword);
        
        // Step 11: Again, go to Cart
        homePage.clickCart();
        cartPage = new CartPage(driver, helper);

        // Step 12: Verify products still in cart after login
        List<String> cartProductsAfterLogin = cartPage.getAllCartProductNames();
        Assert.assertTrue(cartProductsAfterLogin.containsAll(searchedProducts),
                "Products not persisted in cart after login!");
        
        softAssert.assertAll();
    }
}

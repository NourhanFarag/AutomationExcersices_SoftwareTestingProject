package tests;

import Pages.*;
import base.BaseTest;
import io.qameta.allure.Step;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

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
        stepSearchAndAddProductsToCart();
        stepVerifyCartBeforeLogin();
        stepLogin(user);
        stepVerifyCartAfterLogin();
    }

    private List<String> searchedProducts;

    @Step("Step 3-8: Search Products and add to cart")
    private void stepSearchAndAddProductsToCart() {
        HomePage home = new HomePage(driver, helper);
        ProductsPage products = home.clickProducts();
        products.searchForProduct("Dress");
        searchedProducts = products.getAllSearchedProductNames();
        if (searchedProducts.isEmpty()) {
            throw new AssertionError("No products found for search term!");
        }
        products.addAllSearchResultsToCart();
    }

    @Step("Step 9: Verify products in cart before login")
    private void stepVerifyCartBeforeLogin() {
        HomePage home = new HomePage(driver, helper);
        home.clickCart();
        CartPage cart = new CartPage(driver, helper);
        if (!cart.getAllCartProductNames().containsAll(searchedProducts)) {
            throw new AssertionError("Not all searched products are in cart before login!");
        }
    }

    @Step("Step 10-11: Login")
    private void stepLogin(LoadingData.LoginUser user) {
        HomePage home = new HomePage(driver, helper);
        home.clickSignupLogin();
        LoginPage login = new LoginPage(driver, helper);
        login.enterLoginEmail(user.loginEmail);
        login.enterLoginPassword(user.loginPassword);
        login.clickLoginButton();
    }

    @Step("Step 12: Verify products persist in cart after login")
    private void stepVerifyCartAfterLogin() {
        HomePage home = new HomePage(driver, helper);
        home.clickCart();
        CartPage cart = new CartPage(driver, helper);
        if (!cart.getAllCartProductNames().containsAll(searchedProducts)) {
            throw new AssertionError("Products not persisted in cart after login!");
        }
    }
}

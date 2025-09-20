package Pages;

import SeleniumFramework.SeleniumHelper;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
/**
 *
 * @author Nourhan Farag
 */
public class ProductsPage {
    private final WebDriver driver;
    private final SeleniumHelper helper;
    
    private final By allProductsHeader = By.xpath("//h2[contains(text(),'All Products')]");
    private final By productsList = By.cssSelector(".features_items .product-image-wrapper");
    private final By firstProductViewBtn = By.xpath("(//a[contains(text(),'View Product')])[1]");
    private final By searchInput = By.id("search_product");
    private final By searchButton = By.id("submit_search");
    private final By searchedProductsHeader = By.xpath("//h2[contains(text(),'Searched Products')]");
    private final By searchedProductsList = By.cssSelector(".features_items .product-image-wrapper");
    private final By firstProductAddToCart = By.xpath("(//a[contains(text(),'Add to cart')])[1]");
    private final By secondProductAddToCart = By.xpath("(//a[contains(text(),'Add to cart')])[3]");
    private final By continueShoppingBtn = By.cssSelector(".btn.btn-success.close-modal.btn-block");
    private final By viewCartBtn = By.xpath("//u[contains(text(),'View Cart')]");

    public ProductsPage (WebDriver driver, SeleniumHelper helper) {
        this.driver = driver;
        this.helper = helper;
    }
    
    public String getAllProductsHeaderText() {
        return helper.getText(allProductsHeader);
    }

    public boolean isProductsListVisible() {
        return helper.isElementDisplayed(productsList);
    }

    public ProductDetailPage clickFirstViewProduct() {
        helper.safeClick(firstProductViewBtn);
        return new ProductDetailPage(driver, helper);
    }
    
    public void searchForProduct(String productName) {
        helper.sendKeys(searchInput, productName);
        helper.safeClick(searchButton);
    }

    public String getSearchedProductsHeaderText() {
        return helper.getText(searchedProductsHeader);
    }

    public boolean areSearchedProductsVisible() {
        return helper.isElementDisplayed(searchedProductsList);
    }
    
    public java.util.List<String> getAllSearchedProductNames() {
        java.util.List<String> productNames = new java.util.ArrayList<>();
        java.util.List<org.openqa.selenium.WebElement> products =
                driver.findElements(By.cssSelector(".features_items .productinfo p"));
        for (org.openqa.selenium.WebElement product : products) {
            productNames.add(product.getText());
        }
        return productNames;
    }
    
    public void addFirstProductToCart() {
        helper.hoverOver(firstProductAddToCart);
        helper.click(firstProductAddToCart);
    }

    public void addSecondProductToCart() {
        helper.hoverOver(secondProductAddToCart);
        helper.click(secondProductAddToCart);
    }

    public void clickContinueShopping() {
        helper.click(continueShoppingBtn);
    }

    public void clickViewCart() {
        helper.click(viewCartBtn);
    }
}

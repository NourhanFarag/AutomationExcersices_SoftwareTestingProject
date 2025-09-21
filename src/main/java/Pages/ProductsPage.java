package Pages;

import SeleniumFramework.SeleniumHelper;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nourhan Farag
 */
public class ProductsPage {
    private final WebDriver driver;
    private final SeleniumHelper helper;
    
    // ===============================
    // Locators
    // ===============================    
    private final By allProductsHeader = By.xpath("//h2[contains(text(),'All Products')]");
    private final By productsList = By.cssSelector(".features_items .product-image-wrapper");
    private final By firstProductViewBtn = By.xpath("(//a[contains(text(),'View Product')])[1]");
    private final By searchInput = By.id("search_product");
    private final By searchButton = By.id("submit_search");
    private final By searchedProductsHeader = By.xpath("//h2[contains(text(),'Searched Products')]");
    private final By firstProductAddToCart = By.xpath("(//a[contains(text(),'Add to cart')])[1]");
    private final By secondProductAddToCart = By.xpath("(//a[contains(text(),'Add to cart')])[3]");
    private final By continueShoppingBtn = By.cssSelector(".btn.btn-success.close-modal.btn-block");
    private final By viewCartBtn = By.xpath("//a[contains(text(),'Cart')]");
    private final By cartLinkLocator = By.xpath("//a[contains(text(),'Cart')]");

    // Store product names added to cart
    public List<String> addedProductNames = new ArrayList<>();

    public ProductsPage (WebDriver driver, SeleniumHelper helper) {
        this.driver = driver;
        this.helper = helper;
    }
    
    // ===============================
    // Page Verifications
    // ===============================
    public String getAllProductsHeaderText() {
        return helper.getText(allProductsHeader);
    }

    public boolean isProductsListVisible() {
        return helper.isElementDisplayed(productsList);
    }

    public boolean areSearchedProductsVisible() {
        try {
            List<WebElement> products = driver.findElements(By.cssSelector(".features_items .product-image-wrapper"));
            return !products.isEmpty() && products.get(0).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    
    // ===============================
    // Product Actions
    // ===============================
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

    public List<String> getAllSearchedProductNames() {
        List<String> productNames = new ArrayList<>();
        List<WebElement> products = driver.findElements(By.cssSelector(".features_items .productinfo p"));
        for (WebElement product : products) {
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

    public void clickCart() {
        helper.click(cartLinkLocator);
    }

    /**
     * Add all search results dynamically to cart
     * @return 
     */
    public List<String> addAllSearchResultsToCart() {
        List<WebElement> searchResults = driver.findElements(By.cssSelector(".features_items .product-image-wrapper"));
        int totalProducts = searchResults.size();

        for (int i = 1; i <= totalProducts; i++) {
            By currentProductName   = By.xpath("(//div[@class='productinfo text-center']/p)[" + i + "]");
            By currentProductAddBtn = By.xpath("(//div[@class='productinfo text-center'])[" + i + "]//a[contains(@class,'add-to-cart')]");
            try {
                helper.scrollToElement(currentProductName);
                String productName = helper.getText(currentProductName);
                addedProductNames.add(productName);

                helper.scrollToElement(currentProductAddBtn);
                helper.safeClick(currentProductAddBtn);

                try { helper.safeClick(continueShoppingBtn); } catch (Exception ignore) {}
            } catch (Exception e) {
                System.out.println("Skipping product " + i + " due to: " + e.getMessage());
            }
        }
        return addedProductNames;
    }

    // ===============================
    // Category & Brand Actions
    // ===============================
    public void clickCategory(String categoryName) {
        By categoryLocator = By.xpath("//div[@class='left-sidebar']//a[@href='#" + categoryName + "']");
        helper.safeClick(categoryLocator);
    }

    public void clickSubCategory(String parentCategory, String subCategoryName) {
        By subCategoryLocator = By.xpath("//div[@id='" + parentCategory + "']//a[contains(text(),'" + subCategoryName + "')]");
        helper.safeClick(subCategoryLocator);
    }

    public String getCategoryPageHeaderText() {
        By categoryHeader = By.cssSelector(".features_items h2.title.text-center");
        return helper.getText(categoryHeader).trim();
    }

    public List<String> getCategoryProducts() {
        List<String> productNames = new ArrayList<>();
        List<WebElement> products = driver.findElements(By.cssSelector(".features_items .productinfo p"));
        for (WebElement product : products) {
            productNames.add(product.getText());
        }
        return productNames;
    }

    public void clickBrand(String brandName) {
        By brandLocator = By.xpath("//div[@class='brands_products']//a[contains(@href, '/brand_products/" + brandName + "')]");
        helper.safeClick(brandLocator);
    }

    public String getBrandPageHeaderText() {
        By brandHeader = By.cssSelector(".features_items h2.title.text-center");
        return helper.getText(brandHeader).trim();
    }

    public boolean areBrandProductsVisible() {
        return helper.isElementDisplayed(productsList);
    }

    public List<String> getBrandProducts() {
        List<String> productNames = new ArrayList<>();
        List<WebElement> products = driver.findElements(By.cssSelector(".features_items .productinfo p"));
        for (WebElement product : products) {
            productNames.add(product.getText());
        }
        return productNames;
    }
}

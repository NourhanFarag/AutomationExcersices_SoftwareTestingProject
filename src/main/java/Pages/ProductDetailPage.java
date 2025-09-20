package Pages;

import SeleniumFramework.SeleniumHelper;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
/**
 *
 * @author Nourhan Farag
 */
public class ProductDetailPage {
    private final WebDriver driver;
    private final SeleniumHelper helper;

    private final By productName = By.xpath("//div[@class='product-information']/h2");
    private final By productCategory = By.xpath("//div[@class='product-information']/p[contains(text(),'Category')]");
    private final By productPrice = By.xpath("//div[@class='product-information']/span/span");
    private final By productAvailability = By.xpath("//div[@class='product-information']/p[b[contains(text(),'Availability')]]");
    private final By productCondition = By.xpath("//div[@class='product-information']/p[b[contains(text(),'Condition')]]");
    private final By productBrand = By.xpath("//div[@class='product-information']/p[b[contains(text(),'Brand')]]");
    private final By productInfo = By.cssSelector(".product-information");
    private final By quantityInput = By.id("quantity");
    private final By addToCartBtn = By.cssSelector("button.btn.btn-default.cart");
    private final By viewCartBtn = By.xpath("//u[contains(text(),'View Cart')]");

    public ProductDetailPage(WebDriver driver, SeleniumHelper helper) {
        this.driver = driver;
        this.helper = helper;
    }
    
    public String getProductName() {
        return helper.getText(productName);
    }
    
    public String getCategory() {
        return helper.getText(productCategory);
    }
    
    public String getPrice() {
        return helper.getText(productPrice);
    }
    
    public String getAvailability() {
        return helper.getText(productAvailability); 
    }
    
    public String getCondition() {
        return helper.getText(productCondition); 
    }
    
    public String getBrand() {
        return helper.getText(productBrand); 
    }

    public boolean isProductInfoVisible() {
        return helper.isElementDisplayed(productInfo);
    }

    public void setQuantity(String quantity) {
        helper.sendKeys(quantityInput, quantity);
    }

    public void clickAddToCart() {
        helper.click(addToCartBtn);
    }

    public CartPage clickViewCart() {
        helper.click(viewCartBtn);
        return new CartPage(driver, helper);
    }
}

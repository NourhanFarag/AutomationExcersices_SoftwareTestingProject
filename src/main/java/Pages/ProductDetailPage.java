package Pages;

import SeleniumFramework.SeleniumHelper;
import org.openqa.selenium.*;

public class ProductDetailPage {
    private final WebDriver driver;
    private final SeleniumHelper helper;

    // ===============================
    // Locators
    // ===============================
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

    // -------- Review Section Locators --------
    private final By reviewSection = By.cssSelector("ul.nav.nav-tabs li.active a[href='#reviews']");
    private final By reviewNameInput = By.id("name");
    private final By reviewEmailInput = By.id("email");
    private final By reviewTextArea = By.id("review");
    private final By reviewSubmitBtn = By.id("button-review");
    private final By reviewSuccessMsg = By.xpath("//span[contains(text(),'Thank you for your review.')]");

    public ProductDetailPage(WebDriver driver, SeleniumHelper helper) {
        this.driver = driver;
        this.helper = helper;
    }

    // ===============================
    // Product Info Methods
    // ===============================
    public String getProductName() { return helper.getText(productName); }
    public String getCategory() { return helper.getText(productCategory); }
    public String getPrice() { return helper.getText(productPrice); }
    public String getAvailability() { return helper.getText(productAvailability); }
    public String getCondition() { return helper.getText(productCondition); }
    public String getBrand() { return helper.getText(productBrand); }
    public boolean isProductInfoVisible() { return helper.isElementDisplayed(productInfo); }
    public void setQuantity(String quantity) { helper.sendKeys(quantityInput, quantity); }
    public void clickAddToCart() { helper.click(addToCartBtn); }
    public CartPage clickViewCart() { helper.click(viewCartBtn); return new CartPage(driver, helper); }

    // ===============================
    // Review Methods
    // ===============================
    public boolean isReviewSectionVisible() { return helper.isElementDisplayed(reviewSection); }
    public void enterReviewDetails(String name, String email, String reviewText) {
        helper.sendKeys(reviewNameInput, name);
        helper.sendKeys(reviewEmailInput, email);
        helper.sendKeys(reviewTextArea, reviewText);
    }
    public void submitReview() { helper.click(reviewSubmitBtn); }
    public String getReviewSuccessMessage() { return helper.getText(reviewSuccessMsg); }
}
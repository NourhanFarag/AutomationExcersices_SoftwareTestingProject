package Pages;


import SeleniumFramework.SeleniumHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
/**
 *
 * @author Nourhan Farag
 */
public class CartPage {
    private final WebDriver driver;
    private final SeleniumHelper helper;
    
    private final By subscriptionSection = By.cssSelector("div.footer-widget h2"); // 'SUBSCRIPTION'
    private final By emailInput = By.id("susbscribe_email");
    private final By subscribeButton = By.id("subscribe");
    private final By successMessage = By.cssSelector("div.alert-success.alert");
    private final By cartTable = By.cssSelector("#cart_info_table");
    private final By firstProductName = By.xpath("//tr[1]//td[@class='cart_description']/h4/a");
    private final By secondProductName = By.xpath("//tr[2]//td[@class='cart_description']/h4/a");
    private final By firstProductPrice = By.xpath("//tr[1]//td[@class='cart_price']/p");
    private final By secondProductPrice = By.xpath("//tr[2]//td[@class='cart_price']/p");
    private final By firstProductQuantity = By.xpath("//tr[1]//td[@class='cart_quantity']/button");
    private final By secondProductQuantity = By.xpath("//tr[2]//td[@class='cart_quantity']/button");
    private final By firstProductTotal = By.xpath("//tr[1]//td[@class='cart_total']/p");
    private final By secondProductTotal = By.xpath("//tr[2]//td[@class='cart_total']/p");
    private final By proceedToCheckoutBtn = By.xpath("//a[contains(text(),'Proceed To Checkout')]");
    private final By registerLoginBtn = By.xpath("//u[contains(text(),'Register / Login')]");
    private final By addressDetails = By.xpath("//h2[contains(text(),'Address Details')]");
    private final By reviewOrder = By.xpath("//h2[contains(text(),'Review Your Order')]");
    private final By commentTextarea = By.name("message");
    private final By placeOrderBtn = By.xpath("//a[contains(text(),'Place Order')]");

    public CartPage(WebDriver driver, SeleniumHelper helper) {
        this.driver = driver;
        this.helper = helper;
    }
    
    public void scrollToFooter() {
        helper.scrollToElement(subscriptionSection);
    }
    
    public void subscribeWithEmail(String email) {
        helper.sendKeys(emailInput, email);
        helper.click(subscribeButton);
    }
    
    public String getSubscriptionSuccessMessage() {
        return helper.getText(successMessage);
    }
    
    public boolean isCartPageVisible() {
        return helper.isElementDisplayed(cartTable);
    }

    public String getFirstProductName() {
        return helper.getText(firstProductName);
    }

    public String getSecondProductName() {
        return helper.getText(secondProductName);
    }

    public String getFirstProductPrice() {
        return helper.getText(firstProductPrice);
    }

    public String getSecondProductPrice() {
        return helper.getText(secondProductPrice);
    }

    public String getFirstProductQuantity() {
        return helper.getText(firstProductQuantity);
    }

    public String getSecondProductQuantity() {
        return helper.getText(secondProductQuantity);
    }

    public String getFirstProductTotal() {
        return helper.getText(firstProductTotal);
    }

    public String getSecondProductTotal() {
        return helper.getText(secondProductTotal);
    }
    
    public CheckoutPage clickProceedToCheckout() {
        helper.click(proceedToCheckoutBtn);
        return new CheckoutPage(driver, helper);
    }

    public LoginPage clickRegisterLogin() {
        helper.click(registerLoginBtn);
        return new LoginPage(driver, helper);
    }

    public boolean isAddressDetailsVisible() {
        return helper.isElementDisplayed(addressDetails);
    }

    public boolean isReviewOrderVisible() {
        return helper.isElementDisplayed(reviewOrder);
    }

    public void enterComment(String comment) {
        helper.sendKeys(commentTextarea, comment);
    }

    public PaymentPage clickPlaceOrder() {
        helper.click(placeOrderBtn);
        return new PaymentPage(driver, helper);
    }
}

package Pages;

import SeleniumFramework.SeleniumHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 *
 * @author Nourhan Farag
 */
public class CheckoutPage {

    private final WebDriver driver;
    private final SeleniumHelper helper;

    // ===============================
    // Locators
    // ===============================
    private final By addressDetails = By.xpath("//h2[contains(text(),'Address Details')]");
    private final By reviewOrder = By.xpath("//h2[contains(text(),'Review Your Order')]");
    private final By commentTextArea = By.name("message");
    private final By placeOrderBtn = By.xpath("//a[contains(text(),'Place Order')]");
    private final By deliveryAddressBox = By.id("address_delivery");
    private final By billingAddressBox = By.id("address_invoice");

    public CheckoutPage(WebDriver driver, SeleniumHelper helper) {
        this.driver = driver;
        this.helper = helper;
    }

    // ===============================
    // Verifications
    // ===============================
    public boolean isAddressDetailsVisible() {
        return helper.isElementDisplayed(addressDetails);
    }

    public boolean isReviewOrderVisible() {
        return helper.isElementDisplayed(reviewOrder);
    }

    // ===============================
    // Actions
    // ===============================
    public void enterComment(String comment) {
        helper.sendKeys(commentTextArea, comment);
    }

    public PaymentPage clickPlaceOrder() {
        helper.click(placeOrderBtn);
        return new PaymentPage(driver, helper);
    }
    
    // ===============================
    // Address Parsing
    // ===============================
    public Address getDeliveryAddress() {
        return parseAddressBlock(deliveryAddressBox);
    }

    public Address getBillingAddress() {
        return parseAddressBlock(billingAddressBox);
    }

    private Address parseAddressBlock(By addressLocator) {
        String block = helper.getText(addressLocator);
        String[] lines = block.split("\\n");

        String name    = lines[1].trim();
        String company = lines[2].trim();
        String addr1   = lines[3].trim();
        String addr2   = lines[4].trim();
        String city    = lines[5].trim().split(" ")[0];
        String state   = lines[5].trim().split(" ")[1];
        String zipcode = lines[5].trim().split(" ")[2];
        String country = lines[6].trim();
        String mobile  = lines[7].trim();

        return new Address(name, company, addr1, addr2, city, state, country, zipcode, mobile);
    }
}
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

    private final By addressDetails = By.xpath("//h2[contains(text(),'Address Details')]");
    private final By reviewOrder = By.xpath("//h2[contains(text(),'Review Your Order')]");
    private final By commentTextArea = By.name("message");
    private final By placeOrderBtn = By.xpath("//a[contains(text(),'Place Order')]");

    public CheckoutPage(WebDriver driver, SeleniumHelper helper) {
        this.driver = driver;
        this.helper = helper;
    }

    public boolean isAddressDetailsVisible() {
        return helper.isElementDisplayed(addressDetails);
    }

    public boolean isReviewOrderVisible() {
        return helper.isElementDisplayed(reviewOrder);
    }

    public void enterComment(String comment) {
        helper.sendKeys(commentTextArea, comment);
    }

    public PaymentPage clickPlaceOrder() {
        helper.click(placeOrderBtn);
        return new PaymentPage(driver, helper);
    }
}
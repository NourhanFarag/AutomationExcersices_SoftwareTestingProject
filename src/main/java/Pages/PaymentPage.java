package Pages;

import SeleniumFramework.SeleniumHelper;
import org.openqa.selenium.*;
import java.io.File;

/**
 * 
 * @author Nourhan Farag
 */
public class PaymentPage {
    private final WebDriver driver;
    private final SeleniumHelper helper;

    // ===============================
    // Locators
    // ===============================
    private final By nameOnCard = By.name("name_on_card");
    private final By cardNumber = By.name("card_number");
    private final By cvc = By.name("cvc");
    private final By expiryMonth = By.name("expiry_month");
    private final By expiryYear = By.name("expiry_year");
    private final By payAndConfirmBtn = By.id("submit"); // adjust locator if different
    private final By orderSuccessMsg = By.xpath("//p[contains(text(),'Congratulations! Your order has been confirmed!')]");
    private final By downloadInvoiceBtn = By.xpath("//a[contains(text(),'Download Invoice')]");
    private final By continueBtn = By.xpath("//a[contains(text(),'Continue')]");

    // local Downloads folder
    private final String downloadPath = System.getProperty("user.home") + "/Downloads/";

    public PaymentPage(WebDriver driver, SeleniumHelper helper) {
        this.driver = driver;
        this.helper = helper;
    }

    // ===============================
    // Payment Methods
    // ===============================
    public PaymentPage enterPaymentDetails(String name, String number, String cvcCode, String month, String year) {
        helper.sendKeys(nameOnCard, name);
        helper.sendKeys(cardNumber, number);
        helper.sendKeys(cvc, cvcCode);
        helper.sendKeys(expiryMonth, month);
        helper.sendKeys(expiryYear, year);
        return this;
    }

    public OrderConfirmationPage clickPayAndConfirm() {
        helper.click(payAndConfirmBtn);
        return new OrderConfirmationPage(driver, helper);
    }

    public String getOrderSuccessMessage() {
        return helper.getText(orderSuccessMsg).trim();
    }

    public void downloadInvoice() {
        helper.click(downloadInvoiceBtn);
        // wait a little for download to start if needed
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean isInvoiceDownloaded() {
        File folder = new File(downloadPath);
        File[] files = folder.listFiles((dir, name) -> 
            name.toLowerCase().contains("invoice")
        );
        return files != null && files.length > 0;
    }

    public void clickContinue() {
        helper.click(continueBtn);
    }
}

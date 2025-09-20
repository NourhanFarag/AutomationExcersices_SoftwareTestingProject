package Pages;

import SeleniumFramework.SeleniumHelper;
import org.openqa.selenium.*;

/**
 *
 * @author Nourhan Farag
 */
public class PaymentPage {
    private final WebDriver driver;
    private final SeleniumHelper helper;

    private final By nameOnCard = By.name("name_on_card");
    private final By cardNumber = By.name("card_number");
    private final By cvc = By.name("cvc");
    private final By expiryMonth = By.name("expiry_month");
    private final By expiryYear = By.name("expiry_year");
    private final By payAndConfirmBtn = By.id("submit"); // adjust locator if different

    public PaymentPage(WebDriver driver, SeleniumHelper helper) {
        this.driver = driver;
        this.helper = helper;
    }

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
}

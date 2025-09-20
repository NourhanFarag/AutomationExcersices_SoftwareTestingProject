package Pages;

import SeleniumFramework.SeleniumHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
/**
 *
 * @author Nourhan Farag
 */
public class OrderConfirmationPage {
    private final WebDriver driver;
    private final SeleniumHelper helper;

    private final By successMessage = By.xpath("//p[contains(text(),'Congratulations! Your order has been confirmed!')]");

    public OrderConfirmationPage(WebDriver driver, SeleniumHelper helper) {
        this.driver = driver;
        this.helper = helper;
    }

    public boolean isOrderSuccessMessageVisible() {
        return helper.isElementDisplayed(successMessage);
    }
    public String getOrderSuccessMessageText() {
        return helper.getText(successMessage).trim();
    }

}

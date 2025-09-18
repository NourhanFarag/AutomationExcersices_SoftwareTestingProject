package Pages;

/**
 *
 * @author Nourhan Farag
 */
import SeleniumFramework.SeleniumHelper;
import org.openqa.selenium.*;

public class AccountCreatedPage {
    private final WebDriver driver;
    private final SeleniumHelper helper;

    private final By accountCreatedText = By.xpath("//b[text()='Account Created!']");
    private final By continueBtn = By.xpath("//a[@data-qa='continue-button']");
    
    public AccountCreatedPage(WebDriver driver, SeleniumHelper helper) {
        this.driver = driver;
        this.helper = helper;
    }

    public String getAccountCreatedText() {
        return helper.getText(accountCreatedText).trim();
    }

    public HomePage clickContinue() {
        helper.click(continueBtn);
        return new HomePage(driver, helper);
    }
}

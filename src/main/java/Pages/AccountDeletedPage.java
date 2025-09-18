package Pages;

/**
 *
 * @author Nourhan Farag
 */
import SeleniumFramework.SeleniumHelper;
import org.openqa.selenium.*;

public class AccountDeletedPage {
    private final WebDriver driver;
    private final SeleniumHelper helper;

    private final By accountDeletedText = By.xpath("//b[text()='Account Deleted!']");
    private final By continueBtn = By.xpath("//a[@data-qa='continue-button']");

    public AccountDeletedPage(WebDriver driver, SeleniumHelper helper) {
        this.driver = driver;
        this.helper = helper;
    }

    public String getAccountDeletedText() {
        return helper.getText(accountDeletedText).trim();
    }

    public HomePage clickContinue() {
        helper.click(continueBtn);
        return new HomePage(driver, helper);
    }
}

package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import SeleniumFramework.SeleniumHelper;
/**
 *
 * @author Nourhan Farag
 */
public class ContactUsPage {
    private final WebDriver driver;
    private final SeleniumHelper helper; 
    
    // ===============================
    // Locators
    // ===============================
    private final By getInTouchHeader = By.xpath("//h2[contains(text(),'Get In Touch')]");
    private final By nameField = By.name("name");
    private final By emailField = By.name("email");
    private final By subjectField = By.name("subject");
    private final By messageField = By.cssSelector("textarea[name='message']");
    private final By uploadFileInput = By.cssSelector("#contact-us-form input[type='file']");
    private final By submitButton = By.cssSelector("form[action='/contact_us'] input[type='submit']");
    private final By successMessage = By.cssSelector("#contact-page .status.alert-success");
    private final By homeButton = By.cssSelector("a.btn.btn-success"); 

    public ContactUsPage(WebDriver driver, SeleniumHelper helper) {
        this.driver = driver;
        this.helper = helper;
    }
    
    // ===============================
    // Actions
    // ===============================

    public String isGetInTouchVisible() {
        return helper.getText(getInTouchHeader);
    }
    
    public void enterName(String name) {
        helper.sendKeys(nameField, name);
    }

    public void enterEmail(String email) {
        helper.sendKeys(emailField, email);
    }

    public void enterSubject(String subject) {
        helper.sendKeys(subjectField, subject);
    }

    public void enterMessage(String message) {
        helper.sendKeys(messageField, message);
    }
    
    public void uploadFile(String filepath) {
        helper.uploadFile(uploadFileInput, filepath);
    }
    
    public void clickSubmit() {
        helper.click(submitButton);
    }

    public String getSuccessMessage() {
        return helper.getText(successMessage);
    }

    public void acceptAlert() {
        helper.acceptAlert();
    }

    public HomePage clickHomeButton() {
        helper.click(homeButton);
        return new HomePage(driver, helper);
    }
}

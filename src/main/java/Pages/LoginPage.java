package Pages;

/**
 *
 * @author Nourhan Farag
 */
import SeleniumFramework.SeleniumHelper;
import org.openqa.selenium.*;
public class LoginPage {
    private final WebDriver driver;
    private final SeleniumHelper helper;
    private final By newUserSignupText = By.xpath("//h2[text()='New User Signup!']");
    private final By nameField = By.xpath("//input[@data-qa='signup-name']");
    private final By emailField = By.xpath("//input[@data-qa='signup-email']");
    private final By signupBtn = By.xpath("//button[@data-qa='signup-button']");
    private final By loginToAccountHeader = By.xpath("//h2[normalize-space()='Login to your account']");
    private final By loginEmailField = By.xpath("//input[@data-qa='login-email']");
    private final By loginPasswordField = By.cssSelector("input[data-qa='login-password']");
    private final By loginButton = By.cssSelector("button[data-qa='login-button']");
    private final By loginErrorMessage = By.xpath("//p[@style='color: red;']");

    private final By newUserSignupHeader = By.xpath("//h2[text()='New User Signup!']");
    private final By signupNameField = By.name("name");
    private final By signupEmailField = By.xpath("//input[@data-qa='signup-email']");
    private final By signupButton = By.xpath("//button[@data-qa='signup-button']");
    private final By signupError = By.xpath("//p[text()='Email Address already exist!']");

    public LoginPage(WebDriver driver, SeleniumHelper helper) {
        this.driver = driver;
        this.helper = helper;
    }
    
    public String getNewUserSignupText() {
        return helper.getText(newUserSignupText).trim();
    }

    public RegisterPage enterSignupDetails(String name, String email) {
        helper.sendKeys(nameField, name);
        helper.sendKeys(emailField, email);
        helper.click(signupBtn);
        return new RegisterPage(driver, helper);
    }
    
    public String getLoginHeaderText() {
        return helper.getText(loginToAccountHeader).trim();
    }
    
    public void enterLoginEmail(String email) {
        helper.sendKeys(loginEmailField, email);
    }

    public void enterLoginPassword(String password) {
        helper.sendKeys(loginPasswordField, password);
    }
    
    public void clickLoginButton() {
        helper.click(loginButton);
    }
    
    public String getLoginErrorMessage() {
        return helper.getText(loginErrorMessage).trim();
    }
    
    public String getNewUserSignupHeaderText() {
        return helper.getText(newUserSignupHeader).trim();
    }

    public void enterSignupName(String name) {
        helper.sendKeys(signupNameField, name);
    }

    public void enterSignupEmail(String email) {
        helper.sendKeys(signupEmailField, email);
    }

    public void clickSignupButton() {
        helper.click(signupButton);
    }

    public String getSignupErrorText() {
        return helper.getText(signupError).trim();
    }
}

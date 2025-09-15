package Pages;

/**
 *
 * @author Nourhan Farag
 */
import SeleniumFramework.SeleniumHelper;
import org.openqa.selenium.*;

public class RegisterPage {
    private final WebDriver driver;
    private final SeleniumHelper helper;
    
    private final By accountInfoHeader = By.xpath("//b[text()='Enter Account Information']");
    private final By titleMr = By.id("id_gender1");
    private final By titleMrs = By.id("id_gender2");
    private final By passwordField = By.id("password");
    private final By dayDropdown = By.id("days");
    private final By monthDropdown = By.id("months");
    private final By yearDropdown = By.id("years");
    private final By newsletterCheckbox = By.id("newsletter");
    private final By offersCheckbox = By.id("optin");

    private final By firstNameField = By.id("first_name");
    private final By lastNameField = By.id("last_name");
    private final By companyField = By.id("company");
    private final By address1Field = By.id("address1");
    private final By address2Field = By.id("address2");
    private final By countryDropdown = By.id("country");
    private final By stateField = By.id("state");
    private final By cityField = By.id("city");
    private final By zipcodeField = By.id("zipcode");
    private final By mobileNumberField = By.id("mobile_number");
    
    private final By createAccountBtn = By.xpath("//button[@data-qa='create-account']");

    public RegisterPage(WebDriver driver, SeleniumHelper helper) {
        this.driver = driver;
        this.helper = helper;
    }
    
    public String getEnterAccountInfoText() {
        return helper.getText(accountInfoHeader).trim();
    }
    
    public void selectTitle (String title) {
        if(title.equalsIgnoreCase("Mr")) {
            helper.selectRadioButton(titleMr);
        } else {
            helper.selectRadioButton(titleMrs);
        }
    }
    
    public void enterPass(String password) {
        helper.sendKeys(passwordField, password);
    }
    
    public void selectDOB(String day, String month, String year) {
        helper.selectDropdownByValue(dayDropdown, day);
        helper.selectDropdownByVisibleText(monthDropdown, month);
        helper.selectDropdownByValue(yearDropdown, year);
    }

    public void checkNewsletter() {
        helper.checkCheckbox(newsletterCheckbox);
    }

    public void checkSpecialOffers() {
        helper.checkCheckbox(offersCheckbox);
    }
    
    public void enterPersonalDetails(String fname, String lname, String company,
                                     String address1, String address2, String country,
                                     String state, String city, String zipcode, String mobile) {
        helper.sendKeys(firstNameField, fname);
        helper.sendKeys(lastNameField, lname);
        helper.sendKeys(companyField, company);
        helper.sendKeys(address1Field, address1);
        helper.sendKeys(address2Field, address2);
        helper.selectDropdownByVisibleText(countryDropdown, country);
        helper.sendKeys(stateField, state);
        helper.sendKeys(cityField, city);
        helper.sendKeys(zipcodeField, zipcode);
        helper.sendKeys(mobileNumberField, mobile);
    }

    public AccountCreatedPage clickCreateAccount() {
        helper.click(createAccountBtn);
        return new AccountCreatedPage(driver, helper);
    }
}

package Pages;

import SeleniumFramework.SeleniumHelper;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
/**
 *
 * @author Nourhan Farag
 */
public class HomePage {
    private final WebDriver driver;
    private final SeleniumHelper helper;
    
    private final By homeLogo = By.cssSelector("img[alt='Website for automation practice']");
    private final By signupLoginBtn = By.xpath("//a[contains(text(),'Signup / Login')]");
    private final By loggedInAs = By.xpath("//a[contains(text(),'Logged in as')]");
    private final By loggedInUsername = By.xpath("//li[a[contains(text(),'Logged in as')]]/a/b");
    private final By deleteAccountBtn = By.xpath("//a[contains(text(),'Delete Account')]");
    private final By loggedInAsText = By.xpath("//li[a[contains(normalize-space(.),'Logged in as')]]");
    private final By logoutBtn = By.xpath("//a[contains(text(),'Logout')]");
    private final By contactUsButton = By.xpath("//a[contains(text(),'Contact us')]");
    private final By homeButton = By.xpath("//a[@class='btn btn-success']");
    private final By testCasesButton = By.cssSelector("a[href='/test_cases']");
    private final By testCasesHeader = By.cssSelector(".title.text-center");
    
    public HomePage(WebDriver driver, SeleniumHelper helper) {
        this.driver = driver;
        this.helper = helper;
    }
    
    public boolean isHomePageVisible() {
        return helper.isElementDisplayed(homeLogo);
    }
    
    public LoginPage clickSignupLogin() {
        helper.click(signupLoginBtn);
        return new LoginPage(driver, helper);
    }
    
    public boolean isLoggedInAsVisible(String expectedUsername) {
        String text = helper.getText(loggedInAs);
        return text.contains(expectedUsername);
    }

    public String getLoggedInAsText() {
        return helper.getText(loggedInAsText).trim();
    }

    public String getLoggedInUsername() {
        return helper.getText(loggedInUsername).trim();
    }

    public boolean isLoggedInAs(String expectedUsername) {
        return getLoggedInUsername().equalsIgnoreCase(expectedUsername == null ? "" : expectedUsername.trim());
    }

    public AccountDeletedPage clickDeleteAccount() {
        helper.click(deleteAccountBtn);
        return new AccountDeletedPage(driver, helper);
    }
    
    public LoginPage clickLogout() {
        helper.click(logoutBtn);
        return new LoginPage(driver, helper);
    }
    
    public void clickContactUs() {
        driver.findElement(contactUsButton).click();
    }

    public void clickHomeButton() {
        driver.findElement(homeButton).click();
    }
    
    public void clickTestCases() {
        helper.click(testCasesButton);
    }
    
    public boolean isTestCasesPageVisible() {
        return helper.isElementDisplayed(testCasesHeader);
    }
}

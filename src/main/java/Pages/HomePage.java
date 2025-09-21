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
    
    // ===============================
    // Locators
    // ===============================
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
    private final By productsButton = By.xpath("//a[contains(text(),'Products')]");
    private final By subscriptionText = By.xpath("//h2[contains(text(),'Subscription')]");
    private final By subscriptionEmailInput = By.id("susbscribe_email");
    private final By subscriptionArrowBtn = By.id("subscribe");
    private final By successMessage = By.xpath("//div[contains(text(),'You have been successfully subscribed!')]");
    private final By cartButton = By.xpath("//a[contains(text(),'Cart')]");
    private final By firstProductViewBtn = By.xpath("(//a[contains(text(),'View Product')])[1]");
    private final By recommendedSection = By.xpath("//h2[contains(text(),'recommended items')]");
    private final By firstRecommendedAddToCartBtn = By.xpath("(//div[@id='recommended-item-carousel']//a[contains(text(),'Add to cart')])[1]");
    private final By viewCartBtnRecommended = By.xpath("//u[contains(text(),'View Cart')]");
    private final By scrollUpArrow = By.id("scrollUp");
    private final By SubscriptionArrow = By.id("subscribe");
    private final By topText = By.xpath("//h2[contains(text(),'Full-Fledged practice website for Automation Engineers')]");
    private final By footer = By.tagName("footer"); 
    
    public HomePage(WebDriver driver, SeleniumHelper helper) {
        this.driver = driver;
        this.helper = helper;
    }
    
    // ===============================
    // Page Verifications
    // ===============================
    public boolean isHomePageVisible() {
        return helper.isElementDisplayed(homeLogo);
    }
    
    public boolean isLoggedInAsVisible(String expectedUsername) {
        String text = helper.getText(loggedInAs);
        return text.contains(expectedUsername);
    }
    
    public boolean isLoggedInAs(String expectedUsername) {
        return getLoggedInUsername().equalsIgnoreCase(expectedUsername == null ? "" : expectedUsername.trim());
    }

    public boolean isSubscriptionSectionVisible() {
        return helper.isElementDisplayed(subscriptionText);
    }
    
    public boolean isTopTextVisible() {
        return helper.isElementDisplayed(topText);
    }
    
    public boolean isTestCasesPageVisible() {
        return helper.isElementDisplayed(testCasesHeader);
    }
    
    public boolean isCategoriesSidebarVisible() {
        return helper.isElementDisplayed(By.xpath("//div[@class='left-sidebar']/h2[text()='Category']"));
    }
    
    public boolean isRecommendedSectionVisible() {
        return helper.isElementDisplayed(recommendedSection);
    }
    
    // ===============================
    // Page Actions
    // ===============================

    public LoginPage clickSignupLogin() {
        helper.click(signupLoginBtn);
        return new LoginPage(driver, helper);
    }
    
    
    public String getLoggedInAsText() {
        return helper.getText(loggedInAsText).trim();
    }

    public String getLoggedInUsername() {
        return helper.getText(loggedInUsername).trim();
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
    
    public ProductsPage clickProducts() {
        helper.click(productsButton);
        return new ProductsPage(driver, helper);
    }
    
    public void scrollToFooter() {
        helper.scrollIntoView(footer);
    }

    public String getSubscriptionText() {
        return helper.getText(subscriptionText);
    }

    public void subscribeWithEmail(String email) {
        helper.sendKeys(subscriptionEmailInput, email);
        helper.safeClick(subscriptionArrowBtn);
    }

    public String getSubscriptionSuccessMessage() {
        return helper.getText(successMessage);
    }
    
    public void clickCart() {
        helper.click(cartButton);
    }
    
    public void clickFirstViewProduct() {
        helper.safeClick(firstProductViewBtn);
    }
    
    public ProductsPage selectCategory(String mainCategory, String subCategory) {
        // Expand main category
        By mainCategoryLocator = By.xpath("//div[@class='panel-group category-products']//a[@href='#" + mainCategory + "']");
        helper.click(mainCategoryLocator);

        // Click on subcategory inside the expanded div
        By subCategoryLocator = By.xpath("//div[@id='" + mainCategory + "']//a[normalize-space()='" + subCategory + "']");
        helper.click(subCategoryLocator);

        return new ProductsPage(driver, helper);
    }

    public void addFirstRecommendedProductToCart() {
        helper.scrollToElement(firstRecommendedAddToCartBtn);
        helper.safeClick(firstRecommendedAddToCartBtn);
    }

    public CartPage clickViewCartFromRecommended() {
        helper.safeClick(viewCartBtnRecommended);
        return new CartPage(driver, helper);
    }

    public void clickScrollUpArrow() {
        helper.click(scrollUpArrow);
        helper.waitForVisibility(topText, 5);
    }
    
    public void scrollToBottom() {
        helper.scrollToElement(SubscriptionArrow);
    }
}

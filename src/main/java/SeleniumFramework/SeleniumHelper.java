package SeleniumFramework;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Nourhan
 */
public class SeleniumHelper {

    private final WebDriver driver;
    private static final int DEFAULT_TIMEOUT = 10;

    public SeleniumHelper(WebDriver driver) {
        this.driver = driver;
    }

    // =============================== 
    // WAITING METHODS 
    // ===============================

    public void implicitWait(int seconds) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
    }

    public WebElement waitForPresence(By locator, int timeoutSeconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
                .until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public WebElement waitForVisibility(By locator, int timeoutSeconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForClickable(By locator, int timeoutSeconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void fluentWait(By locator, int timeoutSeconds, int pollingMillis, String timeoutMessage) {
        new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeoutSeconds))
                .pollingEvery(Duration.ofMillis(pollingMillis))
                .withMessage(timeoutMessage)
                .ignoring(NoSuchElementException.class)
                .until(d -> d.findElement(locator));
    }

    // =============================== 
    // NAVIGATION 
    // ===============================

    public void navigateToURL(String url) { driver.get(url); }

    public String getPageTitle() { return driver.getTitle(); }

    public String getCurrentURL() { return driver.getCurrentUrl(); }

    public void navigateBack() { driver.navigate().back(); }

    public void navigateForward() { driver.navigate().forward(); }

    public void refreshPage() { driver.navigate().refresh(); }

    // =============================== 
    // ELEMENT INTERACTIONS 
    // ===============================

    public WebElement findElement(By locator) {
        return waitForPresence(locator, DEFAULT_TIMEOUT);
    }

    public List<WebElement> findElements(By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    public void click(By locator) {
        try {
            waitForClickable(locator, DEFAULT_TIMEOUT).click();
        } catch (ElementClickInterceptedException e) {
            // fallback: JS click
            safeClick(locator);
        } catch (TimeoutException e) {
            throw new RuntimeException("Element not clickable: " + locator, e);
        }
    }

    public void safeClick(By locator) {
        WebElement element = waitForClickable(locator, DEFAULT_TIMEOUT);
        executeJS("arguments[0].click();", element);
    }

    public void rightClick(By locator) {
        WebElement element = waitForVisibility(locator, DEFAULT_TIMEOUT);
        new Actions(driver).contextClick(element).perform();
    }

    public void sendKeys(By locator, String text) {
        WebElement element = waitForVisibility(locator, DEFAULT_TIMEOUT);
        element.clear();
        element.sendKeys(text);
    }

    public String getText(By locator) {
        try {
            return waitForVisibility(locator, DEFAULT_TIMEOUT).getText();
        } catch (Exception e) {
            return "";
        }
    }

    public WebElement getElement(By locator) {
        return waitForVisibility(locator, DEFAULT_TIMEOUT);
    }

    public void hoverOver(By locator) {
        WebElement element = waitForVisibility(locator, DEFAULT_TIMEOUT);
        new Actions(driver).moveToElement(element).perform();
    }

    public void hoverAndClick(By locator) {
        WebElement element = waitForVisibility(locator, DEFAULT_TIMEOUT);
        new Actions(driver).moveToElement(element).click().perform();
    }

    public void doubleClick(By locator) {
        WebElement element = waitForVisibility(locator, DEFAULT_TIMEOUT);
        new Actions(driver).doubleClick(element).perform();
    }

    public void clickAndHold(By locator) {
        WebElement element = waitForVisibility(locator, DEFAULT_TIMEOUT);
        new Actions(driver).clickAndHold(element).perform();
    }

    public void releaseMouse() {
        new Actions(driver).release().perform();
    }

    public String getAttribute(By locator, String attribute) {
        return waitForVisibility(locator, DEFAULT_TIMEOUT).getAttribute(attribute);
    }

    public void uploadFile(By locator, String filePath) {
        WebElement uploadElement = waitForVisibility(locator, DEFAULT_TIMEOUT);
        uploadElement.sendKeys(filePath);
    }

    // =============================== 
    // DROPDOWNS 
    // ===============================

    public void selectDropdownByVisibleText(By locator, String visibleText) {
        new Select(waitForVisibility(locator, DEFAULT_TIMEOUT)).selectByVisibleText(visibleText);
    }

    public void selectDropdownByValue(By locator, String value) {
        new Select(waitForVisibility(locator, DEFAULT_TIMEOUT)).selectByValue(value);
    }

    public void selectDropdownByIndex(By locator, int index) {
        new Select(waitForVisibility(locator, DEFAULT_TIMEOUT)).selectByIndex(index);
    }

    // =============================== 
    // ADVANCED INTERACTIONS 
    // ===============================

    public void dragAndDrop(By sourceLocator, By targetLocator) {
        WebElement source = waitForVisibility(sourceLocator, DEFAULT_TIMEOUT);
        WebElement target = waitForVisibility(targetLocator, DEFAULT_TIMEOUT);
        new Actions(driver).dragAndDrop(source, target).perform();
    }

    public void checkCheckbox(By locator) {
        WebElement checkbox = waitForVisibility(locator, DEFAULT_TIMEOUT);
        if (!checkbox.isSelected()) checkbox.click();
    }

    public void uncheckCheckbox(By locator) {
        WebElement checkbox = waitForVisibility(locator, DEFAULT_TIMEOUT);
        if (checkbox.isSelected()) checkbox.click();
    }

    public void selectRadioButton(By locator) {
        WebElement radio = waitForVisibility(locator, DEFAULT_TIMEOUT);
        if (!radio.isSelected()) radio.click();
    }

    public void scrollToElement(By locator) {
        WebElement element = waitForVisibility(locator, DEFAULT_TIMEOUT);
        new Actions(driver).scrollToElement(element).perform();
    }

    public void scrollIntoView(By locator) {
        WebElement element = waitForVisibility(locator, DEFAULT_TIMEOUT);
        executeJS("arguments[0].scrollIntoView(true);", element);
    }

    public void scrollToBottom() {
        executeJS("window.scrollTo(0, document.body.scrollHeight)");
    }

    // =============================== 
    // WINDOWS & ALERTS 
    // ===============================

    public void switchToWindowByTitle(String windowTitle) {
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
            if (driver.getTitle().equals(windowTitle)) break;
        }
    }

    public void switchToWindowByHandle(String windowHandle) {
        driver.switchTo().window(windowHandle);
    }

    public void switchToTab(int index) {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        if (index >= 0 && index < tabs.size()) {
            driver.switchTo().window(tabs.get(index));
        } else {
            throw new IllegalArgumentException("Invalid tab index: " + index);
        }
    }

    public void closeCurrentWindow() { driver.close(); }

    public void acceptAlert() { driver.switchTo().alert().accept(); }

    public void dismissAlert() { driver.switchTo().alert().dismiss(); }

    public String getAlertText() { return driver.switchTo().alert().getText(); }

    public void sendTextToAlert(String text) {
        Alert alert = driver.switchTo().alert();
        alert.sendKeys(text);
        alert.accept();
    }

    // =============================== 
    // UTILITIES 
    // ===============================

    public boolean isElementDisplayed(By locator) {
        try {
            return waitForVisibility(locator, DEFAULT_TIMEOUT).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public Object executeJS(String script, Object... args) {
        return ((JavascriptExecutor) driver).executeScript(script, args);
    }

    public File takeScreenshot(String filePath) {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File dest = new File(filePath);
        try {
            Files.copy(screenshot.toPath(), dest.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Failed to save screenshot: " + filePath, e);
        }
        return dest;
    }

    public void closeBrowser() {
        Driver.quitDriver();
    }

    public void tearDown() {
        closeBrowser();
    }
}

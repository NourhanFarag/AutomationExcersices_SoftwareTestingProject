package SeleniumFramework;

/**
 *
 * @author Nourhan Farag
 */

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class SeleniumHelper {

    private final WebDriver driver;
    private static final int DEFAULT_TIMEOUT = 10;

    public SeleniumHelper(WebDriver driver) {
        this.driver = driver;
    }

    // ===============================
    // WAITING METHODS
    // ===============================

    // Applies an implicit wait for a fixed number of seconds
    public void implicitWait(int seconds) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
    }

    // Waits until an element is present in the DOM (may still be invisible)
    public WebElement waitForPresence(By locator, int timeoutSeconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
                .until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    // Waits until an element is visible on the page
    public WebElement waitForVisibility(By locator, int timeoutSeconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Waits until an element is clickable (visible and enabled)
    public WebElement waitForClickable(By locator, int timeoutSeconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    // Customizable wait that polls periodically until the element is found
    public void fluentWait(By locator, int timeoutSeconds, int pollingMillis, String timeoutMessage) {
        new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeoutSeconds))
                .pollingEvery(Duration.ofMillis(pollingMillis))
                .withMessage(timeoutMessage)
                .ignoring(NoSuchElementException.class)
                .until(d -> d.findElement(locator));
    }

    // ===============================
    // NAVIGATION METHODS
    // ===============================

    // Opens a given URL in the current browser window
    public void navigateToURL(String url) { driver.get(url); }

    // Retrieves the current page title
    public String getPageTitle() { return driver.getTitle(); }

    // Retrieves the current page URL
    public String getCurrentURL() { return driver.getCurrentUrl(); }

    // Navigates back in browser history
    public void navigateBack() { driver.navigate().back(); }

    // Navigates forward in browser history
    public void navigateForward() { driver.navigate().forward(); }

    // Refreshes the current page
    public void refreshPage() { driver.navigate().refresh(); }

    // ===============================
    // ELEMENT INTERACTIONS
    // ===============================

    // Clicks an element after waiting for it to be clickable
    public void click(By locator) {
        try {
            waitForClickable(locator, DEFAULT_TIMEOUT).click();
        } catch (TimeoutException e) {
            throw new RuntimeException("Element not clickable: " + locator, e);
        }
    }

    // Performs a right-click (context menu click) on an element
    public void rightClick(By locator) {
        try {
            WebElement element = waitForVisibility(locator, DEFAULT_TIMEOUT);
            new Actions(driver).contextClick(element).perform();
        } catch (Exception e) {
            throw new RuntimeException("Failed to right-click element: " + locator, e);
        }
    }

    // Sends text input to a field (clears any existing value first)
    public void sendKeys(By locator, String text) {
        try {
            WebElement element = waitForVisibility(locator, DEFAULT_TIMEOUT);
            element.clear();
            element.sendKeys(text);
        } catch (Exception e) {
            throw new RuntimeException("Failed to send keys to element: " + locator, e);
        }
    }

    // Retrieves the visible text of an element
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

    // Moves the mouse over an element (useful for menus, hover actions)
    public void hoverOver(By locator) {
        WebElement element = waitForVisibility(locator, DEFAULT_TIMEOUT);
        new Actions(driver).moveToElement(element).perform();
    }

    // Retrieves the value of a given attribute from an element
    public String getAttribute(By locator, String attribute) {
        return waitForVisibility(locator, DEFAULT_TIMEOUT).getAttribute(attribute);
    }

    // Uploads a file by sending a file path to a file input field
    public void uploadFile(By locator, String filePath) {
        WebElement uploadElement = waitForVisibility(locator, DEFAULT_TIMEOUT);
        uploadElement.sendKeys(filePath);
    }

    // ===============================
    // DROPDOWNS
    // ===============================

    // Selects a dropdown option by its visible text
    public void selectDropdownByVisibleText(By locator, String visibleText) {
        new Select(waitForVisibility(locator, DEFAULT_TIMEOUT)).selectByVisibleText(visibleText);
    }

    // Selects a dropdown option by its value attribute
    public void selectDropdownByValue(By locator, String value) {
        new Select(waitForVisibility(locator, DEFAULT_TIMEOUT)).selectByValue(value);
    }

    // Selects a dropdown option by its index number
    public void selectDropdownByIndex(By locator, int index) {
        new Select(waitForVisibility(locator, DEFAULT_TIMEOUT)).selectByIndex(index);
    }

    // ===============================
    // ADVANCED INTERACTIONS
    // ===============================

    // Performs a drag-and-drop from source element to target element
    public void dragAndDrop(By sourceLocator, By targetLocator) {
        WebElement source = waitForVisibility(sourceLocator, DEFAULT_TIMEOUT);
        WebElement target = waitForVisibility(targetLocator, DEFAULT_TIMEOUT);
        new Actions(driver).dragAndDrop(source, target).perform();
    }

    // Ensures a checkbox is checked (only clicks if unchecked)
    public void checkCheckbox(By locator) {
        WebElement checkbox = waitForVisibility(locator, DEFAULT_TIMEOUT);
        if (!checkbox.isSelected()) checkbox.click();
    }

    // Ensures a checkbox is unchecked (only clicks if checked)
    public void uncheckCheckbox(By locator) {
        WebElement checkbox = waitForVisibility(locator, DEFAULT_TIMEOUT);
        if (checkbox.isSelected()) checkbox.click();
    }

    // Selects a radio button (only clicks if not already selected)
    public void selectRadioButton(By locator) {
        WebElement radio = waitForVisibility(locator, DEFAULT_TIMEOUT);
        if (!radio.isSelected()) radio.click();
    }

    // Scrolls the page until the element is visible in viewport
    public void scrollToElement(By locator) {
        WebElement element = waitForVisibility(locator, DEFAULT_TIMEOUT);
        new Actions(driver).scrollToElement(element).perform();
    }

    // ===============================
    // WINDOWS & ALERTS
    // ===============================

    // Switches browser context to a window by matching its title
    public void switchToWindowByTitle(String windowTitle) {
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
            if (driver.getTitle().equals(windowTitle)) break;
        }
    }

    // Switches browser context to a specific window handle
    public void switchToWindowByHandle(String windowHandle) {
        driver.switchTo().window(windowHandle);
    }

    // Closes the current active browser window
    public void closeCurrentWindow() { driver.close(); }

    // Accepts an active alert pop-up
    public void acceptAlert() { driver.switchTo().alert().accept(); }

    // Dismisses (cancels) an active alert pop-up
    public void dismissAlert() { driver.switchTo().alert().dismiss(); }

    // Retrieves the text from an active alert pop-up
    public String getAlertText() { return driver.switchTo().alert().getText(); }

    // Sends input text to an alert (prompt) and accepts it
    public void sendTextToAlert(String text) {
        Alert alert = driver.switchTo().alert();
        alert.sendKeys(text);
        alert.accept();
    }

    // ===============================
    // UTILITIES
    // ===============================

    // Checks whether an element is visible on the page
    public boolean isElementDisplayed(By locator) {
        try {
            return waitForVisibility(locator, DEFAULT_TIMEOUT).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Executes custom JavaScript code in the browser context
    public Object executeJS(String script, Object... args) {
        return ((JavascriptExecutor) driver).executeScript(script, args);
    }

    // Captures a screenshot and saves it to the given file path
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

    // Closes the browser session using Driver class
    public void closeBrowser() {
        Driver.quitDriver();
    }

    // Shuts down the browser (alias for closeBrowser, for test cleanup)
    public void tearDown() {
        closeBrowser();
    }
}

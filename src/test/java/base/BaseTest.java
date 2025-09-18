package base;

import SeleniumFramework.Driver;
import SeleniumFramework.SeleniumHelper;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 *
 * @author Nourhan Farag
 */

public class BaseTest {
    public WebDriver driver;
    public SeleniumHelper helper;
    private static final String BASE_URL = "https://automationexercise.com";

    @BeforeMethod (alwaysRun = true)
    public void setUp() {
        driver = Driver.getDriver();
        helper = new SeleniumHelper(driver);
        helper.navigateToURL(BASE_URL);
    }

    @AfterMethod (alwaysRun = true)
    public void tearDown(ITestResult result) {
        if(ITestResult.FAILURE == result.getStatus()){
            saveScreenshotOnFailure();
        }
        Driver.quitDriver();
    }
    
    // Capture screenshot and attach to Allure Report
    @Attachment(value = "Failure Screenshot", type = "image/png")
    public byte[] saveScreenshotOnFailure() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}

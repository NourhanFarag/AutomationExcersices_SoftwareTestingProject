package tests;

import Pages.*;
import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
/**
 *
 * @author Nourhan Farag
 */
public class TestCases_TC7 extends BaseTest{
    
    @Test
    public void verifyTestCasesPage() {
        HomePage homePage = new HomePage(driver, helper);

        // Step 3: Verify Home Page
        Assert.assertTrue(homePage.isHomePageVisible(), "Home page is not visible!");

        // Step 4: Click on Test Cases button
        homePage.clickTestCases();

        // Step 5: Verify user is navigated to test cases page
        Assert.assertTrue(driver.getCurrentUrl().contains("/test_cases"), "User was not navigated to Test Cases Page");
        Assert.assertTrue(homePage.isTestCasesPageVisible(), "Test Cases page is not visible!");
    }
}

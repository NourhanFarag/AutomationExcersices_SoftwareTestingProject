package tests;

import Pages.*;
import base.BaseTest;
import org.testng.annotations.Test;
import io.qameta.allure.Step;

/**
 * 
 * @author Nourhan Farag
 */
public class TestCases_TC7 extends BaseTest {

    @Test
    public void testTestCasesPage() {
        stepVerifyHomePage();
        stepGoToTestCasesPage();
        stepVerifyTestCasesPageVisible();
    }

    @Step("Step 3: Verify Home Page is visible")
    private void stepVerifyHomePage() {
        HomePage home = new HomePage(driver, helper);
        attachText("Expected Output", "Home page visible");
        if (!home.isHomePageVisible()) {
            attachText("Actual Output", "Home page NOT visible");
            throw new AssertionError("Home page is not visible!");
        }
    }

    @Step("Step 4: Navigate to Test Cases page")
    private void stepGoToTestCasesPage() {
        HomePage home = new HomePage(driver, helper);
        home.clickTestCases();
        attachText("Expected Output", "Navigated to Test Cases page");
    }

    @Step("Step 5: Verify Test Cases page visible")
    private void stepVerifyTestCasesPageVisible() {
        HomePage home = new HomePage(driver, helper);
        if (!driver.getCurrentUrl().contains("/test_cases") || !home.isTestCasesPageVisible()) {
            attachText("Actual Output", "Test Cases page NOT visible");
            throw new AssertionError("Test Cases page is not visible!");
        }
    }
}

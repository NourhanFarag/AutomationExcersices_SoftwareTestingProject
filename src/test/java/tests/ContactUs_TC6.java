package tests;

import Pages.*;
import base.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import io.qameta.allure.Step;
import tests.LoadingData.ContactUsUsers;

/**
 * 
 * @author Nourhan Farag
*/
public class ContactUs_TC6 extends BaseTest {

    @DataProvider(name = "contactUsData")
    public Object[][] getContactUsData() throws Exception {
        ContactUsUsers[] users = LoadingData.readContactUsForms("contactUsUsers.json");
        Object[][] data = new Object[users.length][1];
        for (int i = 0; i < users.length; i++) {
            data[i][0] = users[i];
        }
        return data;
    }

    @Test(dataProvider = "contactUsData")
    public void testContactUsForm(ContactUsUsers user) {
        SoftAssert softAssert = new SoftAssert();

        stepVerifyHomePage();
        stepGoToContactUs();
        stepFillContactForm(user);
        stepSubmitForm();

        softAssert.assertAll();
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

    @Step("Step 4: Navigate to Contact Us page")
    private void stepGoToContactUs() {
        HomePage home = new HomePage(driver, helper);
        home.clickContactUs();
        attachText("Expected Output", "Contact Us page visible");
    }

    @Step("Step 6-8: Fill and submit Contact Us form")
    private void stepFillContactForm(ContactUsUsers user) {
        ContactUsPage contactUsPage = new ContactUsPage(driver, helper);
        contactUsPage.enterName(user.contactUsFormName);
        contactUsPage.enterEmail(user.contactUsFormEmail);
        contactUsPage.enterSubject(user.contactUsFormSubject);
        contactUsPage.enterMessage(user.contactUsFormMsg);
        contactUsPage.uploadFile(user.contactUsFormFilePath);
        attachText("Test Input", "Filled contact form details and uploaded file");
    }

    @Step("Step 9-10: Submit form and verify success message")
    private void stepSubmitForm() {
        ContactUsPage contactUsPage = new ContactUsPage(driver, helper);
        contactUsPage.clickSubmit();
        contactUsPage.acceptAlert();
        attachText("Expected Output", "Success! Your details have been submitted successfully.");
        if (!contactUsPage.getSuccessMessage().equals("Success! Your details have been submitted successfully.")) {
            attachText("Actual Output", contactUsPage.getSuccessMessage());
            throw new AssertionError("Contact form submission failed!");
        }
        contactUsPage.clickHomeButton();
    }
}

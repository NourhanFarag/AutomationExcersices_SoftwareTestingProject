package tests;


import Pages.*;
import base.BaseTest;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.Test;

import org.testng.annotations.DataProvider;
/**
 *
 * @author Nourhan Farag
 */
public class ContactUs_TC6 extends BaseTest{
    
    @DataProvider(name = "contactUsData")
    public Object[][] getContactUsData() throws Exception {
        LoadingData.ContactUsUsers[] users = LoadingData.readContactUsForms("contactUsUsers.json");
        Object[][] data = new Object[users.length][1];
        for (int i = 0; i < users.length; i++) {
            data[i][0] = users[i];
        }
        return data;
    }

    @Test(dataProvider = "contactUsData")
    public void testContactUsForm(LoadingData.ContactUsUsers user) {
        HomePage homePage = new HomePage(driver, helper);
        SoftAssert softAssert = new SoftAssert();

        // Step 3: Verify Home Page
        softAssert.assertTrue(homePage.isHomePageVisible(), "Home page is not isible!");
        
        // Step 4: Click on Contact Us
        homePage.clickContactUs();
        
        // Step 5: Verify 'GET IN TOUCH' is visible
        ContactUsPage contactUsPage = new ContactUsPage(driver, helper);
        softAssert.assertEquals(contactUsPage.isGetInTouchVisible(), "GET IN TOUCH", "Header text mismatch!");
        
        // Step 6: Enter form details
        contactUsPage.enterName(user.contactUsFormName);
        contactUsPage.enterEmail(user.contactUsFormEmail);
        contactUsPage.enterSubject(user.contactUsFormSubject);
        contactUsPage.enterMessage(user.contactUsFormMsg);

        // Step 7: Upload file
        contactUsPage.uploadFile(user.contactUsFormFilePath);

        // Step 8: Submit form
        contactUsPage.clickSubmit();

        // Step 9: Handle alert
        contactUsPage.acceptAlert();

        // Step 10: Verify success message
        softAssert.assertEquals(
            contactUsPage.getSuccessMessage(),
            "Success! Your details have been submitted successfully.",
            "Success message mismatch!"
        );

        // Step 11: Go back home
        homePage = contactUsPage.clickHomeButton();
        softAssert.assertTrue(homePage.isHomePageVisible(), "Home page not visible after Contact Us submission");
    }
}

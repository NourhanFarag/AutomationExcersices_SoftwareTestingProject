package tests;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 *
 * @author Nourhan Farag
 */
public class LoadingData {
    private static final String TEST_DATA_PATH = "src/test/resources/data/";
    
    // Registration User
    public static class RegisterUser {
        public String signUpName;
        public String signUpEmail;
        public String title;
        public String password;
        public String dobDay;
        public String dobMonth;
        public String dobYear;
        public String firstName;
        public String lastName;
        public String company;
        public String address1;
        public String address2;
        public String country;
        public String state;
        public String city;
        public String zipcode;
        public String mobileNumber;
    }

    // Login User
    public static class LoginUser {
        public String loginEmail;
        public String loginPassword;
        
    }
    
    // contact us form submissions
    public static class ContactUsUsers {
        public String contactUsFormName;
        public String contactUsFormEmail;
        public String contactUsFormSubject;
        public String contactUsFormMsg;
        public String contactUsFormFilePath;
    }

    // payment details
    public static class PaymentData {
        public String nameOnCard;
        public String cardNumber;
        public String cvc;
        public String expiryMonth;
        public String expiryYear;
    }

    // product reviews
    public static class ReviewsData {
        public String reviewerName;
        public String reviewerEmail;
        public String reviewText;
    }

    public static String readFromFile(String fileName, String key) throws FileNotFoundException {
        FileReader reader = new FileReader(TEST_DATA_PATH + fileName);
        JsonElement element = JsonParser.parseReader(reader);
        return element.getAsJsonObject().get(key).getAsString();
    }

    public static RegisterUser[] readRegisterUsers(String fileName) throws FileNotFoundException {
        FileReader reader = new FileReader(TEST_DATA_PATH + fileName);
        RegisterUser[] ListOfCredentials = new Gson().fromJson(reader, RegisterUser[].class);
        return ListOfCredentials;
    }

    public static LoginUser[] readLoginUsers(String fileName) throws FileNotFoundException {
        FileReader reader = new FileReader(TEST_DATA_PATH + fileName);
        LoginUser[] ListOfCredentials = new Gson().fromJson(reader, LoginUser[].class);
        return ListOfCredentials;
    }
    
    public static ContactUsUsers[] readContactUsForms(String fileName) throws FileNotFoundException {
        FileReader reader = new FileReader(TEST_DATA_PATH + fileName);
        ContactUsUsers[] ListOfCredentials = new Gson().fromJson(reader, ContactUsUsers[].class);
        return ListOfCredentials;
    }
    
    public static PaymentData[] readpaymentData(String fileName) throws FileNotFoundException {
        FileReader reader = new FileReader(TEST_DATA_PATH + fileName);
        PaymentData[] ListOfCredentials = new Gson().fromJson(reader, PaymentData[].class);
        return ListOfCredentials;
    }
    
    public static ReviewsData[] readReviewData(String fileName) throws FileNotFoundException {
        FileReader reader = new FileReader(TEST_DATA_PATH + fileName);
        ReviewsData[] ListOfCredentials = new Gson().fromJson(reader, ReviewsData[].class);
        return ListOfCredentials;
    }
}
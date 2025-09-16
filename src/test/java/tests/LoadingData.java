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
        public String title;
        public String name;
        public String email;
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
        public String name;
        public String loginEmail;
        public String loginPassword;
        
    }
    
    public static class InvalidLoginUser {
        public String loginEmail;
        public String loginPassword;
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
    
    public static InvalidLoginUser[] readInvalidLoginUsers(String fileName) throws FileNotFoundException {
        FileReader reader = new FileReader(TEST_DATA_PATH + fileName);
        InvalidLoginUser[] ListOfCredentials = new Gson().fromJson(reader, InvalidLoginUser[].class);
        return ListOfCredentials;
    }
}
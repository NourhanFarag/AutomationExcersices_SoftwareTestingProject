package Pages;

import java.util.Objects;

/**
 * Address model to store user address information.
 * @author Nourhan Farag
 */
public class Address {
    
    // ===============================
    // Fields
    // ===============================
    private final String fullName;
    private final String company;
    private final String address1;
    private final String address2;
    private final String city;
    private final String state;
    private final String country;
    private final String zipcode;
    private final String mobile;

    // ===============================
    // Constructor
    // ===============================
    /**
     * Constructor to initialize all address fields.
     *
     * @param fullName Full name of the person
     * @param company Company name
     * @param address1 Primary address
     * @param address2 Secondary address
     * @param city City
     * @param state State
     * @param country Country
     * @param zipcode Postal/ZIP code
     * @param mobile Mobile number
     */
    public Address(String fullName, String company, String address1, String address2,
                   String city, String state, String country, String zipcode, String mobile) {
        this.fullName = fullName;
        this.company = company;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zipcode = zipcode;
        this.mobile = mobile;
    }

    // ===============================
    // Getters
    // ===============================
    // Accessor methods for each field
    public String getFullName() { return fullName; }
    public String getCompany() { return company; }
    public String getAddress1() { return address1; }
    public String getAddress2() { return address2; }
    public String getCity() { return city; }
    public String getState() { return state; }
    public String getCountry() { return country; }
    public String getZipcode() { return zipcode; }
    public String getMobile() { return mobile; }

    // ===============================
    // String Representation
    // ===============================
    /**
     * Returns a formatted string of the full address information.
     * Useful for logging or displaying addresses in tests.
     * @return 
     */
    @Override
    public String toString() {
        return String.format(
            "Full Name: %s%nCompany: %s%nAddress1: %s%nAddress2: %s%nCity: %s%nState: %s%nCountry: %s%nZipcode: %s%nMobile: %s",
            fullName, company, address1, address2, city, state, country, zipcode, mobile
        );
    }

    // ===============================
    // Equality Methods
    // ===============================
    /**
     * Checks if two Address objects are equal by comparing all key fields.
     * Mobile and fullName are intentionally excluded from equality check
     * because addresses are usually compared based on location, not name/phone.
     * @param o
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        Address address = (Address) o;
        return Objects.equals(company, address.company) &&
               Objects.equals(address1, address.address1) &&
               Objects.equals(address2, address.address2) &&
               Objects.equals(city, address.city) &&
               Objects.equals(state, address.state) &&
               Objects.equals(country, address.country) &&
               Objects.equals(zipcode, address.zipcode);
    }

    /**
     * Generates hash code based on key address fields.
     * Ensures consistency with equals() for collections like HashMap.
     * @return 
     */
    @Override
    public int hashCode() {
        return Objects.hash(company, address1, address2,
                            city, state, country, zipcode);
    }
}

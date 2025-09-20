package Pages;
import java.util.Objects;

public class Address {
    private final String fullName;
    private final String company;
    private final String address1;
    private final String address2;
    private final String city;
    private final String state;
    private final String country;
    private final String zipcode;
    private final String mobile;

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

    public String getFullName() { return fullName; }
    public String getCompany() { return company; }
    public String getAddress1() { return address1; }
    public String getAddress2() { return address2; }
    public String getCity() { return city; }
    public String getState() { return state; }
    public String getCountry() { return country; }
    public String getZipcode() { return zipcode; }
    public String getMobile() { return mobile; }

    @Override
    public String toString() {
        return String.format(
            "Full Name: %s%nCompany: %s%nAddress1: %s%nAddress2: %s%nCity: %s%nState: %s%nCountry: %s%nZipcode: %s%nMobile: %s",
            fullName, company, address1, address2, city, state, country, zipcode, mobile
        );
    }

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

    @Override
    public int hashCode() {
        return Objects.hash(company, address1, address2,
                            city, state, country, zipcode);
    }
}

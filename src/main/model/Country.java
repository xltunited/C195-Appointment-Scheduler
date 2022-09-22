package main.model;

/**
 * Class for the Country object
 */
public class Country {

    private final int countryID;
    private final String country;

    /**
     * Constructor for the Country list
     */
    public Country(int countryID, String country) {
        this.countryID = countryID;
        this.country = country;
    }

    /**
     * @return the countryID
     */
    public int getCountryID() {
        return countryID;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }


    /**
     * @return The string the combobox uses to display the country in the list
     */
    public String toString() {
        return country;
    }
}

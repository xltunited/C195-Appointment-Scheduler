package main.model;

/**
 * Class for the Diivision object
 */
public class Division {

    private final int divisionID;
    private final String division;
    private final int countryID;

    /**
     *  Constructor for the first level division object
    */
    public Division(int divisionID, String division, int countryID) {
        this.divisionID = divisionID;
        this.division = division;
        this.countryID = countryID;
    }

    /**
     * @return the divisionID
     */
    public int getDivisionID() {
        return divisionID;
    }

    /**
     * @return the division
     */
    public String getDivision() {
        return division;
    }

    /**
     * @return the countryID
     */
    public int getCountryID() {
        return countryID;
    }


    /**
     * @return The string the combobox uses to display the division in the list
     */
    public String toString() {
        return division;
    }
}

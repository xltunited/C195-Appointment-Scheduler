package main.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.model.Country;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class for all the queries needed to work with the Country object
 */
public class CountryQueries {

    /**
     @return All countries in the database
     */
    public static ObservableList<Country> getAllCountries() throws SQLException, Exception{

        ObservableList<Country> countries = FXCollections.observableArrayList();

        //SQL statement to get all countries from countries table
        String sqlStatement="SELECT * FROM countries";

        //Make the query to execute
        Query.makeQuery(sqlStatement);

        //Store query result
        ResultSet result = Query.getResult();

        //Check if there are countries

        while(result.next()){

            //Implemented getting all values in separate variables for clarity
            int countryID = result.getInt("Country_ID");
            String countryName = result.getString("Country");

            Country temp = new Country(countryID, countryName);

            countries.add(temp);

        }

        return countries;

    }

    /**
     @return country whose ID matches ID given
     */
    public static Country getCountryByID(int countryID) throws SQLException, Exception{

        //SQL statement to get selected country
        String sqlStatement="SELECT * FROM countries WHERE Country_ID = " + String.valueOf(countryID) + ";";

        //Make the query to execute
        Query.makeQuery(sqlStatement);

        //Store query result
        ResultSet result = Query.getResult();

        //Check if there are countrys
        while(result.next()){

            //Implemented getting all values in separate variables for clarity
            int countryIDRes = result.getInt("Country_ID");
            String countryName = result.getString("Country");

            Country temp = new Country(countryIDRes, countryName);

            return temp;

        }

        return null;

    }
}

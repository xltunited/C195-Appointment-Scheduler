package main.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.model.Country;
import main.model.Division;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class to hold the read functions for the Division table
 */
public class DivisionQueries {

    /**
     @return divisions in the database based on the user choice
     */
    public static ObservableList<Division> getDivisions(Country selectedCountry) throws SQLException, Exception{

        ObservableList<Division> divisions = FXCollections.observableArrayList();

        //SQL statement to get all divisions from divisions table
        String sqlStatement="SELECT * FROM first_level_divisions WHERE Country_ID = '" + String.valueOf(selectedCountry.getCountryID()) + "';";

        //Make the query to execute
        Query.makeQuery(sqlStatement);

        //Store query result
        ResultSet result = Query.getResult();

        //Check if there are divisions

        while(result.next()){

            //Implemented getting all values in separate variables for clarity
            int divisionID = result.getInt("Division_ID");
            String divisionName = result.getString("Division");
            int countryID = result.getInt("Country_ID");

            Division temp = new Division(divisionID, divisionName, countryID);

            divisions.add(temp);

        }

        return divisions;

    }

    /**
     @return division whose ID matches ID given
     */
    public static Division getDivisionByID(int divisionID) throws SQLException, Exception{

        //SQL statement to get selected division
        String sqlStatement="SELECT * FROM first_level_divisions WHERE Division_ID = " + String.valueOf(divisionID) + ";";

        //Make the query to execute
        Query.makeQuery(sqlStatement);

        //Store query result
        ResultSet result = Query.getResult();

        //Check if there are divisions
        while(result.next()){

            //Implemented getting all values in separate variables for clarity
            int divisionIDRes = result.getInt("Division_ID");
            String divisionName = result.getString("Division");
            int countryID = result.getInt("Country_ID");

            Division temp = new Division(divisionIDRes, divisionName, countryID);

            return temp;

        }

        return null;

    }
}

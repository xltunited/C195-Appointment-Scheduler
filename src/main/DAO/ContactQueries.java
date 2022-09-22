package main.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.model.Contact;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class for all the queries needed to work with the Contact object
 */
public class ContactQueries {

    /**
     @return All contacts from the database
     */
    public static ObservableList<Contact> getAllContacts() throws SQLException, Exception{

        ObservableList<Contact> contacts = FXCollections.observableArrayList();

        //Select all contacts
        String sqlStatement="Select * FROM contacts";
        //Make the query to execute
        Query.makeQuery(sqlStatement);

        //Store query result
        ResultSet result = Query.getResult();

        //Create user object to add to list
        while(result.next()){

            //Empty identifier fields for security reasons
            int contactID = result.getInt("Contact_ID");
            String contactName = result.getString("Contact_Name");
            String email = result.getString("Email");

            Contact temp = new Contact(contactID, contactName, email);

            contacts.add(temp);

        }

        return contacts;

    }
}

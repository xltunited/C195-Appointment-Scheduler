package main.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.model.Customer;

import java.sql.ResultSet;

/**
 * Class to hold the creation, read and update functions for the Customer table
 */
public class CustomerQueries {

    /**
     @return All customers in the database
     */
    public static ObservableList<Customer> getAllCustomers() throws Exception{

        ObservableList<Customer> customers = FXCollections.observableArrayList();

        //SQL statement to get all customers from customer table
        String sqlStatement="SELECT * FROM customers";

        //Make the query to execute
        Query.makeQuery(sqlStatement);

        //Store query result
        ResultSet result = Query.getResult();

        //Check if there are customers

        while(result.next()){

            //Implemented getting all values in separate variables for clarity
            int customerID = result.getInt("Customer_ID");
            String customerName = result.getString("Customer_Name");
            String customerAddress = result.getString("Address");
            String customerPostalCode = result.getString("Postal_Code");
            String customerPhone = result.getString("Phone");
            int divisionID = result.getInt("Division_ID");

            Customer temp = new Customer(customerID, customerName, customerAddress, customerPostalCode, customerPhone, divisionID);

            customers.add(temp);

        }

        return customers;

    }

    /**
     * @param newCustomer Customer object that contains the necessary information to add a new customer to the customers table
     */
    public static void addCustomerDatabase(Customer newCustomer){

        String sqlStatement = "INSERT INTO customers (Customer_name, Address, Postal_Code, Phone, Division_ID) VALUES('" + newCustomer.getCustomerName()+ "', '" + newCustomer.getAddress()+ "', '" + newCustomer.getPostalCode()+"', '" + newCustomer.getPhone() + "', " + String.valueOf(newCustomer.getDivisionID()) + " )";

        //Make the query to execute
        Query.makeQuery(sqlStatement);

    }

    /**
     * @param updatedCustomer Customer object that contains the necessary information to update a new customer to the customers table
     */
    public static void updateCustomerDatabase(Customer updatedCustomer) {

        String sqlStatement = "UPDATE customers SET Customer_name =' "+ updatedCustomer.getCustomerName()+"', Address = '" + updatedCustomer.getAddress()+ "', Postal_Code = '" + updatedCustomer.getPostalCode()+ "', Phone = '" + updatedCustomer.getPhone() + "', Division_ID = " + String.valueOf(updatedCustomer.getDivisionID()) +" WHERE Customer_ID = " + String.valueOf(updatedCustomer.getCustomerID()) + ";";

        //Make the query to execute
        Query.makeQuery(sqlStatement);

    }

    /**
     * @param deletedCustomer Customer object that will get deleted
     */
    public static void deleteCustomerDatabase(Customer deletedCustomer)  {

        String sqlStatement1 = "DELETE FROM appointments WHERE Customer_ID =" + String.valueOf(deletedCustomer.getCustomerID());

        //Make the query to execute
        Query.makeQuery(sqlStatement1);

        String sqlStatement2 = "DELETE FROM customers WHERE Customer_ID =" + String.valueOf(deletedCustomer.getCustomerID());

        //Make the query to execute
        Query.makeQuery(sqlStatement2);
    }
}

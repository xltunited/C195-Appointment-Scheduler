package main.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import main.model.Country;
import main.model.Customer;
import main.model.Division;

import java.net.URL;
import java.util.ResourceBundle;

import static main.DAO.CountryQueries.*;
import static main.DAO.CustomerQueries.updateCustomerDatabase;
import static main.DAO.DivisionQueries.*;
import static main.controller.Login.applicationViewLoad;

/**
 * Controller for the Update Customer View
 */
public class UpdateCustomer implements Initializable {

    private ObservableList<Country> countries = FXCollections.observableArrayList();
    private ObservableList<Division> divisions = FXCollections.observableArrayList();

    @FXML
    private TextField customerIDField, customerNameField, addressField, postalCodeField, phoneField;

    @FXML private ComboBox<Country> countryBox;
    @FXML private ComboBox<Division> divisionBox;

    @FXML private Text validationText;

    /**
     * Function to initialize country data and customer ID
     * @param url resource location
     * @param rb resource bundle
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            countries = getAllCountries();
        } catch (Exception e) {
            e.printStackTrace();
        }

        countryBox.setItems(countries);

    }

    /**
     * Function that loads the Customer object to be updated
     * And the Country and division boxes for selection
     * @param toBeUpdatedCustomer Customer object received from the Application controller to be updated
     * @throws Exception SQL queries being fired
     */
    public void loadCustomer(Customer toBeUpdatedCustomer) throws Exception {

        customerIDField.setText(String.valueOf(toBeUpdatedCustomer.getCustomerID()));
        customerNameField.setText(toBeUpdatedCustomer.getCustomerName());
        addressField.setText(toBeUpdatedCustomer.getAddress());
        postalCodeField.setText(toBeUpdatedCustomer.getPostalCode());
        phoneField.setText(toBeUpdatedCustomer.getPhone());

        Division customerDivision = getDivisionByID(toBeUpdatedCustomer.getDivisionID());

        divisionBox.setValue(customerDivision);

        Country customerCountry = getCountryByID(customerDivision.getCountryID());

        divisions = getDivisions(customerCountry);

        divisionBox.setItems(divisions);

        countryBox.setValue(customerCountry);

    }

    /**
     * Returns the user to the main Application page
     * @param event event information
     */
    @FXML
    public void cancelUpdateCustomer(ActionEvent event){

        applicationViewLoad(event, "");
    }

    /**
     * Function that runs when a country is selected to update the division's comboBox
     * @param event event information
     */
    @FXML
    void onSelectCountry(ActionEvent event) {

        Country selectedCountry = countryBox.getValue();

        try {
            divisions = getDivisions(selectedCountry);
        } catch (Exception e) {
            e.printStackTrace();
        }

        divisionBox.setItems(divisions);
        divisionBox.setValue(divisionBox.getItems().get(0));

    }

    /**
     * Function that creates a customer object then passes it onto the database query file to insert into table
     * @param event event information
     * @throws Exception When date can't be parsed
     */
    @FXML
    public void updateCustomer(ActionEvent event) throws Exception {

        validationText.setText("");

        String validations = "";

        if(customerNameField.getText() == ""){

            validations = validations + "-Customer Name cannot be empty.\n";

        }

        if(addressField.getText() == ""){

            validations = validations + "-Address cannot be empty.\n";

        }

        if(postalCodeField.getText() == ""){

            validations = validations + "-Postal Code cannot be empty.\n";

        }

        if(phoneField.getText() == ""){

            validations = validations + "-Phone cannot be empty.\n";

        }

        if(countryBox.getValue() == null){

            validations = validations + "-Select a country to then select a State/Province.\n";

        }

        if(divisionBox.getValue() == null){

            validations = validations + "-Select State/Province to continue.\n";

        }

        if(validations.length() == 0){

            //Customer object to be added
            Customer updatedCustomer = new Customer(Integer.parseInt(customerIDField.getText()), customerNameField.getText(), addressField.getText(), postalCodeField.getText(), phoneField.getText(), divisionBox.getValue().getDivisionID());

            updateCustomerDatabase(updatedCustomer);

            applicationViewLoad(event, "Customer has been updated");

        } else {

            validationText.setText(validations);

        }

    }

}

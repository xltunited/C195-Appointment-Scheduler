package main.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.model.Appointment;
import main.model.Contact;

import java.net.URL;
import java.util.ResourceBundle;

import static main.DAO.AppointmentQueries.getAppByContact;
import static main.DAO.ContactQueries.getAllContacts;
import static main.controller.Login.applicationViewLoad;

/**
 * Controller for the Contact Report class
 */
public class ContactReport implements Initializable {

    ObservableList<Appointment> appointments = FXCollections.observableArrayList();

    ObservableList<Contact> contacts = FXCollections.observableArrayList();
    @FXML ComboBox<Contact> contactComboBox;

    //All Appointments table setup
    @FXML
    private TableView<Appointment> appointmentsTable;

    @FXML private TableColumn<Appointment, Integer> appointmentIDColumn;
    @FXML private TableColumn<Appointment, String> titleColumn;
    @FXML private TableColumn<Appointment, String> typeColumn;
    @FXML private TableColumn<Appointment, String> descriptionColumn;
    @FXML private TableColumn<Appointment, String> startColumn;
    @FXML private TableColumn<Appointment, String> endColumn;
    @FXML private TableColumn<Appointment, Integer> customerIDColumn;

    /**
     * Initialize the combo box with total contacts, and set up the appointment table
     * @param url resource location
     * @param rb resource bundle
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //Set contacts in the combo box
        try {
            contacts = getAllContacts();
        } catch (Exception e) {
            e.printStackTrace();
        }

        contactComboBox.setItems(contacts);

        //Table setup
        appointmentIDColumn.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("appointmentID"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("title"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("type"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("description"));
        startColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("start"));
        endColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("end"));
        customerIDColumn.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("customerID"));

    }

    /**
     * Function to return to the main application screen
     * @param event event information
     */
    @FXML
    public void returnToMain(ActionEvent event){

        applicationViewLoad(event, "");

    }

    /**
     * Function that runs when a contact is selected on the combo box
     * @param event event information
     * @throws Exception SQL exception when database query executed
     */
    @FXML
    void onSelectContact(ActionEvent event) throws Exception {

        appointments = getAppByContact(contactComboBox.getValue().getContactID());

        appointmentsTable.setItems(appointments);

    }

}

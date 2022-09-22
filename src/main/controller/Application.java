package main.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import main.model.Appointment;
import main.model.Customer;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;

import static main.DAO.AppointmentQueries.*;
import static main.DAO.CustomerQueries.*;
import static main.controller.Login.applicationViewLoad;
import static main.controller.Login.loginUser;

/**
 * Controller for the main application
 */
public class Application implements Initializable {

    //Lists to hold all customer and appointment data
    public static ObservableList<Customer> customerList = FXCollections.observableArrayList();
    public static ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();
    public static ObservableList<Appointment> monthlyAppointmentList = FXCollections.observableArrayList();
    public static ObservableList<Appointment> weeklyAppointmentList = FXCollections.observableArrayList();

    //Stage for PopUp
    private final Stage pBox = new Stage();

    //Labels for custom messages
    @FXML private Label deleteConfirmation;

    //Customer table setup
    @FXML
    public TableView<Customer> allCustomersTable;

    @FXML private TableColumn<Customer, Integer> customerIDColumn;
    @FXML private TableColumn<Customer, String> customerNameColumn;
    @FXML private TableColumn<Customer, String> addressColumn;
    @FXML private TableColumn<Customer, String> postalCodeColumn;
    @FXML private TableColumn<Customer, String> phoneColumn;
    @FXML private TableColumn<Customer, Integer> divisionIDColumn;

    //All Appointments table setup
    @FXML
    private TableView<Appointment> allAppointmentsTable;

    @FXML private TableColumn<Appointment, Integer> appointmentIDColumn;
    @FXML private TableColumn<Appointment, String> titleColumn;
    @FXML private TableColumn<Appointment, String> descriptionColumn;
    @FXML private TableColumn<Appointment, String> locationColumn;
    @FXML private TableColumn<Appointment, String> typeColumn;
    @FXML private TableColumn<Appointment, String> startColumn;
    @FXML private TableColumn<Appointment, String> endColumn;
    @FXML private TableColumn<Appointment, Integer> customerIDColumnAP;
    @FXML private TableColumn<Appointment, Integer> userIDColumn;
    @FXML private TableColumn<Appointment, Integer> contactIDColumn;

    //Monthly Appointments table setup
    @FXML
    private TableView<Appointment> monthlyAppointmentsTable;

    @FXML private TableColumn<Appointment, Integer> appointmentIDColumnMP;
    @FXML private TableColumn<Appointment, String> titleColumnMP;
    @FXML private TableColumn<Appointment, String> descriptionColumnMP;
    @FXML private TableColumn<Appointment, String> locationColumnMP;
    @FXML private TableColumn<Appointment, String> typeColumnMP;
    @FXML private TableColumn<Appointment, String> startColumnMP;
    @FXML private TableColumn<Appointment, String> endColumnMP;
    @FXML private TableColumn<Appointment, Integer> customerIDColumnMP;
    @FXML private TableColumn<Appointment, Integer> userIDColumnMP;
    @FXML private TableColumn<Appointment, Integer> contactIDColumnMP;

    //Weekly Appointments table setup
    @FXML
    private TableView<Appointment> weeklyAppointmentsTable;

    @FXML private TableColumn<Appointment, Integer> appointmentIDColumnWP;
    @FXML private TableColumn<Appointment, String> titleColumnWP;
    @FXML private TableColumn<Appointment, String> descriptionColumnWP;
    @FXML private TableColumn<Appointment, String> locationColumnWP;
    @FXML private TableColumn<Appointment, String> typeColumnWP;
    @FXML private TableColumn<Appointment, String> startColumnWP;
    @FXML private TableColumn<Appointment, String> endColumnWP;
    @FXML private TableColumn<Appointment, Integer> customerIDColumnWP;
    @FXML private TableColumn<Appointment, Integer> userIDColumnWP;
    @FXML private TableColumn<Appointment, Integer> contactIDColumnWP;

    /**
     * Initialize the Application view with table content
     * @param url resource location
     * @param rb resource bundle
     */
    public void initialize(URL url, ResourceBundle rb) {

        //Initialize values for customer table
        //Handle SQL Exceptions
        try {
            customerList = getAllCustomers();
        } catch (Exception e) {
            e.printStackTrace();
        }

        customerIDColumn.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("customerID"));
        customerNameColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerName"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("address"));
        postalCodeColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("postalCode"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("phone"));
        divisionIDColumn.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("divisionID"));

        allCustomersTable.setItems(customerList);

        //Initialize values for all appointments' table
        //Handle SQL Exceptions
        try {
            appointmentList = getAllAppointments();
        } catch (Exception e) {
            e.printStackTrace();
        }

        appointmentIDColumn.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("appointmentID"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("title"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("description"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("location"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("type"));
        startColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("start"));
        endColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("end"));
        customerIDColumnAP.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("customerID"));
        userIDColumn.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("userID"));
        contactIDColumn.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("contactID"));

        allAppointmentsTable.setItems(appointmentList);

        //Initialize values for monthly appointments' tables
        //Handle SQL Exceptions
        try {
            monthlyAppointmentList = getMonthlyAppointments();
        } catch (Exception e) {
            e.printStackTrace();
        }

        appointmentIDColumnMP.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("appointmentID"));
        titleColumnMP.setCellValueFactory(new PropertyValueFactory<Appointment, String>("title"));
        descriptionColumnMP.setCellValueFactory(new PropertyValueFactory<Appointment, String>("description"));
        locationColumnMP.setCellValueFactory(new PropertyValueFactory<Appointment, String>("location"));
        typeColumnMP.setCellValueFactory(new PropertyValueFactory<Appointment, String>("type"));
        startColumnMP.setCellValueFactory(new PropertyValueFactory<Appointment, String>("start"));
        endColumnMP.setCellValueFactory(new PropertyValueFactory<Appointment, String>("end"));
        customerIDColumnMP.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("customerID"));
        userIDColumnMP.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("userID"));
        contactIDColumnMP.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("contactID"));

        monthlyAppointmentsTable.setItems(monthlyAppointmentList);

        //Initialize values for monthly appointments' tables
        //Handle SQL Exceptions
        try {
            weeklyAppointmentList = getWeeklyAppointments();
        } catch (Exception e) {
            e.printStackTrace();
        }

        appointmentIDColumnWP.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("appointmentID"));
        titleColumnWP.setCellValueFactory(new PropertyValueFactory<Appointment, String>("title"));
        descriptionColumnWP.setCellValueFactory(new PropertyValueFactory<Appointment, String>("description"));
        locationColumnWP.setCellValueFactory(new PropertyValueFactory<Appointment, String>("location"));
        typeColumnWP.setCellValueFactory(new PropertyValueFactory<Appointment, String>("type"));
        startColumnWP.setCellValueFactory(new PropertyValueFactory<Appointment, String>("start"));
        endColumnWP.setCellValueFactory(new PropertyValueFactory<Appointment, String>("end"));
        customerIDColumnWP.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("customerID"));
        userIDColumnWP.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("userID"));
        contactIDColumnWP.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("contactID"));

        weeklyAppointmentsTable.setItems(weeklyAppointmentList);

    }

    /**
     * Function to load Delete/Update Messages
     * @param message Text to add to application main page
     */
    public void loadApplication(String message){

        deleteConfirmation.setText(message);

    }

    /**
     * Function to load upcoming appointments
     */
    public void loadUpcomingApp() {

        AtomicReference<String> upcoming = new AtomicReference<>("");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


        appointmentList.forEach(appointment -> {

            LocalDateTime appStartTime= LocalDateTime.parse(appointment.getStart(), formatter);

            LocalDateTime timeNow = LocalDateTime.now();

            if(appointment.getUserID() == loginUser.getUserID() && Duration.between(appStartTime, timeNow).toMinutes() >= -15 && Duration.between(appStartTime, timeNow).toMinutes() <= 0){

                upcoming.set(upcoming.get() + "Upcoming appointment with Appointment ID = " + appointment.getAppointmentID() + " at " + appointment.getStart() + "\n");

            }

        });

        if(upcoming.get().length() == 0){

            upcoming.set("No upcoming appointments.");

        }

        deleteConfirmation.setText(upcoming.get());

    }

    /**
     * Function to go to the Add Customer FXML Scene
     * @param event event information
     * @throws IOException When file can't be found
     */
    @FXML
    public void addCustomerSceneChange(ActionEvent event) throws IOException {

        Parent addCustomerFXML = FXMLLoader.load(getClass().getResource("../view/AddCustomer.fxml"));

        Scene addCustomerScene = new Scene(addCustomerFXML);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(addCustomerScene);

        window.show();

    }

    /**
     * Function to go to the Update Customer FXML Scene
     * @param event event information
     */
    @FXML
    public void updateCustomerSceneChange(ActionEvent event) {

        try {

            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(getClass().getResource("../view/UpdateCustomer.fxml"));

            Parent updateCustomerFXML = loader.load();

            Scene updateCustomerScene = new Scene(updateCustomerFXML);

            UpdateCustomer controller = loader.getController();

            controller.loadCustomer(allCustomersTable.getSelectionModel().getSelectedItem());

            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

            window.setScene(updateCustomerScene);

            window.show();

        } catch (Exception e) {

            System.out.println(e);

        }

    }

    /**
     * Function to call when the delete button is pressed.The lambda function here adds a function to the created javafx button when clicked, this will run the function that confirms the delete, and passes the event object in order to be able to reload the main screen with an updated list of customers
     * @lambda The lambda function here adds a function to the created javafx button when clicked, this will run the function that confirms the delete, and passes the event object in order to be able to reload the main screen with an updated list of customers
     * @param event event information
     */
    @FXML
    public void deleteCustomer(ActionEvent event) {

        Customer selectedCustomer = allCustomersTable.getSelectionModel().getSelectedItem();

        if(selectedCustomer == null){

            return;

        }

        //Buttons and Label for Popup
        Button yes = new Button("Ok");
        yes.setStyle("-fx-margin-right: 40px;");

        Button no = new Button("Cancel");

        Label ays = new Label("Do you want to delete this customer?");
        ays.setMinWidth(450);
        ays.setMinHeight(50);
        ays.setStyle("-fx-font: 24 arial;");

        //Popup for removing an item
        FlowPane dPlane = new FlowPane();

        dPlane.getChildren().addAll();
        dPlane.setPadding(new Insets(20));
        dPlane.setVgap(20);
        dPlane.setStyle("-fx-background: white;");

        //Action to take when canceling delete
        no.setOnAction(e -> pBox.close() );

        //Action to take when confirming delete
        yes.setOnAction(e -> {
            try {
                confirmCustomerDelete(selectedCustomer, event);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        dPlane.getChildren().addAll(ays,yes,no);

        Scene popup = new Scene(dPlane, 450, 200);

        pBox.setScene(popup);

        pBox.showAndWait();

    }

    /**
     * @param selected customer selected to be deleted
     * @param event object neede to be able to reference the scene
     */
    public void confirmCustomerDelete( Customer selected, ActionEvent event) {

        deleteCustomerDatabase(selected);

        String message = "Customer Has Been Deleted!";

        applicationViewLoad(event, message);

        pBox.close();

    }

    /**
     * Function to change to the Add Appointment View
     * @param event event information
     * @throws IOException if file can't be found
     */
    @FXML
    public void addAppointmentSceneChange(ActionEvent event) throws IOException {

        Parent addAppointmentFXML = FXMLLoader.load(getClass().getResource("../view/AddAppointment.fxml"));

        Scene addAppointmentScene = new Scene(addAppointmentFXML);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(addAppointmentScene);

        window.show();

    }

    /**
     * Function to change to the Update Appointment View
     * @param event event information
     */
    @FXML
    public void updateAppointmentSceneChange(ActionEvent event) {

        try {

            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(getClass().getResource("../view/UpdateAppointment.fxml"));

            Parent updateAppointmentFXML = loader.load();

            Scene updateAppointmentScene = new Scene(updateAppointmentFXML);

            UpdateAppointment controller = loader.getController();

            controller.loadAppointment(allAppointmentsTable.getSelectionModel().getSelectedItem());

            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

            window.setScene(updateAppointmentScene);

            window.show();

        } catch (Exception e) {

            System.out.println(e);

        }

    }

    /**
     * Function that fires when a user has clicked the delete button under Appointments
     * @param event event information
     */
    @FXML
    public void deleteAppointment(ActionEvent event) {

        Appointment selectedAppointment = allAppointmentsTable.getSelectionModel().getSelectedItem();

        if(selectedAppointment == null){

            return;

        }

        //Buttons and Label for Popup
        Button yes = new Button("Ok");
        yes.setStyle("-fx-margin-right: 40px;");

        Button no = new Button("Cancel");

        Label ays = new Label("Do you want to delete this appointment?");
        ays.setMinWidth(450);
        ays.setMinHeight(50);
        ays.setStyle("-fx-font: 24 arial;");

        //Popup for removing an item
        FlowPane dPlane = new FlowPane();

        dPlane.getChildren().addAll();
        dPlane.setPadding(new Insets(20));
        dPlane.setVgap(20);
        dPlane.setStyle("-fx-background: white;");

        //Action to take when canceling delete
        no.setOnAction(e -> pBox.close() );

        //Action to take when confirming delete
        yes.setOnAction(e -> {
            try {
                confirmAppointmentDelete(selectedAppointment, event);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        dPlane.getChildren().addAll(ays,yes,no);

        Scene popup = new Scene(dPlane, 450, 200);

        pBox.setScene(popup);

        pBox.showAndWait();

    }

    /**
     * @param selected customer selected to be deleted
     * @param event object neede to be able to reference the scene
     */
    public void confirmAppointmentDelete( Appointment selected, ActionEvent event) throws Exception {

        String message = "Appointment with ID =" + selected.getAppointmentID() +" with type of " + selected.getType() +" Has Been Deleted!";

        deleteAppointmentDatabase(selected);

        applicationViewLoad(event, message);

        pBox.close();

    }

    /**
     * Function to change to the Appointments Report Scene
     * @param event event information
     * @throws IOException if file can't be found
     */
    @FXML
    public void appointmentsReportView(ActionEvent event) throws IOException {

        Parent appointmentsRecordFXML = FXMLLoader.load(getClass().getResource("../view/AppointmentReport.fxml"));

        Scene appointmentsRecordScene = new Scene(appointmentsRecordFXML);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(appointmentsRecordScene);

        window.show();

    }

    /**
     * Function to change to the Contact Report Scene
     * @param event event information
     * @throws IOException if file can't be found
     */
    @FXML
    public void contactReportSceneChange(ActionEvent event) throws IOException {

        Parent contactReportFXML = FXMLLoader.load(getClass().getResource("../view/ContactReport.fxml"));

        Scene contactReportScene = new Scene(contactReportFXML);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(contactReportScene);

        window.show();

    }

    /**
     * Function to change to the Customer Report Scene
     * @param event event information
     * @throws IOException if file can't be found
     */
    @FXML
    public void customerReportSceneChange(ActionEvent event) throws IOException {

        Parent customerReportFXML = FXMLLoader.load(getClass().getResource("../view/CustomerReport.fxml"));

        Scene customerReportScene = new Scene(customerReportFXML);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(customerReportScene);

        window.show();

    }

}

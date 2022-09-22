package main.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import main.model.*;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;

import static main.DAO.AppointmentQueries.addAppointmentDatabase;
import static main.DAO.ContactQueries.getAllContacts;
import static main.DAO.UserQueries.getAllUsers;
import static main.Utilities.TimeConverter.*;
import static main.controller.Application.appointmentList;
import static main.controller.Application.customerList;
import static main.controller.Login.applicationViewLoad;

/**
 * Controller for the Add Appointment View
 */
public class AddAppointment implements Initializable {

    private ObservableList<Contact> contacts;
    private ObservableList<User> users;

    @FXML
    private Text validationText;

    @FXML private TextField appointmentIDField, titleField, descriptionField, locationField, typeField, startTimeField, endTimeField;

    @FXML private ComboBox<Contact> contactBox;
    @FXML private ComboBox<Customer> customerBox;
    @FXML private ComboBox<User> userBox;

    @FXML private DatePicker dateBox;

    /**
     * Function to initialize contact, customer and user comboboxes
     * @param url resource location
     * @param rb resource bundle
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        if (appointmentList.size() > 0){
            appointmentIDField.setText(String.valueOf(appointmentList.get(appointmentList.size() - 1).getAppointmentID() +1));
        } else {
            appointmentIDField.setText("0");
        }

        try {
            contacts = getAllContacts();
            users = getAllUsers();
        } catch (Exception e) {
            e.printStackTrace();
        }

        customerBox.setItems(customerList);
        contactBox.setItems(contacts);
        userBox.setItems(users);

        activateAutoSubmit(dateBox);

    }

    /**
     * Function that creates a customer object then passes it onto the database query file to insert into table
     * @param event event information
     * @throws DateTimeParseException Triggered when date can't be parsed
     * @throws Exception General Exception
     */
    @FXML
    public void addAppointment(ActionEvent event) throws DateTimeParseException, Exception {

        validationText.setText("");

        String validations = "";

        if (Objects.equals(titleField.getText(), "")) {

            validations = validations + "-Title cannot be empty.\n";

        }

        if (Objects.equals(descriptionField.getText(), "")) {

            validations = validations + "-Description cannot be empty.\n";

        }

        if (Objects.equals(locationField.getText(), "")) {

            validations = validations + "-Location cannot be empty.\n";

        }

        if (Objects.equals(typeField.getText(), "")) {

            validations = validations + "-Type cannot be empty.\n";

        }

        if(dateBox.getValue()== null){

            validations = validations + "-Please select a date.\n";

        } else {

            java.sql.Date datePicked = java.sql.Date.valueOf(dateBox.getValue());

            java.sql.Date today = new java.sql.Date(Calendar.getInstance().getTime().getTime());

            LocalDate datePickedDay = datePicked.toLocalDate();

            LocalDate todayDay = today.toLocalDate();

            if(datePicked.after(today) || todayDay.compareTo(datePickedDay) == 0){

                System.out.println(dateBox.getValue());

            } else{

                validations = validations + "-Date cannot be before today.\n";
            }

        }

        //Check if new appointment conflicts with other appointments
        AtomicReference<Boolean> incorrectTime = new AtomicReference<>(false);


        if (Objects.equals(startTimeField.getText(), "")) {

            validations = validations + "-Start Time cannot be empty.\n";
            incorrectTime.set(true);


        } else {

            DateTimeFormatter strictTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");

            try {

                LocalTime.parse(startTimeField.getText(), strictTimeFormatter);

            } catch (DateTimeParseException | NullPointerException e) {

                validations = validations + "-Invalid Start Time format.\n";
                incorrectTime.set(true);

            }

            if(incorrectTime.get() == false){

                LocalDateTime startFullEST = convertToEST(LocalDateTime.of(java.sql.Date.valueOf(dateBox.getValue()).toLocalDate(), LocalTime.parse(startTimeField.getText())));

                if(startFullEST.getHour() < 8 || startFullEST.getHour() >= 22){

                    validations = validations + "-Start Time outside of business hours.\n";

                }

            }

        }

        if (Objects.equals(endTimeField.getText(), "")) {

            validations = validations + "-End Time cannot be empty.\n";
            incorrectTime.set(true);


        } else {

            DateTimeFormatter strictTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");

            try {
                LocalTime.parse(endTimeField.getText(), strictTimeFormatter);

            } catch (DateTimeParseException | NullPointerException e) {

                validations = validations + "-Invalid End Time format.\n";
                incorrectTime.set(true);

            }

            if(incorrectTime.get() == false) {

                LocalDateTime endFullEST = convertToEST(LocalDateTime.of(java.sql.Date.valueOf(dateBox.getValue()).toLocalDate(), LocalTime.parse(endTimeField.getText())));

                if (endFullEST.getHour() < 8 || endFullEST.getHour() >= 22) {

                    validations = validations + "-End Time outside of business hours.\n";

                }

            }

        }

        if (customerBox.getValue() == null) {

            validations = validations + "-Please select a Customer ID from the combo box.\n";

        }

        if (userBox.getValue() == null) {

            validations = validations + "-Please select a User ID from the combo box.\n";

        }

        if (contactBox.getValue() == null) {

            validations = validations + "-Please select a Contact from the combo box.\n";

        }

        if(!incorrectTime.get() && dateBox.getValue() != null) {


            LocalDateTime startNew = LocalDateTime.of(java.sql.Date.valueOf(dateBox.getValue()).toLocalDate(), LocalTime.parse(startTimeField.getText()));

            LocalDateTime endNew = LocalDateTime.of(java.sql.Date.valueOf(dateBox.getValue()).toLocalDate(), LocalTime.parse(endTimeField.getText()));

            if (startNew.isAfter(endNew)){

                validations = validations + "-Start time cannot be before End Time.\n";

            }

            if (startNew.equals(endNew)){

                validations = validations + "-Start time cannot be the same as End Time.\n";

            }

        }

        if(!incorrectTime.get() && customerBox.getValue() != null && contactBox.getValue() != null) {

            //Check if new appointment conflicts with other appointments
            AtomicReference<Boolean> conflicting = new AtomicReference<>(false);

            appointmentList.forEach((appointment -> {

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

                LocalDateTime startNew = LocalDateTime.of(java.sql.Date.valueOf(dateBox.getValue()).toLocalDate(), LocalTime.parse(startTimeField.getText()));

                LocalDateTime endNew = LocalDateTime.of(java.sql.Date.valueOf(dateBox.getValue()).toLocalDate(), LocalTime.parse(endTimeField.getText()));

                LocalDateTime appStart = LocalDateTime.parse(appointment.getStart(), formatter);

                LocalDateTime appEnd = LocalDateTime.parse(appointment.getEnd(), formatter);

                if(customerBox.getValue().getCustomerID() == appointment.getCustomerID() ){

                    if (startNew.isAfter(appStart) && startNew.isBefore(appEnd) || endNew.isAfter(appStart) && endNew.isBefore(appEnd) || startNew.getYear() == appStart.getYear() && startNew.getMonthValue() == appStart.getMonthValue() && startNew.getDayOfMonth() == appStart.getDayOfMonth() && startNew.getHour() == appStart.getHour() && startNew.getMinute() == appStart.getMinute()) {

                        conflicting.set(true);

                    }

                }

            }));

            if(conflicting.get() == true){

                validations = validations + "Appointment Times conflict for this Customer/Contact.\n";

            }

        }

        if (validations.length() == 0) {

            //Appointment object to be added
            Appointment newAppointment = new Appointment(Integer.parseInt(appointmentIDField.getText()), titleField.getText(), descriptionField.getText(), locationField.getText(), typeField.getText(), dateBox.getValue() + " " + startTimeField.getText(), dateBox.getValue() + " " + endTimeField.getText(), customerBox.getValue().getCustomerID(), userBox.getValue().getUserID(), contactBox.getValue().getContactID());

            addAppointmentDatabase(newAppointment);

            applicationViewLoad(event, "Appointment has been added");

        } else {

            validationText.setText(validations);

        }

    }

    /**
     * Returns the user to the main Application page
     * @param event event information
     */
    @FXML
    public void cancelAddAppointment(ActionEvent event) {

        applicationViewLoad(event, "");
    }

    /**
     * This will automatically submit the input on lost of focus, if it's typed but not submitted (with ENTER) or clicked in the calendar in the date picker. This Lambda function will fire when the listener activates, if the user puts a date manually and not through the calendar, this function will help capture it.
     * @lambda This Lambda function will fire when the listener activates, if the user puts a date manually and not through the calendar, this function will help capture it
     * @param picker to apply
     */
    public void activateAutoSubmit(DatePicker picker) {

        // On Focus Leave Listener
        picker.focusedProperty().addListener((observable, oldValue, newValue) -> {

            if (!newValue) {

                try {

                    // Set typed text to DatePicker value
                    picker.setValue(picker.getConverter().fromString(picker.getEditor().getText()));

                } catch (Exception e) {

                    // For wrong input return old value
                    picker.getEditor().setText(picker.getConverter().toString(picker.getValue()));

                }

            }

        });

    }

}

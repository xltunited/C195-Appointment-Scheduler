package main.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import static main.DAO.AppointmentQueries.*;
import static main.controller.Login.applicationViewLoad;

/**
 * Controller for the Appointment Report View
 */
public class AppointmentReport implements Initializable {

    //Set Values in the month's combox box
    ObservableList<Integer> months = FXCollections.observableArrayList(0,1,2,3,4,5,6,7,8,9,10,11,12);
    @FXML ComboBox<Integer> monthComboBox;

    @FXML TextField typeField;
    @FXML Label setTotal;

    /**
     * Function to return to the main screen
     * @param event event information
     */
    @FXML
    public void returnToMain(ActionEvent event){

        applicationViewLoad(event, "");

    }

    /**
     * Initializing the values in the month combobox
     * @param url resource location
     * @param rb resource bundle
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        monthComboBox.setItems(months);
        monthComboBox.setValue(0);

    }

    /**
     * Function that runs when the submit button is clicked, writes to screen appointment counts
     * @param event event information
     * @throws SQLException when results are not found
     */
    @FXML
    public void submit(ActionEvent event) throws SQLException {

        setTotal.setText("");

        int selectedMonth = monthComboBox.getValue();
        String type = typeField.getText();

        if(selectedMonth == 0 && type.length() != 0){

            setTotal.setText(countAppType(type));

        } else if(selectedMonth != 0 && type.length() == 0) {

            setTotal.setText(countAppMonth(LocalDateTime.now().withMonth(selectedMonth)));

        } if(selectedMonth != 0 && type.length() != 0){

            setTotal.setText(countAppMonthType(LocalDateTime.now().withMonth(selectedMonth), type));

        } else if( selectedMonth == 0 && type.length() == 0){

            setTotal.setText("Nothing to count.");

        }

    }
}

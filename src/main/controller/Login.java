package main.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.Utilities.PopupBox;
import main.model.User;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;

import static main.DAO.UserQueries.authorizeUser;
import static main.Utilities.TimeConverter.convertToUTC;

/**
 * Controller for the Login view
 */
public class Login implements Initializable {

    //Holds the user authorized for the application
    public static User loginUser;

    //Get the user's locale for language settings
    public static Locale userLocale = Locale.getDefault();

    //Get login form translations depending on user's language setting
    public static ResourceBundle labels = ResourceBundle.getBundle("LoginLabels", userLocale);

    //Get the user's system zone to display
    ZoneId zone = ZoneId.systemDefault();

    //Labels to be translated to English or French depending on user's locale
    @FXML private Label usernameLabel, passwordLabel, zoneLabel;
    @FXML private TextField usernameField, passwordField;
    @FXML private Button loginButton;

    /**
     * Function to initialize translated values
     * @param url resource location
     * @param rb resource bundle
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //Setting translations
        usernameLabel.setText(labels.getString("label1"));
        passwordLabel.setText(labels.getString("label2"));

        loginButton.setText(labels.getString("label3"));

        //Display Zone in login form
        zoneLabel.setText(zone.getId());

    }

    /**
     * Function to call when the login button is pressed
     * This authenticates the user
     * Writes to file "login_activity.txt" all attempts made
     * @param event event information
     * @throws Exception IO exception for writing to file
     */
    @FXML public void login (ActionEvent event) throws Exception {

        //Create file writer object
        FileWriter fWriter = new FileWriter("login_activity.txt", true);
        PrintWriter outputFile = new PrintWriter(fWriter);

        //Create User object based on login form
        loginUser = new User(usernameField.getText(), passwordField.getText());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        Boolean authorized = authorizeUser(loginUser);

        if(authorized) {

            applicationUpcomingAppointments(event);


            outputFile.println("User " + usernameField.getText() + " successfully logged in at " + convertToUTC(LocalDateTime.now()).format(formatter) + "UTC");



        } else {

            PopupBox errorMessage = new PopupBox("login");


            outputFile.println("User " + usernameField.getText() + " gave invalid log-in in at " + convertToUTC(LocalDateTime.now()).format(formatter) + "UTC");


            errorMessage.getPopupStage().showAndWait();


        }

        outputFile.close();

    }

    /**
     * Function to call when a different view controlled with a different controller wants to return to the main screen
     * @param event event information
     * @param message string to pass to the main application
     */
    public static void applicationViewLoad(ActionEvent event, String message){

        try {

            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(Login.class.getResource("../view/Application.fxml"));

            Parent applicationFXML = loader.load();

            Scene applicationScene = new Scene(applicationFXML);

            Application controller = loader.getController();

            controller.loadApplication(message);

            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

            window.setScene(applicationScene);

            window.show();

        } catch (Exception e) {

            System.out.println(e);

        }

    }

    /**
     * Function that checks whether there are upcoming appointments in the next 15 minutes of a user's login
     * @param event event information
     */
    public static void applicationUpcomingAppointments(ActionEvent event){

        try {

            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(Login.class.getResource("../view/Application.fxml"));

            Parent applicationFXML = loader.load();

            Scene applicationScene = new Scene(applicationFXML);

            Application controller = loader.getController();

            controller.loadUpcomingApp();

            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

            window.setScene(applicationScene);

            window.show();

        } catch (Exception e) {

            System.out.println(e);

        }

    }

}

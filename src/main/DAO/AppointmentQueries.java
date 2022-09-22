package main.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.Utilities.TimeConverter;
import main.model.Appointment;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static main.Utilities.TimeConverter.convertToUTC;

/**
 * Class for all the queries needed to work with the Appointment object
 */
public class AppointmentQueries {

    /**
     @return All appointments in the database
     */
    public static ObservableList<Appointment> getAllAppointments() throws Exception{

        ObservableList<Appointment> appointments = FXCollections.observableArrayList();

        //SQL statement to get all appointments from customer table
        String sqlStatement="SELECT * FROM appointments";

        //Make the query to execute
        Query.makeQuery(sqlStatement);

        //Store query result
        ResultSet result = Query.getResult();

        //Check if there are appointments
        if (!result.isBeforeFirst() ) {
            return appointments;
        } else {

            while(result.next()){

                //Implemented getting all values in separate variables for clarity
                int appointmentID = result.getInt("Appointment_ID");
                String title = result.getString("Title");
                String description = result.getString("Description");
                String location = result.getString("Location");
                String type = result.getString("Type");

                //Convert Start times to the System default time zones
                Date startDate = result.getDate("Start");
                Time startTime = result.getTime("Start");

                String convertedStart = TimeConverter.convertDateTimeToDefault(startDate, startTime);

                //Convert End times to the System default time zones
                Date endDate = result.getDate("End");
                Time endTime = result.getTime("End");

                String convertedEnd = TimeConverter.convertDateTimeToDefault(endDate, endTime);

                int customerID = result.getInt("Customer_ID");
                int userID = result.getInt("User_ID");
                int contactID = result.getInt("Contact_ID");

                Appointment temp = new Appointment(appointmentID, title, description, location, type, convertedStart, convertedEnd, customerID, userID, contactID);

                appointments.add(temp);

            }

            return appointments;
        }

    }

    /**
     * @param contactID id of contact's whose appointments belong
     @return All appointments in the database
     */
    public static ObservableList<Appointment> getAppByContact(int contactID) throws Exception{

        ObservableList<Appointment> appointments = FXCollections.observableArrayList();

        //SQL statement to get all appointments from customer table
        String sqlStatement="SELECT * FROM appointments WHERE Contact_ID=" + contactID;

        //Make the query to execute
        Query.makeQuery(sqlStatement);

        //Store query result
        ResultSet result = Query.getResult();

        //Check if there are appointments
        if (!result.isBeforeFirst() ) {
            return appointments;
        } else {

            while(result.next()){

                //Implemented getting all values in separate variables for clarity
                int appointmentID = result.getInt("Appointment_ID");
                String title = result.getString("Title");
                String description = result.getString("Description");
                String location = result.getString("Location");
                String type = result.getString("Type");

                //Convert Start times to the System default time zones
                Date startDate = result.getDate("Start");
                Time startTime = result.getTime("Start");

                String convertedStart = TimeConverter.convertDateTimeToDefault(startDate, startTime);

                //Convert End times to the System default time zones
                Date endDate = result.getDate("End");
                Time endTime = result.getTime("End");

                String convertedEnd = TimeConverter.convertDateTimeToDefault(endDate, endTime);

                int customerID = result.getInt("Customer_ID");
                int userID = result.getInt("User_ID");

                Appointment temp = new Appointment(appointmentID, title, description, location, type, convertedStart, convertedEnd, customerID, userID, contactID);

                appointments.add(temp);

            }

            return appointments;
        }

    }

    /**
     * @param customerID id of customer's whose appointments belong
     @return All appointments in the database
     */
    public static ObservableList<Appointment> getAppByCustomer(int customerID) throws Exception{

        ObservableList<Appointment> appointments = FXCollections.observableArrayList();

        //SQL statement to get all appointments from customer table
        String sqlStatement="SELECT * FROM appointments WHERE Customer_ID=" + customerID;

        //Make the query to execute
        Query.makeQuery(sqlStatement);

        //Store query result
        ResultSet result = Query.getResult();

        //Check if there are appointments
        if (!result.isBeforeFirst() ) {
            return appointments;
        } else {

            while(result.next()){

                //Implemented getting all values in separate variables for clarity
                int appointmentID = result.getInt("Appointment_ID");
                String title = result.getString("Title");
                String description = result.getString("Description");
                String location = result.getString("Location");
                String type = result.getString("Type");

                //Convert Start times to the System default time zones
                Date startDate = result.getDate("Start");
                Time startTime = result.getTime("Start");

                String convertedStart = TimeConverter.convertDateTimeToDefault(startDate, startTime);

                //Convert End times to the System default time zones
                Date endDate = result.getDate("End");
                Time endTime = result.getTime("End");

                String convertedEnd = TimeConverter.convertDateTimeToDefault(endDate, endTime);


                int userID = result.getInt("User_ID");
                int contactID = result.getInt("Contact_ID");

                Appointment temp = new Appointment(appointmentID, title, description, location, type, convertedStart, convertedEnd, customerID, userID, contactID);

                appointments.add(temp);

            }

            return appointments;
        }

    }

    /**
     @return All appointments happening this month
     */
    public static ObservableList<Appointment> getMonthlyAppointments() throws Exception{

        ObservableList<Appointment> appointments = FXCollections.observableArrayList();

        //SQL statement to get all appointments from customer table
        String sqlStatement="SELECT * FROM appointments WHERE YEAR(Start) = YEAR(CURRENT_DATE()) AND MONTH(Start) = MONTH(CURRENT_DATE());";

        //Make the query to execute
        Query.makeQuery(sqlStatement);

        //Store query result
        ResultSet result = Query.getResult();

        //Check if there are appointments
        if (!result.isBeforeFirst() ) {
            return appointments;
        } else {

            while(result.next()){

                //Implemented getting all values in separate variables for clarity
                int appointmentID = result.getInt("Appointment_ID");
                String title = result.getString("Title");
                String description = result.getString("Description");
                String location = result.getString("Location");
                String type = result.getString("Type");

                //Convert Start times to the System default time zones
                Date startDate = result.getDate("Start");
                Time startTime = result.getTime("Start");

                String convertedStart = TimeConverter.convertDateTimeToDefault(startDate, startTime);

                //Convert End times to the System default time zones
                Date endDate = result.getDate("End");
                Time endTime = result.getTime("End");

                String convertedEnd = TimeConverter.convertDateTimeToDefault(endDate, endTime);

                int customerID = result.getInt("Customer_ID");
                int userID = result.getInt("User_ID");
                int contactID = result.getInt("Contact_ID");

                Appointment temp = new Appointment(appointmentID, title, description, location, type, convertedStart, convertedEnd, customerID, userID, contactID);

                appointments.add(temp);

            }

            return appointments;
        }

    }

    /**
     @return All appointments happening this week
     */
    public static ObservableList<Appointment> getWeeklyAppointments() throws Exception{

        ObservableList<Appointment> appointments = FXCollections.observableArrayList();

        //SQL statement to get all appointments from customer table
        String sqlStatement="SELECT * FROM appointments WHERE YEAR(Start) = YEAR(CURRENT_DATE()) AND WEEK(Start) = WEEK(CURRENT_DATE());";

        //Make the query to execute
        Query.makeQuery(sqlStatement);

        //Store query result
        ResultSet result = Query.getResult();

        //Check if there are appointments
        if (!result.isBeforeFirst() ) {
            return appointments;
        } else {

            while(result.next()){

                //Implemented getting all values in separate variables for clarity
                int appointmentID = result.getInt("Appointment_ID");
                String title = result.getString("Title");
                String description = result.getString("Description");
                String location = result.getString("Location");
                String type = result.getString("Type");

                //Convert Start times to the System default time zones
                Date startDate = result.getDate("Start");
                Time startTime = result.getTime("Start");

                String convertedStart = TimeConverter.convertDateTimeToDefault(startDate, startTime);

                //Convert End times to the System default time zones
                Date endDate = result.getDate("End");
                Time endTime = result.getTime("End");

                String convertedEnd = TimeConverter.convertDateTimeToDefault(endDate, endTime);

                int customerID = result.getInt("Customer_ID");
                int userID = result.getInt("User_ID");
                int contactID = result.getInt("Contact_ID");

                Appointment temp = new Appointment(appointmentID, title, description, location, type, convertedStart, convertedEnd, customerID, userID, contactID);

                appointments.add(temp);

            }

            return appointments;
        }

    }

    /**
     * @param newAppointment Appointment object that contains the necessary information to add a new appointment to the appointments table
     */
    public static void addAppointmentDatabase(Appointment newAppointment){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        LocalDateTime appStart = convertToUTC(LocalDateTime.parse(newAppointment.getStart(), formatter));

        LocalDateTime appEnd = convertToUTC(LocalDateTime.parse(newAppointment.getEnd(), formatter));

        String sqlStatement = "INSERT INTO appointments (Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID) VALUES('" + newAppointment.getTitle()+ "', '" + newAppointment.getDescription()+ "', '" + newAppointment.getLocation()+"', '" + newAppointment.getType() + "', '" + appStart.format(formatter) + "', '" + appEnd.format(formatter) + "', " + String.valueOf(newAppointment.getCustomerID()) + ", " + String.valueOf(newAppointment.getUserID()) + ", " + String.valueOf(newAppointment.getContactID()) + " )";

        //Make the query to execute
        Query.makeQuery(sqlStatement);

    }

    /**
     * @param updatedAppointment Appointment object that contains the necessary information to update a appointment to the appointments table
     */
    public static void updateAppointmentDatabase(Appointment updatedAppointment){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        LocalDateTime appStart = convertToUTC(LocalDateTime.parse(updatedAppointment.getStart(), formatter));

        LocalDateTime appEnd = convertToUTC(LocalDateTime.parse(updatedAppointment.getEnd(), formatter));


        String sqlStatement = "UPDATE appointments SET Title =' "+ updatedAppointment.getTitle() +"', Description = '" + updatedAppointment.getDescription()+ "', Location = '" + updatedAppointment.getLocation()+ "', Type = '" + updatedAppointment.getType() + "', Start = '" + appStart.format(formatter) + "', End = '" + appEnd.format(formatter) + "', Customer_ID = " + String.valueOf(updatedAppointment.getCustomerID()) + ", User_ID = " + String.valueOf(updatedAppointment.getUserID()) + ", Contact_ID = " + String.valueOf(updatedAppointment.getContactID()) +" WHERE Appointment_ID = " + String.valueOf(updatedAppointment.getAppointmentID()) + ";";

        //Make the query to execute
        Query.makeQuery(sqlStatement);

    }

    /**
     * @param deletedAppointment Appointment object that will get deleted
     */
    public static void deleteAppointmentDatabase(Appointment deletedAppointment)  {

        String sqlStatement1 = "DELETE FROM appointments WHERE Appointment_ID =" + String.valueOf(deletedAppointment.getAppointmentID());

        //Make the query to execute
        Query.makeQuery(sqlStatement1);

    }

    /**
     * @param type type of appointment
     */
    public static String countAppType(String type) throws SQLException  {

        int counter = 0;

        String sqlStatement1 = "SELECT count(Type) FROM appointments WHERE Type='" + type +"';";

        //Make the query to execute
        Query.makeQuery(sqlStatement1);

        //Get result
        ResultSet result = Query.getResult();

        while(result.next()){

           counter = result.getInt("count(Type)");

        }

        return String.valueOf(counter);

    }

    /**
     * @param monthPicked for number of appointments
     */
    public static String countAppMonth(LocalDateTime monthPicked) throws SQLException  {

        int counter = 0;

        String sqlStatement1 = "SELECT count(Type) FROM client_schedule.appointments WHERE YEAR(Start)=YEAR(CURRENT_DATE()) AND Month(Start)=Month('"+ monthPicked.getYear() + "/" + monthPicked.getMonthValue()+"/01')";

        //Make the query to execute
        Query.makeQuery(sqlStatement1);

        //Get result
        ResultSet result = Query.getResult();

        while(result.next()){

            counter = result.getInt("count(Type)");

        }

        return String.valueOf(counter);

    }

    /**
     * @param monthPicked Month picked by the user to count appointments
     * @param type Type input by the user to count appointments
     * @return the total count of appointments through the filter
     * @throws SQLException if value input is wrong
     */
    public static String countAppMonthType(LocalDateTime monthPicked, String type) throws SQLException  {

        int counter = 0;

        String sqlStatement1 = "SELECT count(Type) FROM client_schedule.appointments WHERE Type='" + type + "' AND YEAR(Start)=YEAR(CURRENT_DATE()) AND Month(Start)=Month('"+ monthPicked.getYear() + "/" + monthPicked.getMonthValue()+"/01')";

        //Make the query to execute
        Query.makeQuery(sqlStatement1);

        //Get result
        ResultSet result = Query.getResult();

        while(result.next()){

            System.out.println(result.getInt("count(Type)"));

            counter = result.getInt("count(Type)");

        }

        return String.valueOf(counter);

    }

}

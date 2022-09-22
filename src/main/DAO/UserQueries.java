package main.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class to hold the Read functions for the Users table
 */
public class UserQueries {

    /**
    @param loginUser The user object created from the login form
    @return True or false depending on the result of the query
    */
    public static Boolean authorizeUser(User loginUser) throws SQLException, Exception{

        //Check if username and password match database records
        String sqlStatement="Select * FROM users WHERE User_Name  = '" + loginUser.getUserName()+ "' AND Password = '" + loginUser.getPassword() + "'";

        //Make the query to execute
        Query.makeQuery(sqlStatement);

        //Store query result
        ResultSet result = Query.getResult();

        //Check if there are matching records if empty user or password is wrong
        if (!result.isBeforeFirst() ) {
            return false;
        } else {

            while(result.next()){

                int userID=result.getInt("User_ID");
                loginUser.setUserID(userID);

            }

            return true;
        }

    }

    /**
     * @return the result of getting all users from the database
     */
    public static ObservableList<User> getAllUsers() throws SQLException, Exception{

        ObservableList<User> users = FXCollections.observableArrayList();

        //Select all users
        String sqlStatement="Select User_ID FROM users";
        //Make the query to execute
        Query.makeQuery(sqlStatement);

        //Store query result
        ResultSet result = Query.getResult();

        //Create user object to add to list
        while(result.next()){

            //Empty identifier fields for security reasons
            int userID=result.getInt("User_ID");
            String userName = "";
            String userPassword = "";

            User temp = new User(userName, userPassword);
            temp.setUserID(userID);

            users.add(temp);

        }

        return users;

    }
}

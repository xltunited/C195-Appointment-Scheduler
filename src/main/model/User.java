package main.model;

/**
 * Class for the User object
 */
public class User {

    private int userID = 0;
    private final String userName;
    private final String password;

    /**
     * Create User Object
     */
    public User( String userName, String password) {

        this.userName = userName;
        this.password = password;

    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @return the userID
     */
    public int getUserID(){
        return userID;
    }

    /**
     * @param userID the userID to set to the User object
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * @return The string the combobox uses to display the user in the add/update appointment view
     */
    public String toString() {
        return String.valueOf(userID);
    }

}

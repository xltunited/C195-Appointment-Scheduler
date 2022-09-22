package main.model;

/**
 * Class for the Contact object
 */
public class Contact {

    private final int contactID;
    private String contactName;
    private String email;

    /**
     * Create Contact Object
     */
    public Contact(int contactID, String contactName, String email){

        this.contactID = contactID;
        this.contactName = contactName;
        this.email = email;

    }

    /**
     * @return the contactID
     */
    public int getContactID() {
        return contactID;
    }

    /**
     * @return the contactName
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * @param contactName the contactName to set
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return The string the combobox uses to display the contact in the add/update appointment view
     */
    public String toString() {
        return String.valueOf(contactID);
    }
}

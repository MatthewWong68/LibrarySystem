import java.util.ArrayList;

/**
 * Matthew have added toString method(needed in Edit User Page).
 *
 * @author
 */
public class User extends Account {
    private double balance;
    //REPLACE ArrayList<String> --> ArrayList<Copy>
    private ArrayList<String> borrowedItems;

    /**
     * Constructor for the class.
     *
     * @param username    The username for the account.
     * @param firstname   The user's first name.
     * @param lastName    The user's last name.
     * @param phoneNumber The user's phone number.
     * @param address     The user's home address.
     * @param imgPath     The location to the profile picture.
     * @param balance     The user's balance based on fees and payments.
     */
    public User(String username, String firstname, String lastName,
                String phoneNumber, String address, String imgPath, double balance) {
        super(username, firstname, lastName, phoneNumber, address, imgPath);
        this.balance = balance;
    }

    /**
     * Gets the balance.
     *
     * @return The user's balance.
     */
    public double getBalance() {
        return this.balance;
    }

    /**
     * Sets the balance.
     *
     * @param balance Balance to be set to the user.
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Gets borrowed items.
     *
     * @return An ArrayList of borrowed items by the user.
     */
    public ArrayList<String> getBorrowedItems() {
        return this.borrowedItems;
    }

    /**
     * Sets borrowed items.
     *
     * @param borrowedItems An ArrayList of borrowed items.
     */
    public void setBorrowedItems(ArrayList<String> borrowedItems) {
        this.borrowedItems = borrowedItems;
    }

    /**
     * Get a short description of the user that is suitable for use in a ListView.
     *
     * @return A short description of the user.
     */
    public String toString() {
        return getUsername() + " - " + getFirstName() + " - " + getLastName() + " - " + getPhoneNumber()
                + " - " + getAddress() + " - " + getImgPath() + " - " + balance;
    }

}

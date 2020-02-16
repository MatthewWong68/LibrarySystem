/**
 * This class creates an Account object.
 * <p>
 * Matthew added a commented out toString method since it may need to use in Edit User page.
 *
 * @author Joshua Placidi
 * @version v1.0.1
 * - no copyright
 */

public class Account {
    private String username;
    private String firstname;
    private String lastName;
    private String phoneNumber;
    private String address;
    private String imgPath;


    /**
     * constructor of Account
     *
     * @param username    The user name.
     * @param firstname   The first name of the user.
     * @param lastName    The last name of the user.
     * @param phoneNumber The phone number of the user.
     * @param address     The address of the user.
     * @param imgPath     The location of the picture.
     */
    public Account(String username, String firstname, String lastName, String phoneNumber, String address, String imgPath) {
        this.username = username;
        this.firstname = firstname;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.imgPath = imgPath;
    }

    /**
     * Gets username.
     *
     * @return username The username of the account.
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Sets username.
     *
     * @param username The username of the account.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the first name.
     *
     * @return firstname The first name of the user.
     */
    public String getFirstName() {
        return this.firstname;
    }

    /**
     * Sets the first name.
     *
     * @param firstname The first name of the user.
     */
    public void setFirstName(String firstname) {
        this.firstname = firstname;
    }

    /**
     * Gets phone number.
     *
     * @return The phone number of the user.
     */
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    /**
     * Sets phone number.
     *
     * @param phoneNumber The phone number of the user
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets address.
     *
     * @return address The address of the user.
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * Sets address.
     *
     * @param address The address of the user.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets last name.
     *
     * @return lastname The last name of the user.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets last name.
     *
     * @param lastName The last name of the user.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets image path.
     *
     * @return image path to the user's profile picture.
     */
    public String getImgPath() {
        return imgPath;
    }

    /**
     * Sets image path.
     *
     * @param imgPath Image path to the user's profile picture.
     */
    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }


}

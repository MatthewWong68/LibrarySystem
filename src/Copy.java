/**
 * A class responsible for information about each copy of a resource.
 *
 * @author Isaac McIntyre
 */

public class Copy {
    private int id;
    private String resourceID;
    private int loanDuration;
    private int available; // 0 - not available, 1 - available

    /**
     * Creates an object in which a copy of a certain resource is stored.
     *
     * @param id           uniquely identifies itself as a certain copy
     * @param resourceID   determines what resource it is
     * @param loanDuration determines duration of loan
     * @param available    flag that shows rather a resource is available or not
     */
    public Copy(int id, String resourceID, int loanDuration, int available) {
        this.id = id;
        this.resourceID = resourceID;
        this.loanDuration = loanDuration;
        this.available = available;
    }

    public String toString() {
        String availability;
        if (available == 1) {
            availability = "available";
        } else {
            availability = "unavailable";
        }
        return (id + ": is " + availability);
    }

    /**
     * @return id of the copy
     */
    public int getUniqueID() {
        return id;
    }

    /**
     * @param id changes id of the copy to the inputted id
     */
    public void setUniqueID(int id) {
        this.id = id;
    }

    /**
     * @return resourceID of the copy
     */
    public String getResourceID() {
        return resourceID;
    }

    /**
     * @param resourceID changes the reference ID of the copy
     */
    public void setResourceID(String resourceID) {
        this.resourceID = resourceID;
    }

    /**
     * @return duration of the loan
     */
    public int getLoanDuration() {
        return loanDuration;
    }

    /**
     * @param loanDuration changes the duration of the loan
     */

    public void setLoanDuration(int loanDuration) {
        this.loanDuration = loanDuration;
    }

    /**
     * @return a boolean of whether the copy is available or not
     */
    public int isAvailable() {
        return available;
    }

    /**
     * @param available changes whether the copy is available or not
     */
    public void setAvailable(int available) {
        this.available = available;
    }
}

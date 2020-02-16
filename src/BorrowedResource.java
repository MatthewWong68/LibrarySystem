import java.sql.SQLException;

/**
 * Creates an object for a borrowed resource.
 *
 * @author Luke Burtonwood
 * @version v1.0.0
 * - no copyright
 */
public class BorrowedResource {
    private String userID;
    private String rID;
    private int copyID;
    private long dateOut;
    private long dateDue;

    /**
     * Creates an object that stores which user has borrowed which copy of a certain resource
     *
     * @param userID  username of the user
     * @param rID     ID of the resource being borrowed
     * @param copyID  ID of the copy of the resource being ordered
     * @param dateOut date in which the user borrowed the copy
     * @param dateDue date in which the copy is due
     */
    public BorrowedResource(String userID, String rID, int copyID, long dateOut, long dateDue) {
        this.userID = userID;
        this.rID = rID;
        this.copyID = copyID;
        this.dateOut = dateOut;
        this.dateDue = dateDue;
    }

    /**
     * @return userID of the user that borrowed the copy
     */
    public String getUserID() {
        return userID;
    }

    /**
     * @param userID changes the userID to the given input
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }

    /**
     * @return rID of the resource that a copy is being borrowed
     */
    public String getrID() {
        return rID;
    }

    /**
     * @param rID changes the rID to the given input
     */
    public void setrID(String rID) {
        this.rID = rID;
    }

    /**
     * @return copyID of the copy which is being borrowed
     */
    public int getCopyID() {
        return copyID;
    }

    /**
     * @param copyID changes the copyID to the input
     */
    public void setCopyID(int copyID) {
        this.copyID = copyID;
    }

    /**
     * @return the date in which the resource was borrowed
     */
    public long getDateOut() {
        return dateOut;
    }

    /**
     * @param dateOut changes the date in which the resource was borrowed
     */
    public void setDateOut(long dateOut) {
        this.dateOut = dateOut;
    }

    /**
     * @return the date in which the borrowed resource is due back
     */
    public long getDateDue() {
        return dateDue;
    }

    /**
     * @param dateDue changes the date in which the resource is due back
     */
    public void setDateDue(long dateDue) {
        this.dateDue = dateDue;
    }

    private Resource getResourceObject() throws SQLException {
        return DataAdapter.readResource(this.rID);
    }

    public String toString() {
        Resource resource = null;
        try {
            resource = this.getResourceObject();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String dueDate = "None";
        if (this.dateDue != 0) {
            dueDate = DataAdapter.longToString(this.dateDue);
        }
        return "ID: " + this.getrID() + " Due Date: " + dueDate + " | Title: " + resource.getTitle();
    }

    /**
     * A method that will format some of the data of the BorrowedResource to a string for when the librarian views
     * overdue copies.
     *
     * @return The string format to the BorrowedResource consisting of copyID, userID, and the days overdue.
     */
    public String overdueCopyToString() {
        long currentDate = System.currentTimeMillis() / 1000L;
        String daysOverdue = String.valueOf((int) Math.floor((currentDate - dateDue) / 86400)); //round down the days overdue
        return ("ID: " + String.valueOf(copyID) + " Username: " + userID + " Days Overdue: " + daysOverdue);
    }

    public String toStringReserved() throws SQLException {
        return "ID: " + this.getrID() + " | Title: " + this.getResourceObject().getTitle();
    }
}

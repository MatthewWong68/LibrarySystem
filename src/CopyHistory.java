/**
 * @author Eduard Zakarian
 */

public class CopyHistory {
    private int chid; // a unique id in the database
    private String rid; //Resource ID
    private int cid; //Copy ID
    private String username;
    //Dates are in the UNIX timestamp
    private long dateout;
    private long datein;


    /**
     * @param chid     Copy history ID.
     * @param rid      Resource ID.
     * @param cid      Copy ID.
     * @param username Username.
     * @param dateout  Date when the copy has been taken out.
     * @param datein   Date when the copy is returned.
     */
    public CopyHistory(int chid, String rid, int cid, String username, long dateout, long datein) {
        this.chid = chid;
        this.rid = rid;
        this.cid = cid;
        this.username = username;
        this.dateout = dateout;
        this.datein = datein;
    }

    /**
     * @return A String that formats the object to be readable in a list view in the GUI.
     */
    public String toString() {
        //for returning out via the GUI
        return "Resource: " + rid + " " + username + " borrowed on " + DataAdapter.longToString(dateout) +
                " returned on " + DataAdapter.longToString(datein);
    }

    /**
     * Gets the copy history ID.
     *
     * @return copy history ID.
     */
    public int getChid() {
        return chid;
    }

    /**
     * Gets the resource ID.
     *
     * @return resource ID.
     */
    public String getRid() {
        return rid;
    }

    /**
     * Gets the copy ID.
     *
     * @return copy ID.
     */
    public int getCid() {
        return cid;
    }

    /**
     * Gets the username.
     *
     * @return username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the date when the copy has been taken out.
     *
     * @return dateout.
     */
    public long getDateout() {
        return dateout;
    }

    /**
     * Gets the date when the copy is returned.
     *
     * @return datein.
     */
    public long getDatein() {
        return datein;
    }


}
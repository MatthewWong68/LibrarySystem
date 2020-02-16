import java.sql.SQLException;

/**
 * A model of a requested resource. Each requested resource has a username, a resource ID and a request date.
 *
 * @author Matthew
 */
public class RequestedResource {

    private String username;
    private String rid;
    private long requestDate;

    /**
     * Create a new requested resource.
     *
     * @param Username    The name of the user.
     * @param Rid         The ID of the resource.
     * @param RequestDate The date the resource has been requested.
     */
    public RequestedResource(String Username, String Rid, long RequestDate) {
        this.username = Username;
        this.rid = Rid;
        this.requestDate = RequestDate;
    }

    /**
     * @return resource ID of requested resource
     */
    public String getRId() {
        return rid;
    }

    public String getUsername() {
        return username;
    }

    /**
     * @return the date the resource was requested
     */
    public long getRDate() {
        return requestDate;
    }


    /**
     * Get a short description of the requested resource that is suitable for use in a ListView.
     *
     * @return A short description of the requested resource.
     * @throws SQLException
     */
    private Resource getResourceObject() throws SQLException {
        return DataAdapter.readResource(this.rid);
    }

    public String toString() {
        Resource resource = null;
        try {
            resource = this.getResourceObject();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "ID: " + this.getRId() + " Date Requested: " + DataAdapter.longToString(this.requestDate) + " | Title: "
                + resource.getTitle();
    }
}
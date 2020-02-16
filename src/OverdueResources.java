/**
 * A model of an overdue resource. Each overdue resource has a username, a borrow date, a fine payment and a resource name .
 *
 * @author Matthew
 */
public class OverdueResources {

    private String username;
    private String resourceName;
    private String bDate;
    private String payment;

    public OverdueResources(String username, String resourceName, String bDate, String payment) {
        this.username = username;
        this.resourceName = resourceName;
        this.bDate = bDate;
        this.payment = payment;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    /**
     * Get a short description of the overdue resource that is suitable for use in a ListView.
     *
     * @return A short description of the overdue resource.
     */
    public String toString() {
        return username + " - " + resourceName + " - " + bDate + " - " + "Paid: $" + payment;
    }
}

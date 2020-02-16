/**
 * A class responsible for storing instances of fines that have been applied.
 *
 * @author Sai Sud
 */

public class Fine {

    private int fId;
    private String userId;
    private String rId;
    private int cid;
    private long setDate;
    private int amountDue; //amount due is in pennies.

    /**
     * Constructor for the Fine.
     *
     * @param fId       The fine's identifier.
     * @param userId    The user that has received the fine.
     * @param rId       The resource that the fine is applied for.
     * @param cid       The copy of the resource that the fine is applied for.
     * @param setDate   The date that the fine was set.
     * @param amountDue The amount of the fine.
     */
    public Fine(int fId, String userId, String rId, int cid, long setDate, int amountDue) {
        this.fId = fId;
        this.userId = userId;
        this.rId = rId;
        this.cid = cid;
        this.setDate = setDate;
        this.amountDue = amountDue;
    }

    public int getFId() {
        return fId;
    }

    public String getUserId() {
        return userId;
    }


    public String getRId() {
        return rId;
    }

    public int getAmountDue() {
        return amountDue;
    }

    public void setAmountDue(int amountDue) {
        this.amountDue = amountDue;
    }


    public int getCId() {
        return cid;
    }

}
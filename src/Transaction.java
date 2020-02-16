/**
 * A class that is used for holding information about transactions whether they are fines or payments.
 *
 * @author Joshua Placidi
 */

public class Transaction {

    private int tId;
    private int fId;
    private long setDate;
    private long endDate;
    private int amount;
    private String userId;

    /**
     * Constructor for the Transaction
     *
     * @param tId     Identifier for the transaction.
     * @param fId     The fine identifier.
     * @param setDate The date that the transaction was set.
     * @param amount  The amount of the transaction.
     * @param userId  The user that the transaction was for.
     * @param endDate The end date of the transaction.
     */
    public Transaction(int tId, int fId, long setDate, int amount, String userId, long endDate) {
        this.tId = tId;
        this.fId = fId;
        this.setDate = setDate;
        this.amount = amount;
        this.userId = userId;
        this.endDate = endDate;
    }

    /**
     * A method to convert the attributes of the class to be readable by the user (in a ListView placed in the GUI).
     *
     * @return A single string with information about the transaction.
     */
    public String toString() {
        double finalAmount = amount / 100.0;
        String output = "";
        if (fId == 0) {
            output = "Payment " + DataAdapter.longToString(setDate) + "  | Amount: £" + finalAmount;
        } else {
            String status = "";
            if (endDate == 0) {
                status = "Unpaid";
            } else {
                status = "Paid";
            }
            output = "Fine         " + DataAdapter.longToString(setDate) + " | Amount: £" + -finalAmount + " | Status: " + status;
        }
        return output;
    }
}
import java.sql.SQLException;
import java.util.ArrayList;

public class Librarian extends Account {
    private int staffNumber;
    private long employmentDate;

    public Librarian(String username, String name, String lastName,
                     String phoneNumber, String address, String imgPath, int staffNumber, long employmentDate) {
        super(username, name, lastName, phoneNumber, address, imgPath);
        this.staffNumber = staffNumber;
        this.employmentDate = employmentDate;
    }

    public void borrowResource(User user, Resource resource) throws SQLException {
        if (DataAdapter.getUserUnpaidBalance(user) == 0) {
            BorrowHandler.borrowResource(user, resource);
        } else {
            //Error Pop Up
        }
    }


    public void returnResource(User user, Resource resource, Copy copy) throws SQLException {

        BorrowHandler.returnResource(user, resource, copy);

    }


    public void makePayment(User user, double amount) throws SQLException {
        ArrayList<Fine> fineList = DataAdapter.getAllUserFines(user);
        ArrayList<Fine> updatedFines = new ArrayList<>();
        DataAdapter.addTransaction(user, (int) (amount * 100), null);

        for (Fine currentFine : fineList) {
            if ((int) (amount * 100) >= currentFine.getAmountDue()) {
                amount -= currentFine.getAmountDue();
                currentFine.setAmountDue(0);
                DataAdapter.setFineAsPayed(currentFine.getFId());

            } else {
                currentFine.setAmountDue((int) (currentFine.getAmountDue() - (amount * 100)));
                amount = 0;
            }
            updatedFines.add(currentFine);
        }
        DataAdapter.setAllUserFineAmounts(updatedFines);
    }

    public int getStaffNumber() {
        return this.staffNumber;
    }

    public long getEmploymentDate() {
        return this.employmentDate;
    }

    public ArrayList<BorrowedResource> getAllOverdueCopies() {
        return BorrowHandler.getAllOverdueCopies();
    }
}
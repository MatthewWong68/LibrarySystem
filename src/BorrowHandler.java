/**
 * A class responsible for communication between the DataAdapter and the other classes, especially
 * for handling actions such as borrowing and returning resources.
 *
 * @author Isaac McIntyre
 */

import java.sql.SQLException;
import java.util.ArrayList;

public class BorrowHandler {
    /**
     * Method to handle a user reserving a resource
     *
     * @param user     The user wishing to reserve the resource
     * @param resource The resource
     */
    public static void borrowResource(User user, Resource resource) throws SQLException {

        int copyID = DataAdapter.available(resource); //-1 indicates anything else the copy id
        if (copyID != -1) { //if the copy is available
            DataAdapter.collectResource(user, resource, copyID);
            BorrowedResource br = DataAdapter.getBorrowedResourceInfo(user, resource, DataAdapter.readCopy(copyID));
            CopyHistory ch = new CopyHistory(0, resource.getUniqueId(), copyID, user.getUsername(),
                    br.getDateOut(), 0);
            DataAdapter.addCopyHistory(ch);
        } else { //if the copy is not available
            DataAdapter.addRequestedResource(user, resource); //adds to requested resource table
            BorrowedResource borrowedResource = getOldestBorrowedResource(DataAdapter.getAllBorrowedResources(resource));

            long dueDate = calculateDueDate(borrowedResource);

            DataAdapter.setDueDate(resource, (User) DataAdapter.readAccount(borrowedResource.getUserID()),
                    borrowedResource.getCopyID(), dueDate);
        }
    }

    /**
     * Method for what happens when a copy of a resource is returned.
     *
     * @param user     The user that borrowed the copy.
     * @param resource The resource of the copy borrowed.
     * @param copy     The copy of the resource borrowed.
     */
    public static void returnResource(User user, Resource resource, Copy copy) throws SQLException {
        BorrowedResource br = DataAdapter.
                getBorrowedResourceInfo(user, resource, DataAdapter.readCopy(copy.getUniqueID()));
        CopyHistory ch = new CopyHistory(0, resource.getUniqueId(), copy.getUniqueID(), user.getUsername(),
                br.getDateOut(), 0);
        DataAdapter.setCHDateIn(ch);
        if (DataAdapter.isResourceOverdue(user, resource, copy)) { //if overdue
            Fine f = new Fine(DataAdapter.genFineNum(), user.getUsername(), resource.getUniqueId(), copy.getUniqueID(),
                    0, 0);
            DataAdapter.addFine(f);
        }
        DataAdapter.deleteBorrowedResource(user, resource, copy);
        if (DataAdapter.isResourceRequested(resource)) { //checks if someone is in request queue for this resource
            CopyHistory ch1 = new CopyHistory(0, resource.getUniqueId(), copy.getUniqueID(),
                    BorrowHandler.getNextRequested(DataAdapter.getAllRequested(resource.getUniqueId())).getUsername(),
                    br.getDateOut(), 0);
            DataAdapter.reserveResource(BorrowHandler.getNextRequested(DataAdapter.getAllRequested(resource.getUniqueId())), resource, copy);
            DataAdapter.deleteRequestedResource(BorrowHandler.getNextRequested(DataAdapter.getAllRequested(resource.getUniqueId())), resource);

            DataAdapter.addCopyHistory(ch1);
        } else {
            DataAdapter.markCopyAvailability(resource, copy.getUniqueID(), true); //marks availability of copy as true
        }

        //remove user from BorrowedResoource


    }

    /**
     * Will return oldest borrowed resource.
     *
     * @param resource the resource in which the oldest is searched
     * @return the oldest borrowed requested resource
     */
    public static RequestedResource getNextResourceRequest(Resource resource) throws SQLException {
        ArrayList<RequestedResource> requestedResources = DataAdapter.getAllResourceRequests(resource);
        //loop through array list and return with the smallest date
        long smallestDate = requestedResources.get(0).getRDate();
        RequestedResource requestedResource = requestedResources.get(0);
        for (RequestedResource current : requestedResources) { //iterate through and find oldest borrowed resource
            if (smallestDate < current.getRDate()) {
                smallestDate = current.getRDate();
                requestedResource = current;
            }
        }
        return requestedResource;
    }


    /**
     * returns a list of resources the user has reserved
     *
     * @param user the user
     * @return ArrayList of BorrowedResources
     */
    public static ArrayList<BorrowedResource> getReservedResources(User user) throws SQLException {
        ArrayList<BorrowedResource> allBorrowedResources = DataAdapter.getBorrowedResources(user);
        ArrayList<BorrowedResource> reservedResources = new ArrayList<BorrowedResource>();
        for (BorrowedResource current : allBorrowedResources) {
            if (current.getDateOut() == 0) {
                reservedResources.add(current);
            }
        }

        return reservedResources;
    }

    /**
     * Find the oldest borrowed resource from an array of borrowed resources.
     *
     * @param borrowedResources The array of borrowed resources.
     * @return The oldest borrowed resource.
     */
    public static BorrowedResource getOldestBorrowedResource(ArrayList<BorrowedResource> borrowedResources) {
        long time = borrowedResources.get(0).getDateOut();
        BorrowedResource borrowedResource = borrowedResources.get(0);
        for (BorrowedResource current : borrowedResources) {
            if (current.getDateOut() < time) {
                time = current.getDateOut();
                borrowedResource = current;
            }
        }
        return borrowedResource;
    }

    /**
     * Method that will calculate the due date to be set for a given borrowed resource.
     *
     * @param borrowedResource The borrowed resource that the due date is set for.
     * @return The due date in unix timestamp format.
     */
    public static long calculateDueDate(BorrowedResource borrowedResource) throws SQLException {
        int loanDuration = DataAdapter.getCopyDuration(borrowedResource.getrID(), borrowedResource.getCopyID());
        long currentTime = System.currentTimeMillis() / 1000L;
        long tomorrowTime = currentTime + 86400;
        long loanDurationEndTime = borrowedResource.getDateOut() + (loanDuration * 86400);

        if (tomorrowTime > loanDurationEndTime) {
            return tomorrowTime;
        } else {
            return loanDurationEndTime;
        }
    }

    /**
     * A method responsible for returning all borrowed resources that are overdue in the library.
     *
     * @return The overdue borrowed resources.
     */
    public static ArrayList<BorrowedResource> getAllOverdueCopies() {
        ArrayList<BorrowedResource> overdueCopies = new ArrayList<>(); //ArrayList of overdue copies

        try {
            ArrayList<BorrowedResource> allBorrowedResources = DataAdapter.getAllBorrowedResources(); //gets all borrowed resources
            long currentTime = System.currentTimeMillis() / 1000L;
            for (BorrowedResource current : allBorrowedResources) {
                if (current.getDateDue() < currentTime && current.getDateDue() != 0) { //check if the due date is smaller than current time
                    overdueCopies.add(current);
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return overdueCopies;
    }

    /**
     * Gets the number of overdue resources.
     * @param user The user that has borrowed the resources.
     * @return The number of resources that are overdue for the user.
     */
    public static int getNumOverdueResources(User user) {
        int numOverdueResources = 0;
        ArrayList<BorrowedResource> borrowedResources = getAllOverdueCopies();
        for (BorrowedResource current : borrowedResources) {
            if (current.getUserID().equals(user.getUsername())) {
                numOverdueResources++;
            }
        }

        return numOverdueResources;
    }

    /**
     * Finds the user that has the oldest request for a resource.
     * @param requestedResources A list of requested resources.
     * @return The user that has the oldest request.
     */
    public static User getNextRequested(ArrayList<RequestedResource> requestedResources) {
        long date = requestedResources.get(0).getRDate();
        String username = requestedResources.get(0).getUsername();
        for (RequestedResource current : requestedResources) {
            if (current.getRDate() < date) {
                date = current.getRDate();
                username = current.getUsername();
            }
        }
        try {
            return (User) DataAdapter.readAccount(username);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

}
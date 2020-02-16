import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Controller for the requested items window.
 *
 * @author Matthew
 * @version 1.2
 */
public class RequestedItemsController {

    // The main list that will store all the requested items.
    @FXML
    private ListView<String> requestedItemsList;

    @FXML
    private BorderPane requestedItemsPane;

    @FXML
    private Button closeButton;

    /**
     * Initialize the controller.
     *
     * @throws SQLException Throws when database error occurs.
     */
    public void initialize() throws SQLException {
        // Create some initial borrowed items.
        ArrayList<RequestedResource> r = DataAdapter
                .getRequestedResources((User) LoginPageController.getCurrentAccount());
        displayIt(r);
    }

    /**
     * This method will display the item on the list view.
     *
     * @param r ArrayList of requested resources.
     */
    public void displayIt(ArrayList<RequestedResource> r) {
        // Add each requested item to the displayed list
        for (RequestedResource rr : r) {
            requestedItemsList.getItems().add(rr.toString());
        }

    }

    /**
     * This method will bring you back to the user page.
     *
     * @param event The event that will appear in the screen.
     */
    @FXML
    void goToUserPage(ActionEvent event) {
        closeWindow();
    }

    /**
     * This method will close the current stage.
     */
    @FXML
    void closeWindow() {
        Stage stage = (Stage) requestedItemsPane.getScene().getWindow();
        stage.close();
    }
}

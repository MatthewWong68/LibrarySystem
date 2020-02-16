import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Controller for the reserved items window.
 *
 * @author Matthew
 * @version 1.2
 */
public class ReservedItemsController {

    // The main list that will store all the reserved items.
    @FXML
    private ListView<String> reservedItemsList;

    @FXML
    private BorderPane reservedItemsPane;

    @FXML
    private Button closeButton;

    /**
     * Initialize the controller.
     *
     * @throws SQLException Throws when database error occurs.
     */
    public void initialize() throws SQLException {
        // Create some initial borrowed items.
        ArrayList<BorrowedResource> br = BorrowHandler
                .getReservedResources((User) LoginPageController.getCurrentAccount());
        displayIt(br);
    }

    /**
     * This method will display the item on the list view.
     *
     * @param br ArrayList of borrowed resources.
     * @throws SQLException Throws when database error occurs.
     */
    public void displayIt(ArrayList<BorrowedResource> br) throws SQLException {
        // Add each reserved item to the displayed list
        for (BorrowedResource b : br) {
            reservedItemsList.getItems().add(b.toStringReserved());
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
        Stage stage = (Stage) reservedItemsPane.getScene().getWindow();
        stage.close();
    }
}

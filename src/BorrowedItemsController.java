import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Controller for the borrowed items window.
 *
 * @author Matthew
 * @version 1.2
 */
public class BorrowedItemsController {

    @FXML
    private ListView<String> borrowedItemsList;

    @FXML
    private BorderPane borrowedItemsPane;

    @FXML
    private Button borrowButton;

    @FXML
    private Button closeButton;

    /**
     * Initialize the controller.
     *
     * @throws SQLException Throws when database error occurs.
     */
    public void initialize() throws SQLException {
        //Import the borrowed resources from the database.
        ArrayList<BorrowedResource> br =
                DataAdapter.getBorrowedResources((User) LoginPageController.getCurrentAccount());
        displayIt(br);
    }

    /**
     * This method will display the item on the list view.
     *
     * @param br Arraylist of the borrowed resources.
     */
    public void displayIt(ArrayList<BorrowedResource> br) {
        // Add each borrowed item to the displayed list
        for (BorrowedResource b : br) {
            if (b.getDateOut() != 0)
                borrowedItemsList.getItems().add(b.toString());
        }

    }

    /**
     * This method will bring you back to the User page.
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
        Stage stage = (Stage) borrowedItemsPane.getScene().getWindow();
        stage.close();
    }
}

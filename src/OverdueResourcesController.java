import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Controller for the overdue resources window.
 *
 * @author Matthew
 * @version 1.2
 */
public class OverdueResourcesController {

    // The main list that will store all the overdue resources.
    @FXML
    private ListView<String> overdueResourcesList;

    @FXML
    private BorderPane overdueResourcesPane;

    @FXML
    private Button closeButton;

    /**
     * Initialize the controller.
     */
    public void initialize() {
        displayIt();
    }

    /**
     * This method will display the overdue resources on the list view.
     */
    public void displayIt() {
        Librarian librarian = (Librarian) LoginPageController.getCurrentAccount();
        ArrayList<BorrowedResource> overdueCopies = librarian.getAllOverdueCopies();
        for (BorrowedResource current : overdueCopies) {
            // will output a list of overdue resources from overdueResourceList
            overdueResourcesList.getItems().add(current.overdueCopyToString());
        }

    }

    /**
     * This method will bring you back to the Librarian page.
     *
     * @param event The event that will appear in the screen.
     */
    @FXML
    void goToLibrarianPage(ActionEvent event) {
        closeWindow();
    }

    /**
     * This method will close the current stage.
     */
    @FXML
    void closeWindow() {
        Stage stage = (Stage) overdueResourcesPane.getScene().getWindow();
        stage.close();
    }

}
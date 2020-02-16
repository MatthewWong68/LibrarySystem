import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.sql.SQLException;

/**
 * Controller for the borrow resources window.
 *
 * @author Matthew
 * @version 1.2
 */
public class BorrowResourcesController {

    @FXML
    private BorderPane borrowResourcesPane;

    @FXML
    private Button borrowButton;

    @FXML
    private Button closeButton;

    @FXML
    private TextField userusername;

    @FXML
    private TextField rid;

    /**
     * This method will bring you back to the librarian page.
     *
     * @param event The event that will appear in the screen.
     */
    @FXML
    void goToLibrarianPage(ActionEvent event) {
        closeWindow();
    }

    /**
     * This method will store the information that the librarian typed in into the
     * database. And return back to the librarian page.
     *
     * @param event
     * @throws SQLException Throws when database error occurs.
     */
    @FXML
    void storeAndGoToLibrarianPage(ActionEvent event) throws SQLException {

        User u = (User) DataAdapter.readAccount(userusername.getText());
        Resource r = DataAdapter.readResource(rid.getText());
        Librarian l = (Librarian) LoginPageController.getCurrentAccount();

        // Using the method in DataAdapter to borrow resource.
        l.borrowResource(u, r);

        closeWindow();
    }

    /**
     * This method will close the current stage.
     */
    @FXML
    void closeWindow() {
        Stage stage = (Stage) borrowResourcesPane.getScene().getWindow();
        stage.close();
    }
}

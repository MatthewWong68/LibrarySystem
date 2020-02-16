import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Controller for the borrow resources window.
 *
 * @author Matthew, Eduard
 * @version 1.2
 */
public class CollectResourceController {

    // Setup the information that the librarian will type in.
    private String username;

    @FXML
    private Button collectButton;

    @FXML
    private BorderPane collectResourcePane;

    @FXML
    private TextField usernameTextField;

    /**
     * This method will store the information that the librarian typed in into the
     * database. And return back to the librarian page.
     *
     * @param event The event that will appear in the screen.
     * @throws SQLException Throws when database error occurs.
     */
    @FXML
    void storeAndGoToLibrarianPage(ActionEvent event) throws SQLException {
        username = usernameTextField.getText();
        User u = (User) DataAdapter.readAccount(username);

        // User trying to collect the resource using BorrowHandler class.
        ArrayList<BorrowedResource> br = BorrowHandler.getReservedResources(u);
        for (BorrowedResource b : br) {
            DataAdapter.addCollectionDate(username, b.getrID(), b.getCopyID());
        }
        closeWindow();
    }

    /**
     * This method will close the current stage.
     */
    @FXML
    void closeWindow() {
        Stage stage = (Stage) collectResourcePane.getScene().getWindow();
        stage.close();
    }
}

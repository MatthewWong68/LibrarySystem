import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.sql.SQLException;

/**
 * Controller for the return resources window.
 *
 * @author Matthew
 * @version 1.2
 */
public class ReturnResourcesController {

    //Setup the information that the librarian will type in.
    private String cID;
    private String rID;
    private String username;

    @FXML
    private BorderPane returnResourcesPane;

    @FXML
    private TextField usernameTextField;

    @FXML
    private Button returnButton;

    @FXML
    private Button closeButton;

    @FXML
    private TextField copyIDTextField;

    @FXML
    private TextField resourceIDTextField;

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
     * This method will store the information that the librarian typed in into the database. And return back to the librarian page.
     *
     * @param event The event that will appear in the screen.
     * @throws SQLException Throws when database error occurs.
     */
    @FXML
    void storeAndGoToLibrarianPage(ActionEvent event) throws SQLException {
        User u = (User) DataAdapter.readAccount(usernameTextField.getText());

        Resource r = DataAdapter.readResource(resourceIDTextField.getText());
        Librarian l = (Librarian) LoginPageController.getCurrentAccount();
        Copy c = DataAdapter.readCopy(Integer.parseInt(copyIDTextField.getText()));
        // Return a resource using method in DataAdapter.
        l.returnResource(u, r, c);

        closeWindow();
    }

    /**
     * This method will close the current stage.
     */
    @FXML
    void closeWindow() {
        Stage stage = (Stage) returnResourcesPane.getScene().getWindow();
        stage.close();
    }
}

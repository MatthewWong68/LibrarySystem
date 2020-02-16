import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Controller for the create new user window.
 *
 * @author Matthew
 * @version 1.2
 */
public class CreateNewUserController {

    // Setup the information that the librarian will type in.
    private String newusername;
    private String newFirstName;
    private String newLastName;
    private String newPhoneNumber;
    private String newAddress;

    private Alert alert = new Alert(AlertType.ERROR);

    @FXML
    private BorderPane createNewUserPane;

    @FXML
    private Button returnButton;

    @FXML
    private Button closeButton;

    @FXML
    private TextField username;

    @FXML
    private TextField phoneNumber;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField address;

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
     * This method will store the information that the librarian typed in into the
     * database. And return back to the librarian page.
     *
     * @param event The event that will appear in the screen.
     * @throws Exception Throws when the librarian entered the wrong value to the
     *                   textfield.
     */
    @FXML
    void storeAndGoToLibrarianPage(ActionEvent event) throws Exception {

        newusername = username.getText();
        newFirstName = firstName.getText();
        newLastName = lastName.getText();
        newPhoneNumber = phoneNumber.getText();
        newAddress = address.getText();
        String defaultImg = "Images/Avatar_00000.png";

        if (newusername.isEmpty() || newFirstName.isEmpty() || newLastName.isEmpty() || newPhoneNumber.isEmpty()
                || newAddress.isEmpty()) {
            alert.setHeaderText("Please enter all the information!");
            alert.showAndWait();

        } else {
            // Create the new user in the database using method in DataAdapter.
            User u = new User(newusername, newFirstName, newLastName, newPhoneNumber, newAddress, defaultImg, 0);
            DataAdapter.writeData(u);
            closeWindow();
        }

    }

    /**
     * This method will close the current stage.
     */
    @FXML
    void closeWindow() {
        Stage stage = (Stage) createNewUserPane.getScene().getWindow();
        stage.close();
    }

}

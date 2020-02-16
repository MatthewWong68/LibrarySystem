import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.sql.SQLException;

/**
 * Controller for the manage edit current user window.
 *
 * @author Matthew, Josh
 * @version 1.2
 */
public class ManageEditCurrentUserController {

    // The user being edited.
    User userBeingEdit;

    @FXML
    private BorderPane ManageEditCurrentUserPane;

    @FXML
    private Button saveButton;

    @FXML
    private Button closeButton;

    @FXML
    private TextField firstnameTextField;

    @FXML
    private TextField lastnameTextField;

    @FXML
    private TextField phoneNumberTextField;

    @FXML
    private TextField addressTextField;

    @FXML
    private TextField balanceTextField;

    @FXML
    private Button changeButton;

    /**
     * Initialize the controller.
     */
    public void initialize() {
        saveButton.setOnAction(e -> {
            try {
                handleSaveButtonAction();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });

        closeButton.setOnAction(e -> {
            handleCloseButtonAction();
        });
    }

    /**
     * Set the user that is being edited. When this window is closed, the changes
     * will be set in this user object.
     *
     * @param user The user to be edited.
     */
    public void setUserForEditing(User user) {
        // Keep a reference to the user that we are editing.
        this.userBeingEdit = user;

        // Update the GUI to show the existing data.
        firstnameTextField.setText(userBeingEdit.getFirstName());
        lastnameTextField.setText(userBeingEdit.getLastName());
        phoneNumberTextField.setText(userBeingEdit.getPhoneNumber());
        addressTextField.setText(userBeingEdit.getAddress());
        balanceTextField.setText(Double.toString(userBeingEdit.getBalance()));
    }

    /**
     * Handle the close button. Close the edit and close the window.
     */
    private void handleCloseButtonAction() {
        Stage stage = (Stage) ManageEditCurrentUserPane.getScene().getWindow();
        stage.close();
    }

    /**
     * Handle the save button. Save the changes and close the window.
     *
     * @throws SQLException Throws when database error occurs.
     */
    private void handleSaveButtonAction() throws SQLException {

        userBeingEdit.setFirstName(firstnameTextField.getText());
        userBeingEdit.setLastName(lastnameTextField.getText());
        userBeingEdit.setPhoneNumber(phoneNumberTextField.getText());
        userBeingEdit.setAddress(addressTextField.getText());
        userBeingEdit.setBalance(Double.parseDouble(balanceTextField.getText()));

        // Updating the user information using the method in DataAdapter.
        DataAdapter.updateUser(userBeingEdit);

        closeWindow();
    }

    /**
     * Close the window.
     */
    private void closeWindow() {
        Stage stage = (Stage) ManageEditCurrentUserPane.getScene().getWindow();
        stage.close();
    }

}

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.sql.SQLException;

/**
 * Controller for the manage fine payment window.
 *
 * @author Matthew
 * @version 1.2
 */
public class ManageFineController {

    @FXML
    private BorderPane manageFinePane;

    @FXML
    private Button confirmButton;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField amountPaidTextField;

    @FXML
    private TextField usernameTextField;

    /**
     * Initialize the controller.
     */
    public void initialize() {
        confirmButton.setOnAction(e -> {
            handleConfirmButtonAction();
        });

        cancelButton.setOnAction(e -> {
            handleCancelButtonAction();
        });
    }

    /**
     * Handle the close button. Close the edit and close the window.
     */
    private void handleCancelButtonAction() {
        Stage stage = (Stage) manageFinePane.getScene().getWindow();
        stage.close();
    }

    /**
     * Handle the save button. Save the changes and close the window.
     */
    private void handleConfirmButtonAction() {
        double amountPaid = Double.parseDouble(amountPaidTextField.getText());
        String username = (usernameTextField.getText());
        Librarian librarian = (Librarian) LoginPageController.getCurrentAccount();
        try {
            User user = (User) DataAdapter.readAccount(username);
            librarian.makePayment(user, amountPaid);
        } catch (SQLException e) {
            System.out.println(e);
        }
        closeWindow();
    }

    /**
     * Close the window.
     */
    private void closeWindow() {
        Stage stage = (Stage) manageFinePane.getScene().getWindow();
        stage.close();
    }
}

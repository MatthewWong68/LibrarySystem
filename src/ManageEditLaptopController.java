import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.sql.SQLException;

/**
 * Controller for the manage edit laptop window.
 *
 * @author Matthew
 * @version 1.2
 */
public class ManageEditLaptopController {

    // The laptop being edited.
    Laptop laptopBeingEdited;

    @FXML
    private BorderPane manageEditLaptopPane;

    @FXML
    private Button saveButton;

    @FXML
    private Button closeButton;

    @FXML
    private TextField titleTextField;

    @FXML
    private TextField yearTextField;

    @FXML
    private TextField thumbnailPathTextField;

    @FXML
    private TextField numberOfCopiesTextField;

    @FXML
    private TextField manufacturerTextField;

    @FXML
    private TextField modelTextField;

    @FXML
    private TextField installedOSTextField;

    /**
     * Initialize the controller.
     */
    public void initialize() {
        // Setup the me
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
     * Set the laptop that is being edited. When this window is closed, the changes
     * will be set in this laptop object.
     *
     * @param laptop Laptop to be edited.
     */
    public void setLaptopForEditing(Laptop laptop) {
        // Keep a reference to the user that we are editing.
        this.laptopBeingEdited = laptop;

        // Update the GUI to show the existing data.
        titleTextField.setText(laptopBeingEdited.getTitle());
        yearTextField.setText(Integer.toString(laptopBeingEdited.getYear()));
        thumbnailPathTextField.setText(laptopBeingEdited.getThumbnailPath());
        numberOfCopiesTextField.setText(Integer.toString(laptopBeingEdited.getNumCopies()));
        manufacturerTextField.setText(laptopBeingEdited.getManufacturer());
        modelTextField.setText(laptopBeingEdited.getModel());
        installedOSTextField.setText(laptopBeingEdited.getOperatingSystem());
    }

    /**
     * Handle the close button. Close the edit and close the window.
     */
    private void handleCloseButtonAction() {
        Stage stage = (Stage) manageEditLaptopPane.getScene().getWindow();
        stage.close();
    }

    /**
     * Handle the save button. Save the changes and close the window.
     *
     * @throws SQLException Throws when database error occurs.
     */
    private void handleSaveButtonAction() throws SQLException {

        laptopBeingEdited.setTitle(titleTextField.getText());
        laptopBeingEdited.setYear(Integer.parseInt(yearTextField.getText()));
        laptopBeingEdited.setThumbnailPath(thumbnailPathTextField.getText());
        laptopBeingEdited.setNumCopies(Integer.parseInt(numberOfCopiesTextField.getText()));
        laptopBeingEdited.setManufacturer(manufacturerTextField.getText());
        laptopBeingEdited.setModel(modelTextField.getText());
        laptopBeingEdited.setOperatingSystem(installedOSTextField.getText());

        DataAdapter.updateLaptop(laptopBeingEdited);

        closeWindow();
    }

    /**
     * Close the window.
     */
    private void closeWindow() {
        Stage stage = (Stage) manageEditLaptopPane.getScene().getWindow();
        stage.close();
    }

}

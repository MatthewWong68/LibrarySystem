import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.sql.SQLException;

/**
 * Controller for the manage edit dvd window.
 *
 * @author Matthew
 * @version 1.2
 */
public class ManageEditDVDController {

    // The DVD being edited.
    DVD dvdBeingEdit;

    @FXML
    private BorderPane manageEditDVDPane;

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
    private TextField directorTextField;

    @FXML
    private TextField runtimeTextField;

    @FXML
    private TextField languageTextField;

    @FXML
    private TextField subtitleTextField;

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
     * Set the DVD that is being edited.
     * When this window is closed, the changes will be set in this DVD object.
     *
     * @param dvd DVD to be edited.
     */
    public void setDVDForEditing(DVD dvd) {
        // Keep a reference to the user that we are editing.
        this.dvdBeingEdit = dvd;

        // Update the GUI to show the existing data.
        titleTextField.setText(dvdBeingEdit.getTitle());
        yearTextField.setText(Integer.toString(dvdBeingEdit.getYear()));
        thumbnailPathTextField.setText(dvdBeingEdit.getThumbnailPath());
        numberOfCopiesTextField.setText(Integer.toString(dvdBeingEdit.getNumCopies()));
        directorTextField.setText(dvdBeingEdit.getDirector());
        runtimeTextField.setText(Integer.toString(dvdBeingEdit.getRuntime()));
        languageTextField.setText(dvdBeingEdit.getLanguage());
        subtitleTextField.setText(dvdBeingEdit.getSubtitle());
    }

    /**
     * Handle the close button.
     * Close the edit and close the window.
     */
    private void handleCloseButtonAction() {
        Stage stage = (Stage) manageEditDVDPane.getScene().getWindow();
        stage.close();
    }

    /**
     * Handle the save button. Save the changes and close the window.
     *
     * @throws SQLException Throws when database error occurs.
     */
    private void handleSaveButtonAction() throws SQLException {

        dvdBeingEdit.setTitle(titleTextField.getText());
        dvdBeingEdit.setYear(Integer.parseInt(yearTextField.getText()));
        dvdBeingEdit.setThumbnailPath(thumbnailPathTextField.getText());
        dvdBeingEdit.setNumCopies(Integer.parseInt(numberOfCopiesTextField.getText()));
        dvdBeingEdit.setDirector(directorTextField.getText());
        dvdBeingEdit.setRuntime(Integer.parseInt(runtimeTextField.getText()));
        dvdBeingEdit.setLanguage(languageTextField.getText());
        dvdBeingEdit.setSubtitle(subtitleTextField.getText());

        DataAdapter.updateDvd(dvdBeingEdit);

        closeWindow();
    }

    /**
     * Close the window.
     */
    private void closeWindow() {
        Stage stage = (Stage) manageEditDVDPane.getScene().getWindow();
        stage.close();
    }

}

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Controller for the edit dvd window.
 *
 * @author Matthew
 * @version 1.2
 */
public class EditDVDController {

    // Setup an arraylist that will store all DVD in it.
    private ArrayList<DVD> dList = new ArrayList<>();

    @FXML
    private BorderPane editDVDPane;

    @FXML
    private ListView<String> dvdList;

    @FXML
    private Button editButton;

    @FXML
    private Button closeButton;

    /**
     * Initialize the controller.
     *
     * @throws SQLException Throws when database error occurs.
     */
    public void initialize() throws SQLException {
        dList = DataAdapter.readAllDvds();

        displayIt();
    }

    /**
     * This method will display the DVD on the list view.
     */
    public void displayIt() {
        dvdList.getItems().clear();
        // Add each DVD to the displayed list.
        for (DVD d : dList) {
            dvdList.getItems().add(d.toString());
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
     * This method will bring you to the manage edit DVD page.
     *
     * @param event The event that will appear in the screen.
     */
    @FXML
    void goToManageEditDVD(ActionEvent event) {
        // Get the index of the selected item in the displayed list
        int selectedIndex = dvdList.getSelectionModel().getSelectedIndex();

        // Check if librarian selected an item
        if (selectedIndex < 0) {
            // Show a message
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error: Cannot edit dvd");
            alert.setHeaderText(null);
            alert.setContentText("Cannot edit dvd as no dvd is selected. Please select a dvd first.");
            alert.showAndWait();
            return;
        }

        DVD selectedDVD = dList.get(selectedIndex);

        try {
            // Create a FXML loader for loading the Manage Edit DVD FXML file.
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ManageEditDVD.fxml"));

            BorderPane editRoot = (BorderPane) fxmlLoader.load();
            ManageEditDVDController editController = fxmlLoader.<ManageEditDVDController>getController();
            editController.setDVDForEditing(selectedDVD);
            Scene editScene = new Scene(editRoot, Main.SMALL_WINDOW_WIDTH, Main.ENORMOUS_WINDOW_HEIGHT);
            Stage editStage = new Stage();

            editStage.setScene(editScene);
            editStage.setTitle(Main.MANAGE_EDIT_DVD_WINDOW_TITLE);
            editStage.initModality(Modality.APPLICATION_MODAL);
            // Show the edit scene and wait for it to be closed
            editStage.showAndWait();

            displayIt();

        } catch (IOException e) {
            e.printStackTrace();
            // Quit the program (with an error code)
            System.exit(-1);
        }
    }

    /**
     * This method will close the current stage.
     */
    @FXML
    void closeWindow() {
        Stage stage = (Stage) editDVDPane.getScene().getWindow();
        stage.close();
    }
}

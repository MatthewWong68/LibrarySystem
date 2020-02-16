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
 * Controller for the editing current user window.
 *
 * @author Matthew
 * @version 1.2
 */
public class EditCurrentUserController {

    // Setup an arraylist that will store all user in it.
    private ArrayList<User> cuList = new ArrayList<>();

    @FXML
    private BorderPane editCurrentUserPane;

    @FXML
    private Button editButton;

    @FXML
    private Button closeButton;

    @FXML
    private ListView<String> userList;

    /**
     * Initialize the controller.
     *
     * @throws SQLException Throws when database error occurs.
     */
    public void initialize() throws SQLException {
        cuList = DataAdapter.getAllUsers();
        displayIt();
    }

    /**
     * This method will display the users on the list view.
     */
    public void displayIt() {
        userList.getItems().clear();
        // Add all user to the displayed list.
        for (User u : cuList) {
            userList.getItems().add(u.toString());
        }

    }

    /**
     * This method will bring you to the edit user page.
     *
     * @param event The event that will appear in the screen.
     */
    @FXML
    void goToEditUser(ActionEvent event) {
        // Get the index of the selected item in the displayed list
        int selectedIndex = userList.getSelectionModel().getSelectedIndex();

        // Check if librarian selected an item
        if (selectedIndex < 0) {
            // Show a message
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error: Cannot edit user");
            alert.setHeaderText(null);
            alert.setContentText("Cannot edit user as no user is selected. Please select a user first.");
            alert.showAndWait();
            return;
        }

        User selectedUser = cuList.get(selectedIndex);

        try {
            // Create a FXML loader for loading the Manage Edit Current User FXML file.
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ManageEditCurrentUser.fxml"));

            BorderPane editRoot = (BorderPane) fxmlLoader.load();
            ManageEditCurrentUserController editController = fxmlLoader
                    .<ManageEditCurrentUserController>getController();
            editController.setUserForEditing(selectedUser);
            Scene editScene = new Scene(editRoot, Main.SMALL_WINDOW_WIDTH, Main.ENORMOUS_WINDOW_HEIGHT);
            Stage editStage = new Stage();

            editStage.setScene(editScene);
            editStage.setTitle(Main.FINE_PAYMENT_WINDOW_TITLE);
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
        Stage stage = (Stage) editCurrentUserPane.getScene().getWindow();
        stage.close();
    }

}
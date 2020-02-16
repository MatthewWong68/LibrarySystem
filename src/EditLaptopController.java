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
 * Controller for the edit laptop window.
 *
 * @author Matthew
 * @version 1.2
 */
public class EditLaptopController {

    // Setup an arraylist that will store all laptop in it.
    private ArrayList<Laptop> lList = new ArrayList<>();

    @FXML
    private BorderPane editLaptopPane;

    @FXML
    private ListView<String> laptopList;

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
        lList = DataAdapter.readAllLaptops();

        displayIt();
    }

    /**
     * This method will display the laptop on the list view.
     */
    public void displayIt() {
        laptopList.getItems().clear();
        // Add each laptop to the displayed list
        for (Laptop l : lList) {
            laptopList.getItems().add(l.toString());
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
     * This method will bring you to the manage edit laptop page.
     *
     * @param event The event that will appear in the screen.
     */
    @FXML
    void goToManageEditLaptop(ActionEvent event) {
        // Get the index of the selected item in the displayed list
        int selectedIndex = laptopList.getSelectionModel().getSelectedIndex();

        // Check if librarian selected an item
        if (selectedIndex < 0) {
            // Show a message
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error: Cannot edit laptop");
            alert.setHeaderText(null);
            alert.setContentText("Cannot edit laptop as no laptop is selected. Please select a laptop first.");
            alert.showAndWait();
            return;
        }

        Laptop selectedLaptop = lList.get(selectedIndex);

        try {
            // Create a FXML loader for loading the Manage Edit Laptop FXML file.
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ManageEditLaptop.fxml"));

            BorderPane editRoot = (BorderPane) fxmlLoader.load();
            ManageEditLaptopController editController = fxmlLoader.<ManageEditLaptopController>getController();
            editController.setLaptopForEditing(selectedLaptop);
            Scene editScene = new Scene(editRoot, Main.SMALL_WINDOW_WIDTH, Main.ENORMOUS_WINDOW_HEIGHT);
            Stage editStage = new Stage();

            editStage.setScene(editScene);
            editStage.setTitle(Main.MANAGE_EDIT_LAPTOP_WINDOW_TITLE);
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
        Stage stage = (Stage) editLaptopPane.getScene().getWindow();
        stage.close();
    }
}

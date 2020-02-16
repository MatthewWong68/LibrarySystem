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
 * Controller for the edit book window.
 *
 * @author Matthew
 * @version 1.2
 */
public class EditBookController {

    // Setup an arraylist that will store all book in it.
    private ArrayList<Book> bList = new ArrayList<>();

    @FXML
    private BorderPane editBookPane;

    @FXML
    private ListView<String> bookList;

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
        bList = DataAdapter.readAllBooks();

        displayIt();
    }

    /**
     * This method will display the books on the list view.
     */
    public void displayIt() {
        bookList.getItems().clear();
        // Add all book to the displayed list.
        for (Book b : bList) {
            bookList.getItems().add(b.toString());
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
     * This method will bring you to the manage edit book page.
     *
     * @param event The event that will appear in the screen.
     */
    @FXML
    void goToManageEditBook(ActionEvent event) {
        // Get the index of the selected item in the displayed list
        int selectedIndex = bookList.getSelectionModel().getSelectedIndex();

        // Check if librarian selected an item
        if (selectedIndex < 0) {
            // Show a message
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error: Cannot edit book");
            alert.setHeaderText(null);
            alert.setContentText("Cannot edit book as no book is selected. Please select a book first.");
            alert.showAndWait();
            return;
        }

        Book selectedBook = bList.get(selectedIndex);

        try {
            // Create a FXML loader for loading the Manage Edit Book FXML file.
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ManageEditBook.fxml"));

            BorderPane editRoot = (BorderPane) fxmlLoader.load();
            ManageEditBookController editController = fxmlLoader.<ManageEditBookController>getController();
            editController.setBookForEditing(selectedBook);
            Scene editScene = new Scene(editRoot, Main.SMALL_WINDOW_WIDTH, Main.ENORMOUS_WINDOW_HEIGHT);
            Stage editStage = new Stage();

            editStage.setScene(editScene);
            editStage.setTitle(Main.MANAGE_EDIT_BOOK_WINDOW_TITLE);
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
        Stage stage = (Stage) editBookPane.getScene().getWindow();
        stage.close();
    }
}

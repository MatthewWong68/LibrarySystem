import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller for the editing current resources window.
 *
 * @author Matthew
 * @version 1.2
 */
public class EditCurrentResourcesController {

    @FXML
    private BorderPane editCurrentResourcesPane;

    @FXML
    private Button closeButton;

    @FXML
    private Button bookButton;

    @FXML
    private Button dvdButton;

    @FXML
    private Button laptopButton;

    /**
     * This method will bring you to the edit book page.
     *
     * @param event The event that will appear in the screen.
     */
    @FXML
    void goToEditBook(ActionEvent event) {

        try {
            // Create a FXML loader for loading the edit book FXML file.
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EditBook.fxml"));

            BorderPane editBook = (BorderPane) fxmlLoader.load();
            Scene editBookScene = new Scene(editBook, Main.LARGE_WINDOW_WIDTH, Main.ENORMOUS_WINDOW_HEIGHT);
            Stage editBookStage = new Stage();

            editBookStage.setScene(editBookScene);
            editBookStage.setTitle(Main.EDIT_BOOK_WINDOW_TITLE);
            editBookStage.initModality(Modality.APPLICATION_MODAL);
            // Show the edit book scene and wait for it to be closed
            editBookStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            // Quit the program (with an error code)
            System.exit(-1);
        }
    }

    /**
     * This method will bring you to the edit DVD page.
     *
     * @param event The event that will appear in the screen.
     */
    @FXML
    void goToEditDVD(ActionEvent event) {

        try {
            // Create a FXML loader for loading the edit DVD FXML file.
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EditDVD.fxml"));

            BorderPane editDVD = (BorderPane) fxmlLoader.load();
            Scene editDVDScene = new Scene(editDVD, Main.LARGE_WINDOW_WIDTH, Main.ENORMOUS_WINDOW_HEIGHT);
            Stage editDVDStage = new Stage();

            editDVDStage.setScene(editDVDScene);
            editDVDStage.setTitle(Main.EDIT_DVD_WINDOW_TITLE);
            editDVDStage.initModality(Modality.APPLICATION_MODAL);

            // Show the edit DVD scene and wait for it to be closed
            editDVDStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            // Quit the program (with an error code)
            System.exit(-1);
        }
    }

    /**
     * This method will bring you to the edit laptop page.
     *
     * @param event The event that will appear in the screen.
     */
    @FXML
    void goToEditLaptop(ActionEvent event) {

        try {
            // Create a FXML loader for loading the edit laptop FXML file.
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EditLaptop.fxml"));

            BorderPane editLaptop = (BorderPane) fxmlLoader.load();
            Scene editLaptopScene = new Scene(editLaptop, Main.LARGE_WINDOW_WIDTH, Main.ENORMOUS_WINDOW_HEIGHT);
            Stage editLaptopStage = new Stage();

            editLaptopStage.setScene(editLaptopScene);
            editLaptopStage.setTitle(Main.EDIT_LAPTOP_WINDOW_TITLE);
            editLaptopStage.initModality(Modality.APPLICATION_MODAL);
            // Show the edit laptop scene and wait for it to be closed
            editLaptopStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            // Quit the program (with an error code)
            System.exit(-1);
        }
    }

    /**
     * This method will bring you back to the Librarian page.
     *
     * @param event
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
        Stage stage = (Stage) editCurrentResourcesPane.getScene().getWindow();
        stage.close();
    }

}
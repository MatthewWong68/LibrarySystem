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
 * Controller for the create new resources window.
 *
 * @author Matthew
 * @version 1.2
 */
public class CreateNewResourcesController {

    @FXML
    private BorderPane createNewResourcesPane;

    @FXML
    private Button closeButton;

    @FXML
    private Button bookButton;

    @FXML
    private Button dvdButton;

    @FXML
    private Button laptopButton;

    /**
     * This method will bring the librarian to create a new book.
     *
     * @param event The event that will appear in the screen.
     */
    @FXML
    void goToCreateBook(ActionEvent event) {

        try {
            // Create a FXML loader for loading the create new book FXML file.
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CreateNewBook.fxml"));

            BorderPane createBook = (BorderPane) fxmlLoader.load();
            Scene createBookScene = new Scene(createBook, Main.SMALL_WINDOW_WIDTH, Main.GIGANTIC_WINDOW_HEIGHT);
            Stage createBookStage = new Stage();

            createBookStage.setScene(createBookScene);
            createBookStage.setTitle(Main.CREATE_BOOK_WINDOW_TITLE);
            createBookStage.initModality(Modality.APPLICATION_MODAL);
            // Show the create new book scene and wait for it to be closed
            createBookStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            // Quit the program (with an error code)
            System.exit(-1);
        }
    }

    /**
     * This method will bring the librarian to create a new DVD.
     *
     * @param event The event that will appear in the screen.
     */
    @FXML
    void goToCreateDVD(ActionEvent event) {

        try {
            // Create a FXML loader for loading the create new DVD FXML file.
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CreateNewDVD.fxml"));

            BorderPane createDVD = (BorderPane) fxmlLoader.load();
            Scene createDVDScene = new Scene(createDVD, Main.SMALL_WINDOW_WIDTH, Main.ENORMOUS_WINDOW_HEIGHT);
            Stage createDVDStage = new Stage();

            createDVDStage.setScene(createDVDScene);
            createDVDStage.setTitle(Main.CREATE_DVD_WINDOW_TITLE);
            createDVDStage.initModality(Modality.APPLICATION_MODAL);
            // Show the create new DVD scene and wait for it to be closed
            createDVDStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            // Quit the program (with an error code)
            System.exit(-1);
        }
    }

    /**
     * This method will bring the librarian to create a new laptop.
     *
     * @param event The event that will appear in the screen.
     */
    @FXML
    void goToCreateLaptop(ActionEvent event) {

        try {
            // Create a FXML loader for loading the create new laptop FXML file.
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CreateNewLaptop.fxml"));

            BorderPane createLaptop = (BorderPane) fxmlLoader.load();
            Scene createLaptopScene = new Scene(createLaptop, Main.SMALL_WINDOW_WIDTH, Main.ENORMOUS_WINDOW_HEIGHT);
            Stage createLaptopStage = new Stage();

            createLaptopStage.setScene(createLaptopScene);
            createLaptopStage.setTitle(Main.CREATE_LAPTOP_WINDOW_TITLE);
            createLaptopStage.initModality(Modality.APPLICATION_MODAL);
            // Show the create new laptop scene and wait for it to be closed
            createLaptopStage.showAndWait();
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
        Stage stage = (Stage) createNewResourcesPane.getScene().getWindow();
        stage.close();
    }

}

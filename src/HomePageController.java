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
 * Controller for the home page window.
 *
 * @author Matthew
 * @version 1.2
 */
public class HomePageController {

    @FXML
    private Button loginButton;

    @FXML
    private Button browseResourcesButton;

    /**
     * This method will bring the user to the inventory browser.
     *
     * @param event The event that will appear in the screen.
     */
    @FXML
    void goToInventoryBrowser(ActionEvent event) {

        try {
            // Create a FXML loader for loading the inventory browser FXML file.
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("InventoryBrowser.fxml"));

            BorderPane inventoryBrowser = (BorderPane) fxmlLoader.load();
            Scene inventoryBrowserScene = new Scene(inventoryBrowser, Main.INVENTORY_BROWSER_WINDOW_WIDTH,
                    Main.INVENTORY_BROWSER_WINDOW_HEIGHT);
            Stage inventoryBrowserStage = new Stage();

            inventoryBrowserStage.setScene(inventoryBrowserScene);
            inventoryBrowserStage.setTitle(Main.INVENTORY_BROWSER_WINDOW_TITLE);
            inventoryBrowserStage.initModality(Modality.APPLICATION_MODAL);
            // Show the inventory browser scene and wait for it to be closed
            inventoryBrowserStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            // Quit the program (with an error code)
            System.exit(-1);
        }
    }

    /**
     * This method will bring the user to the user page.
     *
     * @param event The event that will appear in the screen.
     */
    @FXML
    void goToLoginPage(ActionEvent event) {

        try {
            // Create a FXML loader for loading the login page FXML file.
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LoginPage.fxml"));

            BorderPane loginPage = (BorderPane) fxmlLoader.load();
            Scene loginScene = new Scene(loginPage, Main.LARGE_WINDOW_WIDTH, Main.LARGE_WINDOW_HEIGHT);
            Stage loginStage = new Stage();

            loginStage.setScene(loginScene);
            loginStage.setTitle(Main.LOGINPAGE_WINDOW_TITLE);
            loginStage.initModality(Modality.APPLICATION_MODAL);
            // Show the login scene and wait for it to be closed
            loginStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            // Quit the program (with an error code)
            System.exit(-1);
        }
    }
}

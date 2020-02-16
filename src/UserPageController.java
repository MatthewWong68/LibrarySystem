import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Controller for the user page window.
 *
 * @author Matthew
 * @version 1.2
 */
public class UserPageController {

    @FXML
    private BorderPane userPane;

    @FXML
    private Label usernameLabel;

    @FXML
    private Label fineLabel;

    @FXML
    private Label overdueResourceLabel;

    @FXML
    private Button borrowedItemsButton;

    @FXML
    private Button requestedItemsButton;

    @FXML
    private Button reservedItemsButton;

    @FXML
    private Button avatarButton;

    @FXML
    private Button transactionHistoryButton;

    @FXML
    private Button browseResourcesButton;

    @FXML
    private Button logoutButton;

    /**
     * Initialize the controller.
     */

    public void initialize() {
        usernameLabel.setText(LoginPageController.getCurrentAccount().getFirstName() + " "
                + LoginPageController.getCurrentAccount().getLastName());
        try {
            double fineAmount = DataAdapter.getUserUnpaidBalance((User) LoginPageController.getCurrentAccount()) / 100;
            fineLabel.setText("Unpaid Fines: £" + fineAmount);
        } catch (SQLException e) {
            System.out.println(e);
        }
        overdueResourceLabel.setText("Number of overdue resources: "
                + BorrowHandler.getNumOverdueResources((User) LoginPageController.getCurrentAccount()));
    }

    /**
     * This method will bring the user to the borrowed items page.
     *
     * @param event The event that will appear in the screen.
     */
    @FXML
    void goToBorrowedItems(ActionEvent event) {

        try {
            // Create a FXML loader for loading the borrowed items FXML file.
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("BorrowedItems.fxml"));

            BorderPane borrowedItems = (BorderPane) fxmlLoader.load();
            Scene borrowedItemsScene = new Scene(borrowedItems, Main.SMALL_WINDOW_WIDTH, Main.SMALL_WINDOW_HEIGHT);
            Stage borrowedItemsStage = new Stage();

            borrowedItemsStage.setScene(borrowedItemsScene);
            borrowedItemsStage.setTitle(Main.BORROWED_ITEMS_WINDOW_TITLE);
            borrowedItemsStage.initModality(Modality.APPLICATION_MODAL);
            // Show the borrowed items scene and wait for it to be closed
            borrowedItemsStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            // Quit the program (with an error code)
            System.exit(-1);
        }

    }

    /**
     * This method will bring the user to the choose image page.
     *
     * @param event The event that will appear in the screen.
     */
    @FXML
    void goToChooseImagePage(ActionEvent event) {
        try {
            // Create a FXML loader for loading the choose image FXML file.
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Avatar.fxml"));

            BorderPane chooseImagePage = (BorderPane) fxmlLoader.load();
            Scene chooseImagePageScene = new Scene(chooseImagePage, Main.LARGE_WINDOW_WIDTH, Main.LARGE_WINDOW_HEIGHT);
            Stage chooseImagePageStage = new Stage();

            chooseImagePageStage.setScene(chooseImagePageScene);
            chooseImagePageStage.setTitle(Main.CHOOSE_IMAGE_PAGE_WINDOW_TITLE);
            chooseImagePageStage.initModality(Modality.APPLICATION_MODAL);
            // Show the choose image scene and wait for it to be closed
            chooseImagePageStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            // Quit the program (with an error code)
            System.exit(-1);
        }
    }

    /**
     * This method will bring the librarian to the inventory browser page.
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
     * This method will bring you back to the Login page.
     *
     * @param event The event that will appear in the screen.
     */
    @FXML
    void goToLoginPage(ActionEvent event) {
        closeWindow();
    }

    /**
     * This method will bring the user to the requested items page.
     *
     * @param event The event that will appear in the screen.
     */
    @FXML
    void goToRequestedItems(ActionEvent event) {

        try {
            // Create a FXML loader for loading the requested items FXML file.
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("RequestedItems.fxml"));

            BorderPane requestedItems = (BorderPane) fxmlLoader.load();
            Scene requestedItemsScene = new Scene(requestedItems, Main.SMALL_WINDOW_WIDTH, Main.SMALL_WINDOW_HEIGHT);
            Stage requestedItemsStage = new Stage();

            requestedItemsStage.setScene(requestedItemsScene);
            requestedItemsStage.setTitle(Main.REQUESTED_ITEMS_WINDOW_TITLE);
            requestedItemsStage.initModality(Modality.APPLICATION_MODAL);
            // Show the requested items scene and wait for it to be closed
            requestedItemsStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            // Quit the program (with an error code)
            System.exit(-1);
        }
    }

    /**
     * This method will bring the user to the reserved items page.
     *
     * @param event The event that will appear in the screen.
     */
    @FXML
    void goToReservedItems(ActionEvent event) {

        try {
            // Create a FXML loader for loading the reserved items FXML file.
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ReservedItems.fxml"));

            BorderPane reservedItems = (BorderPane) fxmlLoader.load();
            Scene reservedItemsScene = new Scene(reservedItems, Main.SMALL_WINDOW_WIDTH, Main.SMALL_WINDOW_HEIGHT);
            Stage reservedItemsStage = new Stage();

            reservedItemsStage.setScene(reservedItemsScene);
            reservedItemsStage.setTitle(Main.RESERVED_ITEMS_WINDOW_TITLE);
            reservedItemsStage.initModality(Modality.APPLICATION_MODAL);
            // Show the reserved items scene and wait for it to be closed
            reservedItemsStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            // Quit the program (with an error code)
            System.exit(-1);
        }

    }

    /**
     * This method will bring the user to the transaction history page.
     *
     * @param event The event that will appear in the screen.
     */
    @FXML
    void goToTransactionHistory(ActionEvent event) {

        try {
            // Create a FXML loader for loading the transaction history FXML file.
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TransactionHistory.fxml"));

            BorderPane transactionHistory = (BorderPane) fxmlLoader.load();
            Scene transactionHistoryScene = new Scene(transactionHistory, Main.SMALL_WINDOW_WIDTH,
                    Main.SMALL_WINDOW_HEIGHT);
            Stage transactionHistoryStage = new Stage();

            transactionHistoryStage.setScene(transactionHistoryScene);
            transactionHistoryStage.setTitle(Main.TRANSACTION_HISTORY_WINDOW_TITLE);
            transactionHistoryStage.initModality(Modality.APPLICATION_MODAL);
            // Show the transaction history scene and wait for it to be closed
            transactionHistoryStage.showAndWait();
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
        Stage stage = (Stage) userPane.getScene().getWindow();
        stage.close();
    }
}

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
 * Controller for the librarian page window.
 *
 * @author Matthew
 * @version 1.2
 */
public class LibrarianPageController {

    @FXML
    private BorderPane librarianPane;

    @FXML
    private Button selectUserButton;

    @FXML
    private Button collectResourceButton;

    @FXML
    private Button borrowResourcesButton;

    @FXML
    private Button returnResourcesButton;

    @FXML
    private Button finePaymentButton;

    @FXML
    private Button avatarButton;

    @FXML
    private Button viewBorrowedResourcesButton;

    @FXML
    private Button viewOverdueResourcesButton;

    @FXML
    private Button browseResourcesButton;

    @FXML
    private Button createUserButton;

    @FXML
    private Button createResourcesButton;

    @FXML
    private Button editUserButton;

    @FXML
    private Button editResourcesButton;

    @FXML
    private Button logoutButton;

    /**
     * This method will bring the librarian to the borrow resources page.
     *
     * @param event The event that will appear in the screen.
     */
    @FXML
    void borrowResources(ActionEvent event) {

        try {
            // Create a FXML loader for loading the borrow resources FXML file.
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("BorrowResources.fxml"));

            BorderPane borrowResources = (BorderPane) fxmlLoader.load();
            Scene borrowResourcesScene = new Scene(borrowResources, Main.SMALL_WINDOW_WIDTH, Main.SMALL_WINDOW_HEIGHT);
            Stage borrowResourcesStage = new Stage();

            borrowResourcesStage.setScene(borrowResourcesScene);
            borrowResourcesStage.setTitle(Main.BORROW_RESOURCES_WINDOW_TITLE);
            borrowResourcesStage.initModality(Modality.APPLICATION_MODAL);
            // Show the borrow resources scene and wait for it to be closed
            borrowResourcesStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            // Quit the program (with an error code)
            System.exit(-1);
        }
    }

    /**
     * This method will bring the librarian to the return resources page.
     *
     * @param event The event that will appear in the screen.
     */
    @FXML
    void returnResources(ActionEvent event) {

        try {
            // Create a FXML loader for loading the return resources FXML file.
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ReturnResources.fxml"));

            BorderPane returnResources = (BorderPane) fxmlLoader.load();
            Scene returnResourcesScene = new Scene(returnResources, Main.SMALL_WINDOW_WIDTH, Main.SMALL_WINDOW_HEIGHT);
            Stage returnResourcesStage = new Stage();

            returnResourcesStage.setScene(returnResourcesScene);
            returnResourcesStage.setTitle(Main.RETURN_RESOURCES_WINDOW_TITLE);
            returnResourcesStage.initModality(Modality.APPLICATION_MODAL);
            // Show the return resources scene and wait for it to be closed
            returnResourcesStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            // Quit the program (with an error code)
            System.exit(-1);
        }
    }

    /**
     * This method will bring the librarian to the collect resources page.
     *
     * @param event The event that will appear in the screen.
     */
    @FXML
    void collectResource() {
        try {
            // Create a FXML loader for loading the collect resource FXML file.
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CollectResource.fxml"));

            BorderPane collectResource = (BorderPane) fxmlLoader.load();
            Scene collectResourceScene = new Scene(collectResource, Main.SMALL_WINDOW_WIDTH, Main.MEDIUM_WINDOW_HEIGHT);
            Stage collectResourceStage = new Stage();

            collectResourceStage.setScene(collectResourceScene);
            collectResourceStage.setTitle(Main.VIEW_A_COPYS_BORROWING_HISTORY_WINDOW_TITLE);
            collectResourceStage.initModality(Modality.APPLICATION_MODAL);
            // Show the collect resource scene and wait for it to be closed
            collectResourceStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            // Quit the program (with an error code)
            System.exit(-1);
        }
    }

    /**
     * This method will bring the librarian to the page which can view a particular
     * copy's borrowing history.
     *
     * @param event The event that will appear in the screen.
     */
    @FXML
    void goToViewACopysBorrowingHistory(ActionEvent event) {

        try {
            // Create a FXML loader for loading the view a copy's borrowing history FXML
            // file.
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ViewACopysBorrowingHistory.fxml"));

            BorderPane collectResource = (BorderPane) fxmlLoader.load();
            Scene collectResourceScene = new Scene(collectResource, Main.LARGE_WINDOW_WIDTH,
                    Main.ENORMOUS_WINDOW_HEIGHT);
            Stage collectResourceStage = new Stage();

            collectResourceStage.setScene(collectResourceScene);
            collectResourceStage.setTitle(Main.VIEW_A_COPYS_BORROWING_HISTORY_WINDOW_TITLE);
            collectResourceStage.initModality(Modality.APPLICATION_MODAL);
            // Show the copy's borrowing history scene and wait for it to be closed
            collectResourceStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            // Quit the program (with an error code)
            System.exit(-1);
        }
    }

    /**
     * This method will bring the librarian to the create new resources page.
     *
     * @param event The event that will appear in the screen.
     */
    @FXML
    void createNewResources(ActionEvent event) {

        try {
            // Create a FXML loader for loading the create new resources FXML file.
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CreateNewResources.fxml"));

            BorderPane createNewResources = (BorderPane) fxmlLoader.load();
            Scene createNewResourcesScene = new Scene(createNewResources, Main.SMALL_WINDOW_WIDTH,
                    Main.MEDIUM_WINDOW_HEIGHT);
            Stage createNewResourcesStage = new Stage();

            createNewResourcesStage.setScene(createNewResourcesScene);
            createNewResourcesStage.setTitle(Main.CREATE_NEW_RESOURCES_WINDOW_TITLE);
            createNewResourcesStage.initModality(Modality.APPLICATION_MODAL);
            // Show the create new resources scene and wait for it to be closed
            createNewResourcesStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            // Quit the program (with an error code)
            System.exit(-1);
        }
    }

    /**
     * This method will bring the librarian to the create new user page.
     *
     * @param event The event that will appear in the screen.
     */
    @FXML
    void createNewUser(ActionEvent event) {

        try {
            // Create a FXML loader for loading the create new user FXML file.
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CreateNewUser.fxml"));

            BorderPane createNewUser = (BorderPane) fxmlLoader.load();
            Scene createNewUserScene = new Scene(createNewUser, Main.MEDIUM_WINDOW_WIDTH, Main.LARGE_WINDOW_HEIGHT);
            Stage createNewUserStage = new Stage();

            createNewUserStage.setScene(createNewUserScene);
            createNewUserStage.setTitle(Main.CREATE_NEW_USER_WINDOW_TITLE);
            createNewUserStage.initModality(Modality.APPLICATION_MODAL);
            // Show the create new user scene and wait for it to be closed
            createNewUserStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            // Quit the program (with an error code)
            System.exit(-1);
        }
    }

    /**
     * This method will bring the librarian to the edit current resource page.
     *
     * @param event The event that will appear in the screen.
     */
    @FXML
    void editCurrentResources(ActionEvent event) {

        try {
            // Create a FXML loader for loading the edit current resources FXML file.
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EditCurrentResources.fxml"));

            BorderPane editCurrentResources = (BorderPane) fxmlLoader.load();
            Scene editCurrentResourcesScene = new Scene(editCurrentResources, Main.SMALL_WINDOW_WIDTH,
                    Main.SMALL_WINDOW_HEIGHT);
            Stage editCurrentResourcesStage = new Stage();

            editCurrentResourcesStage.setScene(editCurrentResourcesScene);
            editCurrentResourcesStage.setTitle(Main.EDIT_CURRENT_RESOURCES_WINDOW_TITLE);
            editCurrentResourcesStage.initModality(Modality.APPLICATION_MODAL);
            // Show the edit current resources scene and wait for it to be closed
            editCurrentResourcesStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            // Quit the program (with an error code)
            System.exit(-1);
        }
    }

    /**
     * This method will bring the librarian to the edit current user page.
     *
     * @param event The event that will appear in the screen.
     */
    @FXML
    void editCurrentUser(ActionEvent event) {

        try {
            // Create a FXML loader for loading the edit current user FXML file.
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EditCurrentUser.fxml"));

            BorderPane editCurrentUser = (BorderPane) fxmlLoader.load();
            Scene editCurrentUserScene = new Scene(editCurrentUser, Main.LARGE_WINDOW_WIDTH,
                    Main.ENORMOUS_WINDOW_HEIGHT);
            Stage editCurrentUserStage = new Stage();

            editCurrentUserStage.setScene(editCurrentUserScene);
            editCurrentUserStage.setTitle(Main.EDIT_CURRENT_USER_WINDOW_TITLE);
            editCurrentUserStage.initModality(Modality.APPLICATION_MODAL);
            // Show the edit current user scene and wait for it to be closed
            editCurrentUserStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            // Quit the program (with an error code)
            System.exit(-1);
        }
    }

    /**
     * This method will bring the librarian to the fine payment page.
     *
     * @param event The event that will appear in the screen.
     */
    @FXML
    void finePayment(ActionEvent event) {

        try {
            // Create a FXML loader for loading the fine payment FXML file.
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ManageFine.fxml"));

            BorderPane finePayment = (BorderPane) fxmlLoader.load();
            Scene finePaymentScene = new Scene(finePayment, Main.SMALL_WINDOW_WIDTH, Main.SMALL_WINDOW_HEIGHT);
            Stage finePaymentStage = new Stage();

            finePaymentStage.setScene(finePaymentScene);
            finePaymentStage.setTitle(Main.FINE_PAYMENT_WINDOW_TITLE);
            finePaymentStage.initModality(Modality.APPLICATION_MODAL);
            // Show the fine payment scene and wait for it to be closed
            finePaymentStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            // Quit the program (with an error code)
            System.exit(-1);
        }
    }

    /**
     * This method will bring the librarian to the choose image page.
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
     * This method will bring the librarian to the view overdue resources page.
     *
     * @param event The event that will appear in the screen.
     */
    @FXML
    void overdueResourcesView(ActionEvent event) {

        try {
            // Create a FXML loader for loading the overdue resources FXML file.
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("OverdueResources.fxml"));

            BorderPane overdueResources = (BorderPane) fxmlLoader.load();
            Scene overdueResourcesScene = new Scene(overdueResources, Main.LARGE_WINDOW_WIDTH,
                    Main.ENORMOUS_WINDOW_HEIGHT);
            Stage overdueResourcesStage = new Stage();

            overdueResourcesStage.setScene(overdueResourcesScene);
            overdueResourcesStage.setTitle(Main.OVERDUE_RESOURCES_WINDOW_TITLE);
            overdueResourcesStage.initModality(Modality.APPLICATION_MODAL);
            // Show the overdue resources scene and wait for it to be closed
            overdueResourcesStage.showAndWait();
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
        Stage stage = (Stage) librarianPane.getScene().getWindow();
        stage.close();
    }
}
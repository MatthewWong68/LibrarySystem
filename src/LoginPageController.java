import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Controller for the login page window.
 *
 * @author Matthew
 * @version 1.2
 */
public class LoginPageController {

    // Setup the information that the librarian will type in.
    private static Account loggedInAccount;
    private String uUsername;
    private String lUsername;
    private Alert alert = new Alert(AlertType.ERROR);

    @FXML
    private TextField userUsername;

    @FXML
    private Button goToUserPageButton;

    @FXML
    private TextField librarianUsername;

    @FXML
    private Button goToLibrarianPageButton;

    /**
     * This method will get the current account logged in.
     *
     * @return loggedInAccount The account which is current logged in.
     */
    public static Account getCurrentAccount() {
        return loggedInAccount;
    }

    /**
     * This method sets the current account.
     *
     * @param newAccount The account which is current logged in.
     */
    public void setCurrentAccount(Account newAccount) {
        loggedInAccount = newAccount;
    }

    /**
     * This method will store the information that the user typed in into the
     * database. And go into the user page.
     *
     * @param event The event that will appear in the screen.
     * @throws SQLException Throws when database error occurs.
     */
    @FXML
    void goToUserPage(ActionEvent event) throws SQLException {

        uUsername = userUsername.getText();

        if (uUsername.isEmpty()) {
            alert.setHeaderText("Please enter your username!");
            alert.showAndWait();

        } else if (DataAdapter.checkUser(uUsername, 0)) {
            loggedInAccount = DataAdapter.readAccount(uUsername);

            try {
                // Create a FXML loader for loading the user page FXML file.
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UserPage.fxml"));

                BorderPane userPage = (BorderPane) fxmlLoader.load();
                Scene userScene = new Scene(userPage, Main.LARGE_WINDOW_WIDTH, Main.LARGE_WINDOW_HEIGHT);
                Stage userStage = new Stage();

                userStage.setScene(userScene);
                userStage.setTitle(Main.USERPAGE_WINDOW_TITLE);
                userStage.initModality(Modality.APPLICATION_MODAL);
                // Show the user page scene and wait for it to be closed
                userStage.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
                // Quit the program (with an error code)
                System.exit(-1);
            }
        } else {
            alert.setHeaderText("Account not found");
            alert.showAndWait();
        }

    }

    /**
     * This method will store the information that the librarian typed in into the
     * database. And go into the librarian page.
     *
     * @param event The event that will appear in the screen.
     * @throws SQLException Throws when database error occurs.
     */
    @FXML
    void goToLibrarianPage(ActionEvent event) throws SQLException {

        lUsername = librarianUsername.getText();

        if (lUsername.isEmpty()) {
            alert.setHeaderText("Please enter your username!");
            alert.showAndWait();

        } else if (DataAdapter.checkUser(lUsername, 1)) {
            loggedInAccount = DataAdapter.readAccount(lUsername);

            try {
                // Create a FXML loader for loading the librarian page FXML file.
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LibrarianPage.fxml"));

                BorderPane librarianPage = (BorderPane) fxmlLoader.load();
                Scene librarianScene = new Scene(librarianPage, Main.LARGE_WINDOW_WIDTH, Main.LARGE_WINDOW_HEIGHT);
                Stage librarianStage = new Stage();

                librarianStage.setScene(librarianScene);
                librarianStage.setTitle(Main.LIBRARIANPAGE_WINDOW_TITLE);
                librarianStage.initModality(Modality.APPLICATION_MODAL);
                // Show the librarian page scene and wait for it to be closed
                librarianStage.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
                // Quit the program (with an error code)
                System.exit(-1);
            }
        } else {
            alert.setHeaderText("Account not found");
            alert.showAndWait();
        }
    }

}
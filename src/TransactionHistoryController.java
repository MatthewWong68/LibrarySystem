import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Controller for the transaction history window.
 *
 * @author Matthew
 * @version 1.2
 */
public class TransactionHistoryController {

    // The main list that will store all the transaction history.
    @FXML
    private ListView<String> transactionHistoryList;

    @FXML
    private BorderPane transactionHistoryPane;

    @FXML
    private Button closeButton;

    /**
     * Initialize the controller.
     *
     * @throws SQLException Throws when database error occurs.
     */
    public void initialize() throws SQLException {
        // Create some initial borrowed items.
        ArrayList<Transaction> tr = DataAdapter.getAllUserTransactions((User) LoginPageController.getCurrentAccount());

        displayIt(tr);
    }

    /**
     * This method will display the item on the list view.
     *
     * @param tr ArrayList of transaction history.
     */
    public void displayIt(ArrayList<Transaction> tr) {
        // Add each transaction history to the displayed list
        for (Transaction b : tr) {
            transactionHistoryList.getItems().add(b.toString());
        }

    }

    /**
     * This method will bring you back to the user page.
     *
     * @param event The event that will appear in the screen.
     */
    @FXML
    void goToUserPage(ActionEvent event) {
        closeWindow();
    }

    /**
     * This method will close the current stage.
     */
    @FXML
    void closeWindow() {
        Stage stage = (Stage) transactionHistoryPane.getScene().getWindow();
        stage.close();
    }
}

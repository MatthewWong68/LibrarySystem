import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Controller for the view a copy's borrowing history window.
 *
 * @author Matthew
 * @version 1.2
 */
public class ViewACopysBorrowingHistoryController {

    @FXML
    private ListView<String> aCopysBorrowingHistory;

    @FXML
    private BorderPane viewACopysBorrowingHistoryPane;

    @FXML
    private Button viewButton;

    @FXML
    private Button closeButton;

    @FXML
    private TextField CopyID;

    /**
     * Initialize the controller.
     */
    public void initialize() {
        // Create some initial borrowed items.
    }

    /**
     * This method will refresh the arraylist.
     *
     * @param event The event that will appear in the screen.
     */
    @FXML
    void RefreshArrayList(ActionEvent event) {
        // get list of borrowing history
        aCopysBorrowingHistory.getItems().clear();
        try {
            ArrayList<CopyHistory> copyHistory = DataAdapter.getCopyHistory(Integer.parseInt(CopyID.getText()));
            for (CopyHistory current : copyHistory) {
                aCopysBorrowingHistory.getItems().add(current.toString());
            }
        } catch (SQLException e) {
            System.out.println(e);
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
        Stage stage = (Stage) viewACopysBorrowingHistoryPane.getScene().getWindow();
        stage.close();
    }
}

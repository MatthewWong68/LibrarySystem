import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Controller for the create new DVD window.
 *
 * @author Matthew
 * @version 1.2
 */
public class CreateNewDVDController {

    // Setup the information that the librarian will type in.
    private String newTitle;
    private int newYear;
    private String newDirector;
    private int newRuntime;
    private String newSubtitleLanguage;
    private String newImagePath;
    private String newLanguage;
    private int duration;
    private int numOfCopies;

    private Alert alert = new Alert(AlertType.ERROR);

    @FXML
    private TextField numofCopiesTextField;

    @FXML
    private TextField durationTextField;

    @FXML
    private BorderPane createNewDVDPane;

    @FXML
    private Button createDVDButton;

    @FXML
    private TextField title;

    @FXML
    private TextField year;

    @FXML
    private TextField director;

    @FXML
    private TextField runtime;

    @FXML
    private TextField subtitleLanguage;

    @FXML
    private TextField language;

    @FXML
    private TextField imagepath;

    /**
     * This method will store the information that the librarian typed in into the
     * database. And return back to the librarian page.
     *
     * @param event The event that will appear in the screen.
     * @throws Exception Throws when the librarian entered the wrong value to the
     *                   textfield.
     */
    @FXML
    void storeAndGoToLibrarianPage(ActionEvent event) throws Exception {

        newTitle = title.getText();
        newYear = Integer.parseInt(year.getText());
        newDirector = director.getText();
        newRuntime = Integer.parseInt(runtime.getText());
        newSubtitleLanguage = subtitleLanguage.getText();
        newImagePath = imagepath.getText();
        newLanguage = language.getText();
        duration = Integer.parseInt(durationTextField.getText());
        numOfCopies = Integer.parseInt(numofCopiesTextField.getText());

        if (newTitle.isEmpty() || Integer.toString(newYear).isEmpty() || newDirector.isEmpty()
                || Integer.toString(newRuntime).isEmpty() || newSubtitleLanguage.isEmpty() || newImagePath.isEmpty()
                || newLanguage.isEmpty() || numofCopiesTextField.getText() == "" || durationTextField.getText() == "") {
            alert.setHeaderText("Please enter all the information!");
            alert.showAndWait();

        } else {

            DVD d = new DVD(newTitle, newYear, newImagePath, numOfCopies, newDirector, newRuntime, newLanguage,
                    newSubtitleLanguage);
            Resource r = d;
            // Create the new DVD in the database using method in DataAdapter.
            DataAdapter.writeResource(r);
            DataAdapter.writeData(d);

            for (int i = 0; i < numOfCopies; i++) {
                DataAdapter.addCopy(r, duration);
            }
            closeWindow();
        }

    }

    /**
     * This method will close the current stage.
     */
    @FXML
    void closeWindow() {
        Stage stage = (Stage) createNewDVDPane.getScene().getWindow();
        stage.close();
    }

}

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Controller for the create new book window.
 *
 * @author Matthew
 * @version 1.2
 */
public class CreateNewBookController {

    // Setup the information that the librarian will type in.
    private String newTitle;
    private int newYear;
    private String newAuthor;
    private String newPublisher;
    private String newISBN;
    private String newGenre;
    private String newImagePath;
    private String newLanguage;
    private int numOfCopies;
    private int duration;

    private Alert alert = new Alert(AlertType.ERROR);

    @FXML
    private BorderPane createNewBookPane;

    @FXML
    private Button createBookButton;

    @FXML
    private TextField numOfCopiesTextField;

    @FXML
    private TextField title;

    @FXML
    private TextField durationTextField;

    @FXML
    private TextField year;

    @FXML
    private TextField author;

    @FXML
    private TextField publisher;

    @FXML
    private TextField genre;

    @FXML
    private TextField isbn;

    @FXML
    private TextField imagepath;

    @FXML
    private TextField language;

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
        newAuthor = author.getText();
        newPublisher = publisher.getText();
        newISBN = isbn.getText();
        newGenre = genre.getText();
        newImagePath = imagepath.getText();
        newLanguage = language.getText();
        duration = Integer.parseInt(durationTextField.getText());
        numOfCopies = Integer.parseInt(numOfCopiesTextField.getText());

        if (newTitle.isEmpty() || Integer.toString(newYear).isEmpty() || newAuthor.isEmpty() || newPublisher.isEmpty()
                || newISBN.isEmpty() || newGenre.isEmpty() || newImagePath.isEmpty() || newLanguage.isEmpty()
                || numOfCopiesTextField.getText() == "" || durationTextField.getText() == "") {
            alert.setHeaderText("Please enter all the information!");
            alert.showAndWait();

        } else {

            Book b = new Book(newTitle, newYear, newImagePath, newAuthor, newPublisher, newGenre, newISBN, newLanguage,
                    numOfCopies);
            Resource r = b;
            // Create the new book in the database using method in DataAdapter.
            DataAdapter.writeResource(r);
            DataAdapter.writeData(r);

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
        Stage stage = (Stage) createNewBookPane.getScene().getWindow();
        stage.close();
    }

}

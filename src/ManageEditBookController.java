import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.sql.SQLException;

/**
 * Controller for the manage edit book window.
 *
 * @author Matthew
 * @version 1.2
 */
public class ManageEditBookController {

    // The book being edited.
    Book bookBeingEdit;

    @FXML
    private BorderPane manageEditBookPane;

    @FXML
    private Button saveButton;

    @FXML
    private Button closeButton;

    @FXML
    private TextField titleTextField;

    @FXML
    private TextField yearTextField;

    @FXML
    private TextField thumbnailPathTextField;

    @FXML
    private TextField numberOfCopiesTextField;

    @FXML
    private TextField authorTextField;

    @FXML
    private TextField publisherTextField;

    @FXML
    private TextField genreTextField;

    @FXML
    private TextField isbnTextField;

    @FXML
    private TextField languageTextField;

    /**
     * Initialize the controller.
     */
    public void initialize() {
        saveButton.setOnAction(e -> {
            try {
                handleSaveButtonAction();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });

        closeButton.setOnAction(e -> {
            handleCloseButtonAction();
        });
    }

    /**
     * Set the book that is being edited.
     * When this window is closed, the changes will be set in this book object.
     *
     * @param book Book to be edited.
     */
    public void setBookForEditing(Book book) {
        // Keep a reference to the user that we are editing.
        this.bookBeingEdit = book;

        // Update the GUI to show the existing data.
        titleTextField.setText(bookBeingEdit.getTitle());
        yearTextField.setText(Integer.toString(bookBeingEdit.getYear()));
        thumbnailPathTextField.setText(bookBeingEdit.getThumbnailPath());
        numberOfCopiesTextField.setText(Integer.toString(bookBeingEdit.getNumCopies()));
        authorTextField.setText(bookBeingEdit.getAuthor());
        publisherTextField.setText(bookBeingEdit.getPublisher());
        genreTextField.setText(bookBeingEdit.getGenre());
        isbnTextField.setText(bookBeingEdit.getIsbn());
        languageTextField.setText(bookBeingEdit.getLanguage());
    }

    /**
     * Handle the close button.
     * Close the edit and close the window.
     */
    private void handleCloseButtonAction() {
        Stage stage = (Stage) manageEditBookPane.getScene().getWindow();
        stage.close();
    }

    /**
     * Handle the save button. Save the changes and close the window.
     *
     * @throws SQLException Throws when database error occurs.
     */
    private void handleSaveButtonAction() throws SQLException {

        bookBeingEdit.setTitle(titleTextField.getText());
        bookBeingEdit.setYear(Integer.parseInt(yearTextField.getText()));
        bookBeingEdit.setThumbnailPath(thumbnailPathTextField.getText());
        bookBeingEdit.setNumCopies(Integer.parseInt(numberOfCopiesTextField.getText()));
        bookBeingEdit.setAuthor(authorTextField.getText());
        bookBeingEdit.setPublisher(publisherTextField.getText());
        bookBeingEdit.setGenre(genreTextField.getText());
        bookBeingEdit.setIsbn(isbnTextField.getText());
        bookBeingEdit.setLanguage(languageTextField.getText());

        // Updating the book information using the method in DataAdapter.
        DataAdapter.updateBook(bookBeingEdit);

        closeWindow();
    }

    /**
     * Close the window.
     */
    private void closeWindow() {
        Stage stage = (Stage) manageEditBookPane.getScene().getWindow();
        stage.close();
    }

}

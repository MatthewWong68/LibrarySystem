import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Controller for the create new laptop window.
 *
 * @author Matthew
 * @version 1.2
 */
public class CreateNewLaptopController {

    // Setup the information that the librarian will type in.
    private String newTitle;
    private int newYear;
    private String newInstalledOS;
    private String newManufacturer;
    private String newModel;
    private String newImagePath;
    private int duration;
    private int copyAmount;

    private Alert alert = new Alert(AlertType.ERROR);

    @FXML
    private TextField copyCountTextField;

    @FXML
    private TextField durationTextField;

    @FXML
    private BorderPane createNewLaptopPane;

    @FXML
    private Button createLaptopButton;

    @FXML
    private TextField title;

    @FXML
    private TextField year;

    @FXML
    private TextField manufacturer;

    @FXML
    private TextField model;

    @FXML
    private TextField imagepath;

    @FXML
    private TextField installedOS;

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
        newModel = model.getText();
        newManufacturer = manufacturer.getText();
        newInstalledOS = installedOS.getText();
        newImagePath = imagepath.getText();
        duration = Integer.parseInt(durationTextField.getText());
        copyAmount = Integer.parseInt(copyCountTextField.getText());

        if (newTitle.isEmpty() || Integer.toString(newYear).isEmpty() || newModel.isEmpty() || newManufacturer.isEmpty()
                || newInstalledOS.isEmpty() || newImagePath.isEmpty() || durationTextField.getText() == ""
                || durationTextField.getText() == "") {
            alert.setHeaderText("Please enter all the information!");
            alert.showAndWait();

        } else {

            Laptop l = new Laptop(newTitle, newYear, newImagePath, copyAmount, newManufacturer, newModel,
                    newInstalledOS);
            Resource r = l;
            // Create the new laptop in the database using method in DataAdapter.
            DataAdapter.writeResource(r);
            DataAdapter.writeData(l);

            for (int i = 0; i < copyAmount; i++) {
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
        Stage stage = (Stage) createNewLaptopPane.getScene().getWindow();
        stage.close();
    }

}

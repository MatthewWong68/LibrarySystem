import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Controller for the inventory browser window.
 *
 * @author Matthew, Josh
 * @version 1.2
 */
public class InventoryBrowserController {

    // Setup the information that the user will type in.
    private static boolean laptopSelected = true;
    private static boolean bookSelected = true;
    private static boolean dvdSelected = true;
    private Resource selectedResource = null;
    private String selectedCopyId = null;

    @FXML
    private BorderPane inventoryBrowserPane;

    @FXML
    private ListView<String> searchOutput;

    @FXML
    private ListView<String> copiesOutput;

    @FXML
    private TextField searchInput;

    @FXML
    private Button searchButton;

    @FXML
    private Button reserveButton;

    @FXML
    private CheckBox filterDVDButton;

    @FXML
    private CheckBox filterBookButton;

    @FXML
    private CheckBox filterLaptopButton;

    @FXML
    private Button closeButton;

    @FXML
    private Label line1;

    @FXML
    private Label line2;

    @FXML
    private Label line3;

    @FXML
    private Label line4;

    /**
     * This method will filter the resources.
     *
     * @param allResources The arraylist of all resources.
     * @param userIn       The user who is currently browsing the resources.
     * @return output The arraylist of all filtered resources.
     */
    static ArrayList<Resource> filter(ArrayList<Resource> allResources, String userIn) {
        ArrayList<Resource> output = new ArrayList<Resource>();
        for (Resource currentResource : allResources) {
            if ((bookSelected && currentResource.getType() == 3) | (laptopSelected && currentResource.getType() == 2)
                    | (dvdSelected && currentResource.getType() == 1)) {
                if (currentResource.getUniqueId().toLowerCase().contains(userIn.toLowerCase())
                        | currentResource.getTitle().toLowerCase().contains(userIn.toLowerCase())) {
                    output.add(currentResource);
                }
            }
        }
        return output;
    }

    /**
     * Initialize the controller.
     */
    public void initialize() {
        if (LoginPageController.getCurrentAccount() == null
                || LoginPageController.getCurrentAccount() instanceof Librarian) {
            reserveButton.setDisable(true);
        }
    }

    /**
     * This method will filter the resource with book type.
     *
     * @param event The event that will appear in the screen.
     * @throws SQLException Throws when database error occurs.
     */
    @FXML
    void filterBook(ActionEvent event) throws SQLException {
        bookSelected = toggle(bookSelected);
        refreshList(event);
    }

    /**
     * This method will filter the resource with DVD type.
     *
     * @param event The event that will appear in the screen.
     * @throws SQLException Throws when database error occurs.
     */
    @FXML
    void filterDVD(ActionEvent event) throws SQLException {
        dvdSelected = toggle(dvdSelected);
        refreshList(event);
    }

    @FXML
    void copyOutput() {

    }

    /**
     * This method will filter the resource with laptop type.
     *
     * @param event The event that will appear in the screen.
     * @throws SQLException Throws when database error occurs.
     */
    @FXML
    void filterLaptop(ActionEvent event) throws SQLException {
        laptopSelected = toggle(laptopSelected);
        refreshList(event);

    }

    /**
     * This method will bring you back to the User page.
     *
     * @param event The event that will appear in the screen.
     */
    @FXML
    void goToUserPage(ActionEvent event) {
        closeWindow();

    }

    @FXML
    void refreshUserIn(ActionEvent event) {

    }

    /**
     * This method will refresh the arraylist in the User Interface.
     *
     * @param event The event that will appear in the screen.
     * @throws SQLException Throws when database error occurs.
     */
    @FXML
    void refreshList(ActionEvent event) throws SQLException {
        ArrayList<Resource> allResources = DataAdapter.getAllResources();
        ArrayList<Resource> resourceList = filter(allResources, searchInput.getText());

        copiesOutput.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String previousId, String listUniqueId) {
                if (listUniqueId != null) {
                    selectedCopyId = listUniqueId.substring(0, listUniqueId.indexOf(':'));
                }
            }
        });

        searchOutput.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

            public void changed(ObservableValue<? extends String> observable, String previousId, String listUniqueId) {
                for (Resource currentResource : resourceList) {
                    String itemId = null;
                    if (listUniqueId != null) {
                        itemId = listUniqueId.substring(0, listUniqueId.indexOf(':'));
                        try {
                            selectedResource = DataAdapter.readResource(itemId);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                    if (currentResource.getUniqueId().equals(itemId)) {
                        line1.setText(currentResource.getTitle() + " (" + currentResource.getYear() + ")");
                        if (currentResource.getNumCopies() != 1) {
                            line4.setText(currentResource.getNumCopies() + " Copies");
                        } else {
                            line4.setText(1 + " Copy");
                        }
                        ArrayList<Copy> copies = null;
                        try {
                            copies = DataAdapter.getAllCopies(currentResource);
                        } catch (SQLException e) {
                            System.out.println(e);
                        }

                        copiesOutput.getItems().clear();
                        for (Copy current : copies) {
                            copiesOutput.getItems().add(current.toString());
                        }

                        switch (currentResource.getType()) {
                            case 1:
                                line2.setText("ID: " + currentResource.getUniqueId() + "\n" + "Director: "
                                        + ((DVD) currentResource).getDirector() + "\n" + "Runtime: "
                                        + ((DVD) currentResource).getRuntime());
                                line3.setText("Language: " + ((DVD) currentResource).getLanguage() + "\n" + "Subtitles: "
                                        + ((DVD) currentResource).getSubtitle());
                                break;
                            case 2:
                                line2.setText("ID: " + currentResource.getUniqueId() + "\n" + "Manufacturer: "
                                        + ((Laptop) currentResource).getManufacturer() + "\n" + "Model: "
                                        + ((Laptop) currentResource).getModel());
                                line3.setText("OS: " + ((Laptop) currentResource).getOperatingSystem());
                                break;
                            case 3:
                                line2.setText("ID: " + currentResource.getUniqueId() + "\n" + "Author: "
                                        + ((Book) currentResource).getAuthor() + "\n" + "Publisher: "
                                        + ((Book) currentResource).getPublisher());
                                line3.setText("Genre: " + ((Book) currentResource).getGenre() + "\n" + "ISBN: "
                                        + ((Book) currentResource).getIsbn() + "\n" + "Language: "
                                        + ((Book) currentResource).getLanguage());
                                break;
                        }

                    }
                }
            }

        });

        searchOutput.getItems().clear();
        for (Resource currentResource : resourceList) {
            String itemInfo = currentResource.getUniqueId().toUpperCase() + ": "
                    + currentResource.getTitle().toUpperCase() + " (" + currentResource.getYear() + ") " + " - ";
            switch (currentResource.getType()) {
                case 1:
                    searchOutput.getItems().add(itemInfo + ((DVD) currentResource).getDirector());
                    break;
                case 2:
                    searchOutput.getItems().add(itemInfo + ((Laptop) currentResource).getManufacturer());
                    break;
                case 3:
                    searchOutput.getItems().add(itemInfo + ((Book) currentResource).getAuthor());
                    break;
            }
        }
    }

    /**
     * This method will help the user to reserve an item.
     *
     * @param e The event that will appear in the screen.
     */
    @FXML
    private void getResourceButtonPress(ActionEvent e) {
        try {
            User user = (User) DataAdapter.readAccount(LoginPageController.getCurrentAccount().getUsername());
            Copy copy = DataAdapter.readCopy(Integer.parseInt(selectedCopyId));
            if (copy.isAvailable() == 1) {
                DataAdapter.reserveResource(user, selectedResource, copy);
                Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                a.setHeaderText("Copy was reserved for you. Please collect at the librarian desk");
                a.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("This copy is unavailable");
                alert.showAndWait();
            }
        } catch (SQLException exception) {
            System.out.println(exception);
        }

    }

    /**
     * This method will switch the boolean value the user have inputted.
     *
     * @param in The boolean value.
     * @return !in The negative of the input boolean.
     */
    private boolean toggle(boolean in) {
        return !in;
    }

    /**
     * This method will close the current stage.
     */
    @FXML
    void closeWindow() {
        Stage stage = (Stage) inventoryBrowserPane.getScene().getWindow();
        stage.close();
    }
}
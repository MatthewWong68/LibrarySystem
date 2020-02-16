/**
 * This class is to allow users to see their avatar or change to a preset.
 *
 * @author Nathan Bowen
 * @version 1.0
 */

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.SQLException;

public class AvatarController {
    @FXML
    private ChoiceBox presetNumberChoiceBox;
    @FXML
    private Button choosePresetButton;
    @FXML
    private Button navigateToDashboard;
    @FXML
    private Button navigateToCustomDrawing;
    @FXML
    private HBox allPresetImages;
    @FXML
    private VBox currentAvatarContainer;


    /**
     * Sets up all buttons in the scene to call an action to perform.
     */
    public void initialize() {

        navigateToCustomDrawing.setOnAction(e -> {
            navigateToCustomDrawingPage();
        });

        navigateToDashboard.setOnAction(e -> {
            navigateToDashboardPage();
        });

        choosePresetButton.setOnAction(e -> {
            setPresetAvatar();
        });

        presetNumberChoiceBox.getItems().addAll(1, 2, 3, 4, 5, 6);

        getPresetImages();

        getCurrentAvatar();

    }

    /**
     * Opens Custom Drawing fxml to navigate to that stage.
     */
    private void navigateToCustomDrawingPage() {
        try {
            Pane root = (Pane) FXMLLoader.load(getClass().getResource("CustomDrawing.fxml"));
            Scene scene = new Scene(root, 600, 500);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Closes stage to go back to dashboard stage.
     */
    private void navigateToDashboardPage() {
        Stage stage = (Stage) navigateToDashboard.getScene().getWindow();
        stage.close();
    }

    /**
     * Get all preset images to display to the user.
     */
    private void getPresetImages() {
        //Change paths to paths of actual preset images
        ImageView presetImage1 = new ImageView("File:Images/Avatar1_00000.png");
        presetImage1.setFitHeight(100);
        presetImage1.setFitWidth(100);

        ImageView presetImage2 = new ImageView("File:Images/Avatar2_00000.png");
        presetImage2.setFitHeight(100);
        presetImage2.setFitWidth(100);

        ImageView presetImage3 = new ImageView("File:Images/Avatar3_00000.png");
        presetImage3.setFitHeight(100);
        presetImage3.setFitWidth(100);

        ImageView presetImage4 = new ImageView("File:Images/Avatar4_00000.png");
        presetImage4.setFitHeight(100);
        presetImage4.setFitWidth(100);

        ImageView presetImage5 = new ImageView("File:Images/Avatar5_00000.png");
        presetImage5.setFitHeight(100);
        presetImage5.setFitWidth(100);

        ImageView presetImage6 = new ImageView("File:Images/Avatar6_00000.png");
        presetImage6.setFitHeight(100);
        presetImage6.setFitWidth(100);

        allPresetImages.getChildren().add(presetImage1);
        allPresetImages.getChildren().add(presetImage2);
        allPresetImages.getChildren().add(presetImage3);
        allPresetImages.getChildren().add(presetImage4);
        allPresetImages.getChildren().add(presetImage5);
        allPresetImages.getChildren().add(presetImage6);
    }

    /**
     * Displays avatar of the user logged in.
     */
    private void getCurrentAvatar() {
        String currentAvatarImagePath = LoginPageController.getCurrentAccount().getImgPath();
        try {
            ImageView currentAvatarImage = new ImageView(currentAvatarImagePath);
            currentAvatarImage.setFitHeight(200);
            currentAvatarImage.setFitWidth(200);
            currentAvatarContainer.getChildren().add(currentAvatarImage);
        } catch (IllegalArgumentException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Can't find an image. Setting to a default preset 1");
            alert.showAndWait();
            String preset1Path = "Images/Avatar1_00000.png";
            ImageView currentAvatarImage = new ImageView(preset1Path);
            currentAvatarImage.setFitHeight(200);
            currentAvatarImage.setFitWidth(200);
            currentAvatarContainer.getChildren().add(currentAvatarImage);
        }


    }

    /**
     * Call method to set users avatar with preset.
     */
    private void setPresetAvatar() {
        //System.out.println(presetNumberChoiceBox.getValue().toString());
        String presetImagePath = LoginPageController.getCurrentAccount().getImgPath();
        int numberChosen = -1;
        if (presetNumberChoiceBox.getValue().toString() != null) {
            String num = presetNumberChoiceBox.getValue().toString();
            numberChosen = Integer.parseInt(num);

        }
        if (numberChosen == 1) {
            presetImagePath = "Images/Avatar1_00000.png";
        } else if (numberChosen == 2) {
            presetImagePath = "Images/Avatar2_00000.png";
        } else if (numberChosen == 3) {
            presetImagePath = "Images/Avatar3_00000.png";
        } else if (numberChosen == 4) {
            presetImagePath = "Images/Avatar4_00000.png";
        } else if (numberChosen == 5) {
            presetImagePath = "Images/Avatar5_00000.png";
        } else if (numberChosen == 6) {
            presetImagePath = "Images/Avatar6_00000.png";
        }
        try {
            DataAdapter.changeAvatar(LoginPageController.getCurrentAccount().getUsername(), presetImagePath);
            LoginPageController.getCurrentAccount().setImgPath(String.format("Images/Avatar%d_00000.png",
                    numberChosen));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}


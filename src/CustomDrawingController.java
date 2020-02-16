/**
 * This class is set up as an environment to allow users to create
 * a custom drawing and set it as their avatar.
 *
 * @author Nathan Bowen
 * @version 1.0
 */

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.SQLException;

public class CustomDrawingController {
    private static final int X_VALUE = 470;
    private static final int Y_VALUE = 135;
    private double xPos;
    private double yPos;
    private GraphicsContext gc;
    private int shapeSize = 0;
    private Color shapeColor = Color.BLACK;
    private Color strokeColor;

    @FXML
    private Canvas canvas;

    @FXML
    private Button straightLineButton;

    @FXML
    private Button particleTraceButton;

    @FXML
    private Button eraseAllButton;

    @FXML
    private Button setAvatarButton;

    @FXML
    private Button closeButton;

    @FXML
    private ColorPicker colorPicker;

    /**
     * Performs an action on the the canvas when the mouse is pressed.
     */
    @FXML
    void onMousePressed() {
        Point p = MouseInfo.getPointerInfo().getLocation();
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(shapeColor);
        gc.beginPath();
        xPos = p.x - X_VALUE;
        yPos = p.y - Y_VALUE;
        gc.lineTo(p.x - X_VALUE, p.y - Y_VALUE);
    }

    /**
     * Performs an action on the canvas when the mouse is dragged.
     */
    @FXML
    void onMouseDragged() {
        Point p = MouseInfo.getPointerInfo().getLocation();
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.fillOval(p.x - X_VALUE, p.y - Y_VALUE, shapeSize, shapeSize);
        gc.stroke();
    }

    /**
     * Performs an action on the canvas when the mouse is released.
     */
    @FXML
    void onMouseReleased() {
        Point p = MouseInfo.getPointerInfo().getLocation();
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.strokeLine(xPos, yPos, p.x - X_VALUE, p.y - Y_VALUE);
    }

    /**
     * Sets up all buttons in the scene to call an action to perform.
     */
    public void initialize() {
        straightLineButton.setOnAction(e -> {
            setStraightLine();
        });

        particleTraceButton.setOnAction(e -> {
            setParticleTrace();
        });

        eraseAllButton.setOnAction(e -> {
            setUpCanvasStyle();
        });

        colorPicker.setOnAction(e -> {
            GraphicsContext gc = canvas.getGraphicsContext2D();
            shapeColor = (colorPicker.getValue());
            if (strokeColor != Color.TRANSPARENT) {
                strokeColor = shapeColor;
                gc.setStroke(shapeColor);
            }
        });

        setAvatarButton.setOnAction(e -> {
            try {
                exportDrawing();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });

        closeButton.setOnAction(e -> {
            Stage stage = (Stage) closeButton.getScene().getWindow();
            stage.close();
        });

        setUpCanvasStyle();
    }

    /**
     * Draws a blank white canvas with black line borders.
     */
    private void setUpCanvasStyle() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setGlobalAlpha(1);
        gc.setFill(Color.WHITE);
        gc.setStroke(Color.BLACK);
        gc.fillRect(0, 0, 500, 500);
        gc.setFill(Color.BLACK);
        gc.setLineWidth(5);
        gc.strokeLine(0, 0, 1000, 0);
        gc.strokeLine(0, 1000, 0, 0);
        gc.strokeLine(500, 1000, 500, 0);
        gc.strokeLine(0, 500, 1000, 500);
        gc.setFill(shapeColor);
        gc.setStroke(strokeColor);
        gc.setLineWidth(2);
    }

    /**
     * Action to set to straight lines functionality.
     */
    private void setStraightLine() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        shapeSize = 0;
        gc.setGlobalAlpha(1);
        gc.setStroke(shapeColor);
        strokeColor = shapeColor;
    }

    /**
     * Action to set to particle trace functionality.
     */
    private void setParticleTrace() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        shapeSize = 20;
        gc.setGlobalAlpha(0.1);
        strokeColor = Color.TRANSPARENT;
        gc.setStroke(Color.TRANSPARENT);
    }

    /**
     * Saves the custom drawn image as a png in the program directory.
     *
     * @throws SQLException
     *             Throws when database error occurs.
     */
    private void exportDrawing() throws SQLException {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setGlobalAlpha(1);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(5);
        gc.strokeLine(0, 0, 1000, 0);
        gc.strokeLine(0, 1000, 0, 0);
        gc.strokeLine(500, 1000, 500, 0);
        gc.strokeLine(0, 500, 1000, 500);
        gc.setFill(shapeColor);
        gc.setStroke(strokeColor);
        gc.setLineWidth(2);
        // Call method to obtain what user is currently logged in

        String pathname = String.format("bin/Images/%sCustomImage.png",
                LoginPageController.getCurrentAccount().getUsername());
        try {
            File file = new File(pathname);
            WritableImage writableImage = canvas.snapshot(null, null);
            BufferedImage bImage = SwingFXUtils.fromFXImage(writableImage, null);
            ImageIO.write(bImage, "png", file);
        } catch (Exception s) {
        }

        DataAdapter.changeAvatar(LoginPageController.getCurrentAccount().getUsername(), pathname);
        LoginPageController.getCurrentAccount().setImgPath(pathname);
    }

}

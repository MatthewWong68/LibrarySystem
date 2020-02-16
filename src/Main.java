import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * The main class for this program.
 *
 * @author Matthew
 */
public class Main extends Application {
    // Constants for the all windows
    public static final int INVENTORY_BROWSER_WINDOW_HEIGHT = 550;
    public static final int INVENTORY_BROWSER_WINDOW_WIDTH = 850;
    public static final int GIGANTIC_WINDOW_HEIGHT = 550;
    public static final int ENORMOUS_WINDOW_HEIGHT = 500;
    public static final int MEDIUM_WINDOW_HEIGHT = 300;
    public static final int MEDIUM_WINDOW_WIDTH = 500;
    public static final int LARGE_WINDOW_HEIGHT = 400;
    public static final int LARGE_WINDOW_WIDTH = 600;
    public static final int SMALL_WINDOW_HEIGHT = 250;
    public static final int SMALL_WINDOW_WIDTH = 400;

    public static final String HOMEPAGE_WINDOW_TITLE = "Home Page";
    public static final String LOGINPAGE_WINDOW_TITLE = "Login Page";
    public static final String USERPAGE_WINDOW_TITLE = "User Page";
    public static final String LIBRARIANPAGE_WINDOW_TITLE = "Librarian Page";
    public static final String BORROW_RESOURCES_WINDOW_TITLE = "Borrow Resources";
    public static final String RETURN_RESOURCES_WINDOW_TITLE = "Return Resources";
    public static final String FINE_PAYMENT_WINDOW_TITLE = "Fine Payment";
    public static final String OVERDUE_ITEMS_WINDOW_TITLE = "Overdue Items";
    public static final String INVENTORY_BROWSER_WINDOW_TITLE = "Inventory Browser";
    public static final String CREATE_NEW_USER_WINDOW_TITLE = "Create new user";
    public static final String CREATE_NEW_RESOURCES_WINDOW_TITLE = "Create new resources";
    public static final String EDIT_CURRENT_USER_WINDOW_TITLE = "Edit current user";
    public static final String EDIT_CURRENT_RESOURCES_WINDOW_TITLE = "Edit current resources";
    public static final String CHOOSE_IMAGE_PAGE_WINDOW_TITLE = "Choose Image Page";
    public static final String CREATE_BOOK_WINDOW_TITLE = "Create new book";
    public static final String CREATE_DVD_WINDOW_TITLE = "Create new DVD";
    public static final String CREATE_LAPTOP_WINDOW_TITLE = "Create new laptop";
    public static final String BORROWED_ITEMS_WINDOW_TITLE = "Borrowed Items";
    public static final String REQUESTED_ITEMS_WINDOW_TITLE = "Requested Items";
    public static final String RESERVED_ITEMS_WINDOW_TITLE = "Reserved Items";
    public static final String TRANSACTION_HISTORY_WINDOW_TITLE = "Transaction History";
    public static final String OVERDUE_RESOURCES_WINDOW_TITLE = "Overdue resources";
    public static final String VIEW_A_COPYS_BORROWING_HISTORY_WINDOW_TITLE = "View a copy's borrowing history";
    public static final String MANAGE_EDIT_DVD_WINDOW_TITLE = "Manage edit DVD";
    public static final String EDIT_DVD_WINDOW_TITLE = "Manage DVD";
    public static final String MANAGE_EDIT_LAPTOP_WINDOW_TITLE = "Manage edit Laptop";
    public static final String EDIT_LAPTOP_WINDOW_TITLE = "Manage Laptop";
    public static final String MANAGE_EDIT_BOOK_WINDOW_TITLE = "Manage edit Book";
    public static final String EDIT_BOOK_WINDOW_TITLE = "Manage Book";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            // Load the main scene.
            Pane root = (Pane) FXMLLoader.load(getClass().getResource("HomePage.fxml"));
            Scene scene = new Scene(root, LARGE_WINDOW_WIDTH, LARGE_WINDOW_HEIGHT);

            // Place the main scene on stage and show it.
            primaryStage.setScene(scene);
            primaryStage.setTitle(HOMEPAGE_WINDOW_TITLE);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
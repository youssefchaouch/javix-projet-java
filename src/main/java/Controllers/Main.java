package Controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        showLeaveRequest();
    }

    public static void showLeaveRequest() throws Exception {
        Parent root = FXMLLoader.load(Main.class.getResource("/LeaveRequest.fxml"));
        primaryStage.setScene(new Scene(root, 700, 600));
        primaryStage.setTitle("Leave Request");
        primaryStage.show();
    }

    public static void showLeaveValidation() throws Exception {
        Parent root = FXMLLoader.load(Main.class.getResource("/LeaveValidation.fxml"));
        primaryStage.setScene(new Scene(root, 700, 600));
        primaryStage.setTitle("Leave Validation");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

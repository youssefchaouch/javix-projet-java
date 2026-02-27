package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartScreenController {

    @FXML private void hrButtonClicked(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/HRMenu.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));
        stage.setTitle("HR Menu");
        stage.setMaximized(true);
        stage.show();

        // Close current window
        ((Stage)(((javafx.scene.control.Button)event.getSource()).getScene().getWindow())).close();
    }

    @FXML private void candidateButtonClicked(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/JobApplicationForm.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));
        stage.setTitle("Application Form");
        stage.setMaximized(true);
        stage.show();

        // Close current window
        ((Stage)(((javafx.scene.control.Button)event.getSource()).getScene().getWindow())).close();
    }
}

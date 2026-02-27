package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

public class HRMenuController {
    @FXML private Button logoutButton;
    @FXML
    private void addJobClicked(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/JobOffer.fxml"));
        Scene scene = ((Node) event.getSource()).getScene();
        scene.setRoot(root);
    }

    @FXML
    private void checkCandidatesClicked(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/CandidateList.fxml"));
        Scene scene = ((Node) event.getSource()).getScene();
        scene.setRoot(root);
    }

    @FXML
    private void handleLogout() {
        try {
            javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(
                    getClass().getResource("/LoginPage.fxml")
            );
            javafx.scene.Parent root = loader.load();

            // Get current stage
            javafx.stage.Stage stage = (javafx.stage.Stage) logoutButton.getScene().getWindow();
            stage.setScene(new javafx.scene.Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Unable to return to Login Page").showAndWait();
        }
    }
}

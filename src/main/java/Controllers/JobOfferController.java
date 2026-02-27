package Controllers;

import Entite.JobOffer;
import Services.JobOfferService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.time.LocalDateTime;

public class JobOfferController {

    @FXML
    private TextField titleField;

    @FXML
    private TextArea descriptionField;

    @FXML
    private TextField statusField;

    @FXML
    private TextField createdByField;

    @FXML
    private Button backButton;

    private JobOfferService service = new JobOfferService();

    @FXML
    private void handleAddOffer() {

        try {
            if (titleField.getText().isEmpty() ||
                    descriptionField.getText().isEmpty() ||
                    statusField.getText().isEmpty() ||
                    createdByField.getText().isEmpty()) {

                showAlert("All fields are required.");
                return;
            }

            int createdBy = Integer.parseInt(createdByField.getText());

            JobOffer offer = new JobOffer(
                    0,
                    titleField.getText(),
                    descriptionField.getText(),
                    statusField.getText(),
                    LocalDateTime.now(),
                    createdBy
            );

            service.add(offer);

            System.out.println("=== Job Offer Added Successfully ===");

            clearFields();

        } catch (NumberFormatException e) {
            showAlert("Created By must be a valid number.");
        } catch (Exception e) {
            showAlert("Error while adding job offer.");
            e.printStackTrace();
        }
    }

    private void clearFields() {
        titleField.clear();
        descriptionField.clear();
        statusField.clear();
        createdByField.clear();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void handleBack() {
        try {
            javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(
                    getClass().getResource("/HRMenu.fxml")
            );
            javafx.scene.Parent root = loader.load();

            // Get current stage
            javafx.stage.Stage stage = (javafx.stage.Stage) backButton.getScene().getWindow();
            stage.setScene(new javafx.scene.Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Unable to return to HR Menu").showAndWait();
        }
    }

    @FXML
    private void handleClear(){
        clearFields();
    }


}

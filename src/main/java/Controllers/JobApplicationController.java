package Controllers;

import Entite.Application;
import Entite.Candidate;
import Services.ApplicationService;
import Services.CandidateService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.time.LocalDateTime;

public class JobApplicationController {

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField phoneField;

    @FXML
    private TextArea resumeArea;

    @FXML
    private TextField jobOfferField;

    @FXML
    private Button saveButton;

    @FXML
    private Button clearButton;

    @FXML
    private Button exitButton;

    @FXML
    private Label errorLabel;

    private CandidateService candidateService;
    private ApplicationService applicationService;

    @FXML
    public void initialize() {
        // Initialize service
        candidateService = new CandidateService();
        applicationService = new ApplicationService();

        // Button actions
        saveButton.setOnAction(e -> saveCandidate());
        clearButton.setOnAction(e -> clearForm());
        exitButton.setOnAction(e -> exitApplication());
    }

    private void saveCandidate() {
        try {
            String email = emailField.getText();
            if(!isValidEmail(email)){
                errorLabel.setText("Invalid email format!");
                clearFields();
                return;
            }
            Candidate candidate = new Candidate();
            Application application = new Application();

            candidate.setFirstName(firstNameField.getText());
            candidate.setLastName(lastNameField.getText());
            candidate.setEmail(emailField.getText());
            candidate.setPhone(phoneField.getText());
            candidate.setResume(resumeArea.getText());

            int candidateID = candidateService.addAndRetrievePk(candidate);

            System.out.println("Candidate with id=" +candidateID +"saved successfully!");

            application.setIdOffer(Integer.parseInt(jobOfferField.getText()));
            application.setIdCandidate(candidateID);
            application.setStatus("PENDING");
            application.setAppliedAt(LocalDateTime.now());

            applicationService.add(application);

            System.out.println("application saved successfully!");

            clearForm();

        } catch (NumberFormatException ex) {
            System.out.println("Invalid ID format: " + ex.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void clearForm() {
        firstNameField.clear();
        lastNameField.clear();
        emailField.clear();
        phoneField.clear();
        resumeArea.clear();
        jobOfferField.clear();
    }

    private void exitApplication() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    public boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email != null && email.matches(emailRegex);
    }

    private void clearFields() {
        firstNameField.clear();
        lastNameField.clear();
        emailField.clear();
        phoneField.clear();
        resumeArea.clear();
        jobOfferField.clear();
    }
}

package Controllers;

import Entite.Application;
import Entite.CandidateApplication;
import Services.ApplicationService;
import Services.CandidateService;
import Utlis.EmailService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CandidateListController {

    @FXML private TableView<CandidateApplication> candidateTable;
    @FXML private TableColumn<CandidateApplication, Integer> idCol;
    @FXML private TableColumn<CandidateApplication, String> firstNameCol;
    @FXML private TableColumn<CandidateApplication, String> lastNameCol;
    @FXML private TableColumn<CandidateApplication, String> emailCol;
    @FXML private TableColumn<CandidateApplication, String> phoneCol;
    @FXML private TableColumn<CandidateApplication, String> statusCol;

    @FXML private Button saveButton;
    @FXML private TextField searchField;
    @FXML private Button backButton;

    private final CandidateService candidateService = new CandidateService();
    private final ApplicationService applicationService = new ApplicationService();
    private final EmailService emailService = new EmailService();

    // Track edited rows
    private final List<CandidateApplication> editedApplications = new ArrayList<>();

    @FXML
    private void initialize() {
        // Bind columns
        idCol.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getCandidateId()).asObject());
        firstNameCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getFirstName()));
        lastNameCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getLastName()));
        emailCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getEmail()));
        phoneCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getPhone()));
        statusCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getStatus()));

        // Enable ComboBox for status column
        statusCol.setCellFactory(ComboBoxTableCell.forTableColumn("Pending", "Accepted", "Rejected"));
        statusCol.setOnEditCommit(event -> {
            CandidateApplication ca = event.getRowValue();
            ca.setStatus(event.getNewValue());
            if (!editedApplications.contains(ca)) {
                editedApplications.add(ca);
            }
        });

        candidateTable.setEditable(true);

        // Load candidate applications from DAO/service
        ObservableList<CandidateApplication> list = FXCollections.observableArrayList(
                candidateService.getCandidateApplications()
        );

        // Wrap list in a FilteredList
        FilteredList<CandidateApplication> filteredData = new FilteredList<>(list, p -> true);

        // Add listener to searchField
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(candidate -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                // Filter by first name, last name, or email
                if (candidate.getFirstName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (candidate.getLastName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (candidate.getEmail().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });

        // Wrap filteredData in a SortedList
        SortedList<CandidateApplication> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(candidateTable.comparatorProperty());

        // Set sorted and filtered data to the table
        candidateTable.setItems(sortedData);

        // Setup Save button action
        saveButton.setOnAction(e -> handleSave());
    }

    @FXML
    private void handleSave() {
        for (CandidateApplication ca : editedApplications) {

            Application app = new Application();
            app.setIdApplication(ca.getApplicationId());
            app.setStatus(ca.getStatus());
            app.setIdOffer(ca.getIdOffer());
            app.setAppliedAt(LocalDateTime.now());
            app.setIdCandidate(ca.getCandidateId());
            applicationService.update(app);

            emailService.sendStatusEmail(
                    ca.getEmail(),
                    ca.getFirstName(),
                    ca.getStatus()
            );
        }

        editedApplications.clear();
        new Alert(Alert.AlertType.INFORMATION, "Changes saved & emails sent!").showAndWait();
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

}

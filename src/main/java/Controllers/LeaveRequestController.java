package Controllers;

import Entite.LeaveRequest;
import Services.LeaveRequestService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class LeaveRequestController {

    private LeaveRequestService leaveService = new LeaveRequestService();

    @FXML
    private TextField txtIdEmployee;

    @FXML
    private DatePicker dpStartDate;

    @FXML
    private DatePicker dpEndDate;

    @FXML
    private TextArea txtReason;

    @FXML
    private ChoiceBox<String> cbStatus;

    @FXML
    private Button btnSubmit;

    @FXML
    private TableView<LeaveRequest> tableLeave;

    @FXML
    private TableColumn<LeaveRequest, Integer> colIdLeave;

    @FXML
    private TableColumn<LeaveRequest, Integer> colIdEmployee;

    @FXML
    private TableColumn<LeaveRequest, LocalDate> colStartDate;

    @FXML
    private TableColumn<LeaveRequest, LocalDate> colEndDate;

    @FXML
    private TableColumn<LeaveRequest, String> colReason;

    @FXML
    private TableColumn<LeaveRequest, String> colStatus;

    @FXML
    private TableColumn<LeaveRequest, LocalDateTime> colRequestedAt;

    @FXML
    private TableColumn<LeaveRequest, Integer> colValidatedBy;

    @FXML
    void submitLeaveRequest(ActionEvent event) {
        try {
            LeaveRequest leave = new LeaveRequest();
            leave.setIdEmployee(Integer.parseInt(txtIdEmployee.getText()));
            leave.setStartDate(dpStartDate.getValue());
            leave.setEndDate(dpEndDate.getValue());
            leave.setReason(txtReason.getText());
            leave.setStatus(cbStatus.getValue());
            leave.setRequestedAt(LocalDateTime.now());
            leave.setValidatedBy(null);

            leaveService.add(leave);
            refreshTable();
            clearForm();
            showAlert("Success", "Leave request added successfully!", Alert.AlertType.INFORMATION);

        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Failed to add leave request. Check your inputs.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void initialize() {
        cbStatus.getItems().addAll("Pending", "Approved", "Rejected");
        cbStatus.setValue("Pending");

        colIdLeave.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getIdLeave()).asObject());
        colIdEmployee.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getIdEmployee()).asObject());
        colStartDate.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getStartDate()));
        colEndDate.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getEndDate()));
        colReason.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getReason()));
        colStatus.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getStatus()));
        colRequestedAt.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getRequestedAt()));
        colValidatedBy.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getValidatedBy()));

        refreshTable();
    }

    private void refreshTable() {
        List<LeaveRequest> leaves = leaveService.getAll();
        ObservableList<LeaveRequest> obsList = FXCollections.observableArrayList(leaves);
        tableLeave.setItems(obsList);
    }

    private void clearForm() {
        txtIdEmployee.clear();
        dpStartDate.setValue(null);
        dpEndDate.setValue(null);
        txtReason.clear();
        cbStatus.setValue("Pending");
    }

    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

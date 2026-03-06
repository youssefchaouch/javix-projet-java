package Controllers;

import Entite.Application;
import Services.ApplicationService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class ApplicationController {

    @FXML
    private TableView<Application> tableView;

    @FXML
    private TableColumn<Application, Integer> colCandidate;

    @FXML
    private TableColumn<Application, Integer> colOffer;

    @FXML
    private TableColumn<Application, String> colStatus;

    @FXML
    private TableColumn<Application, java.time.LocalDateTime> colAppliedAt;

    private ApplicationService service;

    public void initialize() {
        service = new ApplicationService();
        setupTable();
        refreshTable();
    }

    private void setupTable() {
        colCandidate.setCellValueFactory(new PropertyValueFactory<>("idCandidate"));
        colOffer.setCellValueFactory(new PropertyValueFactory<>("idOffer"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colAppliedAt.setCellValueFactory(new PropertyValueFactory<>("appliedAt"));
    }

    @FXML
    private void refreshTable() {
        ObservableList<Application> list = FXCollections.observableArrayList(service.getAll());
        tableView.setItems(list);
    }

    @FXML
    private void addApplication() {
        // hook this to a form later
    }

    @FXML
    private void updateApplication() {
        // hook this to a selection + form later
    }

    @FXML
    private void deleteApplication() {
        Application selected = tableView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            service.delete(selected.getIdApplication());
            refreshTable();
        }
    }
}

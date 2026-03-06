package Controllers;

import Entite.JobOffer;
import Services.JobOfferService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class JobOfferController {

    @FXML
    private TableView<JobOffer> tableView;

    @FXML
    private TableColumn<JobOffer, String> colTitle;

    @FXML
    private TableColumn<JobOffer, String> colDescription;

    @FXML
    private TableColumn<JobOffer, String> colStatus;

    @FXML
    private TableColumn<JobOffer, java.time.LocalDateTime> colCreatedAt;

    @FXML
    private TableColumn<JobOffer, Integer> colCreatedBy;

    private JobOfferService service;

    public void initialize() {
        service = new JobOfferService();
        setupTable();
        refreshTable();
    }

    private void setupTable() {
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colCreatedAt.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
        colCreatedBy.setCellValueFactory(new PropertyValueFactory<>("createdBy"));
    }

    @FXML
    private void refreshTable() {
        ObservableList<JobOffer> list = FXCollections.observableArrayList(service.getAll());
        tableView.setItems(list);
    }

    @FXML
    private void addOffer() {
        // connect to a form later
    }

    @FXML
    private void updateOffer() {
        // connect to a form later
    }

    @FXML
    private void deleteOffer() {
        JobOffer selected = tableView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            service.delete(selected.getIdOffer());
            refreshTable();
        }
    }
}

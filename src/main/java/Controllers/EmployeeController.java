package Controllers;

import Entite.Employee;
import Services.EmployeeService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class EmployeeController {

    @FXML
    private TableView<Employee> tableView;

    @FXML
    private TableColumn<Employee, String> colFirstName;

    @FXML
    private TableColumn<Employee, String> colLastName;

    @FXML
    private TableColumn<Employee, String> colEmail;

    @FXML
    private TableColumn<Employee, String> colPhone;

    @FXML
    private TableColumn<Employee, java.time.LocalDate> colHireDate;

    @FXML
    private TableColumn<Employee, String> colRole;

    @FXML
    private TableColumn<Employee, Double> colSalary;

    @FXML
    private TableColumn<Employee, String> colStatus;

    private EmployeeService service;

    public void initialize() {
        service = new EmployeeService();
        colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colHireDate.setCellValueFactory(new PropertyValueFactory<>("hireDate"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        ObservableList<Employee> list = FXCollections.observableArrayList(service.getAll());
        tableView.setItems(list);
    }
}

package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import Entite.Payroll;
import Services.PayrollService;

public class PayrollController {

    @FXML private TextField txtEmployeeId, txtMonth, txtYear, txtLeaveDays;
    @FXML private Label lblFinalSalary, lblTotalMonth;
    @FXML private TableView<Payroll> tablePayroll;
    @FXML private TableColumn<Payroll, Integer> colId, colEmployee, colMonth, colYear, colLeave;
    @FXML private TableColumn<Payroll, Double> colSalary;

    private PayrollService payrollService = new PayrollService();
    private ObservableList<Payroll> payrollList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colId.setCellValueFactory(cellData -> cellData.getValue().idPayrollProperty().asObject());
        colEmployee.setCellValueFactory(cellData -> cellData.getValue().idEmployeeProperty().asObject());
        colMonth.setCellValueFactory(cellData -> cellData.getValue().monthProperty().asObject());
        colYear.setCellValueFactory(cellData -> cellData.getValue().yearProperty().asObject());
        colLeave.setCellValueFactory(cellData -> cellData.getValue().totalLeaveDaysProperty().asObject());
        colSalary.setCellValueFactory(cellData -> cellData.getValue().finalSalaryProperty().asObject());

        refreshTable();
    }

    private void refreshTable() {
        payrollList.setAll(payrollService.getAll());
        tablePayroll.setItems(payrollList);
    }

    @FXML
    private void generatePayroll() {
        int month = Integer.parseInt(txtMonth.getText());
        int year = Integer.parseInt(txtYear.getText());
        payrollService.generateMonthlyPayroll(month, year);
        refreshTable();
        double total = payrollService.totalSalaryByMonth(month, year);
        lblTotalMonth.setText("Total Salary (Month): " + total);
    }

    @FXML
    private void calculateFinalSalary() {
        int empId = Integer.parseInt(txtEmployeeId.getText());
        int leaveDays = Integer.parseInt(txtLeaveDays.getText());
        Payroll payroll = payrollService.getById(empId);
        if(payroll != null) {
            double salary = payroll.getFinalSalary();
            double dailyRate = salary / 30;
            double finalSalary = salary - (leaveDays * dailyRate);
            lblFinalSalary.setText("Final Salary: " + finalSalary);
        }
    }

    @FXML
    private void clearForm() {
        txtEmployeeId.clear();
        txtMonth.clear();
        txtYear.clear();
        txtLeaveDays.clear();
        lblFinalSalary.setText("0.0");
    }
}

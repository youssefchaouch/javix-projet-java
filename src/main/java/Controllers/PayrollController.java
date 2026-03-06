package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.print.PrinterJob;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Region;

import Entite.Employee;
import Entite.Payroll;
import Services.PayrollService;
import Services.EmployeeService;

import java.time.YearMonth;
import java.util.*;

public class PayrollController {

    // Payroll Tab Fields
    @FXML private TextField txtEmployeeId, txtMonth, txtYear, txtLeaveDays;
    @FXML private TextField txtGenMonth, txtGenYear;
    @FXML private Label lblFinalSalary, lblTotalMonth, lblEmployeeCount;
    @FXML private Label lblTotalRole;
    @FXML private TextField txtRole;
    
    @FXML private TableView<Payroll> tablePayroll;
    @FXML private TableColumn<Payroll, Integer> colId, colEmployee, colMonth, colYear, colLeave;
    @FXML private TableColumn<Payroll, Double> colSalary;

    // Statistics Tab Fields
    @FXML private Label kpiEmployees, kpiTotalSalaries, kpiAverageSalary;
    @FXML private PieChart pieSalaryByRole;
    
    @FXML private LineChart<Number, Number> lineMonthlyStats;
    @FXML private ComboBox<Integer> yearCombo;

    private PayrollService payrollService = new PayrollService();
    private EmployeeService employeeService = new EmployeeService();
    private ObservableList<Payroll> payrollList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        initializePayrollTab();
        initializeStatisticsTab();
        refreshAllData();
    }

    // ============ PAYROLL TAB INITIALIZATION ============
    private void initializePayrollTab() {
        colId.setCellValueFactory(cellData -> cellData.getValue().idPayrollProperty().asObject());
        colEmployee.setCellValueFactory(cellData -> cellData.getValue().idEmployeeProperty().asObject());
        colMonth.setCellValueFactory(cellData -> cellData.getValue().monthProperty().asObject());
        colYear.setCellValueFactory(cellData -> cellData.getValue().yearProperty().asObject());
        colLeave.setCellValueFactory(cellData -> cellData.getValue().totalLeaveDaysProperty().asObject());
        colSalary.setCellValueFactory(cellData -> cellData.getValue().finalSalaryProperty().asObject());
        refreshTable();
        updateEmployeeCount();
    }

    // ============ STATISTICS TAB INITIALIZATION ============
    private void initializeStatisticsTab() {
        // Initialize year dropdown
        ObservableList<Integer> years = FXCollections.observableArrayList(2024, 2025, 2026, 2027, 2028);
        yearCombo.setItems(years);
        yearCombo.setValue(2026);
    }

    // ============ PAYROLL FUNCTIONS ============
    private void refreshTable() {
        payrollList.setAll(payrollService.getAll());
        tablePayroll.setItems(payrollList);
    }

    private void updateEmployeeCount() {
        int count = employeeService.getAll().size();
        lblEmployeeCount.setText(String.valueOf(count));
    }

    @FXML
    private void calculateFinalSalary() {
        try {
            int empId = Integer.parseInt(txtEmployeeId.getText());
            int leaveDays = Integer.parseInt(txtLeaveDays.getText());
            int month = Integer.parseInt(txtMonth.getText());
            int year = Integer.parseInt(txtYear.getText());
            
            Employee emp = employeeService.getById(empId);
            if (emp == null) {
                showAlert("Employee not found");
                return;
            }
            
            int daysInMonth = YearMonth.of(year, month).lengthOfMonth();
            double finalSalary = (emp.getSalary() / daysInMonth) * (daysInMonth - leaveDays);
            lblFinalSalary.setText(String.format("%.2f TND", finalSalary));
        } catch (NumberFormatException e) {
            showAlert("Please enter valid numeric values");
        } catch (Exception e) {
            showAlert("Error calculating salary: " + e.getMessage());
        }
    }

    @FXML
    private void generatePayroll() {
        try {
            int month = Integer.parseInt(txtGenMonth.getText());
            int year = Integer.parseInt(txtGenYear.getText());
            payrollService.generateMonthlyPayroll(month, year);
            refreshTable();
            double total = payrollService.totalSalaryByMonth(month, year);
            lblTotalMonth.setText(String.format("%.2f TND", total));
            showAlert("Payroll generated successfully for " + month + "/" + year);
        } catch (NumberFormatException e) {
            showAlert("Month and year must be numeric");
        } catch (Exception e) {
            showAlert("Error generating payroll: " + e.getMessage());
        }
    }

    @FXML
    private void clearForm() {
        txtEmployeeId.clear();
        txtMonth.clear();
        txtYear.clear();
        txtLeaveDays.clear();
        txtGenMonth.clear();
        txtGenYear.clear();
        txtRole.clear();
        lblFinalSalary.setText("0.00 TND");
        lblTotalMonth.setText("0.00 TND");
    }

    @FXML
    private void calculateTotalByRole() {
        try {
            String role = txtRole.getText();
            int month = Integer.parseInt(txtMonth.getText());
            int year = Integer.parseInt(txtYear.getText());
            double total = payrollService.totalSalaryByRole(role, month, year);
            showAlert("Total salary for role " + role + ": " + String.format("%.2f TND", total));
        } catch (NumberFormatException e) {
            showAlert("Month and year must be numeric");
        }
    }

    @FXML
    private void printPayslip() {
        Payroll p = tablePayroll.getSelectionModel().getSelectedItem();
        if (p == null) {
            showAlert("Please select a payroll entry first");
            return;
        }
        Employee emp = employeeService.getById(p.getIdEmployee());
        StringBuilder sb = new StringBuilder();
        sb.append("========================================\n");
        sb.append("           PAY SLIP - MindRise HR\n");
        sb.append("========================================\n\n");
        sb.append("Payroll ID: ").append(p.getIdPayroll()).append("\n");
        if (emp != null) {
            sb.append("Employee: ")
              .append(emp.getFirstName()).append(" ").append(emp.getLastName())
              .append(" (ID ").append(emp.getIdEmployee()).append(")\n");
            sb.append("Role: ").append(emp.getRole()).append("\n");
            sb.append("Base Salary: ").append(String.format("%.2f", emp.getSalary())).append(" TND\n");
        }
        sb.append("\nMonth/Year: ").append(p.getMonth()).append("/").append(p.getYear()).append("\n");
        sb.append("Leave Days: ").append(p.getTotalLeaveDays()).append("\n");
        sb.append("\n----------------------------------------\n");
        sb.append("Final Salary: ").append(String.format("%.2f", p.getFinalSalary())).append(" TND\n");
        sb.append("----------------------------------------\n");
        sb.append("Generated: ").append(p.getGeneratedAt()).append("\n");
        sb.append("========================================\n");

        TextArea area = new TextArea(sb.toString());
        area.setWrapText(true);
        area.setEditable(false);
        area.setPrefWidth(400);
        area.setPrefHeight(400);
        area.setStyle("-fx-font-family: 'Courier New'; -fx-font-size: 12px;");

        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null && job.showPrintDialog(tablePayroll.getScene().getWindow())) {
            boolean success = job.printPage(area);
            if (success) job.endJob();
        }
    }

    // ============ STATISTICS FUNCTIONS ============
    @FXML
    private void refreshStatistics() {
        try {
            List<Employee> employees = employeeService.getAll();
            
            // Update KPIs
            kpiEmployees.setText(String.valueOf(employees.size()));
            
            double totalSalaries = employees.stream()
                    .mapToDouble(Employee::getSalary)
                    .sum();
            kpiTotalSalaries.setText(String.format("%.2f TND", totalSalaries));
            
            double avgSalary = employees.isEmpty() ? 0 : totalSalaries / employees.size();
            kpiAverageSalary.setText(String.format("%.2f TND", avgSalary));
            
            // Update salary by role table
            updateSalaryByRoleTable(employees);
            
        } catch (Exception e) {
            showAlert("Error refreshing statistics: " + e.getMessage());
        }
    }

    private void updateSalaryByRoleTable(List<Employee> employees) {
        Map<String, RoleSalaryData> roleDataMap = new HashMap<>();
        
        for (Employee emp : employees) {
            String role = emp.getRole();
            roleDataMap.computeIfAbsent(role, k -> new RoleSalaryData(role))
                .addEmployee(emp.getSalary());
        }
        
        ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList();
        for (RoleSalaryData data : roleDataMap.values()) {
            PieChart.Data slice = new PieChart.Data(data.getRole(), data.getTotalSalary());
            // Set the label to show role and value
            slice.setName(data.getRole() + ": " + String.format("%.2f TND", data.getTotalSalary()));
            pieData.add(slice);
        }
        pieSalaryByRole.setData(pieData);
    }

    @FXML
    private void loadMonthlyData() {
        try {
            int year = yearCombo.getValue();
            
            // Create data series for the line chart
            XYChart.Series<Number, Number> series = new XYChart.Series<>();
            series.setName("Total Salary per Month");
            
            // Get total salary for each month of the selected year
            for (int month = 1; month <= 12; month++) {
                double totalSalary = payrollService.totalSalaryByMonth(month, year);
                series.getData().add(new XYChart.Data<>(month, totalSalary));
            }
            
            lineMonthlyStats.getData().clear();
            lineMonthlyStats.getData().add(series);
            
        } catch (Exception e) {
            showAlert("Error loading monthly data: " + e.getMessage());
        }
    }

    private void refreshAllData() {
        updateEmployeeCount();
        refreshStatistics();
        loadMonthlyData();
    }

    // ============ UTILITY FUNCTIONS ============
    private void showAlert(String message) {
        Alert a = new Alert(AlertType.INFORMATION);
        a.setTitle("Payroll System");
        a.setHeaderText(null);
        a.setContentText(message);
        a.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        a.showAndWait();
    }

    // ============ INNER CLASS FOR TABLE DATA ============
    public static class RoleSalaryData {
        private String role;
        private int count = 0;
        private double totalSalary = 0;

        public RoleSalaryData(String role) {
            this.role = role;
        }

        public void addEmployee(double salary) {
            count++;
            totalSalary += salary;
        }

        public String getRole() { return role; }
        public javafx.beans.property.StringProperty roleProperty() {
            return new javafx.beans.property.SimpleStringProperty(role);
        }

        public int getCount() { return count; }
        public javafx.beans.property.IntegerProperty countProperty() {
            return new javafx.beans.property.SimpleIntegerProperty(count);
        }

        public double getTotalSalary() { return totalSalary; }
        public javafx.beans.property.DoubleProperty totalSalaryProperty() {
            return new javafx.beans.property.SimpleDoubleProperty(totalSalary);
        }

        public double getAverageSalary() { return count == 0 ? 0 : totalSalary / count; }
        public javafx.beans.property.DoubleProperty averageSalaryProperty() {
            return new javafx.beans.property.SimpleDoubleProperty(getAverageSalary());
        }
    }
}


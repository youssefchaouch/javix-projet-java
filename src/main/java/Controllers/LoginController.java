package Controllers;

import java.util.List;

import Entite.Employee;
import Services.EmployeeService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label errorLabel;

    private EmployeeService employeeService = new EmployeeService();

    @FXML
    private void handleLogin(ActionEvent event) throws Exception {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        List<Employee> employees = employeeService.getAll();

        Employee authenticatedEmployee = null;
        for (Employee e : employees) {
            if (e.getEmail().equalsIgnoreCase(username) && e.getPhone().equals(password)) {
                authenticatedEmployee = e;
                break;
            }
        }

        if (authenticatedEmployee != null) {
            String role = authenticatedEmployee.getRole();
            if ("HR_DIRECTOR".equalsIgnoreCase(role) || 
                "RECRUITMENT_OFFICER".equalsIgnoreCase(role) || 
                "PAYROLL_OFFICER".equalsIgnoreCase(role)) {
                loadScene("/HRMenu.fxml", "HR Menu", event);
            } else if ("SIMPLE_EMPLOYEE".equalsIgnoreCase(role)) {
                loadScene("/EmployeeMenu.fxml", "Employee Menu", event);
            } else {
                errorLabel.setText("Invalid user role!");
            }
        } else {
            errorLabel.setText("Invalid credentials!");
        }
    }

    @FXML
    private void candidateButtonClicked(ActionEvent event) throws Exception {
        loadScene("/JobApplicationForm.fxml", "Application Form", event);
    }

    private void loadScene(String fxmlPath, String title, ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));
        stage.setTitle(title);
        stage.setMaximized(true);
        stage.show();

        // Close current window
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }
}

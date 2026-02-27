package Controllers;

import Entite.Employee;
import Services.EmployeeService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.List;

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

        boolean authenticated = false;
        for (Employee e : employees) {
            if (e.getEmail().equalsIgnoreCase(username)
                    && e.getPhone().equals(password)
                    && "HR".equalsIgnoreCase(e.getRole())) {
                authenticated = true;
                break;
            }
        }

        if (authenticated) {
            loadScene("/HRMenu.fxml", "HR Menu", event);
        } else {
            errorLabel.setText("Invalid HR credentials!");
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

package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class LeaveValidationController {

    @FXML
    private TableView<?> tableLeave;

    @FXML
    private TableColumn<?, ?> colIdLeave;

    @FXML
    private TableColumn<?, ?> colEmployee;

    @FXML
    private TableColumn<?, ?> colStartDate;

    @FXML
    private TableColumn<?, ?> colEndDate;

    @FXML
    private TableColumn<?, ?> colReason;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private Button btnApprove;

    @FXML
    private Button btnReject;

    @FXML
    public void initialize() {
        // will later:
        // - configure table columns
        // - load leave requests
    }

    @FXML
    private void approveLeave() {
        // triggered when Approve is clicked
        // later: update status to APPROVED
    }

    @FXML
    private void rejectLeave() {
        // triggered when Reject is clicked
        // later: update status to REJECTED
    }
}

package Services;

import Entite.LeaveRequest;
import java.util.List;

public class LeaveRequestService implements IService<LeaveRequest> {

    @Override
    public void add(LeaveRequest leave) {
        // INSERT leave request
    }

    @Override
    public void update(LeaveRequest leave) {
        // UPDATE leave request
    }

    @Override
    public void delete(int id) {
        // DELETE leave request
    }

    @Override
    public List<LeaveRequest> getAll() {
        // SELECT * FROM leave_request
        return null;
    }

    @Override
    public LeaveRequest getById(int id) {
        // SELECT leave request WHERE id
        return null;
    }

    public List<LeaveRequest> getLeavesByEmployee(int employeeId) {
        return null;
    }

    public void approveLeave(int leaveId, int validatorId) {
        // UPDATE status = APPROVED
    }

    public void rejectLeave(int leaveId, int validatorId) {
        // UPDATE status = REJECTED
    }
}

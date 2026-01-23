package Test;

import Entite.LeaveRequest;
import Services.LeaveRequestService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class LeaveRequestTest {
    public static void main(String[] args) {

        LeaveRequestService service = new LeaveRequestService();

        // 1️⃣ Add two leave requests
        LeaveRequest l1 = new LeaveRequest(0, 12,
                LocalDate.of(2026, 2, 1),
                LocalDate.of(2026, 2, 5),
                "Vacation", "PENDING", LocalDateTime.now(), null);
        service.add(l1);
        System.out.println("Added leave request for employee 1.");

        LeaveRequest l2 = new LeaveRequest(0, 1,
                LocalDate.of(2026, 2, 10),
                LocalDate.of(2026, 2, 12),
                "Medical", "PENDING", LocalDateTime.now(), null);
        service.add(l2);
        System.out.println("Added leave request for employee 2.");

        // 2️⃣ Get all leave requests
        System.out.println("\nAll leave requests:");
        List<LeaveRequest> allLeaves = service.getAll();
        for (LeaveRequest l : allLeaves) {
            System.out.println(l.getIdLeave() + " | Employee: " + l.getIdEmployee() +
                    " | " + l.getStartDate() + " to " + l.getEndDate() +
                    " | Reason: " + l.getReason() +
                    " | Status: " + l.getStatus() +
                    " | RequestedAt: " + l.getRequestedAt() +
                    " | ValidatedBy: " + l.getValidatedBy());
        }

        // 3️⃣ Get leave request by ID (first one)
        if (!allLeaves.isEmpty()) {
            int id = allLeaves.get(0).getIdLeave();
            LeaveRequest fetched = service.getById(id);
            System.out.println("\nFetched leave request ID " + id + " for employee " + fetched.getIdEmployee());
        }

        // 4️⃣ Update first leave request
        if (!allLeaves.isEmpty()) {
            LeaveRequest l = allLeaves.get(0);
            l.setStatus("APPROVED");
            l.setValidatedBy(99); // example manager ID
            service.update(l);
            System.out.println("Updated leave request ID " + l.getIdLeave());
        }

        // 5️⃣ Delete second leave request
        if (allLeaves.size() > 1) {
            int id = allLeaves.get(1).getIdLeave();
            service.delete(id);
            System.out.println("Deleted leave request ID " + id);
        }

        // 6️⃣ Show final leave requests
        System.out.println("\nLeave requests after update/delete:");
        allLeaves = service.getAll();
        for (LeaveRequest l : allLeaves) {
            System.out.println(l.getIdLeave() + " | Employee: " + l.getIdEmployee() +
                    " | " + l.getStartDate() + " to " + l.getEndDate() +
                    " | Reason: " + l.getReason() +
                    " | Status: " + l.getStatus() +
                    " | RequestedAt: " + l.getRequestedAt() +
                    " | ValidatedBy: " + l.getValidatedBy());
        }

        System.out.println("\nSimple CRUD test completed!");
    }
}

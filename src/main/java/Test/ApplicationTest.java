package Test;

import Entite.Application;
import Services.ApplicationService;

import java.time.LocalDateTime;
import java.util.List;

public class ApplicationTest {
    public static void main(String[] args) {

        ApplicationService service = new ApplicationService();

        // 1️⃣ Add two applications
        Application app1 = new Application(0, 1, 1, "PENDING", LocalDateTime.now());
        service.add(app1);
        System.out.println("Added application for candidate 1 to offer 1.");

        Application app2 = new Application(0, 4, 1, "PENDING", LocalDateTime.now());
        service.add(app2);
        System.out.println("Added application for candidate 2 to offer 1.");

        // 2️⃣ Get all applications
        System.out.println("\nAll applications:");
        List<Application> allApps = service.getAll();
        for (Application a : allApps) {
            System.out.println(a.getIdApplication() + " | Candidate: " + a.getIdCandidate() +
                    " | Offer: " + a.getIdOffer() +
                    " | Status: " + a.getStatus() +
                    " | AppliedAt: " + a.getAppliedAt());
        }

        // 3️⃣ Get application by ID (first one)
        if (!allApps.isEmpty()) {
            int id = allApps.get(0).getIdApplication();
            Application fetched = service.getById(id);
            System.out.println("\nFetched application ID " + id + " for candidate " + fetched.getIdCandidate());
        }

        // 4️⃣ Update first application
        if (!allApps.isEmpty()) {
            Application a = allApps.get(0);
            a.setStatus("SHORTLISTED");
            service.update(a);
            System.out.println("Updated application ID " + a.getIdApplication() + " to SHORTLISTED.");
        }

        // 5️⃣ Delete second application
        if (allApps.size() > 1) {
            int id = allApps.get(1).getIdApplication();
            service.delete(id);
            System.out.println("Deleted application ID " + id);
        }

        // 6️⃣ Show final applications
        System.out.println("\nApplications after update/delete:");
        allApps = service.getAll();
        for (Application a : allApps) {
            System.out.println(a.getIdApplication() + " | Candidate: " + a.getIdCandidate() +
                    " | Offer: " + a.getIdOffer() +
                    " | Status: " + a.getStatus() +
                    " | AppliedAt: " + a.getAppliedAt());
        }

        System.out.println("\nSimple CRUD test completed!");
    }
}

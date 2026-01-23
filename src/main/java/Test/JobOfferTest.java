package Test;

import Entite.JobOffer;
import Services.JobOfferService;

import java.time.LocalDateTime;
import java.util.List;

public class JobOfferTest {
    public static void main(String[] args) {

        JobOfferService service = new JobOfferService();

        // 1️⃣ Add two job offers
        JobOffer offer1 = new JobOffer(0, "Java Developer", "Develop Java applications",
                "OPEN", LocalDateTime.now(), 1); // createdBy = 1 (example employee ID)
        service.add(offer1);
        System.out.println("Added job offer: Java Developer");

        JobOffer offer2 = new JobOffer(0, "QA Engineer", "Test applications",
                "OPEN", LocalDateTime.now(), 1);
        service.add(offer2);
        System.out.println("Added job offer: QA Engineer");

        // 2️⃣ Get all job offers
        System.out.println("\nAll job offers:");
        List<JobOffer> allOffers = service.getAll();
        for (JobOffer o : allOffers) {
            System.out.println(o.getIdOffer() + " | " + o.getTitle() + " | " + o.getDescription() +
                    " | Status: " + o.getStatus() + " | CreatedAt: " + o.getCreatedAt() +
                    " | CreatedBy: " + o.getCreatedBy());
        }

        // 3️⃣ Get job offer by ID (first one)
        if (!allOffers.isEmpty()) {
            int id = allOffers.get(0).getIdOffer();
            JobOffer fetched = service.getById(id);
            System.out.println("\nFetched job offer ID " + id + ": Title=" + fetched.getTitle());
        }

        // 4️⃣ Update first job offer
        if (!allOffers.isEmpty()) {
            JobOffer o = allOffers.get(0);
            o.setTitle("Senior Java Developer");
            o.setDescription("Develop and design Java applications");
            service.update(o);
            System.out.println("Updated job offer ID " + o.getIdOffer());
        }

        // 5️⃣ Delete second job offer
        if (allOffers.size() > 1) {
            int id = allOffers.get(1).getIdOffer();
            service.delete(id);
            System.out.println("Deleted job offer ID " + id);
        }

        // 6️⃣ Show final job offers
        System.out.println("\nJob offers after update/delete:");
        allOffers = service.getAll();
        for (JobOffer o : allOffers) {
            System.out.println(o.getIdOffer() + " | " + o.getTitle() + " | " + o.getDescription() +
                    " | Status: " + o.getStatus() + " | CreatedAt: " + o.getCreatedAt() +
                    " | CreatedBy: " + o.getCreatedBy());
        }

        System.out.println("\nSimple CRUD test completed!");
    }
}

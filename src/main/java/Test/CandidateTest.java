package Test;

import Entite.Candidate;
import Services.CandidateService;

import java.util.List;

public class CandidateTest {
    public static void main(String[] args) {

        CandidateService service = new CandidateService();

        // 1️⃣ Add two candidates
        Candidate c1 = new Candidate(0, "Alice", "Smith", "alice@example.com", "1234567890", "Alice_Resume.pdf");
        service.add(c1);
        System.out.println("Added candidate Alice.");

        Candidate c2 = new Candidate(0, "Bob", "Johnson", "bob@example.com", "0987654321", "Bob_Resume.pdf");
        service.add(c2);
        System.out.println("Added candidate Bob.");

        // 2️⃣ Get all candidates
        System.out.println("\nAll candidates:");
        List<Candidate> allCandidates = service.getAll();
        for (Candidate c : allCandidates) {
            System.out.println(c.getIdCandidate() + " | " + c.getFirstName() + " " + c.getLastName() +
                    " | Email: " + c.getEmail() + " | Phone: " + c.getPhone() +
                    " | Resume: " + c.getResume());
        }

        // 3️⃣ Get candidate by ID (first one)
        if (!allCandidates.isEmpty()) {
            int id = allCandidates.get(0).getIdCandidate();
            Candidate fetched = service.getById(id);
            System.out.println("\nFetched candidate ID " + id + ": Name=" + fetched.getFirstName() +
                    " " + fetched.getLastName());
        }

        // 4️⃣ Update first candidate
        if (!allCandidates.isEmpty()) {
            Candidate c = allCandidates.get(0);
            c.setEmail("alice_updated@example.com");
            c.setResume("Alice_Resume_Updated.pdf");
            service.update(c);
            System.out.println("Updated candidate ID " + c.getIdCandidate() + " email and resume.");
        }

        // 5️⃣ Delete second candidate
        if (allCandidates.size() > 1) {
            int id = allCandidates.get(1).getIdCandidate();
            service.delete(id);
            System.out.println("Deleted candidate ID " + id);
        }

        // 6️⃣ Show final candidates
        System.out.println("\nCandidates after update/delete:");
        allCandidates = service.getAll();
        for (Candidate c : allCandidates) {
            System.out.println(c.getIdCandidate() + " | " + c.getFirstName() + " " + c.getLastName() +
                    " | Email: " + c.getEmail() + " | Phone: " + c.getPhone() +
                    " | Resume: " + c.getResume());
        }

        System.out.println("\nSimple CRUD test completed!");
    }
}

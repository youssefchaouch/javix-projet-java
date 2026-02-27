package Entite;

public class CandidateApplication {
    private int candidateId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private int applicationId;
    private int idOffer;
    private String status;

    public CandidateApplication(int candidateId, String firstName, String lastName,
                                String email, String phone, int applicationId, int idOffer, String status) {
        this.candidateId = candidateId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.applicationId = applicationId;
        this.idOffer = idOffer;
        this.status = status;
    }

    // Getters & setters
    public int getCandidateId() { return candidateId; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public int getApplicationId() { return applicationId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public int getIdOffer() {
        return idOffer;
    }

    public void setIdOffer(int idOffer) {
        this.idOffer = idOffer;
    }
}

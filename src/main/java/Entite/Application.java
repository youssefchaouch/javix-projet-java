package Entite;

import java.time.LocalDateTime;

public class Application {

    private int idApplication;
    private int idCandidate;
    private int idOffer;
    private String status;
    private LocalDateTime appliedAt;

    public Application() {}

    public Application(int idApplication, int idCandidate, int idOffer,
                       String status, LocalDateTime appliedAt) {
        this.idApplication = idApplication;
        this.idCandidate = idCandidate;
        this.idOffer = idOffer;
        this.status = status;
        this.appliedAt = appliedAt;
    }

    public int getIdApplication() { return idApplication; }
    public void setIdApplication(int idApplication) { this.idApplication = idApplication; }

    public int getIdCandidate() { return idCandidate; }
    public void setIdCandidate(int idCandidate) { this.idCandidate = idCandidate; }

    public int getIdOffer() { return idOffer; }
    public void setIdOffer(int idOffer) { this.idOffer = idOffer; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getAppliedAt() { return appliedAt; }
    public void setAppliedAt(LocalDateTime appliedAt) { this.appliedAt = appliedAt; }
}

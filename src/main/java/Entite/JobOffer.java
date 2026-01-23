package Entite;

import java.time.LocalDateTime;

public class JobOffer {

    private int idOffer;
    private String title;
    private String description;
    private String status;
    private LocalDateTime createdAt;
    private int createdBy;

    public JobOffer() {}

    public JobOffer(int idOffer, String title, String description,
                    String status, LocalDateTime createdAt, int createdBy) {
        this.idOffer = idOffer;
        this.title = title;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
    }

    public int getIdOffer() { return idOffer; }
    public void setIdOffer(int idOffer) { this.idOffer = idOffer; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public int getCreatedBy() { return createdBy; }
    public void setCreatedBy(int createdBy) { this.createdBy = createdBy; }
}

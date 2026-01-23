package Entite;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class LeaveRequest {

    private int idLeave;
    private int idEmployee;
    private LocalDate startDate;
    private LocalDate endDate;
    private String reason;
    private String status;
    private LocalDateTime requestedAt;
    private Integer validatedBy;

    public LeaveRequest() {}

    public LeaveRequest(int idLeave, int idEmployee, LocalDate startDate,
                        LocalDate endDate, String reason, String status,
                        LocalDateTime requestedAt, Integer validatedBy) {
        this.idLeave = idLeave;
        this.idEmployee = idEmployee;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reason = reason;
        this.status = status;
        this.requestedAt = requestedAt;
        this.validatedBy = validatedBy;
    }

    public int getIdLeave() { return idLeave; }
    public void setIdLeave(int idLeave) { this.idLeave = idLeave; }

    public int getIdEmployee() { return idEmployee; }
    public void setIdEmployee(int idEmployee) { this.idEmployee = idEmployee; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getRequestedAt() { return requestedAt; }
    public void setRequestedAt(LocalDateTime requestedAt) { this.requestedAt = requestedAt; }

    public Integer getValidatedBy() { return validatedBy; }
    public void setValidatedBy(Integer validatedBy) { this.validatedBy = validatedBy; }
}

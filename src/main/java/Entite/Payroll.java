package Entite;

import java.time.LocalDateTime;

public class Payroll {

    private int idPayroll;
    private int idEmployee;
    private int month;
    private int year;
    private int totalLeaveDays;
    private double finalSalary;
    private LocalDateTime generatedAt;

    public Payroll() {}

    public Payroll(int idPayroll, int idEmployee, int month, int year,
                   int totalLeaveDays, double finalSalary,
                   LocalDateTime generatedAt) {
        this.idPayroll = idPayroll;
        this.idEmployee = idEmployee;
        this.month = month;
        this.year = year;
        this.totalLeaveDays = totalLeaveDays;
        this.finalSalary = finalSalary;
        this.generatedAt = generatedAt;
    }

    public int getIdPayroll() { return idPayroll; }
    public void setIdPayroll(int idPayroll) { this.idPayroll = idPayroll; }

    public int getIdEmployee() { return idEmployee; }
    public void setIdEmployee(int idEmployee) { this.idEmployee = idEmployee; }

    public int getMonth() { return month; }
    public void setMonth(int month) { this.month = month; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public int getTotalLeaveDays() { return totalLeaveDays; }
    public void setTotalLeaveDays(int totalLeaveDays) { this.totalLeaveDays = totalLeaveDays; }

    public double getFinalSalary() { return finalSalary; }
    public void setFinalSalary(double finalSalary) { this.finalSalary = finalSalary; }

    public LocalDateTime getGeneratedAt() { return generatedAt; }
    public void setGeneratedAt(LocalDateTime generatedAt) { this.generatedAt = generatedAt; }
}

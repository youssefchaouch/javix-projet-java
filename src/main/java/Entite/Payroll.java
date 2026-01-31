package Entite;

import javafx.beans.property.*;
import java.time.LocalDateTime;

public class Payroll {
    private IntegerProperty idPayroll;
    private IntegerProperty idEmployee;
    private IntegerProperty month;
    private IntegerProperty year;
    private IntegerProperty totalLeaveDays;
    private DoubleProperty finalSalary;
    private ObjectProperty<LocalDateTime> generatedAt;

    public Payroll(int idPayroll, int idEmployee, int month, int year, int totalLeaveDays, double finalSalary, LocalDateTime generatedAt) {
        this.idPayroll = new SimpleIntegerProperty(idPayroll);
        this.idEmployee = new SimpleIntegerProperty(idEmployee);
        this.month = new SimpleIntegerProperty(month);
        this.year = new SimpleIntegerProperty(year);
        this.totalLeaveDays = new SimpleIntegerProperty(totalLeaveDays);
        this.finalSalary = new SimpleDoubleProperty(finalSalary);
        this.generatedAt = new SimpleObjectProperty<>(generatedAt);
    }

    public IntegerProperty idPayrollProperty() { return idPayroll; }
    public IntegerProperty idEmployeeProperty() { return idEmployee; }
    public IntegerProperty monthProperty() { return month; }
    public IntegerProperty yearProperty() { return year; }
    public IntegerProperty totalLeaveDaysProperty() { return totalLeaveDays; }
    public DoubleProperty finalSalaryProperty() { return finalSalary; }
    public ObjectProperty<LocalDateTime> generatedAtProperty() { return generatedAt; }

    public int getIdPayroll() { return idPayroll.get(); }
    public int getIdEmployee() { return idEmployee.get(); }
    public int getMonth() { return month.get(); }
    public int getYear() { return year.get(); }
    public int getTotalLeaveDays() { return totalLeaveDays.get(); }
    public double getFinalSalary() { return finalSalary.get(); }
    public LocalDateTime getGeneratedAt() { return generatedAt.get(); }

    public void setIdPayroll(int id) { idPayroll.set(id); }
    public void setIdEmployee(int id) { idEmployee.set(id); }
    public void setMonth(int month) { this.month.set(month); }
    public void setYear(int year) { this.year.set(year); }
    public void setTotalLeaveDays(int days) { totalLeaveDays.set(days); }
    public void setFinalSalary(double salary) { finalSalary.set(salary); }
    public void setGeneratedAt(LocalDateTime date) { generatedAt.set(date); }
}

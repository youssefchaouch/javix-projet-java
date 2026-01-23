package Entite;

import java.time.LocalDate;

public class Employee {

    private int idEmployee;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private LocalDate hireDate;
    private String role;
    private double salary;
    private String status;

    public Employee() {}

    public Employee(int idEmployee, String firstName, String lastName,
                    String email, String phone, LocalDate hireDate,
                    String role, double salary, String status) {
        this.idEmployee = idEmployee;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.hireDate = hireDate;
        this.role = role;
        this.salary = salary;
        this.status = status;
    }

    public int getIdEmployee() { return idEmployee; }
    public void setIdEmployee(int idEmployee) { this.idEmployee = idEmployee; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public LocalDate getHireDate() { return hireDate; }
    public void setHireDate(LocalDate hireDate) { this.hireDate = hireDate; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}

package Test;

import Entite.Employee;
import Services.EmployeeService;

import java.time.LocalDate;
import java.util.List;

public class EmployeeTest {
    public static void main(String[] args) {

        EmployeeService service = new EmployeeService();

        // 1️⃣ Add two employees
        Employee emp1 = new Employee(3021, "alica", "Smith", "alica@exampple.com", "1234567890",
                LocalDate.of(2023, 1, 15), "Developer", 5000.0, "ACTIVE");
        service.add(emp1);
        System.out.println("Added employee Alica.");

        Employee emp2 = new Employee(4103, "Boba", "Johnson", "boba@exampple.com", "0987654321",
                LocalDate.of(2023, 2, 20), "Tester", 4500.0, "ACTIVE");
        service.add(emp2);
        System.out.println("Added employee Boba.");

        // 2️⃣ Get all employees
        System.out.println("\nAll employees:");
        List<Employee> allEmps = service.getAll();
        for (Employee e : allEmps) {
            System.out.println(e.getIdEmployee() + " | " + e.getFirstName() + " " + e.getLastName() +
                    " | Role: " + e.getRole() + " | Salary: " + e.getSalary() +
                    " | Status: " + e.getStatus());
        }

        // 3️⃣ Get employee by ID (first one)
        if (!allEmps.isEmpty()) {
            int id = allEmps.get(0).getIdEmployee();
            Employee fetched = service.getById(id);
            System.out.println("\nFetched employee ID " + id + ": Name=" + fetched.getFirstName() +
                    " " + fetched.getLastName());
        }

        // 4️⃣ Update first employee
        if (!allEmps.isEmpty()) {
            Employee e = allEmps.get(0);
            e.setSalary(5500.0);
            e.setRole("Senior Developer");
            service.update(e);
            System.out.println("Updated employee ID " + e.getIdEmployee() + " salary and role.");
        }

        // 5️⃣ Delete second employee
        if (allEmps.size() > 1) {
            int id = allEmps.get(1).getIdEmployee();
            service.delete(id);
            System.out.println("Deleted employee ID " + id);
        }

        // 6️⃣ Show final employees
        System.out.println("\nEmployees after update/delete:");
        allEmps = service.getAll();
        for (Employee e : allEmps) {
            System.out.println(e.getIdEmployee() + " | " + e.getFirstName() + " " + e.getLastName() +
                    " | Role: " + e.getRole() + " | Salary: " + e.getSalary() +
                    " | Status: " + e.getStatus());
        }

        System.out.println("\nSimple CRUD test completed!");
    }
}

package Services;

import Entite.Employee;
import java.util.List;

public class EmployeeService implements IService<Employee> {

    @Override
    public void add(Employee employee) {
        // INSERT employee into database
    }

    @Override
    public void update(Employee employee) {
        // UPDATE employee
    }

    @Override
    public void delete(int id) {
        // DELETE employee
    }

    @Override
    public List<Employee> getAll() {
        // SELECT * FROM employee
        return null;
    }

    @Override
    public Employee getById(int id) {
        // SELECT employee WHERE id
        return null;
    }

    // Business-specific methods
    public List<Employee> getEmployeesByRole(String role) {
        return null;
    }

    public void updateSalary(int employeeId, double newSalary) {
        // UPDATE salary
    }
}

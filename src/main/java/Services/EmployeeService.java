package Services;

import Entite.Employee;
import Utlis.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeService implements IService<Employee> {

    private Connection con;

    public EmployeeService() {
        con = DataSource.getInstance().getCon();
    }

    @Override
    public void add(Employee employee) {
        try (Statement st = con.createStatement()) {
            String sql = "INSERT INTO employee (first_name, last_name, email, phone, hire_date, role, salary, status) VALUES (" +
                    "'" + employee.getFirstName() + "', " +
                    "'" + employee.getLastName() + "', " +
                    "'" + employee.getEmail() + "', " +
                    "'" + employee.getPhone() + "', " +
                    "'" + Date.valueOf(employee.getHireDate()) + "', " +
                    "'" + employee.getRole() + "', " +
                    employee.getSalary() + ", " +
                    "'" + employee.getStatus() + "')";
            st.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Employee employee) {
        try (Statement st = con.createStatement()) {
            String sql = "UPDATE employee SET " +
                    "first_name='" + employee.getFirstName() + "', " +
                    "last_name='" + employee.getLastName() + "', " +
                    "email='" + employee.getEmail() + "', " +
                    "phone='" + employee.getPhone() + "', " +
                    "hire_date='" + Date.valueOf(employee.getHireDate()) + "', " +
                    "role='" + employee.getRole() + "', " +
                    "salary=" + employee.getSalary() + ", " +
                    "status='" + employee.getStatus() + "' " +
                    "WHERE id_employee=" + employee.getIdEmployee();
            st.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try (Statement st = con.createStatement()) {
            String sql = "DELETE FROM employee WHERE id_employee=" + id;
            st.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Employee> getAll() {
        List<Employee> list = new ArrayList<>();
        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM employee")) {

            while (rs.next()) {
                Employee e = new Employee(
                        rs.getInt("id_employee"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getDate("hire_date").toLocalDate(),
                        rs.getString("role"),
                        rs.getDouble("salary"),
                        rs.getString("status")
                );
                list.add(e);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    @Override
    public Employee getById(int id) {
        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM employee WHERE id_employee=" + id)) {

            if (rs.next()) {
                return new Employee(
                        rs.getInt("id_employee"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getDate("hire_date").toLocalDate(),
                        rs.getString("role"),
                        rs.getDouble("salary"),
                        rs.getString("status")
                );
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}

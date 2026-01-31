package Services;

import Entite.Employee;
import Entite.Payroll;
import Utlis.DataSource;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PayrollService implements IService<Payroll> {

    private Connection con;
    private EmployeeService employeeService;
    private LeaveRequestService leaveRequestService;

    public PayrollService() {
        con = DataSource.getInstance().getCon();
        employeeService = new EmployeeService();
        leaveRequestService = new LeaveRequestService();
    }

    @Override
    public void add(Payroll payroll) {
        try (PreparedStatement pst = con.prepareStatement(
                "INSERT INTO payroll (id_employee, month, year, total_leave_days, final_salary, generated_at) VALUES (?, ?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS)) {

            pst.setInt(1, payroll.getIdEmployee());
            pst.setInt(2, payroll.getMonth());
            pst.setInt(3, payroll.getYear());
            pst.setInt(4, payroll.getTotalLeaveDays());
            pst.setDouble(5, payroll.getFinalSalary());
            pst.setTimestamp(6, Timestamp.valueOf(payroll.getGeneratedAt()));

            pst.executeUpdate();

            ResultSet keys = pst.getGeneratedKeys();
            if (keys.next())
                payroll.setIdPayroll(keys.getInt(1));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Payroll payroll) {
        try (PreparedStatement pst = con.prepareStatement(
                "UPDATE payroll SET total_leave_days=?, final_salary=? WHERE id_payroll=?")) {

            pst.setInt(1, payroll.getTotalLeaveDays());
            pst.setDouble(2, payroll.getFinalSalary());
            pst.setInt(3, payroll.getIdPayroll());

            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try (Statement st = con.createStatement()) {
            st.executeUpdate("DELETE FROM payroll WHERE id_payroll=" + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Payroll> getAll() {
        List<Payroll> list = new ArrayList<>();
        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(
                        "SELECT p.*, e.salary, e.role " +
                                "FROM payroll p JOIN employee e ON p.id_employee=e.id_employee")) {

            while (rs.next()) {
                Payroll p = new Payroll(
                        rs.getInt("id_payroll"),
                        rs.getInt("id_employee"),
                        rs.getInt("month"),
                        rs.getInt("year"),
                        rs.getInt("total_leave_days"),
                        rs.getDouble("final_salary"),
                        rs.getTimestamp("generated_at").toLocalDateTime());
                list.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Payroll getById(int id) {
        try (PreparedStatement pst = con.prepareStatement(
                "SELECT p.*, e.salary, e.role " +
                        "FROM payroll p JOIN employee e ON p.id_employee=e.id_employee WHERE id_payroll=?")) {
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return new Payroll(
                        rs.getInt("id_payroll"),
                        rs.getInt("id_employee"),
                        rs.getInt("month"),
                        rs.getInt("year"),
                        rs.getInt("total_leave_days"),
                        rs.getDouble("final_salary"),
                        rs.getTimestamp("generated_at").toLocalDateTime());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // ========== Fonctions avancées ==========

    /** Génère automatiquement la paie de tous les employés pour un mois donné */
    public void generateMonthlyPayroll(int month, int year) {
        List<Employee> employees = employeeService.getAll();
        for (Employee emp : employees) {
            int leaveDays = leaveRequestService.countApprovedLeaveDays(emp.getIdEmployee(), month, year);
            double dailyRate = emp.getSalary() / 30;
            double finalSalary = emp.getSalary() - (leaveDays * dailyRate);

            Payroll payroll = new Payroll(0, emp.getIdEmployee(), month, year, leaveDays, finalSalary,
                    LocalDateTime.now());
            add(payroll);
        }
    }

    /** Total salaires par mois */
    public double totalSalaryByMonth(int month, int year) {
        double total = 0;
        try (PreparedStatement pst = con.prepareStatement(
                "SELECT SUM(final_salary) as total FROM payroll WHERE month=? AND year=?")) {
            pst.setInt(1, month);
            pst.setInt(2, year);
            ResultSet rs = pst.executeQuery();
            if (rs.next())
                total = rs.getDouble("total");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

    /** Total salaires par rôle */
    public double totalSalaryByRole(String role, int month, int year) {
        double total = 0;
        try (PreparedStatement pst = con.prepareStatement(
                "SELECT SUM(p.final_salary) as total " +
                        "FROM payroll p JOIN employee e ON p.id_employee=e.id_employee " +
                        "WHERE e.role=? AND p.month=? AND p.year=?")) {
            pst.setString(1, role);
            pst.setInt(2, month);
            pst.setInt(3, year);
            ResultSet rs = pst.executeQuery();
            if (rs.next())
                total = rs.getDouble("total");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }
}

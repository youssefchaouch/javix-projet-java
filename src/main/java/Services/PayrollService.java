package Services;

import Entite.Payroll;
import Utlis.DataSource;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PayrollService implements IService<Payroll> {

    private Connection con;

    public PayrollService() {
        con = DataSource.getInstance().getCon();
    }

    @Override
    public void add(Payroll payroll) {
        try (Statement st = con.createStatement()) {
            String sql = "INSERT INTO payroll (id_employee, month, year, total_leave_days, final_salary, generated_at) VALUES (" +
                    payroll.getIdEmployee() + ", " +
                    payroll.getMonth() + ", " +
                    payroll.getYear() + ", " +
                    payroll.getTotalLeaveDays() + ", " +
                    payroll.getFinalSalary() + ", '" +
                    Timestamp.valueOf(payroll.getGeneratedAt()) + "')";
            st.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Payroll payroll) {
        try (Statement st = con.createStatement()) {
            String sql = "UPDATE payroll SET " +
                    "id_employee=" + payroll.getIdEmployee() + ", " +
                    "month=" + payroll.getMonth() + ", " +
                    "year=" + payroll.getYear() + ", " +
                    "total_leave_days=" + payroll.getTotalLeaveDays() + ", " +
                    "final_salary=" + payroll.getFinalSalary() + ", " +
                    "generated_at='" + Timestamp.valueOf(payroll.getGeneratedAt()) + "' " +
                    "WHERE id_payroll=" + payroll.getIdPayroll();
            st.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try (Statement st = con.createStatement()) {
            String sql = "DELETE FROM payroll WHERE id_payroll=" + id;
            st.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Payroll> getAll() {
        List<Payroll> list = new ArrayList<>();
        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM payroll")) {

            while (rs.next()) {
                Payroll p = new Payroll(
                        rs.getInt("id_payroll"),
                        rs.getInt("id_employee"),
                        rs.getInt("month"),
                        rs.getInt("year"),
                        rs.getInt("total_leave_days"),
                        rs.getDouble("final_salary"),
                        rs.getTimestamp("generated_at").toLocalDateTime()
                );
                list.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Payroll getById(int id) {
        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM payroll WHERE id_payroll=" + id)) {

            if (rs.next()) {
                return new Payroll(
                        rs.getInt("id_payroll"),
                        rs.getInt("id_employee"),
                        rs.getInt("month"),
                        rs.getInt("year"),
                        rs.getInt("total_leave_days"),
                        rs.getDouble("final_salary"),
                        rs.getTimestamp("generated_at").toLocalDateTime()
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

package Services;

import Entite.LeaveRequest;
import Utlis.DataSource;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LeaveRequestService implements IService<LeaveRequest> {

    private Connection con;

    public LeaveRequestService() {
        con = DataSource.getInstance().getCon();
    }

    @Override
    public void add(LeaveRequest leave) {
        try (Statement st = con.createStatement()) {
            String sql = "INSERT INTO leave_request (id_employee, start_date, end_date, reason, status, requested_at, validated_by) VALUES ("
                    +
                    leave.getIdEmployee() + ", " +
                    "'" + leave.getStartDate() + "', " +
                    "'" + leave.getEndDate() + "', " +
                    "'" + leave.getReason() + "', " +
                    "'" + leave.getStatus() + "', " +
                    "'" + Timestamp.valueOf(leave.getRequestedAt()) + "', " +
                    (leave.getValidatedBy() == null ? "NULL" : leave.getValidatedBy()) + ")";
            st.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(LeaveRequest leave) {
        try (Statement st = con.createStatement()) {
            String sql = "UPDATE leave_request SET " +
                    "id_employee=" + leave.getIdEmployee() + ", " +
                    "start_date='" + leave.getStartDate() + "', " +
                    "end_date='" + leave.getEndDate() + "', " +
                    "reason='" + leave.getReason() + "', " +
                    "status='" + leave.getStatus() + "', " +
                    "requested_at='" + Timestamp.valueOf(leave.getRequestedAt()) + "', " +
                    "validated_by=" + (leave.getValidatedBy() == null ? "NULL" : leave.getValidatedBy()) + " " +
                    "WHERE id_leave=" + leave.getIdLeave();
            st.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try (Statement st = con.createStatement()) {
            String sql = "DELETE FROM leave_request WHERE id_leave=" + id;
            st.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<LeaveRequest> getAll() {
        List<LeaveRequest> list = new ArrayList<>();
        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM leave_request")) {

            while (rs.next()) {
                LeaveRequest lr = new LeaveRequest(
                        rs.getInt("id_leave"),
                        rs.getInt("id_employee"),
                        rs.getDate("start_date").toLocalDate(),
                        rs.getDate("end_date").toLocalDate(),
                        rs.getString("reason"),
                        rs.getString("status"),
                        rs.getTimestamp("requested_at").toLocalDateTime(),
                        rs.getObject("validated_by") == null ? null : rs.getInt("validated_by"));
                list.add(lr);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public LeaveRequest getById(int id) {
        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM leave_request WHERE id_leave=" + id)) {

            if (rs.next()) {
                return new LeaveRequest(
                        rs.getInt("id_leave"),
                        rs.getInt("id_employee"),
                        rs.getDate("start_date").toLocalDate(),
                        rs.getDate("end_date").toLocalDate(),
                        rs.getString("reason"),
                        rs.getString("status"),
                        rs.getTimestamp("requested_at").toLocalDateTime(),
                        rs.getObject("validated_by") == null ? null : rs.getInt("validated_by"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int countApprovedLeaveDays(int employeeId, int month, int year) {
        int totalDays = 0;
        LocalDate startMonth = LocalDate.of(year, month, 1);
        LocalDate endMonth = startMonth.withDayOfMonth(startMonth.lengthOfMonth());

        try (PreparedStatement pst = con.prepareStatement(
                "SELECT start_date, end_date FROM leave_request WHERE id_employee=? AND status='Approved'")) {
            pst.setInt(1, employeeId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                LocalDate start = rs.getDate("start_date").toLocalDate();
                LocalDate end = rs.getDate("end_date").toLocalDate();

                if (!start.isAfter(endMonth) && !end.isBefore(startMonth)) {
                    LocalDate actualStart = start.isBefore(startMonth) ? startMonth : start;
                    LocalDate actualEnd = end.isAfter(endMonth) ? endMonth : end;
                    totalDays += (int) java.time.temporal.ChronoUnit.DAYS.between(actualStart, actualEnd) + 1;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalDays;
    }

}

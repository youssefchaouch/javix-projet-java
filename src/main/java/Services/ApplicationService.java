package Services;

import Entite.Application;
import Entite.CandidateApplication;
import Utlis.DataSource;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ApplicationService implements IService<Application> {

    private Connection con;

    public ApplicationService() {
        con = DataSource.getInstance().getCon();
    }

    @Override
    public void add(Application application) {
        try (Statement st = con.createStatement()) {
            String sql = "INSERT INTO application (id_candidate, id_offer, status, applied_at) VALUES (" +
                    application.getIdCandidate() + ", " +
                    application.getIdOffer() + ", '" +
                    application.getStatus() + "', '" +
                    Timestamp.valueOf(application.getAppliedAt()) + "')";
            st.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Application application) {
        try (Statement st = con.createStatement()) {
            String sql = "UPDATE application SET " +
                    "id_candidate=" + application.getIdCandidate() + ", " +
                    "id_offer=" + application.getIdOffer() + ", " +
                    "status='" + application.getStatus() + "', " +
                    "applied_at='" + Timestamp.valueOf(application.getAppliedAt()) + "' " +
                    "WHERE id_application=" + application.getIdApplication();
            st.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try (Statement st = con.createStatement()) {
            String sql = "DELETE FROM application WHERE id_application=" + id;
            st.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Application> getAll() {
        List<Application> list = new ArrayList<>();
        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM application")) {

            while (rs.next()) {
                Application a = new Application(
                        rs.getInt("id_application"),
                        rs.getInt("id_candidate"),
                        rs.getInt("id_offer"),
                        rs.getString("status"),
                        rs.getTimestamp("applied_at").toLocalDateTime()
                );
                list.add(a);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Application getById(int id) {
        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM application WHERE id_application=" + id)) {

            if (rs.next()) {
                return new Application(
                        rs.getInt("id_application"),
                        rs.getInt("id_candidate"),
                        rs.getInt("id_offer"),
                        rs.getString("status"),
                        rs.getTimestamp("applied_at").toLocalDateTime()
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}

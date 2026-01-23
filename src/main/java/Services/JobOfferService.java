package Services;

import Entite.JobOffer;
import Utlis.DataSource;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class JobOfferService implements IService<JobOffer> {

    private Connection con;

    public JobOfferService() {
        con = DataSource.getInstance().getCon();
    }

    @Override
    public void add(JobOffer offer) {
        try (Statement st = con.createStatement()) {
            String sql = "INSERT INTO job_offer (title, description, status, created_at, created_by) VALUES (" +
                    "'" + offer.getTitle() + "', " +
                    "'" + offer.getDescription() + "', " +
                    "'" + offer.getStatus() + "', " +
                    "'" + Timestamp.valueOf(offer.getCreatedAt()) + "', " +
                    offer.getCreatedBy() + ")";
            st.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(JobOffer offer) {
        try (Statement st = con.createStatement()) {
            String sql = "UPDATE job_offer SET " +
                    "title='" + offer.getTitle() + "', " +
                    "description='" + offer.getDescription() + "', " +
                    "status='" + offer.getStatus() + "', " +
                    "created_at='" + Timestamp.valueOf(offer.getCreatedAt()) + "', " +
                    "created_by=" + offer.getCreatedBy() + " " +
                    "WHERE id_offer=" + offer.getIdOffer();
            st.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try (Statement st = con.createStatement()) {
            String sql = "DELETE FROM job_offer WHERE id_offer=" + id;
            st.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<JobOffer> getAll() {
        List<JobOffer> list = new ArrayList<>();
        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM job_offer")) {

            while (rs.next()) {
                JobOffer o = new JobOffer(
                        rs.getInt("id_offer"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("status"),
                        rs.getTimestamp("created_at").toLocalDateTime(),
                        rs.getInt("created_by")
                );
                list.add(o);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public JobOffer getById(int id) {
        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM job_offer WHERE id_offer=" + id)) {

            if (rs.next()) {
                return new JobOffer(
                        rs.getInt("id_offer"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("status"),
                        rs.getTimestamp("created_at").toLocalDateTime(),
                        rs.getInt("created_by")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

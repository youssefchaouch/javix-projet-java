package Services;

import Entite.Candidate;
import Utlis.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CandidateService implements IService<Candidate> {

    private Connection con;

    public CandidateService() {
        con = DataSource.getInstance().getCon();
    }

    @Override
    public void add(Candidate candidate) {
        try (Statement st = con.createStatement()) {
            String sql = "INSERT INTO candidate (first_name, last_name, email, phone, resume) VALUES (" +
                    "'" + candidate.getFirstName() + "', " +
                    "'" + candidate.getLastName() + "', " +
                    "'" + candidate.getEmail() + "', " +
                    "'" + candidate.getPhone() + "', " +
                    "'" + candidate.getResume() + "')";
            st.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Candidate candidate) {
        try (Statement st = con.createStatement()) {
            String sql = "UPDATE candidate SET " +
                    "first_name='" + candidate.getFirstName() + "', " +
                    "last_name='" + candidate.getLastName() + "', " +
                    "email='" + candidate.getEmail() + "', " +
                    "phone='" + candidate.getPhone() + "', " +
                    "resume='" + candidate.getResume() + "' " +
                    "WHERE id_candidate=" + candidate.getIdCandidate();
            st.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try (Statement st = con.createStatement()) {
            String sql = "DELETE FROM candidate WHERE id_candidate=" + id;
            st.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Candidate> getAll() {
        List<Candidate> list = new ArrayList<>();
        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM candidate")) {

            while (rs.next()) {
                Candidate c = new Candidate(
                        rs.getInt("id_candidate"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("resume")
                );
                list.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Candidate getById(int id) {
        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM candidate WHERE id_candidate=" + id)) {

            if (rs.next()) {
                return new Candidate(
                        rs.getInt("id_candidate"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("resume")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

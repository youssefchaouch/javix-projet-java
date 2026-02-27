package Services;

import Entite.Candidate;
import Entite.CandidateApplication;
import Utlis.DataSource;

import java.sql.*;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

public class CandidateService implements IService<Candidate> {

    private Connection con;

    public CandidateService() {
        con = DataSource.getInstance().getCon();
    }

    @Override
    public void add(Candidate candidate) {
        // Use java.sql.Statement
        try (Statement st = con.createStatement()) {

            // Escape single quotes
            String sql = "INSERT INTO candidate (first_name, last_name, email, phone, resume) VALUES (" +
                    "'" + candidate.getFirstName().replace("'", "''") + "', " +
                    "'" + candidate.getLastName().replace("'", "''") + "', " +
                    "'" + candidate.getEmail().replace("'", "''") + "', " +
                    "'" + candidate.getPhone().replace("'", "''") + "', " +
                    "'" + candidate.getResume().replace("'", "''") + "')";

            // Execute and return generated keys
            st.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public int addAndRetrievePk(Candidate candidate) {
        String sql = "INSERT INTO candidate (first_name, last_name, email, phone, resume) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            // Set values safely
            ps.setString(1, candidate.getFirstName());
            ps.setString(2, candidate.getLastName());
            ps.setString(3, candidate.getEmail());
            ps.setString(4, candidate.getPhone());
            ps.setString(5, candidate.getResume());

            // Execute insert
            ps.executeUpdate();

            // Retrieve generated auto-increment ID
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    int id = rs.getInt(1);
                    candidate.setIdCandidate(id); // optional: update the object
                    return id;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1; // failed to insert
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

    public List<CandidateApplication> getCandidateApplications() {
        List<CandidateApplication> list = new ArrayList<>();
        String sql = """
        SELECT c.id_candidate, c.first_name, c.last_name, c.email, c.phone,
               a.id_application, a.id_offer, a.status
        FROM candidate c
        LEFT JOIN application a ON c.id_candidate = a.id_candidate
        """;

        try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                CandidateApplication ca = new CandidateApplication(
                        rs.getInt("id_candidate"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getInt("id_application"),
                        rs.getInt("id_offer"),
                        rs.getString("status")
                );
                list.add(ca);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}

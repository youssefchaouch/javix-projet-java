package Services;

import Entite.Candidate;
import java.util.List;

public class CandidateService implements IService<Candidate> {

    @Override
    public void add(Candidate candidate) {
        // INSERT candidate
    }

    @Override
    public void update(Candidate candidate) {
        // UPDATE candidate
    }

    @Override
    public void delete(int id) {
        // DELETE candidate
    }

    @Override
    public List<Candidate> getAll() {
        // SELECT * FROM candidate
        return null;
    }

    @Override
    public Candidate getById(int id) {
        // SELECT candidate WHERE id
        return null;
    }
}

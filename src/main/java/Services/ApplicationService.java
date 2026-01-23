package Services;

import Entite.Application;
import java.util.List;

public class ApplicationService implements IService<Application> {

    @Override
    public void add(Application application) {
        // INSERT application
    }

    @Override
    public void update(Application application) {
        // UPDATE application
    }

    @Override
    public void delete(int id) {
        // DELETE application
    }

    @Override
    public List<Application> getAll() {
        // SELECT * FROM application
        return null;
    }

    @Override
    public Application getById(int id) {
        // SELECT application WHERE id
        return null;
    }

    public List<Application> getApplicationsByOffer(int offerId) {
        return null;
    }

    public void shortlistApplication(int applicationId) {
        // UPDATE status = SHORTLISTED
    }

    public void rejectApplication(int applicationId) {
        // UPDATE status = REJECTED
    }

    public void acceptApplication(int applicationId) {
        // UPDATE status = ACCEPTED
    }
}

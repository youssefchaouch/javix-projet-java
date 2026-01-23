package Services;

import Entite.JobOffer;
import java.util.List;

public class JobOfferService implements IService<JobOffer> {

    @Override
    public void add(JobOffer offer) {
        // INSERT job offer
    }

    @Override
    public void update(JobOffer offer) {
        // UPDATE job offer
    }

    @Override
    public void delete(int id) {
        // DELETE job offer
    }

    @Override
    public List<JobOffer> getAll() {
        // SELECT * FROM job_offer
        return null;
    }

    @Override
    public JobOffer getById(int id) {
        // SELECT job offer WHERE id
        return null;
    }

    public void openOffer(int offerId) {
        // UPDATE status = OPEN
    }

    public void closeOffer(int offerId) {
        // UPDATE status = CLOSED
    }
}

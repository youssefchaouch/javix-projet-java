package Services;

import Entite.Payroll;
import java.util.List;

public class PayrollService implements IService<Payroll> {

    @Override
    public void add(Payroll payroll) {
        // INSERT payroll
    }

    @Override
    public void update(Payroll payroll) {
        // UPDATE payroll
    }

    @Override
    public void delete(int id) {
        // DELETE payroll
    }

    @Override
    public List<Payroll> getAll() {
        // SELECT * FROM payroll
        return null;
    }

    @Override
    public Payroll getById(int id) {
        // SELECT payroll WHERE id
        return null;
    }

    public Payroll generatePayroll(int employeeId, int month, int year) {
        // calculate final salary
        return null;
    }
}

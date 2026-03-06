package Test;

import Entite.Payroll;
import Services.PayrollService;

import java.time.LocalDateTime;
import java.util.List;

public class PayrollTest {
    public static void main(String[] args) {

        PayrollService service = new PayrollService();

        // 1️⃣ Add two payroll records
        Payroll p1 = new Payroll(0, 1, 1, 2026, 2, 3000.0, LocalDateTime.now());
        service.add(p1);
        System.out.println("Added payroll for employee 1.");

        Payroll p2 = new Payroll(0, 12, 1, 2026, 1, 4000.0, LocalDateTime.now());
        service.add(p2);
        System.out.println("Added payroll for employee 2.");

        // 2️⃣ Get all payrolls
        System.out.println("\nAll payrolls:");
        List<Payroll> allPayrolls = service.getAll();
        for (Payroll p : allPayrolls) {
            System.out.println(p.getIdPayroll() + " | Employee: " + p.getIdEmployee() +
                    " | Month/Year: " + p.getMonth() + "/" + p.getYear() +
                    " | Leave Days: " + p.getTotalLeaveDays() +
                    " | Final Salary: " + p.getFinalSalary() +
                    " | GeneratedAt: " + p.getGeneratedAt());
        }

        // 3️⃣ Get payroll by ID (first one)
        if (!allPayrolls.isEmpty()) {
            int id = allPayrolls.get(0).getIdPayroll();
            Payroll fetched = service.getById(id);
            System.out.println("\nFetched payroll ID " + id + " for employee " + fetched.getIdEmployee());
        }

        // 4️⃣ Update first payroll
        if (!allPayrolls.isEmpty()) {
            Payroll p = allPayrolls.get(0);
            p.setFinalSalary(3200.0);
            p.setTotalLeaveDays(3);
            service.update(p);
            System.out.println("Updated payroll ID " + p.getIdPayroll());
        }

        // 5️⃣ Delete second payroll
        if (allPayrolls.size() > 1) {
            int id = allPayrolls.get(1).getIdPayroll();
            service.delete(id);
            System.out.println("Deleted payroll ID " + id);
        }

        // 6️⃣ Show final payrolls
        System.out.println("\nPayrolls after update/delete:");
        allPayrolls = service.getAll();
        for (Payroll p : allPayrolls) {
            System.out.println(p.getIdPayroll() + " | Employee: " + p.getIdEmployee() +
                    " | Month/Year: " + p.getMonth() + "/" + p.getYear() +
                    " | Leave Days: " + p.getTotalLeaveDays() +
                    " | Final Salary: " + p.getFinalSalary() +
                    " | GeneratedAt: " + p.getGeneratedAt());
        }

        // 7️⃣ generate monthly payroll and compute totals
        service.generateMonthlyPayroll(2, 2026);
        System.out.println("Generated payroll for all employees for 2/2026");
        double totalMonth = service.totalSalaryByMonth(2, 2026);
        System.out.println("Total salary for 2/2026 = " + totalMonth);
        double totalManager = service.totalSalaryByRole("Manager", 2, 2026);
        System.out.println("Total salary for role Manager in 2/2026 = " + totalManager);

        System.out.println("\nSimple CRUD test completed!");
    }
}

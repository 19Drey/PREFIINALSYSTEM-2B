package timein.timeout.system;

import java.sql.*;

public class Payslip {
    config db = new config();

    
    public void generatePayslip(int employeeId, int salaryId, double totalAmount, String payDate) {
        String sql = "INSERT INTO Payslip(employee_id, salary_id, total_amount, pay_date) VALUES (?, ?, ?, ?)";
        db.addRecord(sql, employeeId, salaryId, totalAmount, payDate);
        System.out.println("Payslip generated successfully!");
    }

   
    public void viewPayslips() {
        String sql = "SELECT p.id, e.name AS employee_name, e.department, e.position, s.amount AS salary_amount, " +
                     "s.sss_deduction, s.pagibig_deduction, s.philhealth_deduction, s.total_deductions, s.net_salary, " +
                     "p.total_amount AS payslip_amount, p.pay_date " +
                     "FROM Payslip p " +
                     "INNER JOIN Employees e ON p.employee_id = e.id " +
                     "INNER JOIN Salaries s ON p.salary_id = s.id";
        
        db.viewRecords(sql);
    }

    
    public void updatePayslip(int id, double totalAmount, String payDate) {
        String sql = "UPDATE Payslip SET total_amount = ?, pay_date = ? WHERE id = ?";
        db.updateRecord(sql, totalAmount, payDate, id);
        System.out.println("Payslip updated successfully!");
    }

   
    public void deletePayslip(int id) {
        String sql = "DELETE FROM Payslip WHERE id = ?";
        db.deleteRecord(sql, id);
        System.out.println("Payslip deleted successfully!");
    }
}

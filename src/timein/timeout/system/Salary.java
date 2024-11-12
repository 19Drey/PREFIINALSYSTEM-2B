package timein.timeout.system;

import java.sql.*;

public class Salary {
    config db = new config();

   
    public void addSalary(int employeeId, double amount) {
        
        double sssDeduction = amount * 0.045;  
        double pagibigDeduction = amount * 0.02;  
        double philhealthDeduction = amount * 0.05;
        double totalDeductions = sssDeduction + pagibigDeduction + philhealthDeduction;
        double netSalary = amount - totalDeductions;

        String sql = "INSERT INTO Salaries (employee_id, amount, sss_deduction, pagibig_deduction, philhealth_deduction, total_deductions, net_salary) VALUES (?, ?, ?, ?, ?, ?, ?)";
        db.addRecord(sql, employeeId, amount, sssDeduction, pagibigDeduction, philhealthDeduction, totalDeductions, netSalary);
    }

    
    public void viewSalaries() {
        String sql = "SELECT * FROM Salaries";
        db.viewRecords(sql);
    }

    
    public void updateSalary(int id, double amount) {
       
        double sssDeduction = amount * 0.045;  
        double pagibigDeduction = amount * 0.02;  
        double philhealthDeduction = amount * 0.05;  
        double totalDeductions = sssDeduction + pagibigDeduction + philhealthDeduction;
        double netSalary = amount - totalDeductions;

        String sql = "UPDATE Salaries SET amount = ?, sss_deduction = ?, pagibig_deduction = ?, philhealth_deduction = ?, total_deductions = ?, net_salary = ? WHERE id = ?";
        db.updateRecord(sql, amount, sssDeduction, pagibigDeduction, philhealthDeduction, totalDeductions, netSalary, id);
    }

   
    public void deleteSalary(int id) {
        String sql = "DELETE FROM Salaries WHERE id = ?";
        db.deleteRecord(sql, id);
    }
}

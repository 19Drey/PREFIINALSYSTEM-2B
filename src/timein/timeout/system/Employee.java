package timein.timeout.system;

import java.sql.*;

public class Employee {
    static config db = new config();

    public static void addEmployee(String name, String department, String position) {
        String sql = "INSERT INTO Employees(name, department, position) VALUES (?, ?, ?)";
        db.addRecord(sql, name, department, position);
    }

    public static void viewEmployees() {
        String sql = "SELECT * FROM Employees";
        db.viewRecords(sql);
    }

    public static void updateEmployee(int id, String name, String department, String position) {
        String sql = "UPDATE Employees SET name = ?, department = ?, position = ? WHERE id = ?";
        db.updateRecord(sql, name, department, position, id);
    }

    public static void deleteEmployee(int id) {
        String sql = "DELETE FROM Employees WHERE id = ?";
        db.deleteRecord(sql, id);
    }
}
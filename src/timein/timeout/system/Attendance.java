
package timein.timeout.system;



import java.sql.*;

public class Attendance {
    config db = new config();

    public void addAttendance(int employeeId, String date, String timeIn, String timeOut) {
        String sql = "INSERT INTO Attendance(employee_id, date, time_in, time_out) VALUES (?, ?, ?, ?)";
        db.addRecord(sql, employeeId, date, timeIn, timeOut);
    }

    public void viewAttendance() {
        String sql = "SELECT * FROM Attendance";
        db.viewRecords(sql);
    }

    public void updateAttendance(int id, String timeOut) {
        String sql = "UPDATE Attendance SET time_out = ? WHERE id = ?";
        db.updateRecord(sql, timeOut, id);
    }

    public void deleteAttendance(int id) {
        String sql = "DELETE FROM Attendance WHERE id = ?";
        db.deleteRecord(sql, id);
    }
}

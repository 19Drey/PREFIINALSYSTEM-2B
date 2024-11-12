
package timein.timeout.system;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class config {
    private static final String DB_URL = "jdbc:sqlite:Estreraaaa.db";

    public static Connection connectDB() {
        Connection con = null;
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection(DB_URL);
            System.out.println("Connection Successful");
        } catch (Exception e) {
            System.out.println("Connection Failed: " + e.getMessage());
        }
        return con;
    }

    public void addRecord(String sql, Object... values) {
        try (Connection conn = connectDB();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (int i = 0; i < values.length; i++) {
                if (values[i] instanceof Integer) {
                    pstmt.setInt(i + 1, (Integer) values[i]);
                } else if (values[i] instanceof Double) {
                    pstmt.setDouble(i + 1, (Double) values[i]);
                } else {
                    pstmt.setString(i + 1, values[i].toString());
                }
            }
            pstmt.executeUpdate();
            System.out.println("Record added successfully!");
        } catch (SQLException e) {
            System.out.println("Error adding record: " + e.getMessage());
        }
    }

  public void viewRecords(String sql) {
    try (Connection conn = connectDB();
         PreparedStatement pstmt = conn.prepareStatement(sql);
         ResultSet rs = pstmt.executeQuery()) {
        
        int columnCount = rs.getMetaData().getColumnCount();

        // Print header
        for (int i = 1; i <= columnCount; i++) {
            System.out.printf("%-20s |", rs.getMetaData().getColumnName(i));
        }
        System.out.println(); // New line after headers

        // Print divider
        for (int i = 1; i <= columnCount; i++) {
            System.out.print("--------------------");
            
        }
        System.out.println(""); // New line after divider

        // Print rows
        while (rs.next()) {
            for (int i = 1; i <= columnCount; i++) {
                System.out.printf("%-20s |", rs.getString(i));
            }
            System.out.println(); // New line after each row
            System.out.println();
        }
    } catch (SQLException e) {
        System.out.println("Error retrieving records: " + e.getMessage());
    }
}

    public void updateRecord(String sql, Object... values) {
        try (Connection conn = connectDB();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (int i = 0; i < values.length; i++) {
                if (values[i] instanceof Integer) {
                    pstmt.setInt(i + 1, (Integer) values[i]);
                } else if (values[i] instanceof Double) {
                    pstmt.setDouble(i + 1, (Double) values[i]);
                } else {
                    pstmt.setString(i + 1, values[i].toString());
                }
            }
            pstmt.executeUpdate();
            System.out.println("Record updated successfully!");
        } catch (SQLException e) {
            System.out.println("Error updating record: " + e.getMessage());
        }
    }

    public void deleteRecord(String sql, Object... values) {
        try (Connection conn = connectDB();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (int i = 0; i < values.length; i++) {
                pstmt.setInt(i + 1, (Integer) values[i]);
            }
            pstmt.executeUpdate();
            System.out.println("Record deleted successfully!");
        } catch (SQLException e) {
            System.out.println("Error deleting record: " + e.getMessage());
        }
    }
}

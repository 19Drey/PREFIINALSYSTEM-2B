package timein.timeout.system;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;
import timein.timeout.system.Employee;
import timein.timeout.system.Payslip;
import timein.timeout.system.Salary;
import static timein.timeout.system.config.connectDB;

public class MainMenu {
    public static final Scanner scanner = new Scanner(System.in);
    public static final Employee employee = new Employee();
    public static final Salary salary = new Salary();
    public static final Attendance attendance = new Attendance();
    public static final Payslip payslip = new Payslip();

 public static void main(String[] args) {
        boolean running = true;
        
        while (running) {
            System.out.println("                                                   ****************************************");
            System.out.println("                                                                  Main Menu");
            System.out.println("                                                   ****************************************");
            System.out.println("                                                           Please choose an option:");
            System.out.println("                                                   1.  Employees");
            System.out.println("                                                   2.  Salaries");
            System.out.println("                                                   3.  Attendance");
            System.out.println("                                                   4.  Payslips");
            System.out.println("                                                   5.  Reports / Receipt");
            System.out.println("                                                   6.  Attendance Report");
            System.out.println("                                                   7.  Exit");
            System.out.println("                                                   ****************************************");

            int choice = -1;
            boolean validChoice = false;

            while (!validChoice) {
                System.out.print("                                                     Enter your choice (1-7): ");
                try {
                    choice = scanner.nextInt();
                    scanner.nextLine();

                    if (choice >= 1 && choice <= 7) {
                        validChoice = true;
                    } else {
                        System.out.println("Invalid choice. Please enter a number between 1 and 7.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid integer.");
                    scanner.nextLine();
                }
            }

            switch (choice) {
                case 1:
                    manageEmployees();
                    break;
                case 2:
                    manageSalaries();
                    break;
                case 3:
                    manageAttendance();
                    break;
                case 4:
                    managePayslips();
                    break;
                case 5:
                    System.out.print("Enter Employee ID to generate report: ");
                    int employeeIdReport = scanner.nextInt();
                    
                    generateReport(employeeIdReport); 
                   
                    break;
                case 6:
                    System.out.print("Enter Employee ID to generate attendance report: ");
                    int employeeIdAttendance = scanner.nextInt();
                    generateAttendanceReport(employeeIdAttendance); 
                    break;
                case 7:
                    running = false;
                    System.out.println("Exiting the system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }


   public static void manageEmployees() {
    System.out.println("\n==============================");
    System.out.println("        Manage Employees      ");
    System.out.println("==============================");
    System.out.println("| 1 | Add Employee           |");
    System.out.println("| 2 | View Employees         |");
    System.out.println("| 3 | Update Employee        |");
    System.out.println("| 4 | Delete Employee        |");
    System.out.println("| 5 | Back to main menu      |");
    System.out.println("==============================");

    int choice = -1;
    boolean validChoice = false;

    while (!validChoice) {
        System.out.print("Please select an option (1-5): ");
        try {
            choice = scanner.nextInt();
            scanner.nextLine(); 
            if (choice >= 1 && choice <= 5) {
                validChoice = true;
            } else {
                System.out.println("Invalid choice. Please select a number between 1 and 5.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid integer.");
            scanner.nextLine(); 
        }
    }

    switch (choice) {
        case 1:
            System.out.print("Enter name: ");
            String name = scanner.nextLine();
            System.out.print("Enter department: ");
            String department = scanner.nextLine();
            System.out.print("Enter position: ");
            String position = scanner.nextLine();
            employee.addEmployee(name, department, position);
            break;
        case 2:
            employee.viewEmployees();
            break;
        case 3:
            employee.viewEmployees();
            System.out.print("Enter Employee ID to update: ");
            int id = scanner.nextInt();
            scanner.nextLine(); 
            System.out.print("Enter new name: ");
            String newName = scanner.nextLine();
            System.out.print("Enter new department: ");
            String newDepartment = scanner.nextLine();
            System.out.print("Enter new position: ");
            String newPosition = scanner.nextLine();
            employee.updateEmployee(id, newName, newDepartment, newPosition);
            break;
        case 4:
            System.out.print("Enter Employee ID to delete: ");
            int deleteId = scanner.nextInt();
            employee.deleteEmployee(deleteId);
            break;
        case 5:
            return;
        default:
            System.out.println(" Invalid choice. Returning to main menu.");
    }
}


    public static void manageSalaries() {
    System.out.println("\n****************************************");
    System.out.println("        Manage Salaries Menu");
    System.out.println("****************************************");
    System.out.println("Please choose an option:");
    System.out.println("1. Add Salary");
    System.out.println("2. View Salaries");
    System.out.println("3. Update Salary");
    System.out.println("4. Delete Salary");
    System.out.println("5. Return to Main Menu");
    System.out.println("****************************************");

    int choice = -1;
    boolean validChoice = false;

    while (!validChoice) {
        System.out.print("Enter your choice (1-5): ");
        try {
            choice = scanner.nextInt();
            scanner.nextLine(); 
            if (choice >= 1 && choice <= 5) {
                validChoice = true;
            } else {
                System.out.println("Invalid choice. Please select a number between 1 and 5.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid integer.");
            scanner.nextLine(); 
        }
    }

    switch (choice) {
        case 1:
             employee.viewEmployees();
            System.out.print("Enter Employee ID: ");
            int employeeId = scanner.nextInt();
            System.out.print("Enter salary amount: ");
            double salaryAmount = scanner.nextDouble();
            salary.addSalary(employeeId, salaryAmount);
            break;
        case 2:
            salary.viewSalaries();
            break;
        case 3:
            salary.viewSalaries();
            System.out.print("Enter Salary ID to update: ");
            int salaryId = scanner.nextInt();
            System.out.print("Enter new salary amount: ");
            double newAmount = scanner.nextDouble();
            salary.updateSalary(salaryId, newAmount);
            break;
        case 4:
            System.out.print("Enter Salary ID to delete: ");
            int deleteSalaryId = scanner.nextInt();
            salary.deleteSalary(deleteSalaryId);
            break;
        case 5:
            return;
        default:
            System.out.println("Invalid choice. Returning to main menu.");
    }
}


  public static void manageAttendance() {
    System.out.println("\n****************************************");
    System.out.println("        Manage Attendance Menu");
    System.out.println("****************************************");
    System.out.println("Please choose an option:");
    System.out.println("1. Add Attendance");
    System.out.println("2. View Attendance");
    System.out.println("3. Update Attendance");
    System.out.println("4. Delete Attendance");
    System.out.println("5. Return to Main Menu");
    System.out.println("****************************************");

    int choice = -1;
    boolean validChoice = false;

    while (!validChoice) {
        System.out.print("Enter your choice (1-5): ");
        try {
            choice = scanner.nextInt();
            scanner.nextLine();
            if (choice >= 1 && choice <= 5) {
                validChoice = true;
            } else {
                System.out.println("Invalid choice. Please select a number between 1 and 5.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid integer.");
            scanner.nextLine(); 
        }
    }

    switch (choice) {
        case 1:
             employee.viewEmployees();
            System.out.print("Enter Employee ID: ");
            int employeeId = scanner.nextInt();
            scanner.nextLine(); 
            System.out.print("Enter date (YYYY-MM-DD): ");
            String date = scanner.nextLine();
            System.out.print("Enter time in (HH:MM): ");
            String timeIn = scanner.nextLine();
            System.out.print("Enter time out (HH:MM): ");
            String timeOut = scanner.nextLine();
            attendance.addAttendance(employeeId, date, timeIn, timeOut);
            break;
        case 2:
            attendance.viewAttendance();
            break;
        case 3:
            attendance.viewAttendance();
            System.out.print("Enter Attendance ID to update: ");
            int attendanceId = scanner.nextInt();
            scanner.nextLine(); 
            System.out.print("Enter new time In (HH:MM): ");
            String newTimeIn = scanner.nextLine();
            attendance.updateAttendance(attendanceId, newTimeIn);
            System.out.print("Enter new time out (HH:MM): ");
            String newTimeOut = scanner.nextLine();
            attendance.updateAttendance(attendanceId, newTimeOut);
            break;
        case 4:
            System.out.print("Enter Attendance ID to delete: ");
            int deleteAttendanceId = scanner.nextInt();
            attendance.deleteAttendance(deleteAttendanceId);
            break;
        case 5:
            return;
        default:
            System.out.println("Invalid choice. Returning to main menu.");
    }
}


  public static void managePayslips() {
    System.out.println("\n****************************************");
    System.out.println("        Manage Payslips Menu");
    System.out.println("****************************************");
    System.out.println("Please choose an option:");
    System.out.println("1. Generate Payslip");
    System.out.println("2. View Payslips");
    System.out.println("3. Update Payslip");
    System.out.println("4. Delete Payslip");
    System.out.println("5. Return to Main Menu");
    System.out.println("****************************************");

    int choice = -1;
    boolean validChoice = false;

    while (!validChoice) {
        System.out.print("Enter your choice (1-5): ");
        try {
            choice = scanner.nextInt();
            scanner.nextLine(); 
            if (choice >= 1 && choice <= 5) {
                validChoice = true;
            } else {
                System.out.println("Invalid choice. Please select a number between 1 and 5.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid integer.");
            scanner.nextLine(); 
        }
    }

    switch (choice) {
        case 1:
             employee.viewEmployees();
            System.out.print("Enter Employee ID: ");
            int employeeId = scanner.nextInt();
            System.out.print("Enter Salary ID: ");
            int salaryId = scanner.nextInt();
            System.out.print("Enter total amount: ");
            double totalAmount = scanner.nextDouble();
            scanner.nextLine(); 
            System.out.print("Enter pay date (YYYY-MM-DD): ");
            String payDate = scanner.nextLine();
            payslip.generatePayslip(employeeId, salaryId, totalAmount, payDate);
            break;
        case 2:
            payslip.viewPayslips();
            break;
        case 3:
             payslip.viewPayslips();
            System.out.print("Enter Payslip ID to update: ");
            int payslipId = scanner.nextInt();
            System.out.print("Enter new total amount: ");
            double newTotalAmount = scanner.nextDouble();
            scanner.nextLine(); 
            System.out.print("Enter new pay date (YYYY-MM-DD): ");
            String newPayDate = scanner.nextLine();
            payslip.updatePayslip(payslipId, newTotalAmount, newPayDate);
            break;
        case 4:
            System.out.print("Enter Payslip ID to delete: ");
            int deletePayslipId = scanner.nextInt();
            payslip.deletePayslip(deletePayslipId);
            break;
        case 5:
            return;
        default:
            System.out.println("Invalid choice. Returning to main menu.");
    }
}

  private static void generateAttendanceReport(int employeeIdAttendance) {
    int employeeId = -1;
    boolean validInput = false;
 employee.viewEmployees();
    while (!validInput) {
        System.out.print("Enter Employee ID to generate attendance report: ");
        try {
            employeeId = scanner.nextInt();
            scanner.nextLine(); 
            validInput = true; 
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid integer for Employee ID.");
            scanner.nextLine(); 
        }
    }

    String sql = "SELECT e.id AS Employee_ID, e.name AS Employee_Name, e.department AS Department, e.position AS Position, "
               + "a.date AS Attendance_Date, a.time_in AS Time_In, a.time_out AS Time_Out "
               + "FROM Employees e "
               + "INNER JOIN Attendance a ON e.id = a.employee_id "
               + "WHERE e.id = ?";

    try (Connection conn = connectDB();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        pstmt.setInt(1, employeeId);
        ResultSet rs = pstmt.executeQuery();

        System.out.println("\n******************************************");
        System.out.println("              Employee Attendance Report");
        System.out.println("******************************************");

        if (rs.next()) {
            
            System.out.println("Employee ID: " + rs.getInt("Employee_ID"));
            System.out.println("Employee Name: " + rs.getString("Employee_Name"));
            System.out.println("Department: " + rs.getString("Department"));
            System.out.println("Position: " + rs.getString("Position"));
            System.out.println("Attendance Date: " + rs.getString("Attendance_Date"));
            System.out.println("Time In: " + rs.getString("Time_In"));
            System.out.println("Time Out: " + rs.getString("Time_Out"));
        } else {
            System.out.println("No attendance record found for Employee ID: " + employeeId);
        }

        System.out.println("\n******************************************");
        System.out.println("               End of Report");
        System.out.println("******************************************");

    } catch (SQLException e) {
        System.out.println("Error generating attendance report: " + e.getMessage());
    }
}



 private static void generateReport(int employeeIdReport) {
    int employeeId = -1;
    boolean validInput = false;

  
    employee.viewEmployees();  

  
    while (!validInput) {
        System.out.print("Enter Employee ID to generate report: ");
        try {
            employeeId = scanner.nextInt(); 
            scanner.nextLine();  
            
            
            if (employeeId <= 0) {
                System.out.println("Please enter a valid positive Employee ID.");
            } else {
                validInput = true;  
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid integer for Employee ID.");
            scanner.nextLine();  
        }
    }

    
    String sql = "SELECT e.id AS Employee_ID, e.name AS Employee_Name, e.department AS Department, e.position AS Position, "
               + "s.amount AS Basic_Salary, "
               + "s.sss_deduction AS SSS_Deductions, s.pagibig_deduction AS PagIbig_Deductions, s.philhealth_deduction AS PhilHealth_Deductions, "
               + "s.total_deductions AS Total_Deductions, s.net_salary AS Net_Salary, "
               + "p.total_amount AS Payslip_Amount, p.pay_date AS Payslip_Date "
               + "FROM Employees e "
               + "INNER JOIN Salaries s ON e.id = s.employee_id "
               + "INNER JOIN Payslip p ON e.id = p.employee_id "
               + "WHERE e.id = ?";

    try (Connection conn = connectDB();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setInt(1, employeeId); 
        ResultSet rs = pstmt.executeQuery();


        System.out.println("\n******************************************");
        System.out.println("              Employee Payslip Report");
        System.out.println("******************************************");

        if (rs.next()) {
           
            System.out.println("Employee ID: " + rs.getInt("Employee_ID"));
            System.out.println("Employee Name: " + rs.getString("Employee_Name"));
            System.out.println("Department: " + rs.getString("Department"));
            System.out.println("Position: " + rs.getString("Position"));
            System.out.println("Basic Salary: " + rs.getDouble("Basic_Salary"));
            System.out.println("SSS Deductions: " + rs.getDouble("SSS_Deductions"));
            System.out.println("PagIbig Deductions: " + rs.getDouble("PagIbig_Deductions"));
            System.out.println("PhilHealth Deductions: " + rs.getDouble("PhilHealth_Deductions"));
            System.out.println("Total Deductions: " + rs.getDouble("Total_Deductions"));
            System.out.println("Net Salary: " + rs.getDouble("Net_Salary"));
            System.out.println("Payslip Amount: " + rs.getDouble("Payslip_Amount"));
            System.out.println("Payslip Date: " + rs.getString("Payslip_Date"));
        } else {
            System.out.println("No report found for Employee ID: " + employeeId);
        }

        System.out.println("\n******************************************");
        System.out.println("               End of Report");
        System.out.println("******************************************");

    } catch (SQLException e) {
        System.out.println("Error generating report: " + e.getMessage());
    }
}


}









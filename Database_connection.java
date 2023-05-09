import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database_connection {

    public static void main(String[] args) {
        try {
            // Initialize database connection parameters
            String username = "root";
            String password = "Crack it@1011";
            String url = "jdbc:mysql://localhost:3306/MedicalDatabase";
            // Open a connection
            System.out.println("Connecting to database...");
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println(conn);
            Statement stmt = conn.createStatement();

            // Create table for UserDetails
            String sql = "CREATE TABLE UserDetails " +
                    "(id INT NOT NULL AUTO_INCREMENT, " +
                    " firstname VARCHAR(255), " +
                    " lastname VARCHAR(255), " +
                    " email VARCHAR(255), " +
                    " address VARCHAR(255), " +
                    " PRIMARY KEY ( id ))";
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            System.out.println("UserDetails table created successfully");

            // Create table for Patient
            sql = "CREATE TABLE Patient " +
                    "(id INT NOT NULL AUTO_INCREMENT, " +
                    " firstname VARCHAR(255), " +
                    " lastname VARCHAR(255), " +
                    " age INT, " +
                    " gender VARCHAR(255), " +
                    " address VARCHAR(255), " +
                    "phoneNumber INT, " +
                    " PRIMARY KEY ( id ))";
            stmt.executeUpdate(sql);
            System.out.println("Patient table created successfully");

            // Create table for Doctor
            sql = "CREATE TABLE Doctor " +
                    "(id INT NOT NULL AUTO_INCREMENT, " +
                    " firstname VARCHAR(255), " +
                    " lastname VARCHAR(255), " +
                    " age INT, " +
                    " gender VARCHAR(255), " +
                    " specialization VARCHAR(255), " +
                    " phoneNumber INT, " +
                    " PRIMARY KEY ( id ))";
            stmt.executeUpdate(sql);
            System.out.println("Doctor table created successfully");

            // Create table for Staff
            sql = "CREATE TABLE Staff " +
                    "(id INT NOT NULL AUTO_INCREMENT, " +
                    " firstname VARCHAR(255), " +
                    " lastname VARCHAR(255), " +
                    " email VARCHAR(255), " +
                    " address VARCHAR(255), " +
                    " department VARCHAR(255), " +
                    " PRIMARY KEY ( id ))";
            stmt.executeUpdate(sql);
            System.out.println("Staff table created successfully");

            // Create table for MedicalReport
            sql = "CREATE TABLE MedicalReport " +
                    "(id INT NOT NULL AUTO_INCREMENT, " +
                    " patient_id INT, " +
                    " doctor_id INT, " +
                    " report_date DATE, " +
                    " report_text VARCHAR(255), " +
                    " PRIMARY KEY ( id ), " +
                    " FOREIGN KEY ( patient_id ) REFERENCES Patient(id), " +
                    " FOREIGN KEY ( doctor_id ) REFERENCES Doctor(id))";
            stmt.executeUpdate(sql);
            System.out.println("MedicalReport table created successfully");

            // Create table for Test
            sql = "CREATE TABLE Test " +
                    "(id INT NOT NULL AUTO_INCREMENT, " +
                    " medical_report_id INT, " +
                    " test_name VARCHAR(255), " +
                    " result VARCHAR(255), " +
                    " PRIMARY KEY ( id ), " +
                    " FOREIGN KEY ( medical_report_id ) REFERENCES MedicalReport(id))";
            stmt.executeUpdate(sql);
            System.out.println("Test table created successfully");

            // Create table for Prescription
            sql = "CREATE TABLE Prescription " +
                    "(id INT NOT NULL AUTO_INCREMENT, " +
                    " medical_report_id INT, " +
                    " medicine_name VARCHAR(255), " +
                    " dosage VARCHAR(255), " +
                    " PRIMARY KEY ( id ), " +
                    " FOREIGN KEY ( medical_report_id ) REFERENCES MedicalReport(id))";
            stmt.executeUpdate(sql);
            System.out.println("Prescription table created successfully");

            // Create table for Receipt
            sql = "CREATE TABLE Receipt " +
                    "(id INT NOT NULL AUTO_INCREMENT, " +
                    " patient_id INT, " +
                    " staff_id INT, " +
                    " total_amount FLOAT, " +
                    " date_of_issue DATE, " +
                    " PRIMARY KEY ( id ), " +
                    " FOREIGN KEY ( patient_id ) REFERENCES Patient( id ), " +
                    " FOREIGN KEY ( staff_id ) REFERENCES Staff( id ))";
            stmt.executeUpdate(sql);
            System.out.println("Receipt table created successfully");

            // Create table for the Accounts class
            sql = "CREATE TABLE IF NOT EXISTS accounts (\n" +
                    "    account_id INTEGER PRIMARY KEY,\n" +
                    "    patient_id INTEGER,\n" +
                    "    balance DOUBLE,\n" +
                    "    FOREIGN KEY (patient_id) REFERENCES patient(id)\n" + ");";
            stmt.executeUpdate(sql);
            System.out.println("Accounts table created successfully");


            // Create table for the Bill class
            sql = "CREATE TABLE IF NOT EXISTS bill (\n"
                    + "    bill_id INTEGER PRIMARY KEY,\n"
                    + "    patient_id INTEGER,\n"
                    + "    bill_date DATE,\n"
                    + "    bill_amount DOUBLE,\n"
                    + "    FOREIGN KEY (patient_id) REFERENCES patient(id)\n"
                    + ");";
            stmt.executeUpdate(sql);
            System.out.println("Bill table created successfully");
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error occurred while connecting to database: " + e.getMessage());
        }
    }
}

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Receipt {
    private int id;
    private int patientId;
    private int staffId;
    private float totalAmount;
    private String dateOfIssue;

    // Collect receipt information
    public void collectReceiptInfo () {
        // Collect receipt information from user input
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter patient ID: ");
        this.patientId = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        System.out.print("Enter staff ID: ");
        this.staffId = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        System.out.print("Enter total amount: ");
        this.totalAmount = scanner.nextFloat();
        scanner.nextLine(); // Consume newline character
        System.out.print("Enter date of issue: ");
        this.dateOfIssue = scanner.nextLine();
        scanner.close();
    }

    public void saveToDatabase(Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO Receipts (patient_id, staff_id, total_amount, date_of_issue) VALUES (?, ?, ?, ?)");
        statement.setInt(1, patientId);
        statement.setInt(2, staffId);
        statement.setFloat(3, totalAmount);
        statement.setString(4, dateOfIssue);
        statement.executeUpdate();
        statement.close();
    }

    // getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(String dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }
}

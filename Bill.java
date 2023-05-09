import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Bill {
    private int billId;
    private int patientId;
    private String billDate;
    private double billAmount;

    public Bill() {
        this.patientId = patientId;
    }

    public void collectBillInfo() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter bill ID:");
        this.billId = scanner.nextInt();

        System.out.println("Enter bill date (yyyy-mm-dd):");
        this.billDate = scanner.next();

        System.out.println("Enter bill amount:");
        this.billAmount = scanner.nextDouble();
    }

    public void saveToDatabase(Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO Bill (bill_id, patient_id, bill_date, bill_amount) VALUES (?, ?, ?, ?)");
        statement.setInt(1, this.billId);
        statement.setInt(2, this.patientId);
        statement.setString(3, this.billDate);
        statement.setDouble(4, this.billAmount);

        statement.executeUpdate();
        statement.close();
    }
}

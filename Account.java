import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Account {
    private int accountId;
    private int patientId;
    private double balance;

    public Account() {
        this.patientId = patientId;
    }

    public void collectAccountInfo() {
        Scanner scanner = new Scanner(System.in);

        // Collect account info from user input
        System.out.print("Enter account ID: ");
        this.accountId = scanner.nextInt();

        System.out.print("Enter patient ID: ");
        this.patientId = scanner.nextInt();

        System.out.print("Enter balance: ");
        this.balance = scanner.nextDouble();
    }

    public void saveToDatabase(Connection connection) throws SQLException {
        PreparedStatement statement;

        // Check if account already exists
        statement = connection.prepareStatement("SELECT * FROM Account WHERE patient_id = ?");
        statement.setInt(1, this.patientId);
        ResultSet result = statement.executeQuery();

        if (result.next()) {
            // Update existing account
            statement = connection.prepareStatement("UPDATE Account SET balance = ? WHERE account_id = ?");
            statement.setDouble(1, this.balance);
            statement.setInt(2, result.getInt("account_id"));
        } else {
            // Create new account
            statement = connection.prepareStatement("INSERT INTO Account (account_id, patient_id, balance) VALUES (?, ?, ?)");
            statement.setInt(1, this.accountId);
            statement.setInt(2, this.patientId);
            statement.setDouble(3, this.balance);
        }

        statement.executeUpdate();
        statement.close();
    }
}

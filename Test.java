import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Test {
    private int medicalReportId;
    private String testName;
    private String result;

    public void collectTestInfo(int medicalReportId, String testName, String result) {
        this.medicalReportId = medicalReportId;
        this.testName = testName;
        this.result = result;
    }


    public int getMedicalReportId() {
        return medicalReportId;
    }

    public void setMedicalReportId(int medicalReportId) {
        this.medicalReportId = medicalReportId;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void saveToDatabase(Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO Tests (medicalReportId, testName, result) VALUES (?, ?, ?)");
        statement.setInt(1, medicalReportId);
        statement.setString(2, testName);
        statement.setString(3, result);
        statement.executeUpdate();
        statement.close();
    }

    public void displayTestResults() {
        System.out.println("Test Name: " + testName);
        System.out.println("Result: " + result);
        // Example: display the test name and result in a popup window
        JOptionPane.showMessageDialog(null, testName + ": " + result);
    }

    public void collectTestInfo() {
    }
}

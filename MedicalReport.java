import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

public class MedicalReport extends Test implements Laboratory{
    private int patientId;
    private Date reportDate;
    private String reportText;
    private int doctorId;

    public MedicalReport(int patientId, Date reportDate, String reportText, int doctorId) {
        this.patientId = patientId;
        this.reportDate = reportDate;
        this.reportText = reportText;
        this.doctorId = doctorId;
    }

    public MedicalReport() {

    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public String getReportText() {
        return reportText;
    }

    public void setReportText(String reportText) {
        this.reportText = reportText;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public void collectMedicalReportInfo() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter patient ID:");
        this.patientId = scanner.nextInt();

        System.out.println("Enter report date (yyyy-mm-dd):");
        String dateStr = scanner.next();
        try {
            this.reportDate = new Date(java.sql.Date.valueOf(dateStr).getTime());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid date format. Report date will be set to current date.");
            this.reportDate = new Date();
        }

        System.out.println("Enter report text:");
        scanner.nextLine(); // consume remaining newline character
        this.reportText = scanner.nextLine();

        System.out.println("Enter doctor ID:");
        this.doctorId = scanner.nextInt();
    }

    @Override
    public void collectData() {
        collectMedicalReportInfo();
    }

    public void saveToDatabase(Connection connection) throws SQLException {
        // Use instance variables to insert medical report info into MedicalReports table
        PreparedStatement statement = connection.prepareStatement("INSERT INTO MedicalReports (patientId, reportDate, reportText, doctorId) VALUES (?, ?, ?, ?)");
        statement.setInt(1, patientId);
        statement.setDate(2, new java.sql.Date(reportDate.getTime()));
        statement.setString(3, reportText);
        statement.setInt(4, doctorId);
        statement.executeUpdate();
        statement.close();
    }

    public void generateReport(Connection connection) {
        // Get patient name from the database using the patientId
        String patientName = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT name FROM Patients WHERE id = ?");
            statement.setInt(1, patientId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                patientName = resultSet.getString("name");
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Generate report text
        String report = "Medical Report\n\n" +
                "Patient Name: " + patientName + "\n" +
                "Report Date: " + reportDate.toString() + "\n\n" +
                reportText;

        // Print report to console
        System.out.println(report);
    }
}

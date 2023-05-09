import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Prescription {
    private int medicalReportId;
    private String medicineName;
    private String dosage;

    public Prescription() {
    }

    public Prescription(int medicalReportId, String medicineName, String dosage) {
        this.medicalReportId = medicalReportId;
        this.medicineName = medicineName;
        this.dosage = dosage;
    }

    public int getMedicalReportId() {
        return medicalReportId;
    }

    public void setMedicalReportId(int medicalReportId) {
        this.medicalReportId = medicalReportId;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public void collectPrescriptionInfo(int medicalReportId, String medicineName, String dosage) {
        this.medicalReportId = medicalReportId;
        this.medicineName = medicineName;
        this.dosage = dosage;
    }

    public void saveToDatabase(Connection connection) throws SQLException {
        String sql = "INSERT INTO Prescriptions (medicalReportId, medicineName, dosage) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, medicalReportId);
            statement.setString(2, medicineName);
            statement.setString(3, dosage);

            statement.executeUpdate();
        }
    }

    public void collectPrescriptionInfo() {
    }
}

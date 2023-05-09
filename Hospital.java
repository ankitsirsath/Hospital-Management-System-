import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Hospital {
    private Connection connection;

    public Hospital(String myHospital) throws SQLException {
        // Establish connection to the database
        String url = "jdbc:mysql://localhost:3306/MedicalDatabase";
        String user = "root";
        String password = "Crack it@1011";
        this.connection = DriverManager.getConnection(url, user, password);
    }

    public void run() {
        try {
            // Create instances of all classes
            Patient patient = new Patient();
            Doctor doctor = new Doctor();
            WebUserDetails userDetails = new WebUserDetails("John", "Doe", "johndoe@example.com", "123 Main St");
            Account account = new Account();
            MedicalReport medicalReport = new MedicalReport();
            Prescription prescription = new Prescription();
            Receipt receipt = new Receipt();
            Bill bill = new Bill();
            Staff staff = new Staff();
            Test test = new Test();

            // Collect input and save to database for each class
            patient.collectPatientInfo();
            patient.saveToDatabase(connection);

            doctor.collectDoctorInfo();
            doctor.saveToDatabase(connection);

            userDetails.collectUserInfo();
            userDetails.saveToDatabase(connection);

            account.collectAccountInfo();
            account.saveToDatabase(connection);

            medicalReport.collectMedicalReportInfo();
            medicalReport.saveToDatabase(connection);
            medicalReport.generateReport(connection);

            prescription.collectPrescriptionInfo();
            prescription.saveToDatabase(connection);

            bill.collectBillInfo();
            bill.saveToDatabase(connection);

            receipt.collectReceiptInfo();
            receipt.saveToDatabase(connection);

            staff.collectStaffInfo();
            staff.saveToDatabase(connection);

            test.collectTestInfo();
            test.saveToDatabase(connection);
            test.displayTestResults();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        Hospital hospital = null;
        try {
            hospital = new Hospital("My Hospital");
            hospital.run();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //if (hospital != null)
                }
            }
        }


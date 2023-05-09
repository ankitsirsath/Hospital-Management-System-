import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Doctor {
    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private String gender;
    private String specialization;
    private String phoneNumber;

    public Doctor() {
    }

    public Doctor(int id, String firstName, String lastName, int age, String gender, String specialization, String phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.specialization = specialization;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void collectDoctorInfo() {
        // Collect user info from web form and store in instance variables
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.specialization = specialization;
        this.phoneNumber = phoneNumber;
    }

    public void saveToDatabase(Connection connection) throws SQLException {
        String sql = "INSERT INTO Doctors (id,firstName, lastName, age, gender, specialization, phoneNumber) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, String.valueOf(id));
            statement.setString(2, firstName);
            statement.setString(3, lastName);
            statement.setInt(4, age);
            statement.setString(5, gender);
            statement.setString(6, specialization);
            statement.setString(7, phoneNumber);

            statement.executeUpdate();
        }
    }
}

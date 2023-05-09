import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Patient {
    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private String gender;
    private String address;
    private String phoneNumber;

    // constructor
    public Patient() {
    }

    // getters and setters
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // methods
    public void collectPatientInfo() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        // Collect patient information
        System.out.print("Enter patient ID: ");
        setId(scanner.nextInt());
        scanner.nextLine(); // Consume newline character
        System.out.print("Enter patient first name: ");
        setFirstName(scanner.nextLine());
        System.out.print("Enter patient last name: ");
        setLastName(scanner.nextLine());
        System.out.print("Enter patient age: ");
        setAge(scanner.nextInt());
        scanner.nextLine(); // Consume newline character
        System.out.print("Enter patient gender: ");
        setGender(scanner.nextLine());
        System.out.print("Enter patient address: ");
        setAddress(scanner.nextLine());
        System.out.print("Enter patient phone number: ");
        setPhoneNumber(scanner.nextLine());

        // Save patient information to database

        scanner.close();
    }

    public void saveToDatabase(Connection connection) throws SQLException {
        String sql = "INSERT INTO Patients (id, firstName, lastName, age, gender, address, phoneNumber) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, getId());
            statement.setString(2, getFirstName());
            statement.setString(3, getLastName());
            statement.setInt(4, getAge());
            statement.setString(5, getGender());
            statement.setString(6, getAddress());
            statement.setString(7, getPhoneNumber());

            statement.executeUpdate();
        }
    }
}

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Staff {
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String department;
    private String id;

    public Staff(String firstName, String lastName, String email, String address, String department, String id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.department = department;
        this.id = id;
    }

    public Staff() {

    }

    // getters and setters for instance variables
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void saveToDatabase(Connection connection) throws SQLException {
        String sql = "INSERT INTO Staff (firstName, lastName, email, address, department, id) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, email);
            statement.setString(4, address);
            statement.setString(5, department);
            statement.setString(6, id);

            statement.executeUpdate();
        }
    }

    public static Staff collectStaffInfo() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter staff information:");

        System.out.print("First name: ");
        String firstName = scanner.nextLine();

        System.out.print("Last name: ");
        String lastName = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Address: ");
        String address = scanner.nextLine();

        System.out.print("Department: ");
        String department = scanner.nextLine();

        System.out.print("ID: ");
        String id = scanner.nextLine();

        return new Staff(firstName, lastName, email, address, department, id);
    }
}

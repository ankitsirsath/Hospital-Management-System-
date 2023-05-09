import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class UserDetails {
    protected String firstName;
    protected String lastName;
    protected String email;
    protected String address;

    public UserDetails(String firstName, String lastName, String email, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
    }

    public abstract void collectUserInfo(String firstName, String lastName, String email, String address);

    public void saveToDatabase(Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO UserDetails (firstName, lastName, email, address) VALUES (?, ?, ?, ?)");
        statement.setString(1, firstName);
        statement.setString(2, lastName);
        statement.setString(3, email);
        statement.setString(4, address);
        statement.executeUpdate();
        statement.close();
    }

    public void collectUserInfo() {
    }
}

class WebUserDetails extends UserDetails {
    public WebUserDetails(String firstName, String lastName, String email, String address) {
        super(firstName, lastName, email, address);
    }

    public void collectUserInfo(String firstName, String lastName, String email, String address) {
        // Collect user info from web form and store in instance variables
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
    }

    public void saveToDatabase(Connection connection) throws SQLException {
        super.saveToDatabase(connection); // Call superclass method to insert user info into UserDetails table
        // Insert additional data into other tables as needed
        // ...
    }
}

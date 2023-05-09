import java.sql.Connection;
import java.sql.SQLException;

public interface Laboratory {
    void collectData();

    void saveToDatabase(Connection connection) throws SQLException;
}

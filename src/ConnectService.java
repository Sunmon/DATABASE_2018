import java.sql.*;
public interface ConnectService {
    // JDBC로 Connect해주는 interface
public Connection makeConnection(String portNum, String dbName, String id, String password);
}
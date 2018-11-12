import java.sql.*;
public interface StatementService {
    //Statement 만드는 interface
    public Statement makeStatement(Connection con);
}

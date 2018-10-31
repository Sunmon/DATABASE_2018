import java.sql.Connection;
import java.sql.Statement;
public interface StatementService {
    //Statement 만드는 interface
    public Statement makeStatement(Connection con);
}

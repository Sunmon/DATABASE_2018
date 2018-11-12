import java.sql.*;

public class Connector implements ConnectService, StatementService{
    Connection              con;
    Statement               stmt;
    PreparedStatement       pstmt;
    ResultSet               rs;

    //Constructor
    public Connector(){};
    public Connector(String portNum, String dbName, String id, String password)
    {   //Connector 생성자.
        con = makeConnection(portNum, dbName,id,password);
//        stmt = makeStatement(con);
//        pstmt = makePreStatement(con);
    }


    public Statement makeStatement()
    {
        return makeStatement(this.con);
    }

    @Override
    public java.sql.Connection makeConnection(String portNum, String dbName, String id, String password) {
        //접속하는 url 설정
        String url = "jdbc:mariadb://localhost:"+portNum+"/"+dbName;

        //connection 시도
        con = null;
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            System.out.println("드라이버 적재 성공");
            con = DriverManager.getConnection(url, id, password);
            System.out.println("데이터베이스 연결 성공");
        } catch (ClassNotFoundException e) {
            System.out.println("드라이버를 찾을 수 없습니다.");
        } catch (SQLException e) {
            System.out.println("데이터베이스 연결 실패");
        }
        return con;
    }

        @Override
    public Statement makeStatement(Connection con) {
    {
            Statement stmt = null;
            try {
                System.out.println("데이터를 넣으려고 시도중...");
                stmt = con.createStatement();

                //insert data here
                String sql = "INSERT INTO instructor " + "VALUES ('23411', 'test1031', 'Comp. Sci.', 50000)";
                stmt.executeUpdate(sql);
                System.out.println("데이터 insert 완료...");

            } catch (SQLException se) {
                // Handle errors for JDBC
                se.printStackTrace();
            } catch (Exception e) {
                // Handle errors for Class.forName
                e.printStackTrace();
            } finally {
                // finally block used to close resources
                try {
                    if (stmt != null) con.close();
                } catch (SQLException se) {
                } // do nothing
                try {
                    if (con != null) con.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                } // end finally try
            } // end try
            System.out.println("Goodbye!");
            // end JDBCExample
            return stmt;
            }
        }
}

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

    //test for prestsmt
    public void insertData()
    {//예시로 맘대로 하나 넣게 만들었음. 이거 고쳐서 써야지.
        //여기 attributes 이름은 실제 table에 있는 attributes이름으로 해야 함..
        //metadata에서 attribute이름들 가져와서 넣을 수도 있지 않을까?
        String sql = "INSERT INTO instructor (ID, name, dept_name, salary)";
        sql = sql + "VALUES (?, ?, ?, ?)";
        String ID = "29384";
        String name = "test1112";
        String dept_name = "Comp. Sci.";
        int salary = 50000;

        try {
            pstmt    = con.prepareStatement(sql);
            pstmt.setString(1, ID);
            pstmt.setString(2, name);
            pstmt.setString(3, dept_name);
            pstmt.setInt(4, salary);
            int i = pstmt.executeUpdate();
            System.out.println("InsertP 쿼리 수행" + i);
            pstmt.close();
        } catch (SQLException e) { System.out.println("Insert 쿼리 수행 실패");
        e.printStackTrace();}
    }
}

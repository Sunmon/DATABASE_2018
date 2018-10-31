import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
//import java.sql.*;

public class Main {

    public static void main(String[] args) {
        //testing for connection
        Connection con = makeConnection("3306","ku_db","sunmon","computer");

        //testing for statement
        Statement stmt = makeStatement(con);
    }


    public static Connection makeConnection(String portNum, String dbName, String id, String password) {
        //MariaDB기준
        //접속하는 url 설정
        String url = "jdbc:mariadb://localhost:"+portNum+"/"+dbName;

        //connection 시도
        Connection con = null;
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


    public static Statement makeStatement(Connection con) {
        Statement stmt = null;
        try {
            System.out.println("데이터를 넣으려고 시도중...");
            stmt = con.createStatement();

            //insert data here
            String sql = "INSERT INTO instructor " + "VALUES ('23423', 'test1031', 'Comp. Sci.', 50000)";
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
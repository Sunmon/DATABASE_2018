package test;
import java.sql.*;

public class Connector {
    //DB와 연결해주는 클래스...
    //NOTE: 아직은 그냥 DB의 Connection하나로 통일하기 위해 쓴다.
    //NOTE: 나중엔 Update도 이거 통해서 할지도 모르지

    Connection con;
    Statement stmt;
    PreparedStatement pstmt;
    ResultSet rs;

    //Constructor
    public Connector(String portNum, String dbName, String id, String password) {   //Connector 생성자.
        //db 자체에 연결할 이름/비번
        con = makeConnection(portNum, dbName, id, password);
    }

    public java.sql.Connection makeConnection(String portNum, String dbName, String id, String password) {
        //접속하는 url 설정
        String url = "jdbc:mariadb://localhost:" + portNum + "/" + dbName;

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


}
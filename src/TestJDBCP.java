/*import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;*/
import java.sql.*;

public class TestJDBCP {

    String driver        = "org.mariadb.jdbc.Driver";
    //String url           = "jdbc:mariadb://103.218.162.26:3306/employees";
    String url           = "jdbc:mariadb://103.218.162.30:3306/univ";
    String uId           = "konkuk";
    String uPwd          = "computer";

    Connection               con;
    PreparedStatement        pstmt;
    Statement                stmt;
    ResultSet                rs;

    public TestJDBCP() {
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, uId, uPwd);

            if( con != null ){ System.out.println("데이터 베이스 접속 성공"); }

        } catch (ClassNotFoundException e) { System.out.println("드라이버 로드 실패");    }
        catch (SQLException e) { System.out.println("데이터 베이스 접속 실패"); }
    }

    public void createTable(){
        String  sqlC = "CREATE TABLE student2(s_number int(11), SS_number varchar(14), sex varchar(2), mobile varchar(11))";
        String  sqlD = "DROP TABLE student2";
        try {
            stmt = con.createStatement();
            int i = stmt.executeUpdate(sqlD);
            System.out.println("Drop Table 쿼리 수행 " + i);
            i = stmt.executeUpdate(sqlC);
            System.out.println("Create Table 쿼리 수행 " + i);
            stmt.close();
        } catch (SQLException e) { System.out.println("crateTable 쿼리 수행 실패"); }
    }

    public void insertData(){
        String  sql = "INSERT INTO student2 (s_number, SS_number, sex, mobile) ";
        sql = sql + "VALUES  (?, ?, ?, ?)";
        double sn = 2016045112;
        String ssn = "960705-1234567";
        String s = "여";
        String m = "01022011234";

        try {
            pstmt                = con.prepareStatement(sql);
            pstmt.setDouble(1, sn);
            pstmt.setString(2, ssn);
            pstmt.setString(3, s);
            pstmt.setString(4, m);
            int i = pstmt.executeUpdate();
            System.out.println("InsertP 쿼리 수행" + i);
            pstmt.close();
        } catch (SQLException e) { System.out.println("Insert 쿼리 수행 실패"); }
    }

    public void updateData(){
        String  sql = "UPDATE student2 SET s_number=?";
        double sn = 2014045112;
        try {
            pstmt                = con.prepareStatement(sql);
            pstmt.setDouble(1, sn);
            int i = pstmt.executeUpdate();;
            System.out.println("UpdateP 쿼리 수행" + i);
            pstmt.close();
        } catch (SQLException e) { System.out.println("update 쿼리 수행 실패"); }
    }

    public void deleteData(){
        String  sql = "DELETE from student2 where s_number=?";
        double sn = 2014045112;
        try {
            pstmt                = con.prepareStatement(sql);
            pstmt.setDouble(1, sn);
            int i = pstmt.executeUpdate();
            System.out.println("DeleteP 쿼리 수행" + i);
            pstmt.close();
        } catch (SQLException e) { System.out.println("Delete 쿼리 수행 실패"); }
    }

    public void select(){
        String sql    = "select s_number, SS_number, sex, mobile from student2";
        try {
            pstmt                = con.prepareStatement(sql);
            rs                   = pstmt.executeQuery();
            while(rs.next()){
                System.out.print(rs.getInt("s_number")+"\t\t");
                System.out.print(rs.getString("SS_number")+"\t\t");
                System.out.print(rs.getString("sex")+"\t\t");
                System.out.println(rs.getString("mobile")+"\t\t");
            }
            pstmt.close();
        } catch (SQLException e) { System.out.println("select 쿼리 수행 실패"); }
    }

    public void selectMETA(){
        String sql    = "select s_number, SS_number, sex, mobile from student2";
        try {
            pstmt                = con.prepareStatement(sql);
            rs                   = pstmt.executeQuery();

            ResultSetMetaData rsmd = rs.getMetaData();
            int cols = rsmd.getColumnCount();
            System.out.println("Count of column is " + cols);
            System.out.println("and column name is ");
            for (int i = 1; i <= cols; i++){
                System.out.print("\t\t" + rsmd.getColumnLabel(i));
            }
            System.out.println();
            while(rs.next()){
                System.out.print(rs.getInt("s_number")+"\t\t");
                System.out.print(rs.getString("SS_number")+"\t\t");
                System.out.print(rs.getString("sex")+"\t\t");
                System.out.println(rs.getString("mobile")+"\t\t");
            }


            pstmt.close();
        } catch (SQLException e) { System.out.println("select 쿼리 수행 실패"); }
    }

    public void transStart(){
        try {
            con.setAutoCommit(false);   //transaction start point
        }catch (SQLException e) { System.out.println("Transaction 시작");}
    }

    public void transStop(){
        try {
            con.commit();   //transaction start point
        }catch (SQLException e) { System.out.println("Transaction 중지");}
    }

    public static void main(String[] args){
        TestJDBCP dbm    = new TestJDBCP();
        dbm.createTable();
        dbm.transStart();
        dbm.insertData();
        dbm.updateData();
        dbm.select();
        dbm.transStop();

        dbm.selectMETA();

        /*
        dbm.select();

        dbm.select();
        dbm.deleteData();
        dbm.select();
*/
        /*
        dbm.insertData();
        dbm.select();


        dbm.select();
        dbm.deleteData();
        dbm.selectMETA();*/

    }

}

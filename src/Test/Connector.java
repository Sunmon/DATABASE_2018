package Test;
import java.sql.*;
import java.util.ArrayList;

public class Connector implements CartDAO{
    //DB와 연결해서 업데이트하거나, 데이터를 가져오는 역할을 해주는 클래스
    Connection              con;
    Statement               stmt;
    PreparedStatement       pstmt;
    ResultSet               rs;

    //Constructor
    public Connector(String portNum, String dbName, String id, String password)
    {   //Connector 생성자.
        //db 자체에 연결할 이름/비번
        con = makeConnection(portNum, dbName,id,password);
    }



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

    public Statement makeStatement(Connection con) {
        {
            Statement stmt = null;
            try {
                System.out.println("데이터를 넣으려고 시도중...");
                stmt = con.createStatement();

/*                //insert data here
                String sql = "INSERT INTO instructor " + "VALUES ('23411', 'test1031', 'Comp. Sci.', 50000)";
                stmt.executeUpdate(sql);
                System.out.println("데이터 insert 완료...");
*/

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


    //update to daatabase
    public ResultSet select(String sql){
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
        return rs;
    }


    //================================================================
    //================================================================
    ArrayList<CartDTO> cart;

    @Override
    public ArrayList<CartDTO> initialize(String _id) {
        //TODO: 지금은 print만했고, 객체는 생성 안 했음.
        //user가 담은 cart목록 + sell_list에서 상품이름 DB에서 가져와서 DTO로 초기화.

        String sql = "select cart.*, sell_list.p_nickname, sell_list.price " +
                "from cart, sell_list " +
                "where cart.p_code = sell_list.p_code " +
                "AND cart.seller_ID = sell_list.seller_ID";

        //DB에 연결 시도
        try {
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            //col 정보 읽어오기
            ResultSetMetaData rsmd = rs.getMetaData();
            int cols = rsmd.getColumnCount();
            System.out.println("Count of column is " + cols);
            System.out.println("and column name is ");
            for (int i = 1; i <= cols; i++){
                System.out.print(rsmd.getColumnLabel(i)+"\t\t");
            }
            System.out.println();

            while(rs.next())
            {
                System.out.print(rs.getString("p_code") +"\t\t");
                System.out.print(rs.getString("customer_ID")+"\t\t\t");
                System.out.print(rs.getString("seller_ID")+"\t\t");
                System.out.print(rs.getInt("p_count")+"\t\t\t");
                System.out.print(rs.getInt("tot_price")+"\t\t\t");
                System.out.print(rs.getString("p_nickname")+"\t\t");
                System.out.print(rs.getInt("price")+"\t\t");
                System.out.println();
            }
            pstmt.close();
        } catch (SQLException e) {
            System.out.println("DB에서 JAVA로 cart 가져오기 실패");
            e.printStackTrace();
        }

        return null;
    }


    // insertCartDB(CartDTO cd);
    @Override
    public void insertCartDB(){}

    public void insertCartDB(String code, String cID, String sID, int pcount, int tprice){
        //cart DB에 집어넣는 메소드
        //지금은 그냥 DB에 넣지만, DTO이용해서 넣는걸로 바꾸기.
        //나중에 그냥 GUI상에서 클릭하면 알아서 parameter들 추가되는걸로.
        //tot_price는 attribute에서 빼도 된다. 알아서 계산해서 저장해두기.

        String sql = "INSERT INTO cart ";
        sql = sql + "VALUES (?, ?, ?, ?, ?)";
        try
        {
            pstmt                = con.prepareStatement(sql);
            pstmt.setString(1, code);
            pstmt.setString(2, cID);
            pstmt.setString(3, sID);
            pstmt.setInt(4, pcount);
            pstmt.setInt(5, tprice);
            int i = pstmt.executeUpdate();
            System.out.println("Insert 쿼리 수행" + i);
            pstmt.close();
        } catch (SQLException e)
        {
            System.out.println("Insert 쿼리 수행 실패");
            e.printStackTrace();
        }
    }


    @Override
    public void deleteCartDB()
    {


    }

    @Override
    public void updateCartDB() {

    }

    @Override
    public void selectCartDB() {

    }
}

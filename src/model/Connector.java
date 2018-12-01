package model;
import java.sql.*;
import java.util.Calendar;

public class Connector
{
    //DB와 연결해서 업데이트하거나, 데이터를 가져오는 역할을 해주는 클래스
    private Connection con;
    private Statement stmt;
    private PreparedStatement pstmt;
    private ResultSet rs;

    //Constructor
    public Connector(String portNum, String dbName, String id, String password)
    {   //Connector 생성자.
        //db 자체에 연결할 이름/비번
        con = makeConnection(portNum, dbName, id, password);
    }

    public Connection getCon()
    {   //다른 객체에서도 동일 connection을 쓰기 위함
        return con;
    }


    public java.sql.Connection makeConnection(String portNum, String dbName, String id, String password)
    {
        //접속하는 url 설정
        String url = "jdbc:mariadb://localhost:" + portNum + "/" + dbName;

        //connection 시도
        con = null;
        try
        {
            Class.forName("org.mariadb.jdbc.Driver");
            System.out.println("드라이버 적재 성공");
            con = DriverManager.getConnection(url, id, password);
            System.out.println("데이터베이스 연결 성공");
        } catch (ClassNotFoundException e)
        {
            System.out.println("드라이버를 찾을 수 없습니다.");
        } catch (SQLException e)
        {
            System.out.println("데이터베이스 연결 실패");
        }
        return con;
    }

    public User login(String _id, String _pw)
    {   //login해서 맞는 user객체 생성
        String sql = "select *" +
                "from person " +
                "where id = ? AND pw = ?";
        //DB에 연결 시도
        try
        {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, _id);
            pstmt.setString(2, _pw);
            rs = pstmt.executeQuery();

            while (rs.next())
            {
                String ID = rs.getString("ID");
                String pw = rs.getString("pw");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String gender = rs.getString("gender");
                String phone = rs.getString("phone");
                String address = rs.getString("address");
                int height = rs.getInt("height");
                int weight = rs.getInt("weight");
                int points = rs.getInt("points");
                String authority = rs.getString("authority");
                String shop_name = rs.getString("shop_name");
                int revenue = rs.getInt("revenue");

                if (authority.equals("customers"))
                    return new User(ID, pw, name, age, gender, phone, address, height, weight, points, authority);
                else if (authority.equals("sellers"))
                    return new Seller(ID, pw, name, age, gender, phone, address, height, weight, points, authority, shop_name, revenue);
            }
            System.out.println("ID 또는 PW를 확인해 주십시오");
            pstmt.close();
        } catch (SQLException e)
        {
            System.out.println("ID 또는 PW를 확인해 주십시오");
            e.printStackTrace();
        }
    return null;
    }

    public void updateMypage(String _pn,int age,String gender,String address,int height, int weight, String _id, String phone ){
        String sql= "UPDATE person"+
                "SET pw=?, age=?, gender=?, address=?, height=?, weight=?"+
                "where ID=?,phone=?";
        try
        {
            pstmt=con.prepareStatement(sql);
            pstmt.setString(1,_pn);
            pstmt.setInt(2,age);
            pstmt.setString(3,gender);
            pstmt.setString(4,address);
            pstmt.setInt(5,height);
            pstmt.setInt(6,weight);
            pstmt.setString(7,_id);
            pstmt.setString(8,phone);
            rs = pstmt.executeQuery();
            pstmt.close();

        }catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("point 업데이트를 하지 못했습니다."); ;

        }
    }




    public String findPW(String _id, String _pn)
    {   //id와 phone number로 pw 찾는 메소드
        String sql = "SELECT pw "+
                "FROM person "+
                "WHERE id = ? AND phone = ?";
        try
        {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, _id);
            pstmt.setString(2, _pn);
            rs = pstmt.executeQuery();
            while (rs.next())
            {
                String pw = "Password: " + rs.getString("pw");
                pstmt.close();
                return pw;
            }
            String pw = "일치하는 정보가 없습니다.";
            pstmt.close();
            return pw;
        } catch (SQLException e)
        {
            e.printStackTrace();
            String pw = "일치하는 정보가 없습니다.";
            return pw;

        }
        }


    public void updateUserPoint(String _id, int _point)
    {   //user point 업데이트
        String sql = "Update person set points = points + ? where id = ?";
        try
        {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, _point);
            pstmt.setString(2, _id);
            rs = pstmt.executeQuery();
            pstmt.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("point 업데이트를 하지 못했습니다."); ;

        }
    }

    public void insertLog(String p_code, String seller_id, String id, int point)
    {
        //log에 추가하는 메소드
        //TODO: 해야함!!!!


//        Timestamp t = new Timestamp(System.currentTimeMillis());
//        Calendar cal = new Calendar();

     /*   String sql = "INSERT INTO sell_log VALUES (?,?,?,?)";
        try {
            pstmt    = con.prepareStatement(sql);
            pstmt.setString(1, p_code);
            pstmt.setString(2, seller_id);
            pstmt.setInt(3, point);
            pstmt.setDate(4, t);
            pstmt.setString(5,id);
            int i = pstmt.executeUpdate();
            System.out.println("InsertP 쿼리 수행" + i);
            pstmt.close();
        } catch (SQLException e) { System.out.println("Insert 쿼리 수행 실패");
            e.printStackTrace();}
    }
        System.out.println(t);
        System.out.println(t.getTime());
        System.out.println("adsf");


*/
    }
}
   /* //test for prestsmt
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
    }*/


   /* //update to daatabase
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
    }*/


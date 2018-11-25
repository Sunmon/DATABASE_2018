package Test;

import java.sql.*;
import java.util.ArrayList;

public class DAOFactory
{
    Connection con;
    PreparedStatement pstmt;
    ResultSet rs;



    public DAOFactory(Connection con)
    {   //Connector와 같은 Connection으로 설정.
        this.con = con;
        System.out.println("DAOFACT:: connection 완료");

    }

    public DAOFactory setDAO(String dao)
    {
        if(dao.equals("cart")) return new CartDAO(con);
        if(dao.equals("sellList")) return new SellListDAO(con);
        return this;

    }

    public ArrayList initialize(String _id) {
        return null;
    }

    public void printAttributes() throws SQLException {
        //col 정보 읽어오기
        ResultSetMetaData rsmd = rs.getMetaData();
        int cols = rsmd.getColumnCount();
        for (int i = 1; i <= cols; i++) {
            System.out.print(rsmd.getColumnLabel(i) + "\t\t\t");
        }
        System.out.println();

    }

}
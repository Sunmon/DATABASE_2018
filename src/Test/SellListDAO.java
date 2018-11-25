package Test;

import java.sql.Connection;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class SellListDAO extends DAOFactory
{
    ArrayList<SellListDTO> sellList = null;

    @Override
    public ArrayList<SellListDTO> initialize(String _id)
    {   //sellList table 통째로 가져오기 + sellistDTO로 초기화
        //list생성
        sellList = new ArrayList<SellListDTO>();

        String sql = "select * from sell_list";
        //DB에 연결 시도
        try {
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                //print
                String pc = rs.getString("p_code");
                String sID = rs.getString("seller_ID");
                int stock = rs.getInt("stock");
                String size = rs.getString("size");
                String nick = rs.getString("p_nickname");
                int price = rs.getInt("price");

                //sellList에 sellDTO 새 객체 추가
                SellListDTO s = new SellListDTO(pc, sID, price, stock, size, nick);
                sellList.add(s);
            }
            pstmt.close();
        } catch (SQLException e) {
            System.out.println("DB에서 JAVA로 sellList 가져오기 실패");
            e.printStackTrace();
        }

        return sellList;
    }

    public SellListDAO(Connection con) {super(con);}

    @Override
    public void printAttributes()
    {
        System.out.print("p_nickname\t\t");
        System.out.print("size\t\t");
        System.out.print("price\t\t");
        System.out.print("stock\t\t");
        System.out.print("seller_ID\t\t");
        System.out.print("p_code\t\t");
        System.out.println();
    }

}

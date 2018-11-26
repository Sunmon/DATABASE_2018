package Test;

import java.sql.Connection;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class SellListDAO extends DAOFactory
{
    ArrayList<SellListDTO> dtoList = null;
//    ArrayList<SellListDTO> dtoList = null;

    public ArrayList<SellListDTO> getDtoList()
    {
        return dtoList;
    }
    @Override
    public ArrayList<SellListDTO> initialize(String _id)
    {   //dtoList table 통째로 가져오기 + sellistDTO로 초기화
        //list생성
        dtoList = new ArrayList<SellListDTO>();

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
                dtoList.add(s);
            }
            pstmt.close();
        } catch (SQLException e) {
            System.out.println("DB에서 JAVA로 dtoList 가져오기 실패");
            e.printStackTrace();
        }

        return dtoList;
    }

    public SellListDAO(Connection con) {super(con);}

 /*   @Override
    public void printAttributes()
    {
        super.printAttributes();
       *//* System.out.print("p_nickname\t\t");
        System.out.print("size\t\t");
        System.out.print("price\t\t");
        System.out.print("stock\t\t");
        System.out.print("seller_ID\t\t");
        System.out.print("p_code\t\t");
        System.out.println();*//*
    }*/

    @Override
    public void printAllItems()
    {   //cart에 담은 모든 item들 보여줌. (로그인한 ID중)

        for (SellListDTO sto : dtoList)
        {
            printItem(sto);
        }
    }

    public void printItem(SellListDTO sto)
    {
        System.out.print(sto.getP_nickname() + "\t\t");
        System.out.print(sto.getSize() + "\t\t\t");
        System.out.print(sto.getPrice() + "\t\t\t");
        System.out.print(sto.getStock()+ "\t\t\t");
        System.out.print(sto.getSeller_ID()+ "\t\t\t");
        System.out.print(sto.getP_code()+ "\t\t\t");
        System.out.println();
    }



}

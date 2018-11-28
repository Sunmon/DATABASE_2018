package model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class SellListDAO extends DAOFactory
{
    private String attributes[] = {"p_code", "seller_ID", "price", "stock", "size", "p_nickname"};


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

//        String sql = "select * from (sell_list natural join product) natural join ";

        String sql = "select * " +
                "from (sell_list join product using (p_code)) natural join category";
        //DB에 연결 시도
        try {
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                //user에게 보여질 정보 가져오기
                String pc = rs.getString("p_code");
                String sID = rs.getString("seller_ID");
                int stock = rs.getInt("stock");
                String size = rs.getString("size");
                String nick = rs.getString("p_nickname");
                int price = rs.getInt("price");

                //user에게 보여지진 않지만 검색등에 쓸 정보 가져오기
                String p_name = rs.getString("p_name");
                String c_code = rs.getString("c_code");
                String c_code_sub = rs.getString("c_code_sub");
                String c_name = rs.getString("c_name");



                //sellList에 sellDTO 새 객체 추가
                SellListDTO s = new SellListDTO(pc, sID, price, stock, size, nick, p_name, c_code, c_code_sub, c_name);
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


    public String[] getAttributes()
    {
        return attributes;
    }

    public void setAttributes(String[] attributes)
    {
        this.attributes = attributes;
    }

    @Override
    public void printAttributes() throws SQLException {
        for(int i=0; i<attributes.length; i++)
            System.out.print(attributes[i] + "\t\t");
        System.out.println();
    }
}

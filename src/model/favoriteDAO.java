package model;

import java.sql.*;
import java.util.ArrayList;

public class favoriteDAO extends DAOFactory {

    private String attributes[] = {"customer_ID", "p_code", "seller_ID"};

    public ArrayList<FavoriteDTO> getDtoList()
    {
        return dtoList;
    }
    ArrayList<FavoriteDTO> dtoList = null;




    public favoriteDAO(Connection con) {super(con);}



    @Override
    public ArrayList<FavoriteDTO> initialize(String _id)
    {
        dtoList = new ArrayList<FavoriteDTO>();

        String sql = "select favorites.*,sell_list.p_nickname,sell_list.price from favorites,sell_list "+
                "where favorites.p_code = sell_list.p_code"+
                "AND favorites.seller_ID = sell_list.seller_ID "+
                "AND favorites.customer_ID = ?";

        //DB에 연결 시도
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,_id);
            rs = pstmt.executeQuery();

            while (rs.next()) {

                String pc = rs.getString("p_code");
                String sID = rs.getString("seller_ID");
                String nick = rs.getString("p_nickname");
                int price = rs.getInt("price");
                String cID = rs.getString("customer_ID");

                //sellList에 sellDTO 새 객체 추가
                FavoriteDTO s = new FavoriteDTO(pc, cID, sID, nick, price);
                dtoList.add(s);
            }
            pstmt.close();
        } catch (SQLException e) {
            System.out.println("DB에서 JAVA로 dtoList 가져오기 실패");
            e.printStackTrace();
        }

        return dtoList;
    }

    @Override
    public void printAllItems()
    {

        for (FavoriteDTO sto : dtoList)
        {
            printItem(sto);
        }
    }

    public void printItem(FavoriteDTO sto)
    {
        System.out.print(sto.getP_nick() + "\t\t");
        System.out.print(sto.getprice() + "\t\t\t");
        System.out.print(sto.getSeller_ID()+ "\t\t\t");
        System.out.print(sto.getP_code()+ "\t\t\t");
        System.out.println();
    }

    public void insertfavoriteDB(FavoriteDTO ft)
    {   String sql = "INSERT INTO favorites ";
        sql = sql + "VALUES (?, ?, ?)";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, ft.getP_code());
            pstmt.setString(2, ft.getCustomer_ID());
            pstmt.setString(3, ft.getSeller_ID());
            int i = pstmt.executeUpdate();
            System.out.println("Insert 쿼리 수행" + i);
            pstmt.close();
        } catch (SQLException e) {
            System.out.println("Insert 쿼리 수행 실패");
            e.printStackTrace();
        }
    }

    private void deletefavoriteDB(FavoriteDTO ft)
    {
        String sql = "DELETE from favorites where p_code = ? AND seller_ID = ? AND Customer_ID=?";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, ft.getP_code());
            pstmt.setString(2, ft.getSeller_ID());
            pstmt.setString(3, ft.getCustomer_ID());
            int i = pstmt.executeUpdate();
            System.out.println("DeleteP 쿼리 수행" + i);
            pstmt.close();
            dtoList.remove(ft);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Delete 쿼리 수행 실패");
        }
    }


    public void insert(DTO dto)
    {   insertfavoriteDB((FavoriteDTO)dto);
    }
    public void delete(DTO dto) {
        deletefavoriteDB((FavoriteDTO)dto);
    }

}

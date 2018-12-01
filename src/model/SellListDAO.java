package model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class SellListDAO extends DAOFactory
{
    private String attributes[] = {"p_code", "seller_ID", "price", "stock", "size", "p_nickname"};

    ArrayList<SellListDTO> dtoList = null;

    public ArrayList<SellListDTO> getDtoList()
    {
        return dtoList;
    }



    private String _order = "";     //정렬할때 사용



    private String _search = "";    //검색할때 사용

    @Override
    public ArrayList<SellListDTO> initialize(String _id)
    {   //dtoList table 통째로 가져오기 + sellistDTO로 초기화
        //list생성
        dtoList = new ArrayList<SellListDTO>();

//        String sql = "select * from (sell_list natural join product) natural join ";

        String sql = "select * " +
                "from (sell_list join product using (p_code)) natural join category "+ _search + _order;
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
    public void insert(DTO dto)
    {
        insertSellListDB((SellListDTO)dto);
    }


    @Override
    public void delete(DTO dto)
    {
        deleteSellListDB((SellListDTO)dto);

    }


    @Override
    public void update(DTO dto)
    {
        updateSellListDB((SellListDTO)dto);
    }


    private void insertSellListDB(SellListDTO dto)
    {
        //sellListDTO에 있는 값을 DB에 삽입
        String sql = "INSERT INTO sell_list ";
        sql = sql + "VALUES (?, ?, ?, ?, ?, ?)";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, dto.getP_code());
            pstmt.setString(2, dto.getSeller_ID());
            pstmt.setInt(3, dto.getPrice());
            pstmt.setInt(4, dto.getStock());
            pstmt.setString(5, dto.getSize());
            pstmt.setString(6, dto.getP_nickname());
            int i = pstmt.executeUpdate();
            System.out.println("Insert 쿼리 수행" + i);
            pstmt.close();
        } catch (SQLException e) {
            System.out.println("Insert 쿼리 수행 실패");
            e.printStackTrace();
        }
    }

    public SellListDTO select(Connection con, String p_code, String seller_ID)
    {   //임시적으로 만들어놓은 메소드
        SellListDTO s = null;
        String sql = "select * " +
                "from (sell_list join product using (p_code)) natural join category " +
                "where p_code = ? AND seller_ID = ?";
        //DB에 연결 시도
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,p_code);
            pstmt.setString(2,seller_ID);
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
                s = new SellListDTO(pc, sID, price, stock, size, nick, p_name, c_code, c_code_sub, c_name);
            }
            pstmt.close();
        } catch (SQLException e) {
            System.out.println("DB에서 JAVA로 dtoList 가져오기 실패");
            e.printStackTrace();
        }
        return s;
    }

    private void deleteSellListDB(SellListDTO dto)
    {
        String sql = "DELETE from sell_list where p_code = ? AND seller_ID = ?";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, dto.getP_code());
            pstmt.setString(2, dto.getSeller_ID());
            int i = pstmt.executeUpdate();
            System.out.println("DeleteP 쿼리 수행" + i);
            pstmt.close();
            dtoList.remove(dto);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Delete 쿼리 수행 실패");
        }
    }

    public void updateSellListDB(SellListDTO dto)
    {
        //sellListDTO 객체에 있는 내용대로 DB에서 찾아서 수정.
        //재고, 닉네임, 금액 수정
        String sql = "UPDATE sell_list SET stock = ?, price = ?, p_nickname = ? WHERE p_code = ? AND seller_ID = ?";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, dto.getStock());
            pstmt.setInt(2, dto.getPrice());
            pstmt.setString(3, dto.getP_nickname());
            pstmt.setString(4,dto.getP_code());
            pstmt.setString(5, dto.getSeller_ID());
            int i = pstmt.executeUpdate();

            System.out.println("UpdateP 쿼리 수행" + i);
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("update 쿼리 수행 실패");
        }

    }



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

    public String get_order()
    {
        return _order;
    }

    public void set_order(String _order)
    {
        this._order = _order;
    }

    public String get_search()
    {
        return _search;
    }

    public void set_search(String _search)
    {
        this._search = _search;
    }

}

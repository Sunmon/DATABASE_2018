package model;
import java.sql.*;
import java.util.ArrayList;

public class CartDAO extends DAOFactory {

    public ArrayList<CartDTO> getDtoList()
    {
        return dtoList;
    }
    ArrayList<CartDTO> dtoList = null;

    private String attributes[] = {"p_nick", "p_count", "tot_price", "p_code", "seller_ID"};

    public CartDAO(Connection con)
    {
        super(con);
        System.out.println("Cart con완료");
    }



    //FACTORY에서 쓰는거 실험중
    @Override
    public void printAttributes() throws SQLException
    {
        for(int i=0; i<attributes.length; i++)
        System.out.print(attributes[i] + "\t\t");
        System.out.println();
    }

    //print All Items
    @Override
    public void printAllItems()
    {   //cart에 담은 모든 item들 보여줌. (로그인한 ID의 카트)
        for (CartDTO cto : dtoList)
        {
          printItem(cto);
        }
    }



    public void printItem(CartDTO cto)
    {   //cartDTO 객체 하나에 있는 정보들을 print
        System.out.print(cto.getP_nick() + "\t\t");
        System.out.print(cto.getP_count() + "\t\t\t");
        System.out.print(cto.getTot_price() + "\t\t\t");
        System.out.print(cto.getP_code()+ "\t\t\t");
        System.out.print(cto.getSeller_ID()+ "\t\t\t");
        System.out.println();
    }


    public ArrayList<CartDTO> initialize(String _id)
    {
        //user가 담은 cart목록 + sell_list에서 상품이름 DB에서 가져와서 DTO로 초기화.
        //list생성
        dtoList = new ArrayList<CartDTO>();

        String sql = "select cart.*, sell_list.p_nickname, sell_list.price " +
                "from cart, sell_list " +
                "where cart.p_code = sell_list.p_code " +
                "AND cart.seller_ID = sell_list.seller_ID " +
                "AND cart.customer_ID = ?";
        //DB에 연결 시도
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,_id);
            rs = pstmt.executeQuery();


            while (rs.next()) {
                //print
                String pc = rs.getString("p_code");
                String cID = rs.getString("customer_ID");
                String sID = rs.getString("seller_ID");
                int pcount = rs.getInt("p_count");
                int tp = rs.getInt("tot_price");
                String nick = rs.getString("p_nickname");
                int price = rs.getInt("price");

                //cartList에 cart 새 객체 추가
                CartDTO c = new CartDTO(pc, cID, sID, pcount, tp, nick, price);
                dtoList.add(c);
            }
            pstmt.close();
        } catch (SQLException e) {
            System.out.println("DB에서 JAVA로 cart 가져오기 실패");
            e.printStackTrace();
        }


        return dtoList;
    }
    



    public void insert(DTO dto)
    {
        insertCartDB((CartDTO)dto);
    }

    public void delete(DTO dto)
    {
        deleteCartDB((CartDTO)dto);
    }

    public void update(DTO dto)
    {
        updateCartDB((CartDTO)dto);
    }



    public void insertCartDB(CartDTO ct)
    {   //cartDTO에 있는 값을 DB에 업데이트
        //cartList에는 추가 안 함!
        String sql = "INSERT IGNORE INTO  cart ";
        sql = sql + "VALUES (?, ?, ?, ?, ?)";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, ct.getP_code());
            pstmt.setString(2, ct.getCustomer_ID());
            pstmt.setString(3, ct.getSeller_ID());
            pstmt.setInt(4, ct.getP_count());
            pstmt.setInt(5, ct.getTot_price());
            int i = pstmt.executeUpdate();
            System.out.println("Insert 쿼리 수행" + i);
            pstmt.close();
        } catch (SQLException e) {
            System.out.println("Insert 쿼리 수행 실패");
            e.printStackTrace();
        }
    }


    public void deleteCartDB(CartDTO ct)
    {   //CartDTO 입력받아서 거기 있는 내용 삭제.
        //DB에서 삭제 및 List에서도 삭제.
        String sql = "DELETE from cart where p_code = ? AND customer_ID = ? AND seller_ID = ?";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, ct.getP_code());
            pstmt.setString(2, ct.getCustomer_ID());
            pstmt.setString(3, ct.getSeller_ID());
            int i = pstmt.executeUpdate();
            System.out.println("DeleteP 쿼리 수행" + i);
            pstmt.close();

            //cartList에서 삭제
            dtoList.remove(ct);

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Delete 쿼리 수행 실패");
        }

    }


    public void updateCartDB(CartDTO ct)
    {   //CartDTO 객체에 있는 내용대로 DB에서 찾아서 수정.
        //개수, 총금액 수정
        String sql = "UPDATE cart SET p_count = ?, tot_price = ? WHERE p_code = ? AND customer_ID = ? AND seller_ID = ?";  //sql도 바꿔야 함. 알아서 가격이 맞춰지도록.
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, ct.getP_count());
            pstmt.setInt(2, ct.getTot_price());
            pstmt.setString(3, ct.getP_code());
            pstmt.setString(4, ct.getCustomer_ID());
            pstmt.setString(5, ct.getSeller_ID());
            int i = pstmt.executeUpdate();

            System.out.println("UpdateP 쿼리 수행" + i);
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("update 쿼리 수행 실패");
        }
    }


    public CartDTO selectCartDB(String pc, String cid, String sid)
    {   //DB에서 select해서 새 DTO객체를 만들어서 리턴.
        String sql = "select cart.*, sell_list.p_nickname, sell_list.price " +
                "from cart, sell_list " +
                "where cart.p_code = sell_list.p_code " +
                "AND cart.seller_ID = sell_list.seller_ID " +
                "AND cart.p_code = " + pc +
                " AND cart.customer_ID = " + cid +
                " AND cart.seller_ID = " + sid;

        try {
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                //cartList에 cart 새 객체 추가
                CartDTO c = new CartDTO(rs.getString("p_code"),
                        rs.getString("customer_ID"),
                        rs.getString("seller_ID"),
                        rs.getInt("p_count"),
                        rs.getInt("tot_price"),
                        rs.getString("p_nickname"),
                        rs.getInt("price"));
                dtoList.add(c);
                return c;
            }
            pstmt.close();
        } catch (SQLException e) {
            System.out.println("DB에서 JAVA로 cart 가져오기 실패");
            e.printStackTrace();
        }
        return null;
    }


    //Getter AND Setter
    public String[] getAttributes()
    {
        return attributes;
    }

    public void setAttributes(String[] attributes)
    {
        this.attributes = attributes;
    }
}
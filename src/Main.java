//import java.sql.*;
import Test.*;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException
    {
        //Test package에 있는거 새로 테스트하는거.
        Test.Connector con = new Test.Connector("3306", "dbtest_1115", "sunmon", "computer");
//        Test.CartDAO cao = new CartDAO(con.getCon());
        DAOFactory df = new DAOFactory(con.getCon());
        Test.DAOFactory cao = df.setDAO("cart");
        Test.DAOFactory slao = df.setDAO("sellList");

        cao.initialize("sunmon");
        slao.initialize("sunmon");

        User customer = new User();
        customer.setID("sunmon");
//        customer.showLists(slao);
        customer.showLists(cao);


//        System.out.println("고른 상품:");
//        customer.printCartItem(customer.searchCart(cao, "A-123", "seller_2"));
    }
}
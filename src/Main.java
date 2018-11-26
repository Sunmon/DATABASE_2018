//import java.sql.*;
import Test.*;
import View.VLogin;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException
    {
        //Test package에 있는거 새로 테스트하는거.
        Test.Connector con = new Test.Connector("3306", "dbtest_1115", "sunmon", "computer");
        DAOFactory df = new DAOFactory(con.getCon());
        Test.DAOFactory cao = (CartDAO)df.setDAO("cart");
        Test.DAOFactory slao = (SellListDAO)df.setDAO("sellList");


        System.out.println();
        System.out.println();

        User user = con.login("sunmon", "1234");
        cao.initialize(user.getID());
        slao.initialize(user.getID());

        user.showLists(cao);





        /*User customer = new User();
        customer.setID("sunmon");
        customer.showLists(slao);

        System.out.println("cart List:");
        customer.showLists(cao);

        CartDTO c = new CartDTO("C-113", "sunmon", "seller_1", 2, 10000, "product2", 5000);
        customer.addList(cao, c );
        System.out.println();
        System.out.println();
        System.out.println("insert 후");
        customer.showLists(cao);
*/
    }
}
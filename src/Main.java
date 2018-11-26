//import java.sql.*;
import Test.*;
import View.VLogin;
import View.VMain;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException
    {
        //Connect to DB
        Test.Connector con = new Test.Connector("3306", "dbtest_1115", "sunmon", "computer");

//        con.findPW("sunmon", "010-4543-5364");


        //Login GUI
        VMain vm = new VMain();
        User user = vm.runVLogin(con);
        System.out.println("user logined!");                                               //NOTE: remove this println

        //initialize DTOs
        DAOFactory df = new DAOFactory(con.getCon());
        Test.DAOFactory cao = (CartDAO)df.setDAO("cart");
        Test.DAOFactory slao = (SellListDAO)df.setDAO("sellList");
        cao.initialize(user.getID());
        slao.initialize(user.getID());




        //show sellList
        vm.runVsellList(con, user);


/*
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
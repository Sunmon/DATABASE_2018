//import java.sql.*;
import model.*;
import view.VMain;

import java.sql.SQLException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws SQLException
    {
        //Connect to DB
        model.Connector con = new model.Connector("3306", "dbtest_1115", "sunmon", "computer");


        //Login GUI
        VMain vm = new VMain();
        User user = vm.runVLogin(con);


        //initialize DTOs
        DAOFactory df = new DAOFactory(con.getCon());
        model.DAOFactory cao = (CartDAO)df.setDAO("cart");
        model.DAOFactory slao = (SellListDAO)df.setDAO("sellList");
        cao.initialize(user.getID());
        slao.initialize(user.getID());


        //show main frame
        vm.runVMainFrame(con, user);





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
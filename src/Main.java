//import java.sql.*;
import model.*;
import view.VMain;

import java.sql.SQLException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws SQLException
    {
        //Connect to DB
//        model.Connector con = new model.Connector("3306", "test", "konkuk", "computer");
//        model.Connector con = new model.Connector("3306", "Unnamed", "root", "2944");
        model.Connector con = new model.Connector("3306", "dbtest_1115", "sunmon", "computer");

        //Login GUI
        VMain vm = new VMain();
        User user = vm.runVLogin(con);


        //initialize DTOs


        DAOFactory df = new DAOFactory(con.getCon());
        model.DAOFactory slao = (SellListDAO)df.setDAO("sellList");
//        model.DAOFactory cao = (CartDAO)df.setDAO("cart");                    //vm에서 cartDao만들거.
//        cao.initialize(user.getID());
        slao.initialize(user.getID());          //GUI에서 cart누를때마다 할거면 필요없음



        //show main frame
        vm.runVMainFrame(con,user, df);







      /*  CartDTO c = new CartDTO("C-113", "sunmon", "seller_1", 2, 10000, "product2", 5000);
        CartDTO cc = new CartDTO("C-223", "sunmon", "seller_1", 2, 10000, "product2", 5000);

        ArrayList<CartDTO> a = new ArrayList<CartDTO>();
        ArrayList<CartDTO> b = new ArrayList<CartDTO>();

        a.add(c);
        a.add(cc);

        CartDTO d = a.get(0);
        d.setP_code("바꿨다!");

        System.out.println(a.get(0).getP_code());*/



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
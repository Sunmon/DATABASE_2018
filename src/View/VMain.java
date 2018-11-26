package View;

import Test.CartDAO;
import Test.DAOFactory;
import Test.SellListDAO;
import Test.User;

import javax.swing.*;
import java.sql.SQLException;

public class VMain
{

    public static void main(String[] args)
    {
        //Connector con 연결
        Test.Connector con = new Test.Connector("3306", "dbtest_1115", "sunmon", "computer");
        DAOFactory df = new DAOFactory(con.getCon());
        Test.DAOFactory cao = (CartDAO)df.setDAO("cart");
        Test.DAOFactory slao = (SellListDAO)df.setDAO("sellList");



        JFrame frame = new JFrame("VLogin");
        VLogin vlogin = new VLogin(con);
        frame.setContentPane(vlogin.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        User user = vlogin.getUser();

        //FIXME: 화면이 안 사라짐..
        if(vlogin.getUser() != null)
        {
            frame.setVisible(false);    //안보이게 함
            frame.dispose();            //login frame 삭제
        }
    }
}

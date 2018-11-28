package view;
import model.*;
import javax.swing.*;
import java.awt.*;

public class VMain
{   //controller 역할
    Dimension d = new Dimension(414, 736);
    public void main(Connector con)
    {

    }


    public User runVLogin(Connector con)
    {   //login해서 맞는 user 객체 리턴
        JFrame frame = new JFrame("VLogin");
        VLogin vlogin = new VLogin(con);
        frame.setContentPane(vlogin.panel1);
        frame.setPreferredSize(d);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        synchronized (vlogin.getLoginButton())
        {   //vlogin의 login button과 sync
            try
            {   //login버튼 눌러서 제대로 로그인 될 때까지 기다림
                vlogin.getLoginButton().wait();
                System.out.println("button clicked");                                       //NOTE: remove this println
                frame.setVisible(false);
                frame.dispose();
                return vlogin.getUser();
            } catch (InterruptedException e)
            {
                e.printStackTrace();
                return null;
            }
        }
    }





    public void runVsellList(Connector con, User user)
    {
        JFrame frame = new JFrame("VSellList");
        VSellList vs = new VSellList();
        frame.setContentPane(vs.panel1);
        frame.setPreferredSize(d);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


}

package view;
import model.*;
import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class VMain
{   //controller 역할
    Dimension d = new Dimension(414, 736);
    VMainFrame vfm;
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

    public void runVMainFrame(Connector con, User user, DAOFactory dao)
    {   //mainFrame 실행

        //CartDAO 생성 & 초기화
        model.DAOFactory cao = dao.setDAO("cart");
        cao.initialize(user.getID());


        //mainFrame 실행
        vfm = new VMainFrame(con, user);
        vfm.setVisible(true);

        //버튼 누를때마다 새로운 화면 띄워주는 레이아웃 : cardLayout
        CardLayout cards = new CardLayout();
        vfm.getShowPanel().setLayout(cards);

        //card들(화면) 추가
        vfm.getShowPanel().add("home", vfm.getHomePanel());
        vfm.getShowPanel().add("favor", vfm.getFavoritePanel());
        vfm.getShowPanel().add("cart", vfm.getCartPanel());
        vfm.getShowPanel().add("mypg", vfm.getMypagePanel());


        //button 이벤트 설정. 해당 버튼에 따라 화면을 띄워준다.
        vfm.getHomeButton().addActionListener(e->cards.show(vfm.getShowPanel(), "home"));
        vfm.getFavoriteButton().addActionListener(e->cards.show(vfm.getFavoritePanel(), "favor"));
//        vfm.getCartButton().addActionListener(e->cards.show(vfm.getShowPanel(), "cart"));
        vfm.getCartButton().addActionListener(e->
        {
            try
            {
                showCartPage(user, (CartDAO)cao, cards);
            } catch (SQLException e1)
            {
                e1.printStackTrace();
            }
        });
//        vfm.getMypageButton().addActionListener(e->cards.show(vfm.getShowPanel(), "mypg"));
        vfm.getMypageButton().addActionListener(e->showMyPage(user,con,cards));


        //VFavorite는 아직 안만들음 ... 만들어서 VMainFrame.form.FavoritePanel에 추가해야 함.




    }

    public void showCartPage(User user, CartDAO cao, CardLayout cards) throws SQLException
    {   //cart버튼 누르면 cart page를 보여준다.
        cards.show(vfm.getShowPanel(), "cart");
        vfm.getVcart().initTable(user, cao);
    }

    public void showMyPage(User user, Connector con, CardLayout cards)
    {   //mypage보여줌
        cards.show(vfm.getShowPanel(), "mypg");
        vfm.getVmpg().initTable(user, con);

    }


    public void setButtonsTransparent()
    {   // 버튼 내용 투명하게 만들기 (나중에 쓸데가 있겠지)
        //투명 test

        //home Button
        vfm.getHomeButton().setOpaque(false);
        vfm.getHomeButton().setContentAreaFilled(false);
        vfm.getHomeButton().setBorderPainted(false);
        vfm.getHomeButton().setText("    ");

        //favorite Button
        vfm.getFavoriteButton().setOpaque(false);
        vfm.getFavoriteButton().setContentAreaFilled(false);
        vfm.getFavoriteButton().setBorderPainted(false);
        vfm.getFavoriteButton().setText("    ");

        //cart Button
        vfm.getCartButton().setOpaque(false);
        vfm.getCartButton().setContentAreaFilled(false);
        vfm.getCartButton().setBorderPainted(false);
        vfm.getCartButton().setText("    ");

        //mypage Button
        vfm.getMypageButton().setOpaque(false);
        vfm.getMypageButton().setContentAreaFilled(false);
        vfm.getMypageButton().setBorderPainted(false);
        vfm.getMypageButton().setText("    ");

    }




/*

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
*/


}

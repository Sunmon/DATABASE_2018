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

        //SellListDAO 생성 & 초기화
        model.DAOFactory sao = dao.setDAO("sellList");
        sao.initialize(user.getID());

        //Favorite 생성
        model.DAOFactory fao = dao.setDAO("favorites");
//        fao.initialize(user.getID());


        //mainFrame 실행
        vfm = new VMainFrame(con, user);
        vfm.setVisible(true);

        //버튼 누를때마다 새로운 화면 띄워주는 레이아웃 : cardLayout
        CardLayout cards = (CardLayout)vfm.getShowPanel().getLayout();

        //card들(화면) 추가
/*

        cards.addLayoutComponent("sell", vfm.getSellListPanel());
        cards.addLayoutComponent("favor", vfm.getFavoritePanel());
        cards.addLayoutComponent("cart", vfm.getCartPanel());
        cards.addLayoutComponent("mypg", vfm.getMypagePanel());
*/


        /*
        vfm.getShowPanel().add("sell", vfm.getSellListPanel());
        vfm.getShowPanel().add("favor", vfm.getFavoritePanel());
        vfm.getShowPanel().add("cart", vfm.getCartPanel());
        vfm.getShowPanel().add("mypg", vfm.getMypagePanel());*/

        //기본홈화면으로 sellList띄워준다
        showSellListPage(user, (SellListDAO)sao, (CartDAO)cao, con, cards);





        //button 이벤트 설정. 해당 버튼에 따라 화면을 띄워준다.
        vfm.getSellListButton().addActionListener(e->showSellListPage(user, (SellListDAO)sao, (CartDAO)cao, con, cards));
        vfm.getFavoriteButton().addActionListener(e->
        {
            try
            {
                showFavorPage(user, (CartDAO)cao, (favoriteDAO)fao, con, cards);
            } catch (SQLException e1)
            {
                e1.printStackTrace();
            }
        });
        vfm.getCartButton().addActionListener(e->
        {
            try
            {
                showCartPage(user, (CartDAO)cao, (SellListDAO)sao, con, cards);

            } catch (SQLException e1)
            {
                e1.printStackTrace();
            }
        });
        vfm.getMypageButton().addActionListener(e->showMyPage(user,con,cards));




    }

    public void showCartPage(User user, CartDAO cao, SellListDAO sao, Connector con, CardLayout cards) throws SQLException
    {   //cart버튼 누르면 cart page를 보여준다.
        cards.show(vfm.getShowPanel(), "Card3");

        //띄울 내용 초기화
        vfm.getVcart().initTable(user, cao, sao, con);

        // remove from cart button
        vfm.getVcart().getRemoveButton().addActionListener(e->
        {
            try
            {
                vfm.getVcart().removeItems(user, cao, con);
            } catch (SQLException e1)
            {


            }
        });

    }

    public void showMyPage(User user, Connector con, CardLayout cards)
    {   //mypage보여줌
        cards.show(vfm.getShowPanel(), "Card4");
        vfm.getVmpg().initTable(user, con);

    }


    public void showSellListPage(User user, SellListDAO sao, CartDAO cao, Connector con, CardLayout cards)
    {   //sellList page 보여줌
        cards.show(vfm.getShowPanel(), "Card1");


        vfm.getVsell().initTable(user, sao);

        vfm.getVsell().getAddCartButton().addActionListener(e ->
        {
            String msg = vfm.getVsell().addItems(user,sao,cao);
//            JOptionPane.showMessageDialog(vfm.getShowPanel(), msg);


        });

    }


    public void showFavorPage(User user, CartDAO cao, favoriteDAO fao, Connector con, CardLayout cards) throws SQLException
    {   //favor page 보여줌
        cards.show(vfm.getShowPanel(), "Card2");
        vfm.getVfavor().initTable(user, fao);

        //buy (add to cart) button
        vfm.getVfavor().getBuybutton().addActionListener(e ->
        {
            try
            {
                vfm.getVfavor().deliverToCart(user,cao,fao,con);
            } catch (SQLException e1)
            {
                e1.printStackTrace();
            }
        });



     /*   //delete button
        vfm.getVfavor().getDeletebutton().addActionListener(e ->
        vfm.getVfavor().);*/


    }



    public void setButtonsTransparent()
    {   // 버튼 내용 투명하게 만들기 (나중에 쓸데가 있겠지)
        //투명 test

        //sell Button
        vfm.getSellListButton().setOpaque(false);
        vfm.getSellListButton().setContentAreaFilled(false);
        vfm.getSellListButton().setBorderPainted(false);
        vfm.getSellListButton().setText("    ");

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

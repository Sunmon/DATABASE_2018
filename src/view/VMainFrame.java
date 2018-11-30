package view;

import model.Connector;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VMainFrame extends JFrame
{   //메뉴바가 있는 메인 프레임

    static Dimension d = new Dimension(414, 736);
    private JPanel mainPanel;
    private JButton homeButton;
    private JButton favoriteButton;
    private JButton cartButton;
    private JButton mypageButton;
    private JPanel showPanel;
    private JPanel homePanel;
    private JPanel favoritePanel;
    private JPanel cartPanel;
    private JPanel mypagePanel;
    private VSellList vsell;
    private VCart vcart;



    private VMypage vmpg;
    private VFavorite vfavor;

    public VMainFrame(Connector con, User user)
    {
        add(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(d);
    }


    public JPanel getMainPanel()
    {
        return mainPanel;
    }

    public void setMainPanel(JPanel mainPanel)
    {
        this.mainPanel = mainPanel;
    }

    public JButton getHomeButton()
    {
        return homeButton;
    }

    public void setHomeButton(JButton homeButton)
    {
        this.homeButton = homeButton;
    }

    public JButton getFavoriteButton()
    {
        return favoriteButton;
    }

    public void setFavoriteButton(JButton favoriteButton)
    {
        this.favoriteButton = favoriteButton;
    }

    public JButton getCartButton()
    {
        return cartButton;
    }

    public void setCartButton(JButton cartButton)
    {
        this.cartButton = cartButton;
    }

    public JButton getMypageButton()
    {
        return mypageButton;
    }

    public void setMypageButton(JButton mypageButton)
    {
        this.mypageButton = mypageButton;
    }

    public JPanel getShowPanel()
    {
        return showPanel;
    }

    public void setShowPanel(JPanel showPanel)
    {
        this.showPanel = showPanel;
    }

    public JPanel getHomePanel()
    {
        return homePanel;
    }

    public void setHomePanel(JPanel homePanel)
    {
        this.homePanel = homePanel;
    }

    public JPanel getFavoritePanel()
    {
        return favoritePanel;
    }

    public void setFavoritePanel(JPanel favoritePanel)
    {
        this.favoritePanel = favoritePanel;
    }

    public JPanel getCartPanel()
    {
        return cartPanel;
    }

    public void setCartPanel(JPanel cartPanel)
    {
        this.cartPanel = cartPanel;
    }

    public JPanel getMypagePanel()
    {
        return mypagePanel;
    }

    public void setMypagePanel(JPanel mypagePanel)
    {
        this.mypagePanel = mypagePanel;
    }


    public VSellList getVsell()
    {
        return vsell;
    }

    public void setVsell(VSellList vsell)
    {
        this.vsell = vsell;
    }

    public VCart getVcart()
    {
        return vcart;
    }

    public void setVcart(VCart vcart)
    {
        this.vcart = vcart;
    }

    public VMypage getVmpg()
    {
        return vmpg;
    }

    public void setVmpg(VMypage vmpg)
    {
        this.vmpg = vmpg;
    }

    public VFavorite getVfavor()
    {
        return vfavor;
    }

    public void setVfavor(VFavorite vfavor)
    {
        this.vfavor = vfavor;
    }
}

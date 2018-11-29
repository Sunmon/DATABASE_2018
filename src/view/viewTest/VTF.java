package view.viewTest;

import javax.swing.*;
import java.awt.*;

public class VTF extends JFrame
{
    static Dimension d = new Dimension(414, 736);

    private JPanel mainPanel;
    private JButton button1;
    private JButton button2;
    private JPanel cardPanel;
    private JPanel c0;
    private JPanel c1;
    private VC3_Class vc3_notContainer;
    private JPanel c3;
    private VC3_Class vc3_conainer;
    private VC2_Form vc2_container;
    private JPanel c4;
    private VC2_Form vc2_notContainer;
    private JPanel end;

    public VTF()
    {
        add(mainPanel);
//        setPreferredSize(d);
        setSize(d);

    }





    public static Dimension getD()
    {
        return d;
    }

    public static void setD(Dimension d)
    {
        VTF.d = d;
    }

    public JPanel getMainPanel()
    {
        return mainPanel;
    }

    public void setMainPanel(JPanel mainPanel)
    {
        this.mainPanel = mainPanel;
    }

    public JButton getButton1()
    {
        return button1;
    }

    public void setButton1(JButton button1)
    {
        this.button1 = button1;
    }

    public JButton getButton2()
    {
        return button2;
    }

    public void setButton2(JButton button2)
    {
        this.button2 = button2;
    }

    public JPanel getCardPanel()
    {
        return cardPanel;
    }

    public void setCardPanel(JPanel cardPanel)
    {
        this.cardPanel = cardPanel;
    }

    public JPanel getC0()
    {
        return c0;
    }

    public void setC0(JPanel c0)
    {
        this.c0 = c0;
    }

    public JPanel getC1()
    {
        return c1;
    }

    public void setC1(JPanel c1)
    {
        this.c1 = c1;
    }

    public VC3_Class getVc3_notContainer()
    {
        return vc3_notContainer;
    }

    public void setVc3_notContainer(VC3_Class vc3_notContainer)
    {
        this.vc3_notContainer = vc3_notContainer;
    }

    public JPanel getC3()
    {
        return c3;
    }

    public void setC3(JPanel c3)
    {
        this.c3 = c3;
    }

    public VC3_Class getVc3_conainer()
    {
        return vc3_conainer;
    }

    public void setVc3_conainer(VC3_Class vc3_conainer)
    {
        this.vc3_conainer = vc3_conainer;
    }

    public VC2_Form getVc2_container()
    {
        return vc2_container;
    }

    public void setVc2_container(VC2_Form vc2_container)
    {
        this.vc2_container = vc2_container;
    }

    public JPanel getC4()
    {
        return c4;
    }

    public void setC4(JPanel c4)
    {
        this.c4 = c4;
    }

    public VC2_Form getVc2_notContainer()
    {
        return vc2_notContainer;
    }

    public void setVc2_notContainer(VC2_Form vc2_notContainer)
    {
        this.vc2_notContainer = vc2_notContainer;
    }

    public JPanel getEnd()
    {
        return end;
    }

    public void setEnd(JPanel end)
    {
        this.end = end;
    }
}

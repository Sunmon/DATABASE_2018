package view;

import javax.swing.*;

public class VCart extends JPanel
{
    private JPanel mainPanel;
    private JPanel tablePanel;
    private JTable table1;
    private JButton buyButton;
    private JButton removeButton;
    private JLabel totalTextLabel;
    private JLabel pointTextLabel;
    private JLabel totalLabel;
    private JLabel pointLable;

    public VCart()
    {
        add(mainPanel);
//        mainPanel.remove(tempPanel);    //temp 삭제했음. GUI form에서도 삭제하면 됨.
        setVisible(true);
    }
}

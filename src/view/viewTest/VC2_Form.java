package view.viewTest;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VC2_Form extends JPanel
{   //카트 테이블 예제 만드는중



    private JPanel jpanel1;
    private JLabel label1;
    private JTable table1;
    private JButton buyButton;
    private JButton removeButton;
    private JLabel tot_pointLabel;

    public VC2_Form()
    {
        add(jpanel1);
//        jpanel1.remove(label1);

    }


    public void initTable()
    {   //custom create해즈어야 한다.
        //NOTE: table 예제
        String tableCol[] = {"col1", "col2", "col3", "col4", "checkbox"};
        Object rowData[][] = {
                {1, "get", "from", "cartDTO", false},
                {2, "make", "isChecked", "in CartDTO attributes", true},
                {1, "get", "from", "cartDTO", false},
                {2, "make", "isChecked", "in CartDTO attributes", true},
                {1, "get", "from", "cartDTO", false},
                {2, "make", "isChecked", "in CartDTO attributes", true},
                {1, "get", "from", "cartDTO", false},
                {2, "make", "isChecked", "in CartDTO attributes", true},
                {1, "get", "from", "cartDTO", false},
                {2, "make", "isChecked", "in CartDTO attributes", true},
                {1, "get", "from", "cartDTO", false},
                {2, "make", "isChecked", "in CartDTO attributes", true},
                {1, "get", "from", "cartDTO", false},
                {2, "make", "isChecked", "in CartDTO attributes", true},
                {1, "get", "from", "cartDTO", false},
                {2, "make", "isChecked", "in CartDTO attributes", true},
                {1, "get", "from", "cartDTO", false},
                {2, "make", "isChecked", "in CartDTO attributes", true},
                {1, "get", "from", "cartDTO", false},
                {2, "make", "isChecked", "in CartDTO attributes", true},{1, "get", "from", "cartDTO", false},
                {2, "make", "isChecked", "in CartDTO attributes", true},{1, "get", "from", "cartDTO", false},
                {2, "make", "isChecked", "in CartDTO attributes", true},
                {1, "get", "from", "cartDTO", false},
                {1, "get", "from", "cartDTO", false},
                {1, "get", "from", "cartDTO", false},
                {1, "get", "from", "cartDTO", false},
                {1, "get", "from", "cartDTO", false},
                {1, "get", "from", "cartDTO", false},
                {1, "get", "from", "cartDTO", false},
                {1, "get", "from", "cartDTO", false},
                {1, "get", "from", "cartDTO", false},
                {2, "make", "isChecked", "in CartDTO attributes", true},
                {3, "just", "connect", "to DAOs", false}
        };

                        //Default DataModel 선언
        DefaultTableModel dtm = new DefaultTableModel(rowData,tableCol);

        //JTable에 담기
        table1 = new JTable(dtm);

        //scrollPane
        JScrollPane jp = new JScrollPane(table1);

        Dimension d = new Dimension(240, 240);
        //size조절
        table1.setSize(d);
        table1.setPreferredSize(d);
        table1.setPreferredScrollableViewportSize(d);
    }









    //getter and setter
    public JPanel getJpanel1()
    {
        return jpanel1;
    }

    public void setJpanel1(JPanel jpanel1)
    {
        this.jpanel1 = jpanel1;
    }

    public JLabel getLabel1()
    {
        return label1;
    }

    public void setLabel1(JLabel label1)
    {
        this.label1 = label1;
    }

    public JTable getTable1()
    {
        return table1;
    }

    public void setTable1(JTable table1)
    {
        this.table1 = table1;
    }

    public JButton getBuyButton()
    {
        return buyButton;
    }

    public void setBuyButton(JButton buyButton)
    {
        this.buyButton = buyButton;
    }

    public JButton getRemoveButton()
    {
        return removeButton;
    }

    public void setRemoveButton(JButton removeButton)
    {
        this.removeButton = removeButton;
    }

    public JLabel getTot_pointLabel()
    {
        return tot_pointLabel;
    }

    public void setTot_pointLabel(JLabel tot_pointLabel)
    {
        this.tot_pointLabel = tot_pointLabel;
    }

    private void createUIComponents()
    {
        // TODO: place custom component creation code here
        initTable();

    }
}

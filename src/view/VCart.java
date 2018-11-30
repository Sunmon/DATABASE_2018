package view;

import model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.ArrayList;

public class VCart extends JPanel
{
    private JPanel mainPanel;
    private JTable cTable;
    private JButton buyButton;
    private JButton removeButton;
    private JLabel totalTextLabel;
    private JLabel pointTextLabel;
    private JLabel totalLabel;
    private JLabel pointLable;
    private JScrollPane tablePane;

    public VCart()
    {
        add(mainPanel);
        setVisible(true);
    }


    public VCart(User user, Connector con, DAOFactory dao){}


    //...Table(view)에 띄울 데이터 설정
    void initTable(User user, CartDAO cao) throws SQLException
    {
        //Column을 cartDAO에서 가져온다
        String col[] = cao.getAttributes();          //"p_nick", "p_count", "tot_price", "p_code", "seller_ID"

        //DefaultTableModel 생성
        DefaultTableModel dtm = new DefaultTableModel(col, 0);
        dtm.addColumn("check");

        //DefaultTableModel에 값 넣기
        for(CartDTO c : cao.getDtoList())
        {
            Object[] o = {c.getP_nick(), c.getP_count(), c.getTot_price(), c.getP_code(), c.getSeller_ID(), Boolean.TRUE};
            dtm.addRow(o);
        }

       ///JTable에 담기
        cTable.setModel(dtm);
        dtm.fireTableDataChanged();


        //혹시 몰라서 넣어놓음
//        cTable = new JTable(dtm);

        //혹시 몰라서 넣어놓음
        repaint();
        revalidate();

        /* 혹시 몰라서 넣어놓음
        //scrollPane
        tablePane = new JScrollPane(cTable);*/
    }

}

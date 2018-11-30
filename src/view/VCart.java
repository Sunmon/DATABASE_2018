package view;

import com.sun.org.apache.xpath.internal.operations.Bool;
import model.*;

import javax.swing.*;
import javax.swing.event.TableColumnModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
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
        DefaultTableModel dtm = new DefaultTableModel(col, 0){

            //테이블 열 속성 설정 (체크박스도 여기서 설정)
            @Override
            public Class getColumnClass(int column) {
                switch(column)
                {
                    case 0: return String.class;
                    case 1: return Integer.class;
                    case 2: return Integer.class;
                    case 3: return String.class;
                    case 4: return String.class;
                    default: return Boolean.class;
                }
            }


            //사용자가 체크박스, 개수만 수정할 수 있게 만들기
            @Override
            public boolean isCellEditable(int row, int col)
            {
                if(col == 1 || col == 5) return true;
                else return false;
            }




        };

        //체크 열 추가
        dtm.addColumn("check");


        //DefaultTableModel에 값 넣기
        for(CartDTO c : cao.getDtoList())
        {
            Object[] o = {c.getP_nick(), c.getP_count(), c.getTot_price(), c.getP_code(), c.getSeller_ID(), false};
            dtm.addRow(o);
        }

        ///JTable에 담기
        cTable.setModel(dtm);
        dtm.fireTableDataChanged();


        //혹시 몰라서 넣어놓음
//        cTable = new JTable(dtm);


        //특정 셀 갱신하기
        //fireTableCellUpdated (row,col);

        //혹시 몰라서 넣어놓음
        repaint();
        revalidate();

        /* 혹시 몰라서 넣어놓음
        //scrollPane
        tablePane = new JScrollPane(cTable);*/
    }






    public JLabel getTotalLabel()
    {
        return totalLabel;
    }

    public void setTotalLabel(JLabel totalLabel)
    {
        this.totalLabel = totalLabel;
    }

    public JLabel getPointLable()
    {
        return pointLable;
    }

    public void setPointLable(JLabel pointLable)
    {
        this.pointLable = pointLable;
    }


}

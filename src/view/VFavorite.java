package view;

import model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;

public class VFavorite extends JPanel {
    private JPanel mainPanel;
    private JButton buybutton;
    private JButton deletebutton;
    private JPanel tablepanel;
    private JTable table1;

    public VFavorite()
    {
        add(mainPanel);
        setVisible(true);
    }

    public VFavorite(User user, Connector con, DAOFactory dao){}

    void initTable(User user, favoriteDAO cao) throws SQLException {
        //Column을 cartDAO에서 가져온다
        String col[] = cao.getAttributes();          //"p_nickname", "p_code", "seller_ID", "price"

        //DefaultTableModel 생성
        DefaultTableModel dtm = new DefaultTableModel(col, 0){

            public Class getColumnClass(int column) {
                switch(column)
                {
                    case 0: return String.class;
                    case 1: return String.class;
                    case 2: return String.class;
                    case 3: return Integer.class;
                    default: return Boolean.class;
                }
            }


            //사용자가 체크박스, 개수만 수정할 수 있게 만들기
            @Override
            public boolean isCellEditable(int row, int col)
            {
                if(col == 4) return true;
                else return false;
            }


        };



        dtm.addColumn("check");

        //DefaultTableModel에 값 넣기
        for (FavoriteDTO c : cao.getDtoList()) {
            Object[] o = {c.getP_nick(), c.getP_code(), c.getSeller_ID(), c.getprice(), Boolean.TRUE};
            dtm.addRow(o);
        }
        table1.setModel(dtm);
    }



}

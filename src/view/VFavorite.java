package view;

import model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;

public class VFavorite extends JPanel {
    private JPanel mainPanel;



    private JButton buybutton;
    private JButton deletebutton;
    private JScrollPane favorPanel;
    private JTable fTable;

    public VFavorite()
    {
        add(mainPanel);
        setVisible(true);
    }

    public VFavorite(User user, Connector con, DAOFactory dao){}

    void initTable(User user, favoriteDAO cao) throws SQLException {

        cao.initialize(user.getID());

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
            Object[] o = {c.getP_nick(), c.getP_code(), c.getSeller_ID(), c.getprice(), Boolean.FALSE};
            dtm.addRow(o);
        }
        fTable.setModel(dtm);
    }


    public void deliverToCart(User user, CartDAO cao, favoriteDAO fao, Connector con) throws SQLException
    {
        int row=-1;

        user.initTempArrayList();

        while(++row < fTable.getRowCount())
        {
            //체크박스에 체크한 아이템들 임시리스트에 더해줌
            if((Boolean) fTable.getValueAt(row, 4))
            {//"p_nickname", "p_code", "seller_ID", "price"0123
                //primary key
                String nick=(String) fTable.getValueAt(row,0);
                String pc = (String) fTable.getValueAt(row, 1);
                String sid = (String) fTable.getValueAt(row, 2);
                int price =(int) fTable.getValueAt(row, 3);
                //favorite>임시 favoite
                user.addList(user.gettempFavor(), fao.getDtoList().get(row));
                CartDTO ct=user.makeCartDTO(pc,sid,1,price,nick);
//                user.addList(user.getTempCart(), ct);
                cao.insert(ct);
            }
        }


    }

    //TODO: delete 메소드 만들기!!!!!!!!!!!!  :: 세웅.


    public JButton getBuybutton()
    {
        return buybutton;
    }

    public JButton getDeletebutton()
    {
        return deletebutton;
    }


}

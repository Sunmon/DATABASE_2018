package view;

import model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class VSellList extends JPanel
{
    private JPanel mainPanel;
    private JScrollPane sTablePanel;
    private JTable sTable;
    private JComboBox searchCombo;
    private JTextField searchTextField;
    private JButton addCartButton;
    private JButton addFavoriteButton;
    private JButton searchButton;
    private JLabel orderLabel;
    private JPanel orderPanel;
    private JRadioButton priceRadioButton;
    private JRadioButton nameRadioButton;

    public VSellList()
    {
        add(mainPanel);
        setVisible(true);
    }

    //JTable(view)에 띄울 데이터 설정
    void initTable(User user, SellListDAO sao)
    {
        //column을 sellList DAO에서 가져온다
        String col[] =   sao.getAttributes();       //"p_code", "seller_ID", "price", "stock", "size", "p_nickname"

        //DefaultTableModel 생성
        DefaultTableModel dtm = new DefaultTableModel(col, 0)
        {
            //테이블 열 속성 설정(+체크박스 설정)
            @Override
            public Class getColumnClass(int column) {
                switch(column)
                {
                    case 0: return String.class;
                    case 1: return String.class;
                    case 2: return Integer.class;
                    case 3: return Integer.class;
                    case 4: return String.class;
                    case 5: return String.class;
                    default: return Boolean.class;
                }
            }
/*
            //사용자가 체크박스만 수정할 수 있게 만들기
            @Override
            public boolean isCellEditable(int row, int col)
            {
                if(col == 6) return true;
                else return false;
            }*/

        };

        //체크 열 추가
        dtm.addColumn("check");

        //DefaultTableModel에 값 넣기
        for(SellListDTO s : sao.getDtoList())
        {
            Object[] o = {s.getP_code(), s.getSeller_ID(), s.getPrice(), s.getStock(), s.getSize(), s.getP_nickname()};
            dtm.addRow(o);
        }

        ///JTable에 담기
        sTable.setModel(dtm);
        dtm.fireTableDataChanged();

        //특정 셀 갱신하기
        //fireTableCellUpdated (row,col);

        //혹시 몰라서 넣어놓음
        repaint();
        revalidate();

    }


    //카트에 Item 추가  ...메소드 오버로딩
    public void addItems(User user, SellListDAO sao, CartDAO cao)
    {
        //임시 리스트 초기화
        user.initTempArrayList();
        int row = -1;

        //자주쓸거같아서 변수로 설정
        SellListDTO s = null;
        CartDTO c = null;

        while(++row < sTable.getRowCount())
        {
            //체크박스 체크한 아이템들 임시리스트에 추가
            if(!(Boolean)sTable.getValueAt(row, 6)) continue;
            s = sao.getDtoList().get(row);
            c = user.makeCartDTO(s.getP_code(), s.getSeller_ID(), 1, s.getPrice(), s.getP_nickname());
            user.addList(user.getTempSell(), s);
            user.addList(user.getTempCart(), c);
        }


        //임시리스트에 추가한 것들 DB에 추가


    }

    //즐겨찾기에 Item 추가 ... 메소드 오버로딩
    public void addItems(User user, SellListDAO sao, favoriteDAO fao)
    {

    }




}

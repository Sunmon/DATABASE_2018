package view;

import model.SellListDAO;
import model.SellListDTO;

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
    void initTable(SellListDAO sao)
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




}

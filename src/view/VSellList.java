package view;

import model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;

public class VSellList extends JPanel
{
    private JPanel mainPanel;
    private JScrollPane sTablePanel;
    private JTable sTable;
    private JComboBox searchCombo;
    private JTextField searchTextField;
    public JButton getAddCartButton()
    {
        return addCartButton;
    }
    private JButton addCartButton;
    private JButton addFavoriteButton;
    private JButton searchButton;
    private JLabel orderLabel;
    private JPanel orderPanel;
    private JRadioButton priceRadioButton;
    private JRadioButton nameRadioButton;



    User user;
    SellListDAO sao;
    CartDAO cao;
    favoriteDAO fao;
    Connector con;


    public VSellList()
    {
        add(mainPanel);
        setVisible(true);

        //add cart button
        addCartButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                addItems(user, sao, cao);
            }
        });


        addFavoriteButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                addItems(user, sao, fao);

            }
        });


        //이름순 정렬(닉네임)
        nameRadioButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
               sao.set_order("order by p_nickname");
               initTable(user,cao,sao,fao,con);
            }
        });


        //가격순 정렬
        priceRadioButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                sao.set_order("order by price");
                initTable(user,cao,sao,fao,con);
            }
        });
    }

    //JTable(view)에 띄울 데이터 설정
    void initTable(User user, CartDAO cao, SellListDAO sao, favoriteDAO fao, Connector con)
    {
        this.user = user;
        this.cao = cao;
        this.sao = sao;
        this.fao = fao;
        this.con = con;

        //init
        sao.initialize(user.getID());


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

        };

        //체크 열 추가
        dtm.addColumn("check");

        //DefaultTableModel에 값 넣기
        for(SellListDTO s : sao.getDtoList())
        {
            Object[] o = {s.getP_code(), s.getSeller_ID(), s.getPrice(), s.getStock(), s.getSize(), s.getP_nickname(),false};
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

        //검색기능 default 설정
        sao.set_order("");


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

        for(CartDTO cto : user.getTempCart())
        {
            cao.insert(cto);
        }
        String msg = "중복 제외하고 카트에 추가되었습니다.";
        JOptionPane.showMessageDialog(this, msg);
    }

    //즐겨찾기에 Item 추가 ... 메소드 오버로딩
    public void addItems(User user, SellListDAO sao, favoriteDAO fao)
    {
        //임시 리스트 초기화
        user.initTempArrayList();
        int row = -1;

        //자주쓸거같아서 변수로 설정
        SellListDTO s = null;
        FavoriteDTO f = null;

        while(++row < sTable.getRowCount())
        {
            //체크박스 체크한 아이템들 임시리스트에 추가
            if(!(Boolean)sTable.getValueAt(row, 6)) continue;
            s = sao.getDtoList().get(row);
            f = user.makeFavoriteDTO(s.getP_code(), s.getSeller_ID(), s.getP_nickname(), s.getPrice());
            user.addList(user.getTempSell(), s);
            user.addList(user.gettempFavor(), f);
        }


        //임시리스트에 추가한 것들 DB에 추가
        for(FavoriteDTO fto : user.gettempFavor())
        {
            fao.insert(fto);
        }

        String msg =  "중복 제외하고 즐겨찾기에 추가되었습니다.";
        JOptionPane.showMessageDialog(this, msg);

    }



    public JButton getAddFavoriteButton()
    {
        return addFavoriteButton;
    }




}

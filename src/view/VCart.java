package view;

import com.sun.org.apache.xpath.internal.operations.Bool;
import model.*;

import javax.swing.*;
import javax.swing.event.TableColumnModelListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.*;
import java.awt.*;
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


    User user;
    SellListDAO sao;
    CartDAO cao;
    Connector con;


    public VCart()
    {
        add(mainPanel);
        setVisible(true);

        buyButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    buyItems();
                } catch (SQLException e1)
                {
                    e1.printStackTrace();
                }
            }
        });
        removeButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                removeItems();
            }
        });
        /*cTable.addComponentListener(new ComponentAdapter()
        {
        });*/
    }


    public VCart(User user, Connector con, DAOFactory dao){}


    //...JTable(view)에 띄울 데이터 설정
    void initTable(User user, CartDAO cao, SellListDAO sao, Connector con) throws SQLException
    {
        this.user = user;
        this.cao = cao;
        this.sao = sao;
        this.con = con;


        //init
        cao.initialize(user.getID());

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
                if(col == 5) return true;
                if(col == 1 && !(boolean)getValueAt(row, 5) ) return true;
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

        //특정 셀 갱신하기
        //fireTableCellUpdated (row,col);

        //혹시 몰라서 넣어놓음
        repaint();
        revalidate();

        //체크박스 리스너 추가
        //FIXME: 체크박스 리스너 추가
        dtm.addTableModelListener(this::checkBoxChanged);

    }

    public void buyItems() throws SQLException
    {
        buyItems(this.user,this.cao, this.sao, this.con);
    }

    public void buyItems(User user, CartDAO cao, SellListDAO sao, Connector con) throws SQLException
    { //Buy Items 버튼 누르면 실행
        //선택한 아이템들 가격 계산
        int tpoint = Integer.parseInt(totalLabel.getText());             //선택한 아이템들의 총 가격
        int row = -1;

        //임시 리스트들 초기화
        user.initTempArrayList();

        while(++row < cTable.getRowCount())
        {
            //체크박스에 체크한 아이템들 임시리스트에 더해줌
            if((Boolean)cTable.getValueAt(row, 5))
            {
                //primary key
                String pc = (String)cTable.getValueAt(row, 3);
                String sid = (String)cTable.getValueAt(row, 4);

                //해당 cto객체, sao객체 임시 리스트에 추가
                user.addList(user.getTempCart(), cao.getDtoList().get(row));
                user.addList(user.getTempSell(), sao.select(con.getCon(), pc, sid));
            }
        }

        //user point와 비교
        if(user.getPoints() < tpoint)
        {
            JOptionPane.showMessageDialog(this, "포인트가 부족합니다.");
            return;
        }

        //구매
        user.buyItems(cao,sao,con);

        //cartList 테이블화면초기화
        initTable(user, cao, sao, con);

        //user point label 초기화
        pointLable.setText(Integer.toString(user.getPoints()));


        //alert
        String msg = "재고가 부족한 상품 제외하고 구매가 완료되었습니다.";
        JOptionPane.showMessageDialog(this, msg);
        totalLabel.setText("0");

        repaint();
    }


    public void setTotalPrice(int addPoint)
    {
        int temp = Integer.parseInt(totalLabel.getText());
        temp += addPoint;
        totalLabel.setText(Integer.toString(temp));
    }


    private void checkBoxChanged(TableModelEvent e)
    {
        int row = e.getFirstRow();
        int column = e.getColumn();
        if (column == 5)
        {
            TableModel model = (TableModel) e.getSource();
            String columnName = model.getColumnName(column);
            Boolean checked = (Boolean) model.getValueAt(row, column);

            //선택한 체크박스의 DTO객체 가져옴 (그대로 가져오는거임)
            CartDTO c = cao.getDtoList().get(row);

            if (checked)
            {
                System.out.println(columnName + ": " + true);


                int price = c.getP_price();
                int temPc = (int)model.getValueAt(row,1);
                c.setP_count(temPc);
                c.setTot_price(temPc * price);

                //화면에 변경된 가격 설정
                model.setValueAt(c.getTot_price(), row,2);

                //DB에 개수/가격 업데이트
                cao.updateCartDB(c);

                //총 가격 설정
                setTotalPrice(c.getTot_price());
            }

            else
            {
                //총 가격 설정
                setTotalPrice(-c.getTot_price());
            }

        }
    }

    public void removeItems()
    {
        try
        {
            removeItems(user, cao, con);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

    }

    //Cart DB에서 제외하기
    public void removeItems(User user, CartDAO cao, Connector con) throws SQLException
    {//Buy Items 버튼 누르면 실행.

        int row = -1;

        //임시 cartList 초기화
        user.initTempArrayList();

        while(++row < cTable.getRowCount())
        {
            //체크박스에 체크한애들 임시리스트에 더해줌
            if(!(Boolean)cTable.getValueAt(row, 5)) continue;
            user.addList(user.getTempCart(), cao.getDtoList().get(row));
        }

        //DB에서 삭제
        user.removeItems(cao,con);

        //cartList 테이블화면초기화
        initTable(user, cao, sao, con);
        repaint();

        JOptionPane.showMessageDialog(this, "상품이 카트에서 삭제되었습니다.");



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

    public JButton getBuyButton()
    {
        return buyButton;
    }



    public JButton getRemoveButton()
    {
        return removeButton;
    }



}

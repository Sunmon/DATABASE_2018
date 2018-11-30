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


    //...JTable(view)에 띄울 데이터 설정
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

        //특정 셀 갱신하기
        //fireTableCellUpdated (row,col);

        //혹시 몰라서 넣어놓음
        repaint();
        revalidate();

    }

    public void buyItems(User user, CartDAO cao, SellListDAO sao, Connector con) throws SQLException
    { //Buy Items 버튼 누르면 실행

        //선택한 아이템들 가격 계산
        int tpoint = 0;             //선택한 아이템들의 총 가격
        int row = -1;

        ArrayList<CartDTO> templ = new ArrayList<CartDTO>();    //임시로 만들음. 체크한것들 알라고.
        ArrayList<SellListDTO> temps = new ArrayList<SellListDTO>();
        while(++row < cTable.getRowCount())
        {

            //체크박스 아이템들 가격 더해줌
            if((Boolean)cTable.getValueAt(row, 5))
            {
                templ.add(cao.getDtoList().get(row));   //해당 cto객체 임시 리스트에 추가
                temps.add(sao.select(con.getCon(), (String)cTable.getValueAt(row, 3), (String)cTable.getValueAt(row, 4) ));
                tpoint+= (int)cTable.getValueAt(row, 2);
            }
        }

        //가격 더한것들 총 포인트에 표시
        totalLabel.setText(Integer.toString(tpoint)+"p");

        //user point와 비교
        if(user.getPoints() < tpoint)
        {
            JOptionPane.showMessageDialog(this, "포인트가 부족합니다.");
            return;
        }


        //재고 확인
        String errormsg = "재고가 부족한 상품 제외하고 구매가 완료되었습니다.";
        for(int i=0; i<templ.size(); i++)
        {
            if(temps.get(i).getStock() > templ.get(i).getP_count())
            {   //재고 충분하면 DB에서 삭제
                cao.delete(templ.get(i));
                int stock = temps.get(i).getStock();
                temps.get(i).setStock(stock-templ.get(i).getP_count());
                sao.update(temps.get(i));

                int totprice = templ.get(i).getTot_price();
                //user point 감소
                user.setPoints(user.getPoints()-totprice);
                con.updateUserPoint(user.getID(),-totprice);

                //seller point 증가
                con.updateUserPoint(temps.get(i).getSeller_ID(), totprice);

            }
        }
        //cartList 테이블화면초기화
        initTable(user, cao);

        //user point label 초기화
        pointLable.setText(Integer.toString(user.getPoints()));
        JOptionPane.showMessageDialog(this, errormsg);

        repaint();
        return;
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

    public void setBuyButton(JButton buyButton)
    {
        this.buyButton = buyButton;
    }


}

package view;
import model.Connector;
import model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class VMypage extends JPanel
{
    private JPanel mainPanel;
    private JScrollPane tablePanel;
    private JTable mTable;
    private JButton applyButton;
    private JButton pointButton;
    private JButton showLogButton;


    User user;
    Connector con;

    public VMypage()
    {
        add(mainPanel);
        setVisible(true);
        showLogButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                showLogs();
            }
        });
        applyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                applychanges();
            }
        });
        pointButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                VChargePoint.showVChargePoint(user, con);
                initTable(user, con);
            }
        });
    }

    private void applychanges()
    {
        try
        {
            applychanges(user,con);
            initTable(user, con);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

    }

    //...JTable(view)에 띄울 데이터 설정
    public void initTable(User user, Connector con)
    {
        this.user = user;
        this.con = con;

        //Column 설정
        String col[] = {" ", " "};
        Object[][] data = {{"ID", user.getID()},    //0
                {"PW", user.getPw()},               //1
                {"NAME", user.getName()},           //2
                {"AGE", user.getAge()},             //3
                {"GENDER", user.getGender()},       //4
                {"PHONE", user.getPhone()},         //5
                {"ADDRESS", user.getAddress()},     //6
                {"HEIGHT", user.getHeight()},      //7
                {"WEIGHT", user.getWeight()},       //8
                {"POINTS", user.getPoints()},       //9
                {"TYPE", user.getAuthority()}};     //10

        //DefaultTableModel 생성
        DefaultTableModel dtm = new DefaultTableModel(data, col)
        {
            //사용자가 수정할 수 있는 부분, 없는 부분 설정
            @Override
            public boolean isCellEditable(int row, int col)
            {
                if (col == 0) return false; //테이블 정보는 수정불가
                switch (row)
                {
                    case 1:
                    case 3:
                    case 4:
                    case 6:
                    case 7:
                    case 8:
                        return true;
                    default:
                        return false;
                }

            }
        };

        //JTable에 담기
        mTable.setModel(dtm);
        dtm.fireTableDataChanged();
    }


    private void showLogs()
    {
        //내 구매기록을 보여준다.
        try
        {
            VLog.showVLog(user,con);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }


    public void applychanges(User user, Connector con) throws SQLException {
        //get info from view
        String pn = (String) mTable.getValueAt(1, 1);       //oassword
        int age = Integer.parseInt(String.valueOf(mTable.getValueAt(3, 1)));
        String gen = (String) mTable.getValueAt(4, 1);
        String adr = (String) mTable.getValueAt(6, 1);
        int hei = Integer.parseInt(String.valueOf(mTable.getValueAt(7, 1)));
        int wei = Integer.parseInt(String.valueOf(mTable.getValueAt(8, 1)));

        //update user
        user.setPw(pn);
        user.setAge(age);
        user.setGender(gen);
        user.setAddress(adr);
        user.setHeight(hei);
        user.setWeight(wei);

        //update db
        con.updateMypage(pn, age, gen, adr, hei, wei, user.getID(), user.getPhone());
        JOptionPane.showMessageDialog(this, "회원정보가 수정되었습니다.");

    }
}


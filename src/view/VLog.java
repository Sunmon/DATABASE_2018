package view;
import model.Connector;
import model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class VLog extends JDialog
{
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JScrollPane lPanel;
    private JTable lTable;

    public VLog()
    {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {onOK();}
        });
    }

    //...JTable(view)에 띄울 데이터 설정
    public void initTable(User user, Connector con) throws SQLException
    {
        //init
        ResultSet rs = con.showLogs(user.getID());
        ResultSetMetaData rsmd = rs.getMetaData();

        //Column 설정
        String col[] = {"nick name", "p_code", "seller_ID", "points", "pay_time"};


        //DefaultTableModel 생성
        DefaultTableModel dtm = new DefaultTableModel(col, 0){
            //사용자가 아무것도 수정하지 못하게
            @Override
            public boolean isCellEditable(int row, int col)
            {
                return false;
            }
        };

        //DefaultTableModel에 값 넣기
        while(rs.next())
        {
            Object[] o = {rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getInt(4),
                    rs.getTimestamp(5)};
            dtm.addRow(o);
        }

        ///JTable에 담기
        lTable.setModel(dtm);
        dtm.fireTableDataChanged();

        //특정 셀 갱신하기
        //fireTableCellUpdated (row,col);

        //혹시 몰라서 넣어놓음
        repaint();
        revalidate();
    }

    private void onOK()
    {
        dispose();
    }

    public static void showVLog(User user, Connector con) throws SQLException
    {
        VLog dialog = new VLog();
        dialog.initTable(user, con);
        dialog.pack();
        dialog.setVisible(true);
    }
}

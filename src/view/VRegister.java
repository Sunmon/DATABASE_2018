package view;

import model.Connector;
import model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

public class VRegister extends JDialog
{
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JScrollPane tPanel;
    private JTable table1;

    Connector con;
    public VRegister()
    {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {onOK();}
        });

        buttonCancel.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {onCancel();}
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    public void initTable(Connector con)
    {
        this.con = con;
        String col[] = {" ", " "};
        Object[][] data = {{"ID", ""},    //0
                {"PW", ""},               //1
                {"NAME",""},           //2
                {"AGE", ""},             //3
                {"GENDER", ""},       //4
                {"PHONE",""},         //5
                {"ADDRESS", ""},     //6
                {"HEIGHT", ""},      //7
                {"WEIGHT", ""}};       //8

        //DefaultTableModel 생성
        DefaultTableModel dtm = new DefaultTableModel(data, col);

        //JTable에 담기
        table1.setModel(dtm);
        dtm.fireTableDataChanged();
    }

    private void onOK()
    {

        // add your code here
        if(!checkTextFields()) return;
        else{
            //데이터 가져오기
            String ID = (String) table1.getValueAt(0,1);
            String pw = (String) table1.getValueAt(1,1);
            String name = (String) table1.getValueAt(2,1);
            int age = Integer.parseInt((String)table1.getValueAt(3,1));
            String gender = (String) table1.getValueAt(4,1);
            String phone = (String) table1.getValueAt(5,1);
            String address = (String) table1.getValueAt(6,1);
            int height = Integer.parseInt((String)table1.getValueAt(7,1));
            int weight = Integer.parseInt((String)table1.getValueAt(8,1));
            int points = 0;
            String authority = "customers";

            //user 객체 생성
            User user = new User(ID, pw, name, age, gender, phone, address, height, weight, points, authority);
            if(!con.registerUser(user)) JOptionPane.showMessageDialog(this, "이미 가입된 ID 또는 핸드폰 번호입니다.");

            else{
                 JOptionPane.showMessageDialog(this, "가입이 완료되었습니다.");
                dispose();
            }
        }
    }

    private void onCancel()
    {
        // add your code here if necessary
        dispose();
    }


    public static void showVRegister(Connector con)
    {
        VRegister dialog = new VRegister();
        dialog.initTable(con);
        dialog.pack();
        dialog.setVisible(true);
    }

    //모든 필드를 다 채웠는지 확인.
    public boolean checkTextFields()
    {
        for(int i=0; i<table1.getRowCount(); i++)
        {
            if(table1.getValueAt(i,1).equals(""))
            {
                JOptionPane.showMessageDialog(this, "모든 필드를 채워 주십시오");
                return false;
            }
        }

        String gen = (String)(table1.getValueAt(4,1));
        if(gen.length() > 2 )
        {
            JOptionPane.showMessageDialog(this, "성별은 M/F/N중 하나로 입력해 주십시오");
            return false;
        }
        return true;
    }
}

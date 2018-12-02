package view;

import model.Connector;
import model.User;

import javax.swing.*;
import java.awt.event.*;

public class VChargePoint extends JDialog
{   //포인트 충전하는 다이얼로그

    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField pointField;
    private JLabel pointLabel;
    User user;
    Connector con;

    public VChargePoint()
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

    private void onOK()
    {
        // add your code here
        addPoints();
        JOptionPane.showMessageDialog(this, "포인트가 충전되었습니다.");
        dispose();
    }

    private void onCancel()
    {
        // add your code here if necessary
        dispose();
    }

    public static void showVChargePoint(User user, Connector con)
    {
        VChargePoint dialog = new VChargePoint();
        dialog.initUser(user, con);
        dialog.pack();
        dialog.setVisible(true);
    }

    public void addPoints()
    {
        int temp = Integer.parseInt(pointField.getText());
        user.setPoints(user.getPoints() + temp);
        con.updateUserPoint(user.getID(), temp);    //DB에 point +해줌
    }

    public void initUser(User user, Connector con)
    {
        this.user = user;
        this.con = con;
    }
}

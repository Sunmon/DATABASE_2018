package view;

import model.Connector;

import javax.swing.*;
import java.awt.event.*;

public class VFindPW extends JDialog
{
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField IDField;
    private JTextField phoneField;
    private JTextField 비밀번호찾기TextField;

    public VFindPW(Connector con, VLogin v)
    {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {onOK(con, v);}
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

    private void onOK(Connector con, VLogin v)
    {
        // add your code here
        dispose();
        JOptionPane.showMessageDialog(v.panel1, con.findPW(IDField.getText(), phoneField.getText()));
    }

    private void onCancel()
    {
        // add your code here if necessary
        dispose();
    }

/*    public static void main(String[] args)
    {
        VFindPW dialog = new VFindPW(Connector con);
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }*/
}

package View;

import Test.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VLogin
{
    JPanel panel1;
    private JTextField idField;
    private JLabel PWLabel;
    private JLabel IDLabel;
    private JPasswordField pwField;
    private JButton loginButton;
    private JButton registerButton;
    private JButton forgotPasswordButton;

    private boolean isLogined = false;
    User user = null;

    public VLogin(Connector con)
    {
        loginButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.out.println(panel1.getSize());
                user = con.login(idField.getText(), pwField.getText());
                if(user == null)
                    JOptionPane.showMessageDialog(null, "ID/PW를 다시 확인해 주십시오.");
                else
                {
                    isLogined = true;
                    VSellist vs = new VSellist();
                    JFrame frame = new JFrame("VSellist");
                    frame.setContentPane(vs.panel1);
                    frame.setPreferredSize(panel1.getSize());
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.pack();
                    frame.setVisible(true);

                }
            }
        });
    }




    public boolean getIsLogined()
    {
        return isLogined;


    }

    public User getUser()
    {
        return user;
    }





    public void setData(Connector data)
    {
        idField.setText(data.getId());
        pwField.setText(data.getPw());
    }

    public void getData(Connector data)
    {
        data.setId(idField.getText());
        data.setPw(pwField.getText());
    }

    public boolean isModified(Connector data)
    {
        if (idField.getText() != null ? !idField.getText().equals(data.getId()) : data.getId() != null) return true;
        if (pwField.getText() != null ? !pwField.getText().equals(data.getPw()) : data.getPw() != null) return true;
        return false;
    }



}

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
            {   //button 누를때까지 기다리기.
                synchronized (loginButton)
                {
                    tryLogin(con);
                }
            }
        });
    }


    //Login 시도
    public void tryLogin(Connector con)
    {
        user = con.login(idField.getText(), pwField.getText());
        if(user == null)
            JOptionPane.showMessageDialog(null, "ID/PW를 다시 확인해 주십시오.");
        else    //login이 제대로 되었을때 다음 메소드 실행
            loginButton.notify();
    }


    //Getters
    public JButton getLoginButton(){ return loginButton; }
    public User getUser()
    {
        return user;
    }

}

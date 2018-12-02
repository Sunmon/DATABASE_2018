package view;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VLogin {
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

    public VLogin(Connector con) {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {   //button 누를때까지 기다리기.
                synchronized (loginButton) {
                    tryLogin(con);
                }
            }
        });
        forgotPasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                VFindPW dialog = new VFindPW(con, VLogin.this);
                dialog.setLocationRelativeTo(panel1);                //dialog위치 설정.. 왜 이상한지 모르겠다.
                dialog.pack();
                dialog.setVisible(true);
            }
        });
        registerButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                VRegister.showVRegister(con);
            }
        });
    }

    //Login 시도
    public void tryLogin(Connector con) {
        user = con.login(idField.getText(), pwField.getText());
        if (user == null)
            JOptionPane.showMessageDialog(null, "ID/PW를 다시 확인해 주십시오.");
        else if(!user.getAuthority().equals("customers")) JOptionPane.showMessageDialog(null, "판매자/관리자 서비스는 준비 중입니다.");
        else    //login이 제대로 되었을때 다음 메소드 실행
            loginButton.notify();
    }


    //Getters
    public JButton getLoginButton() {
        return loginButton;
    }

    public User getUser() {
        return user;

    }

}
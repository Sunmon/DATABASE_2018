package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VTest
{
    private JPanel testPanel;
    private JButton testButton;


    public VTest()
    {
        testButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("HELLO");
            }
        });
    }
}

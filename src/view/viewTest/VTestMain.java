package view.viewTest;

import javax.swing.*;

public class VTestMain
{
    public static void main(String[] args)
    {
        VFrame vf = new VFrame();
        vf.setVisible(true);
        vf.getShowPanel().remove(vf.getLabel1());
//        vf.getContentPane().remove(vf.getShowPanel());
 /*       Vsell vs = new Vsell();


        vs.setVisible(true);
        vf.getContentPane().add(vs);*/


    }
}

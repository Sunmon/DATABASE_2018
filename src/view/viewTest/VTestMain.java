package view.viewTest;

import javax.swing.*;

public class VTestMain
{
    public static void main(String[] args)
    {
        VFrame vf = new VFrame();
        vf.setVisible(true);

//        NOTE: setContentPane해줘야 object들이 사라져도 모양 유지!! ??? 모름
        vf.getMenuPanel().grabFocus();
        vf.setContentPane(vf.getMenuPanel());

//        vf.getMenuPanel().remove(vf.getShowPanel());
        vf.getContentPane().remove(vf.getShowPanel());
//        vf.getMenuPanel().remove(vf.getShowPanel());




/*      NOTE: 패널 조작 focus
        //특정 패널을 조작하기 전에 focus해야한다!!! 안 그러면 오류남
        vf.getShowPanel().grabFocus();
        vf.getShowPanel().remove(vf.getLabel1());
*/


//        vf.getContentPane().remove(vf.getShowPanel());
        Vsell vs = new Vsell();
        vf.getMenuPanel().add(vs);
//        vf.getContentPane().add(vs);



    }
}

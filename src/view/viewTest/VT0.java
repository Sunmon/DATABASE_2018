package view.viewTest;

import javax.swing.*;
import java.awt.*;

public class VT0
{
    static Dimension d = new Dimension(414, 736);

    public static void main(String[] args)
    {
        VTF vtf = new VTF();
//        vtf.setPreferredSize(d);
        vtf.setVisible(true);

        CardLayout cards = new CardLayout();
        vtf.getCardPanel().setLayout(cards);
        vtf.getButton1().addActionListener(e -> cards.next(vtf.getCardPanel()));

        vtf.getCardPanel().add("c0", vtf.getC0());
        vtf.getCardPanel().add("c1", vtf.getC1());
        vtf.getCardPanel().add("c3", vtf.getC3());
        vtf.getCardPanel().add("c4", vtf.getC4());
        vtf.getCardPanel().add("end",vtf.getEnd());

        cards.show(vtf.getCardPanel(), "end");







    }
}

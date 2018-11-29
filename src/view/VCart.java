package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class VCart extends JPanel
{
    private JPanel mainPanel;
    private JTable cTable;
    private JButton buyButton;
    private JButton removeButton;
    private JLabel totalTextLabel;
    private JLabel pointTextLabel;
    private JLabel totalLabel;
    private JLabel pointLable;
    private JScrollPane tablePane;

    public VCart()
    {
        add(mainPanel);
        setVisible(true);
    }

/* TODO:

    void initTable()
    {

        String tableCol[] = {"col1", "col2", "col3", "col4", "checkbox"};
        Object rowData[][] = {
                {1, "get", "from",
                        //Default DataModel 선언
                        DefaultTableModel dtm = new DefaultTableModel(rowData,tableCol);
        cTable = new JTable()

    }
*/



}

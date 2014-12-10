package pl.edu.amu.dji.jms.lab4;

import pl.edu.amu.dji.jms.lab4.utils.Swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PointOfSalesUI extends JFrame{
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JButton newButton;
    private JButton closeButton;

    public PointOfSalesUI(){
        super("Point of Sales");

        newButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PointOfSalesCreator posc = new PointOfSalesCreator();
                tabbedPane1.addTab("ID:" + posc.getPointOfSales().getId(), posc.getPointOfSalesPane());
                tabbedPane1.setSelectedIndex(tabbedPane1.getTabCount() - 1);
            }
        });

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tabbedPane1.getTabCount() > 0) {
                    int currentTab = tabbedPane1.getSelectedIndex();
                    tabbedPane1.remove(currentTab);
                }
            }
        });

        add(panel1);
        pack();
        setMinimumSize(new Dimension(640, 480));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Swing.centerJFrame(this);
        setVisible(true);
    }
}

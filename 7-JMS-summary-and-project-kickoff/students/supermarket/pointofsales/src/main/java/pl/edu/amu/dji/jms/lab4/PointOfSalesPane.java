package pl.edu.amu.dji.jms.lab4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**textAr
 * Created by kasztan on 08.12.14.
 */
public class PointOfSalesPane extends JPanel{
    private JButton sellButton;
    private JPanel rootPane;
    private JTextArea systemLog;
    private JList<Item> productList;

    private final PointOfSales pointOfSales;

    public PointOfSalesPane(final PointOfSales pointOfSales) {
        this.pointOfSales = pointOfSales;
        setLayout(new GridLayout(1,1));
        add(rootPane);

        systemLog.setEditable(false);

        sellButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Item selectedItem = productList.getSelectedValue();
                if(selectedItem != null) {
                    pointOfSales.sellItem(selectedItem);
                }
            }
        });
    }

    public void log(String message){
        systemLog.append(message + "\r\n");
        systemLog.setCaretPosition(systemLog.getDocument().getLength());
    }

    public void updateProductList(){
        this.productList.setListData(pointOfSales.getProductArray());
    }
}

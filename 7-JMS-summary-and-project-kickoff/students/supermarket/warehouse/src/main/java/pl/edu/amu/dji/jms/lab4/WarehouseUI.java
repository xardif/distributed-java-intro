package pl.edu.amu.dji.jms.lab4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import pl.edu.amu.dji.jms.lab4.utils.Swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@org.springframework.stereotype.Component("warehouseUI")
public class WarehouseUI extends JFrame{
    private JPanel rootPane;
    private JTextArea textArea1;
    private JList<Item> list1;
    private JButton changePriceButton;
    private JButton addItemButton;
    private JButton sendPriceListButton;

    @Autowired
    @Qualifier("warehouse")
    private Warehouse warehouse;

    public WarehouseUI() {
        super("Warehouse");

        changePriceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(list1.getModel().getSize()>0)
                    changePrice();
            }
        });

        sendPriceListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                warehouse.sendProductList();
            }
        });

        addItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addItem();
            }
        });
        textArea1.setEditable(false);

        add(rootPane);
        pack();
        setMinimumSize(new Dimension(640, 480));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Swing.centerJFrame(this);
        setVisible(true);
    }

    public void init(){
        list1.setListData(warehouse.getItemArray());
    }

    public void addItem(){
        JComponent[] components = new JComponent[2];
        components[0] = new JTextField("Name");
        components[1] = new JTextField("Price");
        if(JOptionPane.CANCEL_OPTION == JOptionPane.showConfirmDialog(this, components, "Add item", JOptionPane.OK_CANCEL_OPTION)){
            return;
        }
        String name = ((JTextField)components[0]).getText();
        Double price;
        try {
            price = Double.parseDouble(((JTextField)components[1]).getText());
        } catch(Exception ex){
            return;
        }
        Item item = new Item(name, price);
        warehouse.addItem(item);
        list1.setListData(warehouse.getItemArray());
    }

    public void changePrice(){
        Item item = list1.getSelectedValue();
        Double newPrice;
        try {
            newPrice = Double.parseDouble(JOptionPane.showInputDialog(this, "New price: ", item.getPrice()));
        } catch(Exception ex){
            return;
        }
        warehouse.changePrice(item, newPrice);
        item.setPrice(newPrice);
        list1.updateUI();
    }

    public void log(String message){
        textArea1.append(message + "\r\n");
        textArea1.setCaretPosition(textArea1.getDocument().getLength());
    }
}

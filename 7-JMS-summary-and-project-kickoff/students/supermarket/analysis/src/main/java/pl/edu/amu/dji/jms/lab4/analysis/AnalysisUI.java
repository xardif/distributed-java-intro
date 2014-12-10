package pl.edu.amu.dji.jms.lab4.analysis;

import pl.edu.amu.dji.jms.lab4.Item;
import pl.edu.amu.dji.jms.lab4.utils.Swing;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;



public class AnalysisUI extends JFrame{
    private JPanel panel1;
    private JTextArea textArea1;

    public AnalysisUI() {
        super("AnalysisApp");
        add(panel1);
        pack();

        textArea1.setEditable(false);

        setMinimumSize(new Dimension(450, 200));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Swing.centerJFrame(this);
        setVisible(true);
    }

    public void report(Item soldItem){
        textArea1.append("Item sold: " + soldItem +  "\r\n");
        textArea1.setCaretPosition(textArea1.getDocument().getLength());
    }
}

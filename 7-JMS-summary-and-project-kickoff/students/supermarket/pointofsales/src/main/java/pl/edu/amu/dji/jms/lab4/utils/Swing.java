package pl.edu.amu.dji.jms.lab4.utils;

import javax.swing.*;
import java.awt.*;

public class Swing {

    public static void setDefaultLookAndFeel(){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }

    public static void centerJFrame(JFrame jFrame){
        Dimension screen= Toolkit.getDefaultToolkit().getScreenSize();
        Point centerPoint = new Point(screen.width/2, screen.height/2);
        jFrame.setLocation(centerPoint.x - jFrame.getWidth()/2, centerPoint.y - jFrame.getHeight()/2);
    }
}

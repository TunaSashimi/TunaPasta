package awtswing;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

public class GridLayoutTest extends JFrame {
    public GridLayoutTest() {
        this.setSize(300, 400);
        this.setLayout(new GridLayout(3, 4, 15, 5));

        //如果不添加监听的话无法关闭
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                setVisible(false);
                System.exit(-1);
            }
        });

        for (int i = 0; i < 10; i++) {
            this.add(new JButton(i + ""));
        }
        this.setVisible(true);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        new GridLayoutTest();
    }
}

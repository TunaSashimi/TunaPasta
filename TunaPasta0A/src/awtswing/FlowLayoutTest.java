package awtswing;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

public class FlowLayoutTest extends JFrame {
    public FlowLayoutTest() {
        this.setSize(300, 400);
        this.setLayout(new FlowLayout());
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
        new FlowLayoutTest();
    }
}

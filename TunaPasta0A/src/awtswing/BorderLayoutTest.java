package awtswing;

import javax.swing.*;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class BorderLayoutTest extends JFrame {
    public BorderLayoutTest() {
        this.setSize(300, 400);
        this.setLayout(new BorderLayout());
        this.add(new JButton("北"), BorderLayout.NORTH);
        this.add(new JButton("南"), BorderLayout.SOUTH);
        this.add(new JButton("东"), BorderLayout.EAST);
        this.add(new JButton("西"), BorderLayout.WEST);
        this.add(new JButton("中"), BorderLayout.CENTER);
        //如果不添加监听的话无法关闭
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                setVisible(false);
                System.exit(-1);
            }
        });
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new BorderLayoutTest();
    }
}

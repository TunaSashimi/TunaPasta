package awtswing;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class JFrameTest {
    /**
     * @param args
     */
    public static void main(String[] args) {
        new JFrameTest().testFrame();
    }

    public void testFrame() {
        final JFrame win = new JFrame();
        win.setLayout(new FlowLayout());
        win.setTitle("My first window");
        final JLabel text = new JLabel("Hello World!");
        win.add(text);
        JButton button = new JButton("Click me!");
        button.addActionListener(new ActionListener() {
            int count = 0;

            public void actionPerformed(ActionEvent arg0) {
                System.out.println("button click!");
                text.setText("click " + count++ + " times");
            }
        });
        win.add(button);
        win.setSize(400, 300);
        win.setVisible(true);
        win.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                win.setVisible(false);
                System.exit(-1);
            }
        });
    }
}

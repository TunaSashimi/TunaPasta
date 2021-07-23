package awtswing;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PaintTest {
    public static void main(String[] args) {
        new PaintFrame().launchFrame();
    }
}

class PaintFrame extends Frame {
    //在构造方法中设置窗口监听器~	用WindowAdapter~
    PaintFrame() {
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                setVisible(false);
                System.exit(-1);
            }
        });
    }

    private static final long serialVersionUID = 1L;

    //launFrame()是自己定义的方法，用的时候对象名.方法就调用了
    public void launchFrame() {
        setBounds(200, 200, 640, 480);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.red);
        g.fillOval(50, 50, 30, 30);
        g.setColor(Color.green);
        g.fillRect(80, 80, 40, 40);
        g.setColor(c);    //恢复现场,把颜色设置回原样~
    }
}
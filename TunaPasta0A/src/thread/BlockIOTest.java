package thread;

import java.io.*;

public class BlockIOTest {
    public static void main(String[] args) {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        new Thread() {
            public void run() {
                while (true)
                    try {
// br.readLine()阻塞事件，阻塞当前的线程,直到IO结束(回车)，Block->Runnable状态
                        String line = br.readLine();
                        System.out.println("收到一行数据了：" + line);
                        if ("exit".equals(line)) {
                            break;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
        }.start();
        new Thread() {
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println("捣乱的！");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
}

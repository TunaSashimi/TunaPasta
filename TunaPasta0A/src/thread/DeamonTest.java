package thread;

public class DeamonTest {
    public static void main(String[] args) {
        Thread t1 = new Thread() {
            public void run() {
                for (int i = 0; i < 10000; System.out.println("守护" + i++ + "次!")) ;
            }
        };
        t1.setDaemon(true);// 设置t1为守护线程
        t1.start();
        Thread t2 = new Thread() {
            public void run() {
                for (int i = 0; i < 100; System.err.println("运行" + i++ + "次!")) ;
            }
        };
        t2.start();
    }
}
package thread;

//让程序运行100毫秒后停下来~
public class CopyOfSleepTest {
    public static void main(String[] args) throws InterruptedException {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("ran");
            }
        };
        Thread t = new Thread(r);
        t.start();
        System.out.println("started");
        Thread.sleep(2000);
        System.out.println("interrupting");
        t.interrupt();
        System.out.println("ended");
    }
}
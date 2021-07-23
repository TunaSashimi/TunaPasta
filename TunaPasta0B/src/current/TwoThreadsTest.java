package current;
public class TwoThreadsTest {
    private static Object resource = new Object();
    private static void delay(long n) {
        try {
            Thread.sleep(n);
        } catch (Exception e) {
            System.out.print("Error ");
        }
    }
    public static void main(String[] args) {
        System.out.print("StartMain ");
        new Thread1().start();
        delay(1000);
        Thread t2 = new Thread2();
        t2.start();
        delay(1000);
        t2.interrupt();
        delay(1000);
        System.out.print("EndMain ");
    }
    static class Thread1 extends Thread {
        public void run() {
            synchronized (resource) {
                System.out.print("Startl ");
                delay(6000);
                System.out.print("End1 ");
            }
        }
    }
    static class Thread2 extends Thread {
        public void run() {
            synchronized (resource) {
                System.out.print("Start2 ");
                delay(2000);
                System.out.print("End2 ");
            }
        }
    }
}

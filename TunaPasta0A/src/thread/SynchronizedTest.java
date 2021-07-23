package thread;

public class SynchronizedTest implements Runnable {
    int b = 100;

    public synchronized void m1() throws Exception {    //同步的意思是其他线程无法执行下面语句,但是可以
        b = 1000;                                                        //访问b~
        Thread.sleep(5000);
        System.out.println("b=" + b);
    }

    public void m2() {
        System.out.println(b);
    }

    @Override
    public void run() {
        try {
            m1();
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SynchronizedTest tt = new SynchronizedTest();
        new Thread(tt).start();
        Thread.sleep(4000);
        tt.m2();
    }
}

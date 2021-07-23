package thread;

public class ThreadTest implements Runnable {
    int i = 11;

    public void run() {
        for (; i > 1; )                                // run方法里面的循环,不大于1退出
            synchronized (this) {                // 任何时候只能有一个线程进行操作~
                if (i > 1)
                    try {
                        Thread.sleep(100);    // 买票与找零和打印票时间的延迟
                        System.out.println(Thread.currentThread().getName() + "卖出" + --i);
                    } catch (InterruptedException e) {
                    }
            }
    }

    public static void main(String[] args) {
        ThreadTest d = new ThreadTest();
        new Thread(d, "售票厅1").start();
        new Thread(d, "售票厅2").start();
        new Thread(d, "售票厅3").start();
    }
}

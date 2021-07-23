package thread;

public class InterruptedTest implements Runnable {
    public void run() {
    }

    public static void main(String[] args) {
        InterruptedTest d = new InterruptedTest();
        Thread td = new Thread(d);
        td.start();
        td.interrupt();
        // 测试当前线程是否已经中断。线程的中断状态 由该方法清除。换句话说，如果连续两次调用该方法，
        // 则第二次调用将返回 false
        System.out.println("Point 1: 	td.	isInterrupted()=" + td.isInterrupted());
        //如果特别快,可能有两个true
        System.out.println("Point 2: 	td.	isInterrupted()=" + td.isInterrupted());
        System.out.println("--------");

        Thread t = Thread.currentThread();
        System.out.println("Point A: 	t.	isInterrupted()=" + t.isInterrupted());
        t.interrupt();
        System.out.println("Point B: 	t.	isInterrupted()=" + t.isInterrupted());
        //主线程的中断标记貌似不会查询而清楚
        System.out.println("Point C: 	t.	isInterrupted()=" + t.isInterrupted());
        System.out.println("--------");
        try {
            Thread.sleep(1000);
            System.out.println("was Not interrupted");
        } catch (InterruptedException e) {
            System.out.println("was interrupted");
        }
        //在这里因为sleep抛出了异常,所以清除了中断标志
        //线程如果中断再休眠,则会清除中断标志
        System.out.println("Point D: 	t.	isInterrupted()=" + t.isInterrupted());
        System.out.println(td.getName());
        System.out.println(t.getName());
    }
}

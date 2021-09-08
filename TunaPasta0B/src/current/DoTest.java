package current;

public class DoTest extends Thread {
    public static void main(String[] args) throws InterruptedException {
        DoTest t = new DoTest();
        t.start();
        t.run();
        t.join();
        t.doit();

        System.out.println(4 % 2);
        System.out.println(3 % 2);
        System.out.println(-2 % 2);
        System.out.println(-1 % 2);
    }

    public void run() {
        System.out.println("run");
    }

    public void doit() {
        System.out.println("doit");
    }
}

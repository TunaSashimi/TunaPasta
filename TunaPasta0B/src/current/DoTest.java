package current;

public class DoTest extends Thread {
    public static void main(String[] args) throws InterruptedException {
        DoTest t = new DoTest();
        t.start();
        t.run();
        t.join();
        t.doit();

        System.out.println(57256 % 800);
    }

    public void run() {
        System.out.println("run");
    }

    public void doit() {
        System.out.println("doit");
    }
}

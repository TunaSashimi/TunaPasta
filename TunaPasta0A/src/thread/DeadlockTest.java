package thread;

class B {
    synchronized void startA(DeadlockTest a) {
        System.out.println("执行classB中的startA方法");
        a.startB();
    }

    synchronized void startB() {
        System.out.println("执行classB中的startB方法");
    }
}

public class DeadlockTest extends Thread {
    B b = new B();

    synchronized void startA(B b) {
        System.out.println("执行classA中的startA方法");
//		try {
//			Thread.sleep(4);			//同步锁在第一次的地方~
//		} catch (InterruptedException e) {
//		}
        b.startB();
    }

    synchronized void startB() {
        System.out.println("执行classA中的startB方法");
    }

    @Override    //重写方法中的异常处理只能catch~
    public void run() {
        b.startA(this);
    }

    public DeadlockTest() {
        new Thread(this).start();
        startA(b);
    }

    public static void main(String[] args) {
        new DeadlockTest();
    }
}

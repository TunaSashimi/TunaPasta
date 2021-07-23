package thread;
public class TehreadYieldTest {
//yield只能保证让出线程,然是不保证让出后自己一定抢不到下一次的时间片~
	public static void main(String[] args) {
		T4 t4 = new T4();
		T5 t5 = new T5();
		Thread t6 = new Thread(new T6());
		t4.start();
		t5.start();
		t6.start();
		System.out.println("Main Over!");
	}
}
class T4 extends Thread{
	public void run(){
		for(int i = 0 ; i < 100; i++){
			System.out.println("拿弹弓打你家玻璃！～");
			Thread.yield();
		}
		System.out.println("打完了！");
	}
}
class T5 extends Thread{
	public void run(){
		for(int i = 0 ; i < 100; i++){
			System.out.println("你是谁？");
			Thread.yield();
		}
		System.out.println("问完了！");
	}
}
class T6 implements Runnable{
	public void run() {
		for(int i = 0 ; i < 100; i++){
			System.out.println("修煤气的！");
			Thread.yield();
		}
		System.out.println("修完了！");
	}
	
}



package thread;
//让程序运行100毫秒后停下来~
public class SleepTest implements Runnable{
	static boolean flag=true;
	public static void main(String[] args) {
		new Thread(new SleepTest()).start();
		try {
			Thread.sleep(1000);
			flag=false;
		} catch (InterruptedException e) {
		}
		
		
		new Thread().start();
	}
	@Override
	public void run() {
		while(flag)
			System.out.println("正在运行中");
	}
}

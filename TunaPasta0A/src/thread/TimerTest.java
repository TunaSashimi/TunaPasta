package thread;

import java.util.*;//Timer : 定时器，自动安排任务的类。

public class TimerTest {
    public static void main(String[] args) {
        final Timer timer = new Timer();                        //内部类必须用final修饰
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                timer.cancel();
            }
        }, 5000);
//		延迟0秒，每隔1000毫秒，执行任务(匿名类中定义的任务)
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println(new Date());
            }
        }, 0, 1000);
    }
}
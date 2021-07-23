package current;

/**
 * @author TunaSashimi
 * @date 2021/7/6 10:46
 * @Copyright 2021 TunaSashimi. All rights reserved.
 * @Description
 */
public class ThreadTest {
    static boolean flag = false;
    public static void main(String[] args) throws Exception {
        test();
    }
    public static void test() throws Exception {
        new Thread() {
            @Override
            public void run() {
                System.out.println("Thread1--start");
                while (!flag) {
                }
                System.out.println("Thread1--end");
            }
        }.start();
        Thread.sleep(100);
        new Thread() {
            @Override
            public void run() {
                System.out.println("Thread2--start");
                flag = true;
                System.out.println("Thread2--end");
            }
        }.start();
    }
}
//请问是否能打印出这一句Thread1--end  
package current;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

//线程池 
//如果某些资源的创建比较费劲，也就耗费时间和资源，那就得考虑用pool也就是池来保存一些预先创建好的资源，用的时候拿来用，用完放回去。典型的有jdbc连接池。线程池也算一个。 
//Executors就提供了各种各项的线程池共你选择： 
//1. CachedThreadPool 
//这种线程池会不停的为你创建新的线程，只要当前pool里面没有可用的。这个其实很危险，很容易耗尽你的资源。
//2. FixedThreadPool 
//这个线程池的策略是为你保持指定大小的线程数，这个还算比较好控制，任务比线程数多的时候就排队等候。 
//3. SingleThreadExecutor 
//其实就是固定大小为一的FixedThreadPool。这个策略也很有用。当你只想用一个线程完成一系列任务的时候，就可以用它为你维护你可以任务队列，他会帮你一次顺序调用。完成一件，在做下一件。

public class TraditionalThreadPloolTest {
    /**
     * 线程池
     */
    public static void main(String[] args) {

//    		//	创建了有3条线程的线程池
//        ExecutorService threadPool = Executors.newFixedThreadPool(3);
//        //创建了10条任务（就是i）
//        for(int i=0;i<10;i++){
//            final int currentTask = i+1;
//            threadPool.execute(new Runnable(){
//                public void run() {
//                	//每个任务的内容就是执行十个循环
//                    for(int i=0;i<10;i++){
//                        try {
//                            Thread.sleep(50);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                        System.out.println(Thread.currentThread().getName()+" is servering for " + currentTask + " looping " + (i+1) + " circle");
//                    }
//                }
//            });
//        }
//        //关闭线程池
//        threadPool.shutdown();
//        /*
//         * 上面就是创建了一个固定的线程池，初始化线程池里有三条线程在等候工作
//         * 现在来了十项任务每项任务就是执行循环十次
//         * 从打印的信息中我们可以看出，只有三条线程在工作
//         */


//    	//创建了缓存线程池，缓存线程池的线程个数是不固定的，随着任务的增加而增加线程池中的线程数，随着任务结束而消亡
//        ExecutorService threadPool = Executors.newCachedThreadPool();
//        //创建了10条任务（就是i）
//        for(int i=0;i<10;i++){
//            final int currentTask = i+1;
//            threadPool.execute(new Runnable(){
//                public void run() {
//                	//每个任务的内容就是执行十个循环
//                    for(int i=0;i<10;i++){
//                        try {
//                            Thread.sleep(50);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                        System.out.println(Thread.currentThread().getName()+" is servering for " + currentTask + " looping " + (i+1) + " circle");
//                    }
//                }
//            });
//        }
//        //关闭线程池
//        threadPool.shutdown();


//    	// 下例是单例线程池，好处是这个线程死了还能在搞一个替补，解决了线程死了重新启动的问题
//        ExecutorService threadPool = Executors.newSingleThreadExecutor();
//        //创建了10条任务（就是i）
//        for(int i=0;i<10;i++){
//            final int currentTask = i+1;
//            threadPool.execute(new Runnable(){
//                public void run() {
//                	//每个任务的内容就是执行十个循环
//                    for(int i=0;i<10;i++){
//                        try {
//                            Thread.sleep(50);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                        System.out.println(Thread.currentThread().getName()+" is servering for " + currentTask + " looping " + (i+1) + " circle");
//                    }
//                }
//            });
//        }
//        //关闭线程池
//        threadPool.shutdown();


        /*
         * 用线程池启动定时器,延迟10秒输出语句
         */
        Executors.newScheduledThreadPool(3).schedule(
                new Runnable() {
                    public void run() {

                        System.out.println("bombing");

                    }
                }, 10, TimeUnit.SECONDS);
    }
}
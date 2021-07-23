package thread;

/**
 * 模拟的是生产者往篮子里生产面包,而消费者往篮子里面取面包的情形
 */
public class ProducerTest {
    public static void main(String[] args) {
        SyncStack ss = new SyncStack();
        new Thread(new Producer(ss)).start();// 两生产者,所以消费者要消费40
        new Thread(new Producer(ss)).start();
        new Thread(new Consumer(ss)).start();
    }
}

class Bun { // 面包类,构造方法为面包编号
    int id;

    Bun(int id) {
        this.id = id;
    }

    @Override
    public String toString() { // 重写toString.便于观察
        return "Bun	" + id;
    }
}

class SyncStack { // 篮子类,容量8,方法为装面包和取面包
    int index = 0;
    Bun[] bun = new Bun[8];

    public synchronized void push(Bun b) throws InterruptedException {
        while (index == bun.length)
            this.wait();
        this.notifyAll();
        bun[index] = b;
        index++;
    }

    public synchronized Bun pop() throws InterruptedException {
        while (index == 0)
            this.wait();
        this.notifyAll();
        index--;
        return bun[index];
    }
}

class Producer implements Runnable { // 生产者类,方法为生产20个面包~
    SyncStack ss;

    Producer(SyncStack s) {
        ss = s;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            try {
                ss.push(new Bun(i));
                System.out.println("生产了	" + new Bun(i));
                Thread.sleep((int) (Math.random() * 1000));
            } catch (InterruptedException e) {
            }
        }
    }
}

class Consumer implements Runnable { // 消费者类,本题中有2个生产者,故方法为消费40个面包
    SyncStack ss;

    Consumer(SyncStack s) {
        ss = s;
    }

    @Override
    public void run() {
        for (int i = 0; i < 40; i++) {
            try {
                System.out.println("消费了	" + ss.pop());
                Thread.sleep((int) (Math.random() * 800));
            } catch (InterruptedException e) {
            }
        }
    }
}

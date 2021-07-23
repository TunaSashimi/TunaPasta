package thread;

class Personp {
    private String name;
    private String xingming;
    private boolean flag;

    public synchronized void set(String name, String xingming) {    //加入同步方法,避免只改了一个数据就被取走~set完了才能get~
        if (flag) {                                                                            //flag初始值为false,如果想要先set,这里就为flag,让get的!flag
            try {                                                                            //为真,让get先睡觉~
                wait();
            } catch (InterruptedException e) {
            }
        }
        this.name = name;
        try {
            Thread.sleep(25);                                                        //就算休息40毫秒也没事~
        } catch (InterruptedException e) {
        }
        this.xingming = xingming;
        flag = true;                                                                        //设置完成之后,要改成让自己睡觉的flag
        notifyAll();
    }

    public synchronized String get() {
        if (!flag) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        flag = false;
        notifyAll();
        return this.name + "--->" + this.xingming;
    }
}

class Pro implements Runnable {
    public Personp per;

    public Pro(Personp per) {
        this.per = per;
    }

    public void run() {
        for (int i = 0; i < 80; i++)
            per.set(i % 2 == 0 ? "JS" : "JYT", i % 2 == 0 ? "金圣" : "戒十");            //设置80次per值,注意用法~
    }
}

class Cust implements Runnable {
    public Personp per;

    public Cust(Personp per) {
        this.per = per;
    }

    public void run() {
        for (int i = 0; i < 80; i++)
            System.out.println(per.get());                                //取80次per值
    }
}

public class NotifyTest {
    public static void main(String[] args) {
        Personp per = new Personp();
        new Thread(new Pro(per)).start();
        new Thread(new Cust(per)).start();
    }
}

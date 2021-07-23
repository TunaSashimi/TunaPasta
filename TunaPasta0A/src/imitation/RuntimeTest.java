package imitation;

import java.io.IOException;

public class RuntimeTest {
    public static void main(String[] args) throws IOException, InterruptedException {
        // Process p=Runtime.getRuntime().exec("D:/白银宝/terminal.exe");
        // Thread.sleep(60000);
        // 3秒后自动关闭,为什么有些程序关不了~?
        // p.destroy();//做成死循环,可以造成死机~
        // 用stop试试,看看能否强制关掉

        Runtime p0 = Runtime.getRuntime();
        for (int i = 0; i < 2; i++) {

            p0.exec("notepad");//写字板

            p0.exec("mspaint");//画图板

            p0.exec("mmc");//控制台

            p0.exec("explorer");//资源管理器

            //p0.exec("tsshutdn");//60秒倒计时关机命令
        }

//在pad上面写字

    }
}

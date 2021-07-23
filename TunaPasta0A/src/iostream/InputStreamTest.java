package iostream;

import java.io.*;

public class InputStreamTest {
    public static void main(String[] args) {
        try {
            InputStream in = new FileInputStream("src/iostream/inputstreamdemo.java");
            int b = in.read();
            System.out.println(Integer.toHexString(b));
            b = in.read();
            System.out.println(Integer.toHexString(b));
            b = in.read();
            System.out.println(Integer.toHexString(b));

            b = in.read();
            System.out.println(Integer.toHexString(b));
            b = in.read();
            System.out.println(Integer.toHexString(b));
//返回下一次对此输入流调用的方法可以不受阻塞地从此输入流读取（或跳过）的估计剩余字节数。
            byte[] buf = new byte[in.available()];
            in.read(buf);
//通过使用指定的 charset 解码指定的 byte 数组，构造一个新的 String。
//没有则通过使用平台的默认字符集解码指定的 byte 数组
            String s = new String(buf, "utf-8");    //如"gbk"
            System.out.print(s);
            in.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

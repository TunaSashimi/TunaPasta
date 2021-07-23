package iostream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileOutputTest {
    public static void main(String[] args) throws IOException {
        //构造方法里面直接有String name!
        OutputStream os = new FileOutputStream("f:\\output.txt");
        os.write("output要用到byte数组".getBytes());
        os.close();
        InputStream is = new FileInputStream("f:\\output.txt");
        byte[] b = new byte[1024];
        System.out.println(new String(b, 0, is.read(b)));
        os.close();
        is.close();
        //通过is.read(b)获得b数组和数组长度,打印数组从零到长度
        java.io.Writer w = new java.io.FileWriter("f:\\writer.txt");
        w.write("write可以直接输出字符串");
        w.flush();//表示清空缓存~
//		w.close();//没有这句话的话,写的内容没有了
        java.io.Reader r = new java.io.FileReader("f:\\writer.txt");
        char[] a = new char[1024];
        System.out.println(new String(a, 0, r.read(a)));
    }
}

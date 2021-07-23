package iostream;

import java.io.*;

public class BufferedReaderTest {
    public static void main(String[] args) throws IOException {                    //自己看自己~
        System.err.println("请输入内容:");
        System.out.println("输入的内容为" + new java.io.BufferedReader(new java.io.InputStreamReader(System.in)).readLine());
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("src/iostream/BufferedReaderDemo.java"), "GBK"));
        String line;        //定义的语句没有返回值,还是不能放在循环体里面~
        while ((line = br.readLine()) != null)
            System.out.println(line);
        br.close();
//Java命名惯例,凡是以ImputSTrean和OutputStrean结尾的均为字节流,凡是以Reader和Writer即为的均为字符流~
    }
}

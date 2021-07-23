package iostream;

import java.io.PrintStream;

public class PrintStreamTest {
    public static void main(String[] args) throws Exception {
        PrintStream p = new PrintStream(System.out);//把输出到文件的转到控制台!
        p.println("tuna");
        p.println("TunaSashimi");
        System.setOut(new java.io.PrintStream(new java.io.FileOutputStream("src/iostream/printstreamdemo.java", true)));
    //	相当于	p=new java.io.PrintStream(new java.io.FileOutputStream("src/iostream/printstreamdemo.java",true));
        System.out.println("//king");
        System.out.println("//KingCrab");//把输出到控制台的东西转到文件~
    }//不需要close,因为属于字节流的子类~
}
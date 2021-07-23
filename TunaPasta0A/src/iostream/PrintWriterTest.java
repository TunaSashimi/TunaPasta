package iostream;

public class PrintWriterTest {
    public static void main(String[] args) throws Exception {
        java.io.PrintWriter p = new java.io.PrintWriter(System.out);//拥有实例化子类的方法!~~
        p.println(true);
        p.println(30);
        p.println("Hello");
        p.close();
        p = new java.io.PrintWriter(new java.io.FileOutputStream("src/iostream/printwriterdemo.java", true));
        p.println("//true");
        p.println("//30");
        p.println("//Hello");
        p.close();        //因为是字符流的子类,必须要有close
    }
}
//true
//30
//Hello

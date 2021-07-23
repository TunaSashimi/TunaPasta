package history;

public class BinarySwitchTest {   //注意!public 类说明访问是公有的，如果没有public，程序就不能运行！
    public static void main(String[] args) {

        System.out.println("第一种输出方式");
        int decimal, a, b;
        decimal = new java.util.Scanner(System.in).nextInt();
        String binary = "";
        for (a = 1; a > 0; ) {
            a = decimal / 2;
            b = decimal % 2;
            decimal = decimal / 2;
            binary = b + binary;        //用输出字符串从右到左的方法转换2进制
        }
        System.out.println(binary);

        System.out.println("第二种输出方式");
        int c = 0, d = 0;
        decimal = new java.util.Scanner(System.in).nextInt();
        for (a = 1; a > 0; ) {
            a = decimal / 2;
            b = decimal % 2;
            decimal = decimal / 2;
            c++;                    //用10 的n次幂的问题解决从右到左输出的问题
            d = (int) Math.pow(10, c - 1) * b + d;   //注意用三目遇到1000类的数会出错
        }
        System.out.println(d);
        System.out.println("第三种输出方式,同时解决二进制和十六进制转换");
        System.out.println(Integer.toBinaryString(new java.util.Scanner(System.in).nextInt()));
        System.out.println(Integer.toHexString((new java.util.Scanner(System.in).nextInt())));
    }
}
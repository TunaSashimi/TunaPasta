package current;

public class HexStringTest {
    public static void main(String[] args) {
        //十进制转十六进制
        System.out.println(Integer.toHexString(17));
        //十进制转八进制
        System.out.println(Integer.toOctalString(9));
        //十进制转二进制
        System.out.println(Integer.toBinaryString(3));

        //十六进制转成十进制
        System.out.println(Integer.parseInt("11", 16));
        //八进制转成十进制
        System.out.println(Integer.parseInt("11", 8));
        //二进制转十进制
        System.out.println(Integer.parseInt("11", 2));


        //十六进制转二进制
        System.out.println(Integer.toBinaryString(Integer.parseInt("3", 16)));
    }
}

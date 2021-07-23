package current;

/**
 * @author TunaSashimi
 * @date 2021/7/14 16:27
 * @Copyright 2021 TunaSashimi. All rights reserved.
 * @Description
 */
public class ConverTest {
    public static void main(String[] args) {
        System.out.println(Integer.toBinaryString(Integer.valueOf("1")));
        System.out.println(Integer.toBinaryString(Integer.valueOf("2")));

        System.out.println(Integer.toOctalString(Integer.valueOf("8")));
        System.out.println(Integer.toOctalString(Integer.valueOf("9")));

        System.out.println(Integer.toHexString(Integer.valueOf("16")));
        System.out.println(Integer.toHexString(Integer.valueOf("17")));
    }
}

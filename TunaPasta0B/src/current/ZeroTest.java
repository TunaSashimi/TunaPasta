package current;

public class ZeroTest {
    public static void main(String[] args) {
        System.out.println(Math.hypot(3, 4));
        String str;
        str = String.format("Hi,%s", "王力");
        System.out.println(str);
        str = String.format("Hi,%s:%s.%s", "王南", "王力", "王张");
        System.out.println(str);
        System.out.printf("字母a的大写是：%c %n", 'A');
        System.out.printf("3>7的结果是：%b %n", 3 > 7);
        System.out.printf("100的一半是：%d %n", 100 / 2);
        System.out.printf("100的16进制数是：%x %n", 100);
        System.out.printf("100的8进制数是：%o %n", 100);
        System.out.printf("50元的书打8.5折扣是：%f 元%n", 50 * 0.85);
        System.out.printf("上面价格的16进制数是：%a %n", 50 * 0.85);
        System.out.printf("上面价格的指数表示：%e %n", 50 * 0.85);
        System.out.printf("上面价格的指数和浮点数结果的长度较短的是：%g %n", 50 * 0.85);
        System.out.printf("上面的折扣是%d%% %n", 85);
        System.out.printf("字母A的散列码是：%h %n", 'A');

        int MEASURED_STATE_TOO_SMALL = 0x01000000;
        System.out.println("toBinaryString==>" + Integer.toBinaryString(MEASURED_STATE_TOO_SMALL));
        System.out.println("toOctalString==>" + Integer.toOctalString(MEASURED_STATE_TOO_SMALL));
        System.out.println("toHexString==>" + Integer.toHexString(MEASURED_STATE_TOO_SMALL));
        System.out.println("toDecimalString==>" + MEASURED_STATE_TOO_SMALL);

    }
}

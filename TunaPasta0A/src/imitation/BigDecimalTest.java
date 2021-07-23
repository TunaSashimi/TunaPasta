package imitation;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 定义加\减\乘\除\四舍五入运算的方法
 *
 * @author teacher
 */
public class BigDecimalTest {
    public static void main(String[] args) {
        System.out.println(0.05 + 0.01);
        System.out.println(add(0.05, 0.01));
        System.out.println(sub(0.05, 0.01));
        System.out.println(mul(0.05, 0.01));
        System.out.println(div(2, 0.000000001));
        System.out.println(cat(0.05, 1));            //四舍五入
    }

    /**
     * 实现计算器中两个数相加的功能,返回计算结果
     */
    public static double add(double a, double b) {
        BigDecimal bigA = new BigDecimal(a + "");        //	将 BigDecimal 的字符串表示形式转换为 BigDecimal
        BigDecimal bigB = new BigDecimal(b + "");        //	只有这样才不会出现误差
        return bigA.add(bigB).doubleValue();
    }

    /**
     * 实现计算器中两个数相减的功能,返回计算结果
     */
    public static double sub(double a, double b) {
        BigDecimal bigA = new BigDecimal(a + "");
        BigDecimal bigB = new BigDecimal(b + "");
        return bigA.subtract(bigB).doubleValue();
    }

    /**
     * 实现计算器中两个数相乘的功能,返回计算结果
     */
    public static double mul(double a, double b) {
        BigDecimal bigA = new BigDecimal(a + "");
        BigDecimal bigB = new BigDecimal(b + "");
        return bigA.multiply(bigB).doubleValue();
    }

    /**
     * 实现计算器中两个数相除的功能,返回计算结果
     */
    public static String div(double a, double b) {
        BigDecimal bigA = new BigDecimal(a + "");
        BigDecimal bigB = new BigDecimal(b + "");
        if (0 == b)
            return "Infinity";
        return bigA.divide(bigB).doubleValue() + "";
    }

    /**
     * 实现将浮点数结果四舍五入
     * 可以保留小数点n位
     * 返回计算结果
     */
    public static double cat(double n, int scale) {
        BigDecimal bigA = new BigDecimal(n + "");
        BigDecimal result = bigA.setScale(scale, RoundingMode.HALF_UP);
        return result.doubleValue();
    }
}

package current;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * @author Tunasashimi
 * @date 2019-11-15 16:16
 * @Copyright 2019 TunaSashimi. All rights reserved.
 * @Description
 */

//Double值保留两位小数的四种方法
public class DoubleTest {
    //保留两位小数第三位如果大于4会进一位（四舍五入）
    double f = 6.23556;

    /**
     * 使用精确小数BigDecimal
     */
    public void fun1() {
        BigDecimal bg = new BigDecimal(f);
        /**
         * 参数：
         newScale - 要返回的 BigDecimal 值的标度。
         roundingMode - 要应用的舍入模式。
         返回：
         一个 BigDecimal，其标度为指定值，其非标度值可以通过此 BigDecimal 的非标度值乘以或除以十的适当次幂来确定。
         */
        double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        System.out.println(f1);

        System.out.println(formatToNumber(new BigDecimal("3.435")));
        System.out.println(formatToNumber(new BigDecimal(0)));
        System.out.println(formatToNumber(new BigDecimal("0.00")));
        System.out.println(formatToNumber(new BigDecimal("0.001")));
        System.out.println(formatToNumber(new BigDecimal("0.006")));
        System.out.println(formatToNumber(new BigDecimal("0.206")));

    }

    /**
     * DecimalFormat转换最简便
     */
    public void fun2() {
        DecimalFormat df = new DecimalFormat("#.00");
        System.out.println(df.format(f));
    }

    /**
     * String.format打印最简便
     */
    public void fun3() {
        System.out.println(String.format("%.2f", f));
    }

    /**
     * 使用NumberFormat
     */
    public void fun4() {
        NumberFormat nf = NumberFormat.getNumberInstance();
        /**
         * setMaximumFractionDigits(int newValue)
         设置数的小数部分所允许的最大位数。
         */
        nf.setMaximumFractionDigits(2);
        System.out.println(nf.format(f));
    }

    public static void main(String[] args) {
        DoubleTest dt = new DoubleTest();
        dt.fun1();
        dt.fun2();
        dt.fun3();
        dt.fun4();
    }

    /**
     * @param
     * @return
     * @desc 1.0~1之间的BigDecimal小数，格式化后失去前面的0,则前面直接加上0。
     * 2.传入的参数等于0，则直接返回字符串"0.00"
     * 3.大于1的小数，直接格式化返回字符串
     */
    public static String formatToNumber(BigDecimal obj) {
        DecimalFormat df = new DecimalFormat("#.00");
        if (obj.compareTo(BigDecimal.ZERO) == 0) {
            return "0.00";
        } else if (obj.compareTo(BigDecimal.ZERO) > 0 && obj.compareTo(new BigDecimal(1)) < 0) {
            return "0" + df.format(obj).toString();
        } else {
            return df.format(obj).toString();
        }
    }
}
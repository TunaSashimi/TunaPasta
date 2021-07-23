package imitation;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalculatorTest {
    private static String s;

    public static void main(String[] args) {
        //s为需要计算的数学表达式~
        s = "2*-2*3*3/2--1--1--1+6";
        System.out.println("输入表达式值为:\t" + s);
        try {
            firstdeal();
            followdeal();
        } catch (Exception e) {
            System.out.println("ERROR");
        }
    }

    // 如果包含乘除号,找出乘除法单元表达式用muldiv()方法处理后替换
    private static void firstdeal() {
        while (s.contains("*") || s.contains("/")) {
            Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?[*/]-?\\d+(\\.\\d+)?");
            Matcher matcher = pattern.matcher(s);
            while (matcher.find()) {
                System.out.print("发现乘除匹配项:\t" + matcher.group() + "\n");
                s = s.replace(matcher.group(), muldiv(matcher.group()));
            }
            System.out.println("muldiv()处理为:\t" + s);
        }
        s = s.replaceAll("--", "+");                    //去掉多余的负号避免空指针
        System.out.println("修正最后结果为:\t" + s);
    }

    // muldiv()方法,根据乘除法的单元表达式用BigDcimal计算返回结果
    private static String muldiv(String s) {
        String[] ss = s.split("[\\*\\/]");
        BigDecimal bigA = new BigDecimal(ss[0]);
        BigDecimal bigB = new BigDecimal(ss[1]);
        if (s.contains("*"))
            return bigA.multiply(bigB).doubleValue() + "";
        else
            return (bigA.divide(bigB, 5, BigDecimal.ROUND_HALF_UP)
                    .doubleValue() + "");
    }

    // 如果包含加减号,找出加减法单元表达式用plusminus()方法处理后替换
    private static void followdeal() {
        // 出现在第一位的-号并不是运算符~
        while (s.contains("+") || (s.contains("-") && s.lastIndexOf("-") != 0)) {
            Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?[+-]-?\\d+(\\.\\d+)?");
            Matcher matcher = pattern.matcher(s);
            while (matcher.find()) {
                System.out.print("发现加减匹配项:\t" + matcher.group() + "\n");
                s = s.replace(matcher.group(), plusminus(matcher.group()));
            }
            System.out.println("plusminus()处理为:" + s);
        }
        System.out.println("最后处理结果为:\t" + s);
    }

    // plusminus()方法,根据加减法的单元表达式用BigDcimal计算返回结果
    private static String plusminus(String s) {
        if (s.contains("+")) {
            String[] ss = s.split("[+]");    //看看可以的话去掉+
            BigDecimal bigA = new BigDecimal(ss[0]);
            BigDecimal bigB = new BigDecimal(ss[1]);
            return bigA.add(bigB).doubleValue() + "";
        } else {
            BigDecimal bigA = new BigDecimal(s.substring(0, s.lastIndexOf('-')));
            BigDecimal bigB = new BigDecimal(s.substring(s.lastIndexOf('-') + 1));
            return (bigA.subtract(bigB).doubleValue() + "");
        }
    }
}

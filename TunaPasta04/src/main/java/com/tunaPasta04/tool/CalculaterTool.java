package com.tunaPasta04.tool;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalculaterTool {
    private String s;
    private boolean flag;

    public CalculaterTool(String s) {
        super();
        s = s.replaceAll("\\*", ""); //把用户从键盘输入的影响字符去掉,正则要转义~
        s = s.replaceAll("/", "");
        s = s.replaceAll("×", "*"); // 相当于适配器,转化源目标的字符~
        s = s.replaceAll("÷", "/");
        System.out.println("输入表达式值为:\t" + s);
        s = s.replaceAll("--", "+"); // 匹配两个连续的--,替换为+号
        s = s.replaceAll("\\+\\+", "+");
        s = s.replaceAll("\\*\\+", "*");
        s = s.replaceAll("/\\+", "/");
        if (s.matches(".*[\\+\\-*/]")) // 匹配结尾是+-*/的错误字符串~
            s = s.substring(0, s.length() - 1); // 把尾部的运算符去掉~
        if (s.matches("[\\+*/].*")) // 匹配开头是+*/的错误字符串~
            s = s.substring(1, s.length());    // 把头部的运算符去掉~

        this.s = s;
    }

    public String CalResults() { // 匹配不识别的字符串
        if (s.matches(".*[a-zA-Z_].*") || s.matches(".*[^0-9\\+\\-*/\\.].*"))
            return "ERROR";
        else {

            //线程控制1000毫秒的计算时间~
            new Thread() {
                public void run() {
                    try {
                        Thread.sleep(1000);
                        flag = false;
                        s = "EXCEPTION";
                    } catch (Exception e) {
                    }
                }
            }.start();

            try {
                System.out.println("初始化表达式为:\t" + s);
                firstdeal();
                followdeal();
            } catch (Exception e) {
                return "ERROR";
            }
            return s;
        }
    }

    // 如果包含乘除号,找出乘除法单元表达式用muldiv()方法处理后替换
    private void firstdeal() {
        while (!flag && s.contains("*") || s.contains("/")) {
            Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?[*/]-?\\d+(\\.\\d+)?");
            Matcher matcher = pattern.matcher(s);
            while (matcher.find()) {
                System.out.print("发现乘除匹配项:\t" + matcher.group() + "\n");
                s = s.replace(matcher.group(), muldiv(matcher.group()));
            }
            System.out.println("muldiv()处理为:\t" + s);
        }
        s = s.replaceAll("--", "+"); // 去掉多余的负号避免空指针
        System.out.println("修正最后结果为:\t" + s);
    }

    // muldiv()方法,根据乘除法的单元表达式用BigDcimal计算返回结果
    private String muldiv(String s) {
        String[] ss = s.split("[\\*\\/]");
        BigDecimal bigA = new BigDecimal(ss[0]);
        BigDecimal bigB = new BigDecimal(ss[1]);
        if (s.contains("*")) {
            String result = null;
            try {
                result = bigA.multiply(bigB).intValueExact() + "";
            } catch (Exception e) {
                result = bigA.multiply(bigB).doubleValue() + "";
            }
            return result;
        } else {
            String result = null;
            try {
                result = bigA.divide(bigB, 5, BigDecimal.ROUND_HALF_UP).intValueExact() + "";
            } catch (Exception e) {
                result = bigA.divide(bigB, 5, BigDecimal.ROUND_HALF_UP).doubleValue() + "";
            }
            return result;
        }
    }

    // 如果包含加减号,找出加减法单元表达式用plusminus()方法处理后替换
    private void followdeal() {
        // 出现在第一位的-号并不是运算符~
        while (!flag && s.contains("+") || s.contains("-") && s.lastIndexOf("-") != 0) {//有+号但是不匹配
            Pattern pattern = Pattern.compile("-?\\d+\\.*\\d*[+-]-?\\d+\\.*\\d*");
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
    private String plusminus(String s) {
        if (s.contains("+")) {
            String[] ss = s.split("+"); // 看看可以的话去掉+
//			System.out.println(ss.length);
//			System.out.println(ss[0]);
//			System.out.println(ss[1]);
            BigDecimal bigA = new BigDecimal(ss[0]);
            BigDecimal bigB = new BigDecimal(ss[1]);
            String result = null;
            try {
                result = bigA.add(bigB).intValueExact() + "";
            } catch (Exception e) {
                result = bigA.add(bigB).doubleValue() + "";
            }
            return result;
        } else {
            BigDecimal bigA = new BigDecimal(s.substring(0, s.lastIndexOf('-')));
            BigDecimal bigB = new BigDecimal(s.substring(s.lastIndexOf('-') + 1));
            String result = null;
            try {
                result = bigA.subtract(bigB).intValueExact() + "";
            } catch (Exception e) {
                result = bigA.subtract(bigB).doubleValue() + "";
            }
            return result;
        }
    }
}

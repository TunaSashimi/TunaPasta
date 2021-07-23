package imitation;
//实现两种算法,第一种算法为先求得1000阶乘的位数,再进行计算

/**
 * 一个自然数N，它的位数等于lgN+1。lg就是以10为底的对数。例如250 = 2.5*10^2,则lg250 = 2 + lg2.5，
 * 而lg2.5小于1，所以数250的位数约等于lg250+1，忽略小数部分，就等于3。
 * 所以1000!的位数约等于lg1000!+1 而lg1000! =lg1+lg2+lg3+……+lg1000
 * 这个求和约等于lgx这条曲线在区间[1,1000]与x轴围成的面积，也就是等于lgx在区间[1,1000]的定积分。
 * 这个定积分很好计算，等于(1000ln1000-1000+1)/ln10, ln是自然对数。
 * 所以1000!的阶乘的位数是1+(1000ln1000-1000+1)/ln10 = 2567
 */
public class LargeNumbersFactorialTest {
    public static void main(String[] args) {
        // 先计算1000!的位数~
        double digit = 0;
        for (int i = 1; i <= 1000; i++)
            digit += Math.log10(i);
        System.out.println("1000阶乘的位数有" + (digit + 1) + "位");
        System.out.println();
        //每一位用数组里面的一个元素来代替~
        int[] a = new int[(int) Math.floor(digit + 1)];
        a[0] = 1;
        for (int i = 1; i <= 1000; i++) {
            for (int j = 0; j <= a.length - 1; j++) {
                a[j] = a[j] * i;
                if (j - 1 >= 0) {
                    a[j] += a[j - 1] / 10;
                    a[j - 1] = a[j - 1] % 10;
                }
                if (j - 2 >= 0) {
                    a[j] += a[j - 2] / 100;
                    a[j - 2] = a[j - 2] % 100;
                }
                if (j - 3 >= 0) {
                    a[j] += a[j - 3] / 1000;
                    a[j - 3] = a[j - 3] % 1000;
                }
            }
        }
        System.out.println("---------得出结果---------------------");
        for (int i = a.length - 1; i >= 0; i--)
            System.out.print(i % 100 != 0 ? a[i] : a[i] + "\n");
        //第二种方法,直接使用BigInteger,不过估计面试的时候不会允许使用BigInteger的~
        System.out.println();
        System.out.println("***用BIGINTEGER实现的算法******");
        java.math.BigInteger bans = java.math.BigInteger.valueOf(1);
        for (int i = 1; i <= 1000; i++)
            bans = bans.multiply(java.math.BigInteger.valueOf(i));
        System.out.print(bans);
    }
}
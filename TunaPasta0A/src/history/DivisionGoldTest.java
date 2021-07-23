package history;

//编写程序解决如下问题，某人将一箱黄金分5次出售，第一次卖出全部的一半加二分之一克；
//第二次卖出余下的三分之一加三分之一克； 第三次卖出余下的四分之一加四分之一克；
//第四次卖出余下的五分之一加五分之一克； 最后卖出余下的11克。
//问原来的箱子中共有多少克黄金？将结果打印输出。
public class DivisionGoldTest {
    public static void main(String args[]) {
        double n = 11;
        for (int i = 4; i >= 1; i--) {
            n += (n + 1) / i;
        }
        System.out.println(n);
//第二种,用递归的解法:		
        System.out.println(m(1));
    }

    static double m(double n) {
        if (n == 5) return 11;
        else return ((n + 1) * m(n + 1) + 1) / n;
    }
}
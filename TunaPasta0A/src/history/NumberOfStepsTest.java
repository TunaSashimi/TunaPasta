package history;

//上楼有1阶和2阶两种走法,问对于n阶的楼梯,共有多少种走法
public class NumberOfStepsTest {
    public static void main(String[] args) {
        System.out.println("请输入楼梯的台阶数n");
/*一阶1种走法,二阶2种走法(为特殊点),三阶走楼梯的时候先走一步就剩
 下二阶的走法;先走两步就剩下一阶的走法;四阶的时候先走一步剩下三阶
 的走法,先走两步剩下二阶的走法,即n+3阶为n+2阶与n+1阶的走法之和*/
        int n = new java.util.Scanner(System.in).nextInt();
        long[] a = new long[n + 2];//long型数组的长度只能为int型.
        a[0] = a[1] = 1;
        for (int i = 2; i <= n; i++) {
            a[i] = a[i - 1] + a[i - 2];
        }//此算法可以算到较大的台阶数,推荐!
        System.out.println(a[n]);
//另一种用组合数的算法,		只能算到21
        long sum = 0, t = 0; //sum为总走法,t为走二阶的次数
        for (; n >= 2 * t; t++) {
            sum += m(n - t) / (m(t) * m(n - 2 * t));
        }//用组合数公式,二阶走的次数为t,一阶为n-2*t,总数相加为n-t
        System.out.println(sum);
    }

    public static long m(long n) {
        long m = 1;
        for (; n > 1; n--) {
            m *= n;
        }
        return m;
    }
}

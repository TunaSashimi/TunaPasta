package history;

//约数：  整数a除以整数b(b≠0) 除得的商正好是整数而没有余数，我们就说a能被b整除。
// a叫b的倍数，b叫a的约数（或因数）。所指的一般都是正约数。约数和倍数相互依存
// 注意：一个数的约数包括1及其本身。 
public class ExploreGcdTest {
    public static void main(String[] args) {
        int m = new java.util.Scanner(System.in).nextInt();
        int n = new java.util.Scanner(System.in).nextInt();
        int j = 1;
        int k = m * n;     //这里的k是为第二种算法欧几里德做准备！
        if (n > m) {
            int t = m;
            m = n;
            n = t;
        }                //到目前为止为算法做准备！
//第一种算法：枚举法，将两个数的约数分别一一列出，从中找出其公约数，再从公约数中找出最大的一个，即为这两个数的最大公约数。 			
        for (int i = 1; i <= m; i++)
            if (m % i == 0 & n % i == 0)
                j = i;
        System.out.println("最大公约数为" + j + "  最小公倍数为" + m * n / j);
//第2种算法：辗转相除法(欧几里得算法)用f(x, y）表示x，y的最大公约数，取k = x/y， b = x%y，
        // 则x = ky + b，如果一个数能够同时整除x和y，则必能同时整除b和y，即x和y的公约数与
        // b和y的公约数是相同的，其最大公约数相同，则有f（x, y）= f（y, x%y）（y > 0）
        // 便把原问题转化为求两个更小数的最大公约数，直到其中一个数为0，
        // 剩下的另外一个数就是两者最大的公约数。
        for (; n > 0; ) {
            int i = m % n;
            m = n;
            n = i;
        }
        System.out.println("最大公约数为" + m + "  最小公倍数为" + k / m);
    }
}
//其他算法：分解质因数 ，再从中找出A、B公有的质因数，把这些公有的质因数相乘，即得A、B的最大公约数。
//更相减损术 :即“可半者半之，不可半者，副置分母、子之数，以少减多，更相减损，求其等也。以等数约之。(自己回去百度下~)

package history;

public class AddPrimeNumberTest {
    public static void main(String[] args) {
        long n;
        for (; ; ) {
            System.out.println("请输入一个不为2的偶数");
            n = new java.util.Scanner(System.in).nextLong();
            if (n % 2 == 0 & n > 2)
                break;
            System.out.println("输入错误，请重新输入");
        }
        for (long i = 2; i <= n / 2; i++) {
            int k = 0;
            for (long j = 2; j <= Math.pow(i, 0.5); j++)
                k += (i % j == 0) ? 1 : 0;
            if (k == 0)
                for (long a = 2; a <= Math.pow(n - i, 0.5); a++)
                    k += ((n - i) % a == 0) ? 1 : 0;
            if (k == 0)
                System.out.println(i + " " + (n - i));
        }
    }
}

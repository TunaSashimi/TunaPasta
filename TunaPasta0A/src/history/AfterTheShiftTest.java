package history;

public class AfterTheShiftTest {
    public static void main(String[] args) {
        System.out.println("请输入原有的几个数字的总数");
        int n = new java.util.Scanner(System.in).nextInt();
        int a[] = new int[n];
        System.out.println("请输入原有数字的各个数字值");
        for (int i = 0; i < n; i++)
            a[i] = new java.util.Scanner(System.in).nextInt();
        System.out.println("输入要往后移动的位数");
        int m = new java.util.Scanner(System.in).nextInt();
        System.out.println("原有的各数字为");
        for (int i = 0; i < n; i++)
            System.out.print(a[i % n] + " ");
        System.out.println();
        System.out.println("移动后的各数为");
        for (int i = m; i < n + m; i++)
            System.out.print(a[i % n] + " ");
    }
}

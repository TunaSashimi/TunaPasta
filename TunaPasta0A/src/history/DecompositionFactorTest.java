package history;

//将一个正整数分解质因数。例如：输入90,打印出90=2*3*3*5。
public class DecompositionFactorTest {
    public static void main(String[] args) {
        System.out.print("请键入一个正整数:");
        int i = new java.util.Scanner(System.in).nextInt();
        System.out.print(i + "=");
        for (int j = 2; j <= i; ) {
            if (i == j) {
                System.out.print(j);
                break;
            }
            if (i % j == 0) {
                System.out.print(j + "*");
                i = i / j;
            } else
                j++;
        }
    }
} 

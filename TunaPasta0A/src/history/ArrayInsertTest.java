package history;

public class ArrayInsertTest {
    public static void main(String[] args) {
        System.out.print("请输入原来数组的元素个数：");
        int a[] = new int[new java.util.Scanner(System.in).nextInt()];
        System.out.println("请输入原来数组的各个元素：");
        for (int i = 0; i < a.length; i++)
            a[i] = new java.util.Scanner(System.in).nextInt();

        System.out.println("请输入一个整数,用于插入原有的数组中:");
        int[] b = java.util.Arrays.copyOf(a, a.length + 1);
        b[a.length] = new java.util.Scanner(System.in).nextInt();
        java.util.Arrays.sort(b);
        System.out.print("新生成的的数组排序后显示为");
        for (int i = 0; i < b.length; i++)
            System.out.print(b[i] + " ");
    }
}

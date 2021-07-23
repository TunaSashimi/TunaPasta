package imitation;

public class HanoiTowerTest {
    static long s;

    public static void main(String[] args) {
        System.err.println("请输入汉诺塔层数");
        hanoi(new java.util.Scanner(System.in).nextInt(), 'A', 'B', 'C');// A为源,B为中介,C为目标;
        System.out.println("总共搬运次数为" + s);
    }

    public static void hanoi(int n, char a, char b, char c) {
        s++;
        if (n == 1)
            System.out.println("盘子" + n + "从" + a + "搬到" + c);
        else {
            hanoi(n - 1, a, c, b);
            System.out.println("盘子" + n + "从" + a + "搬到" + c);
            hanoi(n - 1, b, a, c);
        }
    }
}
// Towers()的参数是要移动的盘子的数目，以及会使用到的源塔座(a)
// 中介塔座(b)和目标塔座(c)。盘子数随着方法每调用一次就递减1。
// 源塔座、中介塔座和目标塔座也就发生变化 
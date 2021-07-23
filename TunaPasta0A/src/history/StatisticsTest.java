package history;

public class StatisticsTest {
    public static void main(String[] args) {
        int digital = 0, character = 0, blank = 0, other = 0;
        char ch[] = new java.util.Scanner(System.in).nextLine().toCharArray();
        for (char i : ch)
            if (i >= '0' & i <= '9')
                digital++;
            else if ((i >= 'a' & i <= 'z') | i >= 'A' & i <= 'Z')
                character++;
            else if (i == ' ')
                blank++;
            else
                other++;
        System.out.println("数字个数: " + digital);
        System.out.println("英文字母个数: " + character);
        System.out.println("空格个数: " + blank);
        System.out.println("其他字符个数:" + other);
    }
}

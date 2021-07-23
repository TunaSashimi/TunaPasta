package history;

public class StringSortTest {
    public static void main(String[] args) {
        System.out.println("请输入一个新字符串参与排序");
        String[] s = {"达内", "尹宁峰", "达人", "范佳才", "饭夹菜", new java.util.Scanner(System.in).nextLine()};
        String temp;
        for (int i = 0; i < s.length; i++)
            for (int j = i + 1; j < s.length; j++)
                if ((s[i].compareTo(s[j]) > 0)) {
                    temp = s[i];
                    s[i] = s[j];
                    s[j] = temp;
                }
        for (String i : s)
            System.out.println(i);

        //第二种方法:~~
        String[] x = {"达内", "尹宁峰", "达人", "范佳才", "饭夹菜", "达内内"};
        java.util.Arrays.sort(x);
        for (String i : x)
            System.out.println(i);
    }
}

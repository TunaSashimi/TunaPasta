package history;

public class CapitalRMBTest {
    public static void main(String[] args) {
        String[] a = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
        String[] b = {"", "拾", "佰", "仟", "万", "拾", "佰", "仟"};
        String result = "";
        int c = 0;
        boolean f = true;
        for (int n = new java.util.Scanner(System.in).nextInt(); n > 0; n /= 10) {
            String change = "";
            int m = n % 10;
            if (m != 0) {
                change = a[m] + b[c];
                f = false;
            } else if (!f) {
                change = a[m];
                f = true;
            }
            //如果该输万的时候万位为空,则补上单位~
            if (c == 4 & m == 0) {
                change = "万" + change;
            }
            result = change + result;
            c++;
        }
        System.out.println(result + "圆");
    }
}

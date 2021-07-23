package history;

public class TheSubStringTest {
    public static void main(String args[]) {
        System.out.print("请输入字符串：");
        String s1 = new java.util.Scanner(System.in).nextLine();
        System.out.print("请输入子串：");
        String s2 = new java.util.Scanner(System.in).nextLine();
//		int count = 0;
//		if (s1.equals("") || s2.equals("")) {
//			System.out.println("你输入的字符串或子串为空,无法比较!");
//			System.exit(0);
//		} else {
//			for (int i = 0; i <= s1.length() - s2.length(); i++) {
//				if (s2.equals(s1.substring(i, s2.length() + i))) {
//					count++;
//					i += s2.length() - 1;//为了防止把"aaa"看成有2个"aa"子串。 
//				}
//			}
//			System.out.println("子串在字符串中出现: " + count + " 次");
//		}
        //第二种方法:
        System.out.println("子串出现" + (s1.length() - s1.replaceAll(s2, "").length()) / s2.length() + "次");
    }
}

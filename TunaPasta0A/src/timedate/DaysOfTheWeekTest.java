package timedate;
public class DaysOfTheWeekTest {
	public static void main(String[] args) {
		getChar w = new getChar();
		System.out.println("请输入星期的第一个字母：");
		char getChar = w.get();
		switch (getChar) {
		case 'm':
			System.out.println("Monday");
			break;
		case 'w':
			System.out.println("Wednesday");
			break;
		case 'f':
			System.out.println("Friday");
			break;
		case 't':
			System.out.println("请输入星期的第二个字母：");
			char ch2 = w.get();
			if (ch2 == 'u')
				System.out.println("Tuesday");
			else if (ch2 == 'h')
				System.out.println("Thursday");
			else
				System.out.println("无此写法！");
			break;
		case 's':
			System.out.println("请输入星期的第二个字母：");
			ch2 = w.get();
			if (ch2 == 'u')
				System.out.println("Sunday");
			else if (ch2 == 'a')
				System.out.println("Saturday");
			else
				System.out.println("无此写法！");
			break;
		default:
			System.out.println("无此写法！");
		}
	}
}
class getChar {
	public char get() {
		char ch = new java.util.Scanner(System.in).nextLine().toLowerCase().charAt(0);
		if (ch < 'a' | ch > 'w') {
			System.out.println("输入错误，请重新输入");
			ch = get();
		}
		return ch;
	}
}

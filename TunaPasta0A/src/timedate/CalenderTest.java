package timedate;

import java.text.ParseException;

public class CalenderTest {
    public static void main(String[] args) throws ParseException {
        System.out.println("请按格式yyyy-MM-dd输入日期");
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.setTime(new java.text.SimpleDateFormat("yyyy-MM-dd").parse(new java.util.Scanner(System.in).nextLine()));
        System.out.println(cal.get(java.util.Calendar.DAY_OF_YEAR));

        //第二种方法:
//		int year, month, day;
//		for (;;) {
//			System.out.print("输入年：");
//			year = new java.util.Scanner(System.in).nextInt();
//			System.out.print("输入月：");
//			month =new java.util.Scanner(System.in).nextInt();
//			System.out.print("输入天：");
//			day = new java.util.Scanner(System.in).nextInt();
//			if (year < 0 | month < 0 | month > 12 | day < 0 | day > 31)
//				System.out.println("输入错误，请重新输入！");
//			else
//				break;
//		}
//		System.out.println(year + "-" + month + "-" + day );
//		for (int i = 1; i < month; i++) 
//			switch (i) {
//			case 1:
//			case 3:
//			case 5:
//			case 7:
//			case 8:
//			case 10:
//			case 12:day += 31;break;
//			case 4:
//			case 6:
//			case 9:
//			case 11:day += 30;break;
//			case 2:day += (year % 4 == 0 & year % 100 != 0 | year % 400 == 0) ? 29	: 28;
//		}
//		System.out.println("是这年中的第"+ day  + "天。");
    }
}
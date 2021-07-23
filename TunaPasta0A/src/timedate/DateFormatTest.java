package timedate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateFormatTest {
    public static void main(String[] args) throws ParseException {

        long currentTimeMillis = System.currentTimeMillis();
        System.out.println(currentTimeMillis);//1970.1.1到现在为止的毫秒数

        System.out.println("------------------------------------------------------------");

        Date date = new Date();
        Date date0 = new Date(0);

        System.out.println(date);//当前的系统时间
        System.out.println(date0);//1970.1.1

        System.out.println("------------------------------------------------------------");

        Calendar cal0 = new GregorianCalendar();//当前的系统时间
        Calendar cal1 = Calendar.getInstance();//另一种当前的系统时间
        System.out.println(cal0);
        System.out.println(cal1);
        cal0.setTime(new Date(0));//1970.1.1
        System.out.println(cal0);

        System.out.println(cal0.getTime().getTime());//long型数据
        System.out.println(cal1.getTime().getTime());//long型数据

        System.out.println("------------------------------------------------------------");

        //获取现在时间，并按照指定格式显示
        //把Date或Calendar类型的数据转换成为指定格式的字符串

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


        System.out.println(dateFormat.format(date));
        System.out.println(dateFormat.format(cal1.getTime()));

        //把字符串转换成为Date类型的数据
//        System.out.println(dateFormat.parse("1989-10-10 23:10:10"));


//			DateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
//			Date newYearEve=dateFormat.parse("2015-02-18");
//			Date newYearSeventh=dateFormat.parse("2015-02-24");
//			Date currentTime=new Date();
//			System.out.println(currentTime.after(newYearEve));
//			System.out.println(currentTime.before(newYearSeventh));

        System.out.println("------------------------------------------------------------");

        Date today = simpleDateFormat.parse(dateFormat.format(date) + " " + "23:10:10");
        System.out.println(today);

    }
}

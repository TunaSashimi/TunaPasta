package current;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalendarTest {
    public static void main(String[] args) throws ParseException {

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        calendar.setTime(sdf.parse("2013-07-03"));// 生产日期

        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);// 更新过期日期前周一
        String s1 = sdf.format(calendar.getTime());

        calendar.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);// 更新过期日期前周五
        String s2 = sdf.format(calendar.getTime());

        System.out.println("促消日期:" + s1 + "至" + s2);

        //分表
        System.out.println("215CDE93EB3099416102BA3624670C0E".hashCode() % 50);
    }
}

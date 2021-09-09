package current;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class getAgeTest {
    public static void main(String[] args) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String sftBirth = "1980-4-25";
        Date date = null;
        try {
            date = simpleDateFormat.parse(sftBirth);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int age = getAgeTest.getAge(date);
        System.out.print("age==>" + age);
    }

    public static int getAge(Date birthday) {
        Calendar now = Calendar.getInstance();
        Calendar date = Calendar.getInstance();
        date.setTime(birthday);
        if (now.before(birthday)) {
            throw new IllegalArgumentException("The birthday is before Now,It's unbelievable");
        }
        int yearNow = now.get(Calendar.YEAR);
        int monthNow = now.get(Calendar.MONTH);
        int dayNow = now.get(Calendar.DAY_OF_MONTH);
        int yearBirth = date.get(Calendar.YEAR);
        int monthBirth = date.get(Calendar.MONTH);
        int dayBirth = date.get(Calendar.DAY_OF_MONTH);
        int age = yearNow - yearBirth;
        if (monthNow < monthBirth || (monthNow == monthBirth && dayNow < dayBirth)) {
            age--;
        }
        return age;
    }
}

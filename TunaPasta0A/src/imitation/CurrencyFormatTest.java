package imitation;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

public class CurrencyFormatTest {
    public static void main(String args[]) {
        // 需要格式化的数据
        double value = 123456.784;
        // 设定Locale
        Locale cnLocale = new Locale("zh", "CN");
        Locale usLocale = new Locale("en", "US");
        // 得到local对应的NumberFormat对象
        NumberFormat cnNf = NumberFormat.getCurrencyInstance(cnLocale);
        NumberFormat usNf = NumberFormat.getCurrencyInstance(usLocale);
        // 将上边的double数据格式化输出
        System.out.println("China Currency Format:" + cnNf.format(value));
        System.out.println("United Currency Format:" + usNf.format(value));

        Locale locale = Locale.getDefault();
        System.out.println(locale);
        DateFormat df = DateFormat.getDateInstance(DateFormat.FULL, java.util.Locale.CHINA);
        System.out.println(df.format(new Date()));
    }
}	

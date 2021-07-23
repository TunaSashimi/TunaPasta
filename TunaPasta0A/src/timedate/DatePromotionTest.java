package timedate;


public class DatePromotionTest {
    public static void main(String[] args) throws Exception {
        System.out.println("请按格式yyyy-MM-dd输入日期");// 超市商品促销时间的确定：过期日前两个星期的周五
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.setTime(new java.text.SimpleDateFormat("yyyy-MM-dd").parse(new java.util.Scanner(System.in).nextLine()));// 生产日期
        cal.add(java.util.Calendar.MONTH, 3);// 计算过期日期
        cal.add(java.util.Calendar.WEEK_OF_YEAR, -2);
        cal.set(java.util.Calendar.DAY_OF_WEEK, java.util.Calendar.FRIDAY);// 更新过期日期前周五
        System.out.println("促消日期:" + new java.text.SimpleDateFormat("yyyy年MM月dd日").format(cal.getTime()));
    }
}
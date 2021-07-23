package reflect;

import java.lang.reflect.Field;

public class FunctionBean {

    private String s1;
    private String s2 = "4";

    public static void main(String[] args) {

        FunctionBean functionbean = new FunctionBean();

        // 获取所有的属性

        Field[] fields = functionbean.getClass().getDeclaredFields();

        try {

            for (Field f : fields) {

                System.out.println(f.getType().getName());
                System.out.println(f.getName());
                System.out.println(f.get(functionbean));

//				if (java.lang.String.class.getName().equals(f.getType().getName())) {
//					System.out.println(" 为字符串");
//				}

            }

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    // 从接口获取的方法

    // 上传到接口的方法

    // 保存到数据库方法

    // 类之间传送的方法

}

package reflect;

import java.util.Scanner;
import java.lang.reflect.*;

public class ClassTest {
    public String s1;//必须要public才能获取到
    String s2 = "s2";
    int a;
    int b = 3;

    public static void main(String[] args) throws Exception {
        System.out.println("请输入要反射的类目,类似java.lang.String或者reflect.ClassTest或者reflect.Compute：");

        Scanner sc = new Scanner(System.in);
        String className = sc.nextLine();
        sc.close();

        Class<?> clz = Class.forName(className);

        System.out.println("输入类名：" + clz.getName());// 获取类名

        // 获取所有的方法
        System.out.println("方法列表：");
        Method[] methods = clz.getMethods();
        for (Method m : methods) {
            System.out.println(m.getReturnType().getName() + " " + m.getName());
        }

        // 获取所有的构造器
        System.out.println("构造器列表：");
        Constructor<?>[] cons = clz.getDeclaredConstructors();
        for (Constructor<?> c : cons) {
            System.out.println(c.getName());
        }

        // 获取所有的属性
        System.out.println("属性列表：");
        Field[] fields = clz.getFields();
        for (Field f : fields) {
            System.out.println(f.getName());
        }

        if ("imitation.Compute".equals(clz.getName())) {
            Object o = clz.newInstance();// 调用构造器构造实例

            Class<?>[] types = {int.class, double.class};// 定义方法的参数类型
            Object[] params = {1, 2};
            Method m = clz.getDeclaredMethod("add", types);
            Object result = m.invoke(o, params);
            double d = (Double) result;
            System.out.println(d);
        }

    }
}

class Compute {
    // 一个int属性
    public int c;

    // 两个构造方法
    public Compute() {
    }

    public Compute(int c) {
        this.c = c;
    }

    // 定义方法
    public double add(int a, double b) {
        return a + b;
    }

    public double multi(int a, double b) {
        return a * b;
    }

}

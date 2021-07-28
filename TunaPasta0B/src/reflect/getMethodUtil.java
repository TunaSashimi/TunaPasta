package reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author TunaSashimi
 * @date 2021/7/25 20:19
 * @Copyright 2021 TunaSashimi. All rights reserved.
 * @Description
 */
//通过反射获取方法信息
public class getMethodUtil {
    public static void main(String[] args) {
//        Class<?> c1 = int.class;
//        Class<?> c2 = String.class;
//        Class<?> c3 = double.class;
//        Class<?> c4 = Double.class;
//        Class<?> c5 = void.class;
//        System.out.println(c1.getName());
//        System.out.println(c2.getName());
//        System.out.println(c2.getSimpleName());//不带包名的名称
//        System.out.println(c3.getSimpleName());//不带包名的名称
//        System.out.println(c4.getSimpleName());//不带包名的名称
//        System.out.println(c5.getSimpleName());//不带包名的名称

        String string = "hell";
//        printClassMethodMessage(string);
//        printFieldMessage(string);
        printConMessage(string);
    }

    /**
     * 打印类的信息，包括类的成员函数、成员变量(只获取成员函数)
     *
     * @param object 该对象所属类的信息
     */
    public static void printClassMethodMessage(Object object) {// 该类所属 的信息
        // 要获取类的信息，首先要获取类的类类型
        Class<?> cla = object.getClass();// 传递的是哪个子类的对象，class1就是该子类的类类型
        // 获取类的名称
        System.out.println("类的名称是：" + cla.getName());
        /**
         * Method类，方法对象 一个成员方法就是一个Method对象 getMehtod()方法
         * 获取的是所有得public的函数，包括父类继承的 getDeclaredMethods()获取的是所有该类声明的方法，不同访问权限
         */
        Method[] ms = cla.getMethods();
        for (int i = 0; i < ms.length; i++) {
            // 得到方法的返回值类型的类类型
            Class<?> returnType = ms[i].getReturnType();
            System.out.print(returnType.getName() + " ");
            // 得到方法的名称
            System.out.print(ms[i].getName() + "(");
            // 获取参数类型
            Class[] paramTypes = ms[i].getParameterTypes();
            for (Class class2 : paramTypes) {
                System.out.print(class2.getName() + ",");
            }
            System.out.println(")");

            printFieldMessage(cla);
        }
    }

    /**
     * 获取成员变量信息
     *
     * @param object
     */
    private static void printFieldMessage(Object object) {
        // 要获取类的信息，首先要获取类的类类型
        Class<?> cla = object.getClass();// 传递的是哪个子类的对象，class1就是该子类的类类型
        // 获取类的名称
        System.out.println("类的名称是：" + cla.getName());
        /**
         * 成员变量也是对象 java.lang.reflect.Field Field类封装了关于成员变量的操作
         * getFields()方法获取的是所有的public的成员变量的信息
         * getDeclaredFields获取的是该类自己声明的成员变量的信息
         */
        Field[] fs = cla.getDeclaredFields();
        for (Field field : fs) {
            // 得到成员变量的类型的类类型
            Class<?> filedType = field.getType();
            String typeName = filedType.getName();
            String fieldName = field.getName();
            System.out.println(typeName + " " + fieldName);
        }
    }

    /**
     * 打印对象的构造函数的信息
     *
     * @param object
     */
    public static void printConMessage(Object object) {
        Class<?> cla = object.getClass();
        /**
         * 构造函数也是对象 java.lang.Constructor中封装了构造函数的信息
         * getConstructors获取所有的public的构造函数 getDeclaredConstructors得到所有的构造函数
         */
        Constructor[] cs = cla.getDeclaredConstructors();
        for (Constructor constructor : cs) {
            System.out.print(constructor.getName() + "(");
            // 获取构造函数的参数列表--->得到的是参数列表的类类型
            Class[] paramTypes = constructor.getParameterTypes();
            for (Class class2 : paramTypes) {
                System.out.print(class2.getName() + ",");
            }
            System.out.println(")");
        }
    }

}

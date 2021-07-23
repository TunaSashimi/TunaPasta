package reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

class Person00 {
}

class Person01 {
    private int i;
    private int j;

    //构造方法不公开实例化不了~
    public Person01(int i, int j) {
        this.i = i;
        this.j = j;
    }

    @Override
    public String toString() {
        return "Person01 [i=" + i + ", j=" + j + "]";
    }
}

public class ClassInstanceDemo {
    public static void main(String[] args) {
        Person00 p = new Person00();
        System.out.println(p);
        System.out.println(p.getClass());

        System.out.println("三种方法实例化方法如下,最常用forName()方法,其次是类.class的形式");

        //第一种方法
        System.out.println("第一种方法" + p.getClass().getName());
        //第二种方法
        System.out.println("第二种方法" + Person00.class.getName());
        //第三种方法
        try {
            Class<?> c = Class.forName("reflect.Person00");
            System.out.println("第三种方法" + c.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("实例化有参构造类的方法如下:");
        try {
            Class<?> c = Class.forName("reflect.Person01");
            Constructor<?>[] cons = c.getConstructors();
            Person01 p2 = (Person01) cons[0].newInstance(2, 3);
            System.out.println(p2);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}

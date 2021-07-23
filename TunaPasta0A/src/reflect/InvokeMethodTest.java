package reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class Person03 {
    public void sayHello() {
        System.out.println("Hello");
    }

    public String getInfo() {
        return "HelloWorld";
    }

    public String say(int a, int b) {
        return a + "HelloWorld" + b;
    }
}

public class InvokeMethodTest {
    public static void main(String[] args) {
        try {
            //本程序将调用Person03中的sayHello()方法
            Class<?> c = Class.forName("reflect.Person03");
            //先通过getMethod()方法得到Method,入口是可变参数,因方法调用时候可能有多个参数
            Method m = c.getMethod("sayHello");
            //通过反射机制来调用类中的方法~
            m.invoke(c.newInstance());

            //调用有返回值的方法~
            Method m1 = c.getMethod("getInfo");
            String info = (String) m1.invoke(c.newInstance());
            System.out.println(info);

            //调用有参数,有返回值的方法~
            Method m2 = c.getMethod("say", int.class, int.class);
            String say = (String) m2.invoke(c.newInstance(), 2, 3);
            System.out.println(say);


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}

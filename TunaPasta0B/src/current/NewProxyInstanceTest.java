package current;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author TunaSashimi
 * @date 2021-06-28 22:12
 * @Copyright 2021 TunaSashimi. All rights reserved.
 * @Description
 */
public class NewProxyInstanceTest {
    public interface ITest {
        void add(int a, int b);
    }

    //
    public static void main(String[] args) {
        ITest iTest = (ITest) Proxy.newProxyInstance(ITest.class.getClassLoader(), new Class[]{ITest.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) {
                Integer a = (Integer) args[0];
                Integer b = (Integer) args[1];
                System.out.println("方法名：" + method.getName());
                System.out.println("参数：" + a + " , " + b);
                return null;
            }
        });
        iTest.add(3, 5);
    }
}

package current;

/**
 * @author Tunasashimi
 * @date 2018/7/12 20:05
 * @Copyright 2018 TunaSashimi. All rights reserved.
 * @Description
 */
public class ClassLoaderTest {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getContextClassLoader());
        System.out.println(ClassLoaderTest.class.getClassLoader());
        System.out.println(System.class.getClassLoader());
        System.out.println(ClassLoader.getSystemClassLoader());
        System.out.println(ClassLoader.getSystemClassLoader().getParent());
        System.out.println(ClassLoader.getSystemClassLoader().getParent().getParent());
    }
}

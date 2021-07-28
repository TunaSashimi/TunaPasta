package annotaion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

public class AnnotationDemo {
    //
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public static @interface AnnotationDemoInterface {
        boolean value();
    }


    public static void main(String[] args) {
        new Teacher();
        new Person();
    }

    public static class Person {
        @AnnotationDemoInterface(value = false)
        public int age;
        public String name;

        public Person() {
            //		System.out.println(Person.class == this.getClass());
            try {
                Field f = this.getClass().getField("age");
                AnnotationDemoInterface a = f.getAnnotation(AnnotationDemoInterface.class);
                System.out.println(a.value());
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (SecurityException e) {
                e.printStackTrace();
            }
        }
    }

    public static class Student extends Person {
        @AnnotationDemoInterface(value = true)
        public int age = 1;

        public Student() {
        }
    }

    public static class Teacher extends Person {

        @AnnotationDemoInterface(value = true)
        public int age = 1;

        public Teacher() {
        }

    }
}

package annotaion;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

/**
 * @author TunaSashimi
 * @date 2021/7/25 19:57
 * @Copyright 2021 TunaSashimi. All rights reserved.
 * @Description
 */
public class AnnotationTest {

    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    @Inherited
    @Target(ElementType.METHOD)//注解作用于方法
    public @interface AnnotationTestInterface {
        String name() default "张三丰";

        int abilityNum() default 1;

        String[] abilityNames() default {"太极拳"};
    }

    @AnnotationTestInterface(name = "令狐冲", abilityNum = 2, abilityNames = {"独孤九剑", "吸星大法"})
    public void people1(String name, int age) {
    }

    @AnnotationTestInterface(name = "段誉", abilityNum = 2, abilityNames = {"凌波微步", "六脉神剑"})
    public void people2(String name, int age) {
    }

    @AnnotationTestInterface     //没有配置，则使用默认值
    public void people3(String name, int age) {
    }

    public static void main(String[] args) {
        Method[] methods = AnnotationTest.class.getMethods();//反射获取所有方法

        for (Method m : methods) { //遍历所有方法
            if (m.isAnnotationPresent(AnnotationTestInterface.class)) {//判断方法是否有MyAnnotation注解

                AnnotationTestInterface myAnno = m.getAnnotation(AnnotationTestInterface.class);

                System.out.print(myAnno.name() + "有" + myAnno.abilityNum() + "个技能： ");

                for (String abilityName : myAnno.abilityNames()) {
                    System.out.print(abilityName + " ");
                }
                System.out.println();
            }

        }
    }
}
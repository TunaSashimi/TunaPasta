package current;

import java.lang.reflect.Field;

public class GetDeclaredFieldsTest {
	
	String a = "abc";
	String b = "bcd";
	
	public static void main(String[] args) {
		Class c = GetDeclaredFieldsTest.class;
		String className = c.getName();
		System.out.println("类名："+className);
		
		Field[] fields = c.getFields();
		System.out.println("======实现的接口或者父类的属性 begin=====");
		for (Field field : fields) {
			System.out.println(field.getName());
		}
		System.out.println("======实现的接口或者父类的属性 end=====");
		
		System.out.println("======本来全局属性 begin=====");
		Field[] declaredFields = c.getDeclaredFields();
		for (Field field : declaredFields) {
			System.out.println(field.getName());//用这个来get,set!
		}
		System.out.println("======本来全局属性 end=====");
	}}

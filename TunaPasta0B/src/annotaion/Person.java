package annotaion;

import java.lang.reflect.Field;

public class Person {
	
	@AnnotationTest(value=false)
	public int age;
	public String name;
	
	public Person() {
//		System.out.println(Person.class == this.getClass());
		
		try {
			Field f = this.getClass().getField("age");
			AnnotationTest a = f.getAnnotation(AnnotationTest.class);
			System.out.println(a.value());
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		
	}

}

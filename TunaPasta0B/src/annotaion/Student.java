package annotaion;

public class Student extends Person{

	@AnnotationTest(value = true)
	public int age = 1;

	public Student(){
	}

}

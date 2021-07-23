package annotaion;

public class Teacher extends Person{

	@AnnotationTest(value = true)
	public int age = 1;

	public Teacher(){
	}

}

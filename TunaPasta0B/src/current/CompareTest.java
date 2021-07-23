package current;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
public class CompareTest {
	public static void main(String[] args) {
		ArrayList<Student>studentlist=new ArrayList();
		studentlist.add(new Student(3,"zhang"));
		studentlist.add(new Student(1,"zhang"));
		studentlist.add(new Student(2,"zhang"));
		studentlist.add(new Student(4,"zhang"));
		
		System.out.println(studentlist);
		
		Collections.sort(studentlist, new Comparator<Student>() {
			public int compare(Student arg0, Student arg1) {
				return arg0.age-arg1.age;
			}
		});
		
		System.out.println(studentlist);
	}

}
class Student {
	int age;
	String name;
	public Student(int age, String name) {
		this.age = age;
		this.name = name;
	}
	public String toString() {
		return "Student [age=" + age + ", name=" + name + "]";
	}
}

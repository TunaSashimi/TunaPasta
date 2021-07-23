package history;

public class ComparableTest implements Comparable<Object> {
    private int id;
    private String name;
    int age;
    private int score;

    public ComparableTest(int id, String name, int age, int score) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.score = score;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String toString() {
        return id + ", " + name + ", " + age + ", " + score;
    }

    public static void main(String[] args) {
        ComparableTest[] ss = new ComparableTest[3];
        ss[0] = new ComparableTest(100, "liusong", 30, 90);
        ss[1] = new ComparableTest(45, "liyi", 22, 95);
        ss[2] = new ComparableTest(58, "tangliang", 35, 93);
        java.util.Arrays.sort(ss);
        //可以采取两种方式定义回调函数
        //1.使用匿名类定义回调方法
//		java.util.Arrays.sort(ss, new java.util.Comparator<Student>(){
//			public int compare(Student s1, Student s2) {
//				return s1.name.length()-s2.name.length();
//			}
//		});
        for (ComparableTest s : ss)
            System.out.println(s);
    }

    //2.让Student类实现Comparable接口，并在compareTo方法中定义比较原则。
    public int compareTo(Object s) {
        return this.age - ((ComparableTest) s).age;
//		return ((Integer)this.age).compareTo(((ComparableDemo)s).age);  调用Integer的compare方法!
    }
}
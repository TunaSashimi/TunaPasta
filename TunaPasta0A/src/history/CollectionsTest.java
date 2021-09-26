package history;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CollectionsTest {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList();
        list.add("Tom");
        list.add("Michael Scofield");
        list.add("Jerry");
        list.add("Jenny");
        list.add("Andy");
        list.add("Mac");
        System.out.println(list);
        // 排序,使用字符串的compareTo方法，即按字典顺序排序
        Collections.sort(list);
        System.out.println(list);
        // 使用自定义比较器（按字符长度排序），给字符集合list排序
        // 第一个参数：要排序的集合；
        // 第二个参数：排序原则
        // 数据和排序原则分开
        // CallBack：回调
        Collections.sort(list, (o1, o2) -> o2.length() - o1.length());
        System.out.println(list);

        List<Per> personList = new ArrayList();
        personList.add(new Per(1001, "张无忌", 78, 25));
        personList.add(new Per(1000, "张三丰", 89, 90));
        personList.add(new Per(1002, "赵敏", 95, 20));
        System.out.println(personList);
        java.util.Collections.sort(personList);
        System.out.println(personList);
        //使用collections的二分搜索法来查找!!
        System.err.println(Collections.binarySearch(personList, new Per(1001, "张无忌", 78, 25)));
    }
}
// 定义比较器:比较原则是按照字符串的长度

/**
 * 注意,这里给写成了匿名类的方法,所以原来的ByLength类名也可以省略!!
 */
//class ByLength implements java.util.Comparator<String> {
//	public int compare(String s1, String s2) {
//		return s1.length() - s2.length();
//	}
//}
// 自定义类的排序实现
class Per implements Comparable<Object> {
    int id;
    String name;
    int score;
    int age;

    public Per(int id, String name, int score, int age) {
        this.id = id;
        this.name = name;
        this.score = score;
        this.age = age;
    }

    public String toString() {
        return "[" + id + ", " + name + ", " + score + "," + age + "]";
    }

    // 自定义排序原则
    public int compareTo(Object obj) {
        // return score - ((Person)obj).score;//按score排序
        return age - ((Per) obj).age;// 按age排序
    }
}
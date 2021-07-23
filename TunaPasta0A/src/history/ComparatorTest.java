package history;

import java.util.Comparator;

public class ComparatorTest {
    public static void main(String[] args) {
        String[] names = {"Tom", "Jerry", "Andy", "En", "Michael", "Scofield"};
        java.util.Arrays.sort(names, new Comparator<String>() {
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });
//		有时在实现Comparator接口时，并没有实现equals方法，可程序并没有报错，
//		原因是实现该接口的类也是Object类的子类，而Object类已经实现了equals方法
        for (String name : names)
            System.out.println(name);

        java.util.List<String> list = new java.util.ArrayList();
        list.add("Scofield");
        list.add("peter");
        list.add("Tom");
        list.add("Jenny");
        list.add("Michael");
        java.util.Collections.sort(list, new Comparator<String>() {
            public int compare(String name1, String name2) {
                return name2.length() - name1.length();
            }
        });
        System.out.println(list);
    }
}

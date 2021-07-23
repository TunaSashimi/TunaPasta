package history;

import java.util.ArrayList;
import java.util.List;

public class ArrayListTest {
    public static void main(String[] args) {
        //泛型的使用
        List<Music> l = new ArrayList<>();
//		boolean add(E e)     将指定的元素添加到此列表的尾部 ,该方法返回布尔值
        System.out.println(l.add(new Music(2, "月亮之上")));
        l.add(new Music(1, "忐忑"));
        l.add(new Music(3, "青花瓷"));
//		 void add(int index, E element)  将指定的元素插入此列表中的指定位置,无返回值~
        l.add(0, new Music(4, "甜蜜蜜"));
        l.remove(2);

        System.out.println(l);
        System.out.println(l.get(2));
        System.out.println(l.indexOf(l.get(2)));
        System.out.println(l.contains(l.get(2)));
        System.out.println(l.contains(new Music(3, "青花瓷")));

        for (Music m : l)//对象集合中的是Music对象,集合名l
            System.out.print(m + " ");
        System.out.println("下一种表述");
        for (int i = 0; i < l.size(); i++)
            System.out.print(l.get(i) + " ");
    }
}

class Music {
    private int id;
    private String name;

    public Music(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String toString() {
        return name;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof Music) {
            Music m = (Music) obj;
            return m.id == this.id;
        }
        return false;
    }

    public int hashCode() {
        return id;
    }
}
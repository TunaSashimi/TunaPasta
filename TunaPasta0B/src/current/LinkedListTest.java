package current;

import java.util.LinkedList;
import java.util.List;

public class LinkedListTest {
    public static void main(String[] args) {
        List linkedList = new LinkedList<>();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
        linkedList.add(5);

        System.out.println("E==>"+linkedList.remove(0));
        System.out.println("E==>"+linkedList.remove(0));
        System.out.println("E==>"+linkedList.remove(0));
    }
}

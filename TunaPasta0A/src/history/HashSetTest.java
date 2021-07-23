package history;

import java.util.HashSet;

/**
 * @author TunaSashimi
 * @date 2020-05-25 11:14
 * @Copyright 2020 TunaSashimi. All rights reserved.
 * @Description
 */
public class HashSetTest {

    public static void main(String[] args) {
        HashSet set = new HashSet();
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(4);
        set.add(5);

        set.remove(5);

        System.out.println(set.contains(1));
        System.out.println(set.contains(2));
        System.out.println(set.contains(5));
        System.out.println(set.contains(6));
    }
}


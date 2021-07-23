package current;

/**
 * @author TunaSashimi
 * @date 2020-07-28 16:13
 * @Copyright 2020 TunaSashimi. All rights reserved.
 * @Description
 */
public class VariableParameterTest {
    static String[] names = {"jerry", "tom", "rose"};

    //
    public static void main(String[] args) {
        print();
        print("jerry", "tom");
        print(names);
    }

    public static void print(String... names) {
        for (String name : names) {
            System.out.print(name + " ");
        }
        System.out.println();
    }
}

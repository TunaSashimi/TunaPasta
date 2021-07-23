package imitation;
//导入的是Scanner包~

import java.util.Scanner;

//不写即为友好的.同一包中的可以引用~
class VariableArguTest {
    public String name = "hello";

    /**
     * @param args
     */
    @SuppressWarnings("unused")
    public static void main(String[] args) {
        char[] a = {'a', 'b', 'c', 'd', 'e', 'j', 'a', 'v', 'a'};
        String s = String.valueOf(a);
        String result = s.substring(s.length() - 4);
        System.out.println(result);
        foo(2, 4);
        Scanner sc = new Scanner(System.in);
    }

    //可变参数类似数组
    static void foo(int... x) {
        for (int i = 0; i < x.length; i++) {
            System.out.println(x[i]);
        }
        for (int i : x) {
            System.out.println(i);
        }
    }

}

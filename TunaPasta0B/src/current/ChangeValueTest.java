package current;

public class ChangeValueTest {
    static String s;

    public static void main(String[] args) {
        A a = new A();
        System.out.println(a.a);
        change(a);

        System.out.println(a.a);
    }

    private static void change(A a) {
        a.a = 2;
        if (a.a == 2) {
            return;
        }
        System.out.println(12121);
    }
}

class A {
    int a;
}
package history;

interface AAA {
    void test();
}

public class AnonymousVisitTest {
    public static void main(String[] args) {
        final int age = 0;        // 使用匿名类访问局部变量的时候,局部变量必须用final修饰,否则报错!
        AAA a = new AAA() {
            @Override
            public void test() {

                System.out.println(age);
            }
        };
        a.test();
    }
}

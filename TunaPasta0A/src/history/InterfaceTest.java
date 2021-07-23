package history;

public class InterfaceTest {
    public static void main(String[] args) {
//	一个类中同时使用不同接口中方法相同,返回值不同的方法,要么改方法名,要么用内部类的方法实现
        System.out.println(A.a);
        new A() {
            @Override
            public void f() {
            }
        }.f();
//通过匿名内部来来实例化接口的对象调用方法~		
        new B() {
            @Override
            public int f() {
                return 0;
            }
        }.f();

    }
}

interface A {
    int a = 10;

    void f();
}

interface B {
    int a = 20;

    int f();
}
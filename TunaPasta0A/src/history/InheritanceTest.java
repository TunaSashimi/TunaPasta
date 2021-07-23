package history;

public class InheritanceTest {
    public static void main(String[] args) {
        FatherClass cc = new ChildClass();
        cc.f();
    }
}

class FatherClass {
    int value;

    void f() {
        value = 100;
        System.out.println("Father" + value);
    }
}

class ChildClass extends FatherClass {
    int value;

    void f() {
        super.f();
        value = 200;
        System.out.println("Child" + value);
        System.out.println(value);
        System.out.println(super.value);
    }
}
package current;

public class ClassATest {
    public static void main(String[] args) {
        ClassATest a = new ClassATest();
        a.methodA();
    }

    public void methodA() {
        ClassB classB = new ClassB();
        classB.getValue();
    }
}

//没有ClassC=new ClassC,报空指针异常
class ClassB {
    public ClassC classC;

    public String getValue() {
        return classC.getValue();
    }
}

class ClassC {
    public String value;

    public String getValue() {
        value = "ClassB";
        return value;
    }
}
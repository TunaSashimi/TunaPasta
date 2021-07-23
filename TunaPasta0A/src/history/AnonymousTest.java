package history;

interface Product {
    double getPrice();

    String getName();
}

public class AnonymousTest {        //匿名内部类,就是子类继承父类或帮父类实例化时,隐藏子类名字的形式~
    public void test(Product p) {
        System.out.println("购买了一个" + p.getName() + ",花掉了" + p.getPrice());
    }

    public static void main(String[] args) {
        AnonymousTest ta = new AnonymousTest();
        ta.test(new Product() {    //new的时候帮助实现抽象方法完成实例化~
            public double getPrice() {
                return 567.8;
            }

            public String getName() {
                return "AGP显卡";
            }
        });
    }
}

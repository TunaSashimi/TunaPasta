package history;

abstract class Device {
    private String name;

    public abstract double getPrice();

    public Device() {
    }

    public Device(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

public class AnonymousInner {                //匿名内部类,子类继承父类或帮父类实例化时,隐藏子类名字的形式~
    public void test(Device d) {
        System.out.println("购买了一个" + d.getName() + ",花掉了" + d.getPrice());
    }

    public static void main(String[] args) {
        AnonymousInner ai = new AnonymousInner();
        ai.test(new Device("电子示波器") {    //调用一参方法,使用父类一参构造器的时候,通过实现方法完成实例化~
            @Override
            public double getPrice() {
                return 67.8;
            }
        });
        ai.test(new Device() {        //调用无参方法,实现抽象的同时,这里还重写了父类的初始化块和实例方法~
            // 初始化块
            {
                System.out.println("重写的匿名内部类初始化块");
            }

            // 实现抽象方法
            @Override
            public double getPrice() {
                return 56.2;
            }

            // 重写父类的实例方法
            public String getName() {
                return "键盘";
            }
        });
    }
}

package history;

class aa {
    static {
        System.out.println("执行静态块");
    }

    {
        System.out.println("执行构造块");
    }

    aa() {
        System.out.println("执行构造方法块");
    }
}

public class VariousMethodsTest {
    public static void main(String[] args) {
        // 静态方法块最先执行,只执行一次,构造块随后执行执行多次,构造方法块再后执行执行多次
        new aa();
        new aa();
    }
}

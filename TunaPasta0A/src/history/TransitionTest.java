package history;

public class TransitionTest {
    public static void main(String[] args) {
        People p = new Superman();
        System.out.println(p.a);
        //强转的一次到位与多次到位~
        System.out.println(((Superman) ((Man) p)).c);
        System.out.println(((Superman) p).c);
    }
}

class People {
    int a = 0;
}

class Man extends People {
    int b = 1;
}

class Superman extends Man {
    int c = 2;
}
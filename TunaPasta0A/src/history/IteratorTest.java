package history;

public class IteratorTest {
    public static void main(String[] args) {
        java.util.List<String> list = new java.util.ArrayList();
        list.add("咸鸭蛋");
        list.add("白鸡蛋");
        list.add("咸鸭蛋");
        list.add("松花蛋");
        for (String s : list)
            System.out.println(s);
        System.out.println(list);
    }
}

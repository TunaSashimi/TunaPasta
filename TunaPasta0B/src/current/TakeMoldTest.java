package current;

public class TakeMoldTest {
    public static void main(String[] args) {
        int index = -1 % 3;
        if (index < 0) {
            index = index + 3;
        }
        System.out.println("==>" + index);
    }
}

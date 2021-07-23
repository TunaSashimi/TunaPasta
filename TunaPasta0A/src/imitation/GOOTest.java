package imitation;

public class GOOTest {
    public static void main(String[] args) {
        String o = "";
        int count = 0;
        // 注意答案~
        count += count++;
        System.out.println(count);
        z:
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 2; y++) {
                if (x == 1)
                    break;
                if (x == 2 && y == 1)
                    break z;
                o = o + x + y;
            }
        }
        System.out.println(o);
        try {
            System.exit(0);
        } finally {
            System.out.println("不能能执行了~");
        }
    }
}

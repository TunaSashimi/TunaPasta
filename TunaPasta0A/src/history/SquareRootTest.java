package history;

//题目：一个整数，它加上100后是一个完全平方数，
//再加上168又是一个完全平方数，请问该数是多少？ 
public class SquareRootTest {
    public static void main(String[] args) {
        for (int x = -100000; x <= 100000; x++)
            if (Math.sqrt(x + 100) % 1 == 0 & Math.sqrt(x + 268) % 1 == 0)
                System.out.print(x + "  ");
    }
}
//注意看到完整的控制台，避免漏看答案！！
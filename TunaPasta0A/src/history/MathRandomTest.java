package history;

//随机生成1-36的整数7个并输出,第一个一行的程序~
public class MathRandomTest {
    public static void main(String[] args) {
        for (java.util.Set<Integer> integerHashSet = new java.util.HashSet<Integer>();
             integerHashSet.size() < 8;
             System.err.print(integerHashSet.add((int) (Math.random() * 36 + 1)) ?
                     integerHashSet.size() == 7 ? String.valueOf(integerHashSet) : "" : ""))
            ;
    }
}

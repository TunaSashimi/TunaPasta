package history;

public class AutuIntoBoxTest {
    public static void main(String[] args) {
        //自动装箱的极限是byte值的上限,一旦超过127,
        //系统就为对象分配不同的引用指向
        Integer t1 = 127;
        Integer t2 = 127;
        System.out.println(t1 == t2);
        Integer t3 = (int) 6.5;
        Integer t4 = 6;
        System.out.println(t3 == t4);
        Integer t5 = 128;
        Integer t6 = 128;
        System.out.println(t5 == t6);
    }
}

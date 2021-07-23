package history;

public class LaterInOverLayTest {
    //将字符串中的元音字符用Treemap收集(有序),采用LaterIn(后入式)经典算法完成,值得学习!
    public static void main(String[] args) {
        java.util.Map<Character, Integer> hm = new java.util.TreeMap<Character, Integer>();
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ  ~!@#$%^&*()_+   <>?";
        char[] ch = str.toLowerCase().toCharArray();
        for (char i : ch)
            if (((i - 97) / 4 * 4 + (i - 97) / 12 * 2 - (i - 97) / 17 * 2 == i - 97) & Math.abs(i - 109.5) <= 12.5)
                hm.put(i, 1 + (hm.containsKey(i) ? hm.get(i) : 0));
        System.out.println(hm);
    }
}

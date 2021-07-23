package history;

public class HashMapSpeedTest {
    public static void main(String[] args) {
        // 装数据.用两个map来比较
        java.util.Map<Integer, String> hs1 = new java.util.HashMap();
        java.util.Map<Integer, String> hs2 = new java.util.HashMap();
        //定义两个变量来计算循环次数
        int a = 0, b = 0;

        for (int i = 0; i < 100000; i++)
            hs1.put(i, "JS");
        for (int i = 0; i < 100000; i++)
            hs2.put(i, "JS");

        long stime0 = System.currentTimeMillis();
        //迭代的对象是hs1.KeySet,类型是Object,形参是Key
        for (Integer key : hs1.keySet()) {
            System.out.println(key + hs1.get(key));
            a++;
        }

        long stime1 = System.currentTimeMillis();
        //迭代的对象是hs2.entrySet(),类型是java.util.Map.Entry<Integer,String>,形参是e
        for (java.util.Map.Entry<Integer, String> e : hs2.entrySet()) {
            System.out.println(e.getKey() + e.getValue());
            b++;
        }

        long stime2 = System.currentTimeMillis();
        //	第一种keySet其实是遍历了2次，一是转为iterator，一是从hashmap中取出key所对于的value。
        //	第二种entryset只是遍历了第一次,故推荐使用第二种方式!
        System.out.println((stime1 - stime0));
        System.out.println((stime2 - stime1));
        System.out.println(a);
        System.out.println(b);//本题中对于十万个简单引用数据的处理,Entry方法快了120毫秒左右~
    }
}

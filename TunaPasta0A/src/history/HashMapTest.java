package history;

public class HashMapTest {
    public static void main(String[] args) {
        java.util.Map<String, Player> m = new java.util.HashMap();
        m.put("东", new Player("莫小贝"));
        m.put("南", new Player("貂蝉"));
        m.put("西", new Player("欧阳锋"));
        m.put("北", new Player("小乔"));
        // 通过key找到value
        System.out.println(m.containsKey("北"));
        System.out.println(m.get("东"));
        m.put("东", new Player("王菲"));
        System.out.println(m.get("东"));
        // remove之前和之后打印
        System.out.println(m);
        m.remove("东");
        System.out.println(m);

        System.out.println(m.keySet());
        System.out.println(m.values());
        //遍历HashMap
        for (java.util.Map.Entry<String, Player> e : m.entrySet())
            System.out.println(e.getKey() + "-->" + e.getValue());
    }
}

// 玩牌的人
class Player {
    String name;

    public Player(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
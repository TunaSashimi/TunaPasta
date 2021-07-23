package history;

public class NameMatchesTest {
    public static void main(String[] args) {
        System.out.println(test1("A1"));
        System.out.println(test1("1A"));
        System.out.println(test1("Sample02"));
        System.out.println(test1("_name"));
        System.out.println(test1(""));
        System.out.println(test1("$1"));

//将字符串中的单词out和is变大写,考虑下是否能out和is一起换?		
        String str = "out tom is out a good boy!lookout is window,go out!his this is a apple,out of my house";
        System.out.println(str.replaceAll("\\bout\\b", "out".toUpperCase()).replaceAll("\\bis\\b", "IS"));
    }

    public static boolean test1(String str) {
        if (str == null) {
            return false;
        }
        //Pattern:正则表达式的编译表示形式,Matcher:模式匹配器
        return java.util.regex.Pattern.matches("^[a-zA-Z_\\$](\\w|\\$)*$", str);//用于匹配类名规则
    }
}

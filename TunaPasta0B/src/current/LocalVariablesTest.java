package current;

public class LocalVariablesTest {
    public static void main(String args[]){
        //java局部变量没有默认值，编译不能通过
//        String s ;
        String s = null;
        System.out.println(s);
    }
}

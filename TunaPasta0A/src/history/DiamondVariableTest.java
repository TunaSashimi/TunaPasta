package history;

public class DiamondVariableTest {
    public static void main(String[] krgs) {
        for (int i = 0, j = 0, k = 0; j <= 2 * k * (2 * k - 1) + 2 * k; System.err.print(++i == 1 ? "请输入菱形的边长" : i == 2 ? (k = new java.util.Scanner(System.in).nextInt()) + (j = 1 + 2 * k) > 0 ? "" : "" : j % (2 * k) == 0 ? j++ > 0 ? "\n" : "\n" : String.valueOf((Math.abs(j % (2 * k) - k) == Math.abs(k - 1 - Math.abs(j / (2 * k) - k)) ? j++ > 0 ? '*' : '*' : j++ > 0 ? ' ' : ' '))))
            ;
    }
}
//原代码:
// System.out.println("请输入所要菱形的边长");
// for(int k=new java.util.Scanner(System.in).nextInt(),j=1+2*k;j<=2*k*(2*k-1)+2*k;System.err.print(j%(2*k)==0?j++>0?"\n":"\n":(Math.abs(j%(2*k)-k)==Math.abs(k-1-Math.abs(j/(2*k)-k))?j++>0?'*':'*':j++>0?' ':' ')));
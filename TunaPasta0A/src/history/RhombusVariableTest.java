package history;

public class RhombusVariableTest {
    public static void main(String[] krgs) {
        System.out.println("请输入所要菱形的边长");
        for (int k = new java.util.Scanner(System.in).nextInt(), i = 1 + 2 * k; i <= 2 * k * (2 * k - 1) + 2 * k; System.err.print(i % (2 * k) == 0 ? i++ > 0 ? "\n" : "\n" : (Math.abs(i % (2 * k) - k) == Math.abs(k - 1 - Math.abs(i / (2 * k) - k)) ? i++ > 0 ? '*' : '*' : i++ > 0 ? ' ' : ' ')))
            ;
        //只要把之前的迭代放入执行语句的最后,判断下对错都要执行即可~通式啊!!!!
    }
}
//		第二种方法:
//		System.out.println("请输入所要菱形的边长");
//		int k=new java.util.Scanner(System.in).nextInt();
//		for(int i=1+2*k;i<=2*k*(2*k-1)+2*k;i++)
//				System.err.print(i%(2*k)==0?"\n":(Math.abs(i%(2*k)-k)==Math.abs(k-1-Math.abs(i/(2*k)-k))?'*':' '));

// 	第三种方法:
//		for(int a=1;a<=2*k-1;a++)
//			for(int b=1;b<=2*k;b++)
//				System.out.print(b==2*k?"\n":(Math.abs(b-k)==Math.abs(k-1-Math.abs(a-k))?'*':' '));

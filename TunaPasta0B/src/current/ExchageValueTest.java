package current;

public class ExchageValueTest {
	public static void main(String[] args) {

		//交换a,b值的方法
		
//		1,先说一下我们经常用的那个方法，即借助一个中间变量。
//		这种算法易于理解，特别适合帮助初学者了解计算机程序的特点，是赋值语句的经典应用。
//		所以咱们至少应该掌握这种算法。
//		代码如下：
		//int a, b, temp;
		//a = 10;
		//b = 5;
		//temp = a;
		//a = b;
		//b = temp;
		//
		// System.out.println(a);
		// System.out.println(b);

//		2,下面就讲一下怎么不用中间变量来实现两个值得交换。算术运算
//		这种方法表面看起来简单，但是却不容易想到，现在说一说它的原理：
//		把a、b看做是数轴上的两点，围绕两点间的距离来进行计算。具体过程如下：
//		a=b-a;求出ab两点的距离，并将结果保存在a中；b=b-a;求出a到原点的距离，
//		并将结果保存在b中；a=b+a;求出b到原点的距离，并将结果保存在a中。这样就交换了ab两个数的值。
//		简单来说就是通过普通的+和-运算来实现，代码如下：
		
		// int a, b;
		// a = 10;
		// b = 2;
		// a = b - a;// a=-8,b=2
		// b = b - a;// a=-8,b=10
		// a = b + a;// a=2,b=10 交换成功
		//
		// System.out.println(a);
		// System.out.println(b);

//		3,位运算,通过异或运算来实现两个变量值的交换，这应该算是最神奇的算法了，
//		异或的意思即是相同为0，不同为1，通过异或运算能够是数据中的某些位翻转，
//		其中一个数与任意一个给定的值连续异或两次，值不变，这就是位运算的原理。
//		现在揭晓一下代码吧。(异或:相同结果为0,不同结果为1)
		
//		int a, b;
//		a = 20;
//		b = 2;
//		a = a ^ b;// a=10100,b=0010,两者相异或的结果是10110,并保存在a中。
//		b = a ^ b;// a=10110,b=0010,两者相异或的结果是10100，并保存在b中，换成二进制是20
//		a = a ^ b;// a=10110,b=10100,两者相异或的结果是00010，保存在a中，换成十进制是2
//
//		System.out.println(a);
//		System.out.println(b);
		
	}

}

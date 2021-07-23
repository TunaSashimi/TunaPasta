package history;

public class AncientPoetryTest {
    public static void main(String[] args) {
//		i-=5是立即生效的,无法延后!而不是i--
        for (char i = 20, a[] = "白日依山尽黄河入海流欲穷千里目更上一层楼".toCharArray(); i != 4; System.err.print((Math.abs(i - 7) <= 2) ? a[i -= 5] + "\n" : i > 5 ? a[i -= 5] : a[i += 16]))
            ;
    }
}

//		二循环做法:
//			System.out.println("--------");
//		for (int i = 15; i < 20; i++) {
//			for (int j = i; j >=0; j-=5) 
//				System.out.print(j+" ");
//			System.out.println();
//		}
//
//

//		一循环三目做法:
//		System.out.println("--------");
//		for (int i = 95; i >=4;) {
//			System.out.print(((i%20)<5)?a[(i-=4)%20]+"\n":a[(i-=5)%20]);
//		}
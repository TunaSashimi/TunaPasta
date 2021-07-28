//输出101到200之内的质数（素数）
package history;

public class PrimeNumberTest {
    public static void main(String[] args) {
//String args[]或者String[] args,表示给主方法传一个字符串数组. 
//而args是一个字符串数组的变量名，不是关键字，                                                
//是arguments的缩写，只是一个默认名，一般都习惯性照写.
//argument 【电脑】引数,参数
        int j;
        for (int i = 101; i < 200; i += 2) {
            boolean isPrime = true;
            for (j = 2; j <= Math.sqrt(i); j++) {
                if (i % j == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                System.out.print(i + "  ");
            }
        }
        System.out.println("\n");
//另一种用break和continue语句来做的方法
        int a, b;
        boolean isPrime;
        for (a = 101; a < 200; a += 2) {
            isPrime = true;
            for (b = 2; b <= Math.sqrt(a); b++) {
                if (a % b == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (!isPrime) {
                continue;
            }
            System.out.print(a + "  ");
        }
    }
}

package history;

public class VerificationCodeTest {
//Ean-13码规则：第十三位数字是前十二位数字经过计算得到的校验符。
//例如：690123456789计算其校验符的过程为：
//前12位的奇数位的和：6 + 0 + 2 + 4 + 6 + 8 = 26 　
//前12位的偶数位和：9 + 1 + 3 + 5 + 7 + 9 = 34 　
//将奇数和与偶数和的三倍相加：26 + 34 * 3 = 128 　　
//取结果的个位数：128的个位数为8 用10减去这个个位数：10 - 8 = 2 　　
//所以校验码为2 （注：如果取结果的个位数为0，那么校验码不是为10
//(10 - 0 = 10)，而是0。）
//实现方法ean13()计算验证码，输入12位条码，返回带验证码的条码。

    /**
     * 例如 输入: 692223361219 输出: 6922233612192
     */
    public static void main(String[] args) {
        char[] c = "692223361219".toCharArray();
        int sum = 0;//从右往左数,第一位6%2=0舍去~
        for (int i = 0; i < c.length; i++)
            sum += c[i] - 48 + i % 2 * 2 * (c[i] - 48);
        System.out.println("692223361219" + (10 - sum % 10) % 10);
    }
}

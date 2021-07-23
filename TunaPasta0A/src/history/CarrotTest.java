package history;

/*
 * 一个商人骑一头驴要穿越1000公里长的沙漠，去卖3000根胡萝卜。
 * 已知驴一次性可驮1000根胡萝卜，但每走1公里又要吃掉1根胡萝卜。
 * 问：商人最多可卖出多少胡萝卜？*
 */
//目前局限于算法问题，只能计算总数是1000倍数的萝卜

public class CarrotTest {
    public static void main(String[] args) {
        double n = 3000;        //总萝卜数
        double s = 0;
        //每次消耗1000根萝卜，剩下的且可带走的最多，
        for (; n > 1000; ) {                //萝卜数小于等于1000根结束循环
            double i = n / 1000;            //需要搬运的次数
            double j = 2 * i - 1;           //每次搬运中的总往返数
            s += 1000 / j;                  //每次搬完后前进的距离
            n -= 1000;                      //剩余萝卜数正好抵消了总路程数！
        }
        System.out.println(Math.ceil(n - (1000 - s)));
    }
}

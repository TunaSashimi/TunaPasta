package history;

public class ConvertTest {
    public static void main(String[] args) {
        //输入整数，默认为int的数据类型，输入小数，默认为double的数据类型
        //系统转换原则：容量小的数据类型，在转换的时候自动转换为容量大的类型，排序为：
        //byte，short，char=》int=》long=》float=》double
        //byte，short，char之间不会互相转换，而是转成int类型之后开始进行运算
        //容量大的数据类型转换成容量小的数据类型时，需要加上强制转换符，但可能造成精度降低或溢出！
        int i1 = 123;
        int i2 = 456;
        double d1 = (i1 + i2) * 1.2;        //系统将转换为double型运算
        float f1 = (float) ((i1 + i2) * 1.2);    //系统将转换为double型运算, 需要加强制转换符
        byte b1 = 67;                        //特殊情况，只要不溢出，可以把一个int型的数据赋值给byte，short，char
        byte b2 = 89;                        //byte正数最多可以表示到127
        byte b3 = (byte) (b1 + b2);            //系统将转换为int型运算，需强制转换符
        //int占4字节，byte占1字节，强制转换的时候去掉了前面的三个字节，所以能得出结果！
        System.out.println(b3);
        double d2 = 1e200;
        float f2 = (float) d2;
        //浮点数中的小数点在字节中有专门的存放形式，所以有时候无法使用去掉字节的方式来强制转转，会产生溢出！
        System.out.println(f2);
        float f3 = 1.3f;                    //不加f相当于把double转换成float，而且直接转换不过去
        long l1 = 123;
        long l2 = 100000000000L;            //必须加L(写大写L不易混淆),因为系统直接把整数当做int处理，但是int存放不开
        float f = i1 + i2 + f3;                //系统将转换为float型运算
        long i = (long) f;                    //强制转换舍弃小数部分，不是四舍五入
        System.out.println(d1 + " " + f1 + " " + l1 + " " + l2 + " " + i);
    }
}

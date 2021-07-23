package iostream;

//提供了可以存取java原始数据的方法
public class DataStreamTest {
    public static void main(String[] args) throws Exception {
        //在内存里分配了一个字节数组,把数据流加载在内存中的字节数组中
        java.io.ByteArrayOutputStream bos = new java.io.ByteArrayOutputStream();
        java.io.DataOutputStream dos = new java.io.DataOutputStream(bos);
        dos.writeDouble(Math.random());
        dos.writeBoolean(false);

        java.io.ByteArrayInputStream bas = new java.io.ByteArrayInputStream(bos.toByteArray());
        System.out.println(bas.available());                //一共有几个字节~
        java.io.DataInputStream dis = new java.io.DataInputStream(bas);
        System.out.println(dis.readDouble());            //先写的要先读出来!!
        System.out.println(dis.readBoolean());
    }
}

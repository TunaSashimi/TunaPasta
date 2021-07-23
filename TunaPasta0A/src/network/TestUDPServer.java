package network;

public class TestUDPServer {
    public static void main(String[] args) throws Exception {
        byte[] b = new byte[1024];
        java.net.DatagramPacket dp = new java.net.DatagramPacket(b, b.length);
        java.net.DatagramSocket ds = new java.net.DatagramSocket(5678);//是UDP的端口
        while (true) {
            ds.receive(dp);//把接受到的包放入b中
            java.io.ByteArrayInputStream bas = new java.io.ByteArrayInputStream(b);
            java.io.DataInputStream dis = new java.io.DataInputStream(bas);//设置读取源的地址
            System.out.println(dis.readLong());        //客户端先发的数据要先读才能准确~
            System.out.println(dis.readDouble());
            System.out.println(dis.readDouble());
            System.out.println(dis.readBoolean());
        }
    }
}

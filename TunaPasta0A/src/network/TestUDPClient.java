package network;

public class TestUDPClient {
    public static void main(String[] args) throws Exception {
        java.io.ByteArrayOutputStream bos = new java.io.ByteArrayOutputStream();
        java.io.DataOutputStream dos = new java.io.DataOutputStream(bos);
        dos.writeLong(1000);//将一个 long 值以 8-byte 值形式写入bos基础输出流中
        dos.writeDouble(3.2);
        dos.writeDouble(Math.random());
        dos.writeBoolean(false);

        byte[] b = bos.toByteArray();
        java.net.DatagramPacket dp = new java.net.DatagramPacket(b, b.length, new java.net.InetSocketAddress("192.168.0.149", 5678));
        java.net.DatagramSocket ds = new java.net.DatagramSocket(9999);//占据9999端口向5678端口发数据
        ds.send(dp);
        ds.close();
    }
}
